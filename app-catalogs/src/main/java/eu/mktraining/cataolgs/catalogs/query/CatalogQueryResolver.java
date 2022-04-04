package eu.mktraining.cataolgs.catalogs.query;

import eu.mktraining.cataolgs.catalogs.model.Catalog;
import eu.mktraining.cataolgs.catalogs.service.CatalogService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatalogQueryResolver implements GraphQLQueryResolver {

	final CatalogService productService;

	public Catalog getCatalog(int id) {
		return productService.getById(id);
	}
}
