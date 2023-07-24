package in.reqres.pojo.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserReq {
	public String name, job;
}
