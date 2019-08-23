package model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentInfoDTO {
	private int rentId;
	private String rentTime;
	private int bikeId;
	private int id;
	private int rentSpotName;
	
}
