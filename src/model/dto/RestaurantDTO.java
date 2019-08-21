package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
	private double restaurant_id;
	private String title;
	private String sitelink;
	private String contact;
	private String address;
	private String district;
	private String category;
}
