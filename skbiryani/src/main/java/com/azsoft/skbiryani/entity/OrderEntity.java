package com.azsoft.skbiryani.entity;

import java.util.List;
import com.azsoft.skbiryani.io.OrderItem;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	private Long userId;
	private String userAddress;
	private String phoneNumber;
	private String email;
	
	@ElementCollection
	private List<OrderItem> orderedItems;
	
	private Double amount;
	private String paymentStatus;
	private String razorPayOrderId;
	private String razorPaySignature;
	private String razorPayPaymentId;
	private String orderStatus;
	
}




