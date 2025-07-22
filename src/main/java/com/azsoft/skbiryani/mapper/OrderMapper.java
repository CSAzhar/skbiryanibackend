package com.azsoft.skbiryani.mapper;


import org.springframework.stereotype.Component;

import com.azsoft.skbiryani.entity.OrderEntity;
import com.azsoft.skbiryani.io.OrderRequest;
import com.azsoft.skbiryani.io.OrderResponse;

@Component
public class OrderMapper {
	
	public OrderEntity orderRequestToOrderEntity(OrderRequest orderRequest) {
		return OrderEntity.builder()
				.userAddress(orderRequest.getUserAddress())
				.amount(orderRequest.getAmount())
				.orderedItems(orderRequest.getOrderedItems())
				.email(orderRequest.getEmail())
				.phoneNumber(orderRequest.getPhoneNumber())
				.orderStatus(orderRequest.getOrderStatus())
				.build();
	}
	
	public OrderResponse orderEntityToOrderResponse(OrderEntity orderEntity) {
		return OrderResponse.builder()
				.orderId(orderEntity.getOrderId())
				.userId(orderEntity.getUserId())
				.userAddress(orderEntity.getUserAddress())
				.phoneNumber(orderEntity.getPhoneNumber())
				.email(orderEntity.getEmail())
				.amount(orderEntity.getAmount())
				.paymentStatus(orderEntity.getPaymentStatus())
				.razorPayOrderId(orderEntity.getRazorPayOrderId())
				.orderStatus(orderEntity.getOrderStatus())
				.orderedItems(orderEntity.getOrderedItems())
				.build();
	}

}
