package eu.mktraining.cataolgs.catalogs.repository;

import eu.mktraining.cataolgs.catalogs.model.Catalog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CatalogRepository {

	final Map<Integer, Catalog> store = new HashMap<>();

	@PostConstruct
	void init() {
		store.put(1, Catalog.builder().id(1).name("First").name("Main").build());
		store.put(2, Catalog.builder().id(2).name("Second").name("Main").build());
	}

	public Optional<Catalog> get(int id) {
		return Optional.ofNullable(store.get(id));
	}

	public List<Catalog> findAll() {
		return new ArrayList<>(store.values());
	}
}
