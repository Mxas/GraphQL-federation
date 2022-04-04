package eu.mktraining.cataolgs.appproducts.service;

import eu.mktraining.cataolgs.appproducts.model.Product;
import eu.mktraining.cataolgs.appproducts.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	final ProductRepository productRepository;

	public Product getById(Integer id) {
		return productRepository.get(id).orElseThrow(() -> new RuntimeException("Product not found by " + id));
	}

	public List<Product> findByCatalogId(Integer catalogId) {
		return productRepository.findByCatalogId(catalogId);
	}
}
