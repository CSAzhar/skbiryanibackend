package com.azsoft.skbiryani.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.azsoft.skbiryani.io.OrderRequest;
import com.azsoft.skbiryani.io.OrderResponse;
import com.azsoft.skbiryani.service.IOrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("skb/order/")@AllArgsConstructor
@CrossOrigin
public class OrderController {
	
	private final IOrderService orderService;

	@PostMapping("create")
	public ResponseEntity<OrderResponse> createOrderWithPayment(@RequestBody OrderRequest orderRequest){
		OrderResponse orderResponse = orderService.createMethodWithPayment(orderRequest);
		return ResponseEntity.status(orderResponse.getStatusCode()).body(orderResponse);
	}
	
	@PostMapping("verify")
	public void verifyPayment(@RequestBody Map<String, String> paymentData) {
		orderService.verifyPayment(paymentData, "Paid");
	}
	
	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrders(){
		return ResponseEntity.status(200).body(orderService.getUserOrders());
	}
	
	@DeleteMapping("{orderId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteOrderById(@PathVariable Long orderId) {
		orderService.deleteOrderById(orderId);
	}
	
	@GetMapping("all-admin")
	public ResponseEntity<List<OrderResponse>> getOrdersOfAllUsers(){
		return ResponseEntity.status(200).body(orderService.getOrdersOfAllUsers());
	}
	
	@PatchMapping("{orderId}")
	public void updateOrderStatus(@PathVariable Long orderId, @RequestParam String orderStatus) {
		orderService.updateOrderStatus(orderId, orderStatus);
	}

}











