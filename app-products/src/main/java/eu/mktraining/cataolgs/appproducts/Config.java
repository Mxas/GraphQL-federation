package eu.mktraining.cataolgs.appproducts;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava.SchemaTransformer;
import com.apollographql.federation.graphqljava._Entity;
import eu.mktraining.cataolgs.appproducts.model.Catalog;
import graphql.Scalars;
import graphql.kickstart.tools.SchemaObjects;
import graphql.kickstart.tools.SchemaParser;
import graphql.kickstart.tools.SchemaParserDictionary;
import graphql.kickstart.tools.SchemaParserOptions;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://github.com/setchy/spring-boot-federation-example/tree/master/reviews/src/main/java/com/example/demo/federation

@Configuration
public class Config {

	@Bean
	public SchemaParserDictionary schemaParserDictionary() {
		return new SchemaParserDictionary().add("Catalog", Catalog.class);
	}

	@Bean
	public BeanPostProcessor schemaParserOptionsBuilderPostProcessor() {
		return new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName)
					throws BeansException {
				return bean instanceof SchemaParserOptions.Builder
						? ((SchemaParserOptions.Builder) bean).includeUnusedTypes(true) : bean;
			}
		};
	}


	@Bean
	public SchemaTransformer schemaTransformer(SchemaParser schemaParser) {
		final SchemaObjects schemaObjects = schemaParser.parseSchemaObjects();
		final boolean queryTypeIsEmpty = schemaObjects.getQuery()
				.getFieldDefinitions()
				.isEmpty();
		final GraphQLObjectType newQuery = queryTypeIsEmpty ? schemaObjects.getQuery()
				.transform(graphQLObjectTypeBuilder -> graphQLObjectTypeBuilder.field(
						GraphQLFieldDefinition.newFieldDefinition()
								.name("_dummy")
								.type(Scalars.GraphQLString)
								.build())) : schemaObjects.getQuery();
		final GraphQLSchema graphQLSchema = GraphQLSchema.newSchema()
				.query(newQuery)
				.mutation(schemaObjects.getMutation())
				.subscription(schemaObjects.getSubscription())
				.additionalTypes(schemaObjects.getDictionary())
				.codeRegistry(schemaObjects.getCodeRegistryBuilder()
						.build())
				.build();
		return Federation.transform(graphQLSchema, queryTypeIsEmpty);
	}


	@Bean
	public GraphQLSchema federatedGraphQLSchema(SchemaTransformer schemaTransformer) throws IOException {

		String federationTypeName = "Catalog";

		return schemaTransformer.fetchEntities(env -> env.<List<Map<String, Object>>>getArgument(_Entity.argumentName)
						.stream()
						.map(reference -> {
							if (federationTypeName.equals(reference.get("__typename"))) {
								return resolveReference(reference);
							}
							return null;
						})
						.collect(Collectors.toList()))
				.resolveEntityType(env -> {
					final Object src = env.getObject();
					if (src instanceof Catalog) {
						return env.getSchema()
								.getObjectType(federationTypeName);
					}
					return null;
				})
				.build();
	}

	public static Catalog resolveReference(Map<String, Object> reference) {
		String federatedKeyName = "id";

		if (!(reference.get(federatedKeyName) instanceof Integer)) {
			return null;
		}

		final Integer id = (Integer) reference.get(federatedKeyName);

		Catalog show = new Catalog();
		show.setId(id);
		return show;
	}
}
