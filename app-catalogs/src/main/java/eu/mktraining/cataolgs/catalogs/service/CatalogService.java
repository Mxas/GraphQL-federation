package eu.mktraining.cataolgs.catalogs.service;

import eu.mktraining.cataolgs.catalogs.model.Catalog;
import eu.mktraining.cataolgs.catalogs.repository.CatalogRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogService {

	final CatalogRepository productRepository;

	public Catalog getById(int id) {
		return productRepository.get(id).orElseThrow(() -> new RuntimeException("Product not found by " + id));
	}

	public List<Catalog> findAll() {
		return productRepository.findAll();
	}
}
