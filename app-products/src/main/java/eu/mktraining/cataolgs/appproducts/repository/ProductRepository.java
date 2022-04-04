package eu.mktraining.cataolgs.appproducts.repository;

import eu.mktraining.cataolgs.appproducts.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

	final Map<Integer, Product> store = new HashMap<>();

	@PostConstruct
	void init() {
		store.put(1, Product.builder().id(1).name("First").model("Main").catalogId(1).build());
		store.put(2, Product.builder().id(2).name("Second").model("Main").catalogId(2).build());
		store.put(3, Product.builder().id(3).name("Third").model("Main").catalogId(1).build());
	}

	public Optional<Product> get(int id) {
		return Optional.ofNullable(store.get(id));
	}

	public List<Product> findByCatalogId(Integer catalogId) {
		return store.values().stream().filter(c -> Objects.equals(c.getCatalogId(), catalogId)).collect(Collectors.toList());
	}
}
