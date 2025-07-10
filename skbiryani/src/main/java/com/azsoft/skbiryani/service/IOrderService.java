package com.azsoft.skbiryani.service;

import java.util.List;
import java.util.Map;
import com.azsoft.skbiryani.io.OrderRequest;
import com.azsoft.skbiryani.io.OrderResponse;
import com.razorpay.RazorpayException;

public interface IOrderService {
	OrderResponse createMethodWithPayment(OrderRequest request);
	void verifyPayment(Map<String, String> paymentData, String status);
	List<OrderResponse> getUserOrders();
	void deleteOrderById(Long orderId);
	
	List<OrderResponse> getOrdersOfAllUsers();
	
	void updateOrderStatus(Long orderId, String orderStatus);

}
