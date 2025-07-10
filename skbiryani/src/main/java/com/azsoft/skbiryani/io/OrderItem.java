package com.azsoft.skbiryani.io;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	
	private Long foodId;
	private Integer quantity;
	private double price;
	private String category;
	private String imageUrl;
	private String description;
	private String name;

}
