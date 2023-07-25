package in.reqres.pojo.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResourceListReq {

	@JsonProperty("page")
	private Integer page;

	@JsonProperty("per_page")
	private Integer perPage;

	@JsonProperty("total")
	private Integer total;

	@JsonProperty("total_pages")
	private Integer totalPages;

	private List<Datum> data;

	private Support support;
}
