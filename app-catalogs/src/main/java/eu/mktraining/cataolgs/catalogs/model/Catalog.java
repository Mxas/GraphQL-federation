package eu.mktraining.cataolgs.catalogs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {

	private Integer id;
	private String name;

}
