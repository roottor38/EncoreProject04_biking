package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BUserDTO {

	private String id;
	private String pw;
	private String name;
	private String phone;
	private String rentStatus;
	
	public BUserDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public BUserDTO(String id, String pw, String name, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
	}

}
