package com.azsoft.skbiryani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azsoft.skbiryani.io.CartRequest;
import com.azsoft.skbiryani.io.security.CartResponse;
import com.azsoft.skbiryani.service.CartServiceImpl;

@RestController
@RequestMapping("/skb/cart/")
@CrossOrigin
public class CartController {
	
	@Autowired
	private CartServiceImpl cartService;

	@PostMapping("add")
	public ResponseEntity<CartResponse> addItemsToCart(@RequestBody CartRequest cartRequest){
		CartResponse cartResponse = cartService.addToCart(cartRequest);
		return ResponseEntity.status(cartResponse.getStatusCode()).body(cartResponse);
	}
	
	@PostMapping("remove")
	public ResponseEntity<CartResponse> removeItemsToCart(@RequestBody CartRequest cartRequest){
		CartResponse cartResponse = cartService.removeToCart(cartRequest);
		return ResponseEntity.status(cartResponse.getStatusCode()).body(cartResponse);
	}
	
	@GetMapping
	public ResponseEntity<CartResponse> getCart(){
		CartResponse response = cartService.getCart();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<CartResponse> clearcart(){
		CartResponse response = cartService.clearCart();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
}













