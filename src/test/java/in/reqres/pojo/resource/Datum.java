package in.reqres.pojo.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Datum {

	private Integer id;

	private String name;

	private Integer year;

	private String color;

	@JsonProperty("pantone_value")
	private String pantoneValue;
}
