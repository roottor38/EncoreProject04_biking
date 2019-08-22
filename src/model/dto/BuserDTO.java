package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuserDTO {
	private String id;
	private String pw;
	private String name;
	private String phone;

}
