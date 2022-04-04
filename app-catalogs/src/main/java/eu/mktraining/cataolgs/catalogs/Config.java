package eu.mktraining.cataolgs.catalogs;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//https://github.com/apollographql/federation-jvm#graphql-java-schema-transformation

@Configuration
public class Config {

	@Bean
	public GraphQLSchema customSchema(SchemaParser schemaParser) {
		GraphQLSchema federatedSchema = Federation.transform(schemaParser.makeExecutableSchema())
				.fetchEntities(env -> env.<List<Map<String, Object>>>getArgument(_Entity.argumentName)
						.stream()
						.map(reference -> {
							return null;
						})
						.collect(Collectors.toList()))
				.resolveEntityType(env -> {
					return null;
				})
				.build();

		return federatedSchema;
	}

}
