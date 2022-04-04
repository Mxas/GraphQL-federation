package eu.mktraining.cataolgs.appproducts.grapqql.query;

import eu.mktraining.cataolgs.appproducts.model.Catalog;
import eu.mktraining.cataolgs.appproducts.model.Product;
import eu.mktraining.cataolgs.appproducts.service.ProductService;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsReferenceResolver implements GraphQLResolver<Catalog> {

	final ProductService service;

	public List<Product> products(Catalog product) {
		return service.findByCatalogId(product.getId());
	}
}
