package model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentInfoDTO {
	private int rentId;
	private String rentTime;
	private int bikeId;
	private String id;
	private String rentSpotName;
	
}
