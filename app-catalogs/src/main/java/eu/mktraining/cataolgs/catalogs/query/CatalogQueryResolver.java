package eu.mktraining.cataolgs.catalogs.query;

import eu.mktraining.cataolgs.catalogs.model.Catalog;
import eu.mktraining.cataolgs.catalogs.service.CatalogService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatalogQueryResolver implements GraphQLQueryResolver {

	final CatalogService productService;

	public Catalog getCatalog(int id) {
		return productService.getById(id);
	}

	public List<Catalog> findAll(){
		return productService.findAll();
	}
}
