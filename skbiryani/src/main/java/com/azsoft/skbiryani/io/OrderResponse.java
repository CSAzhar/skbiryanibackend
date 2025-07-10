package com.azsoft.skbiryani.io;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	
	private Long orderId;
	private Long userId;
	private String userAddress;
	private String phoneNumber;
	private String email;
	private Double amount;
	private String paymentStatus;
	private String razorPayOrderId;
	private String orderStatus;
	List<OrderItem> orderedItems;
	
	private Integer statusCode;
	private String message;

}
