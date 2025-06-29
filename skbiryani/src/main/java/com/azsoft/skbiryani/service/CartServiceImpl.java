package com.azsoft.skbiryani.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.entity.CartEntity;
import com.azsoft.skbiryani.io.CartRequest;
import com.azsoft.skbiryani.io.security.CartResponse;
import com.azsoft.skbiryani.repository.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService{
	
	private final CartRepository cartRepository;
	private final UserServiceImpl userService;

	@Override
	public CartResponse addToCart(CartRequest cartRequest) {
		
		CartResponse cartResponse = new CartResponse();
		Long foodId = cartRequest.getFoodId();
		
		try {
			System.out.println("========1======");
			Long loggedInUserId = userService.findByUserId();
			System.out.println("========2======");
			Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
			System.out.println("========3======");
			CartEntity cartEntity =  cartOptional.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));
			System.out.println("========4======");
			Map<Long, Integer> cartItem = cartEntity.getItems();
			System.out.println("========5======");
			cartItem.put(foodId, cartItem.getOrDefault(foodId, 0)+1);
			System.out.println("========6======");
			cartEntity.setItems(cartItem);
			System.out.println("========7======");
			CartEntity savedCart  = cartRepository.save(cartEntity);
			System.out.println("========8======");
			cartResponse.setId(savedCart.getCartId());
			cartResponse.setUserId(savedCart.getUserId());
			cartResponse.setItems(savedCart.getItems());
			cartResponse.setStatusCode(200);
			cartResponse.setMessage("successful");
			
			return cartResponse;
			
		}catch (Exception e) {
			System.out.println("Exception occured while adding food to cart: "+e.getMessage());
			cartResponse.setStatusCode(400);
			cartResponse.setMessage("failed");
			return cartResponse;
		}
		
	}
	
	@Override
	public CartResponse removeToCart(CartRequest cartRequest) {
		
		CartResponse cartResponse = new CartResponse();
		Long foodId = cartRequest.getFoodId();
		
		try {
			
			Long loggedInUserId = userService.findByUserId();

			Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
			
			CartEntity cartEntity =  cartOptional.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));
	
			Map<Long, Integer> cartItem = cartEntity.getItems();
			
			if(cartItem.get(foodId)<1) {
				System.out.println("Add item to cart first");
				cartResponse.setStatusCode(400);
				cartResponse.setMessage("add item to cart first, cart alreay 0");
				return cartResponse;
			}else {
				cartItem.put(foodId, cartItem.get(foodId)-1);
				if(cartItem.get(foodId)==0) cartItem.remove(foodId);
				
				cartEntity.setItems(cartItem);
			
				CartEntity savedCart  = cartRepository.save(cartEntity);

				cartResponse.setId(savedCart.getCartId());
				cartResponse.setUserId(savedCart.getUserId());
				cartResponse.setItems(savedCart.getItems());
				cartResponse.setStatusCode(200);
				cartResponse.setMessage("successful");
				
				return cartResponse;
			}
			
			
		}catch (Exception e) {
			System.out.println("Exception occured while adding food to cart: "+e.getMessage());
			cartResponse.setStatusCode(400);
			cartResponse.setMessage("failed");
			return cartResponse;
		}
		
	}

}
