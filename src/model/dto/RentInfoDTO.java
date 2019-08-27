package model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentInfoDTO {
	private int rentId;
	private String schTime;
	private String returnTime;
	private String rentSpotName;
	
}
