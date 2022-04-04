package eu.mktraining.cataolgs.appproducts.grapqql.query;

import eu.mktraining.cataolgs.appproducts.model.Product;
import eu.mktraining.cataolgs.appproducts.service.ProductService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductQueryResolver implements GraphQLQueryResolver {

	final ProductService productService;

	public Product getProduct(int id){
		return productService.getById(id);
	}
}
