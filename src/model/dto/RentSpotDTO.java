package model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentSpotDTO {
	private String rentSpotName;
	private int numBike;

}
