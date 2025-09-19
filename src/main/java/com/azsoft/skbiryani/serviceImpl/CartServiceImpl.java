package com.azsoft.skbiryani.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.azsoft.skbiryani.dto.CartRequest;
import com.azsoft.skbiryani.dto.CartResponse;
import com.azsoft.skbiryani.entity.CartEntity;
import com.azsoft.skbiryani.exception.CartNotAvailableException;
import com.azsoft.skbiryani.repository.CartRepository;
import com.azsoft.skbiryani.service.ICartService;

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
			
			Long loggedInUserId = userService.findByUserId();
		
			Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
		
			CartEntity cartEntity =  cartOptional.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));
	
			Map<Long, Integer> cartItem = cartEntity.getItems();
		
			cartItem.put(foodId, cartItem.getOrDefault(foodId, 0)+1);

			cartEntity.setItems(cartItem);
			CartEntity savedCart  = cartRepository.save(cartEntity);
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

	@Override
	public CartResponse getCart() {
		CartResponse cartResponse = new CartResponse();
		try {
			Long loggedInUSerId = userService.findByUserId();
			CartEntity cartEntity = cartRepository.findByUserId(loggedInUSerId).orElseThrow(()-> new CartNotAvailableException("No Cart is Available"));
			
			cartResponse.setId(cartEntity.getCartId());
			cartResponse.setUserId(loggedInUSerId);
			cartResponse.setItems(cartEntity.getItems());
			
			cartResponse.setStatusCode(200);
			cartResponse.setMessage("successful");
			return cartResponse;
		}catch (CartNotAvailableException e) {
			cartResponse.setStatusCode(400);
			cartResponse.setMessage("Cart Not created:"+e.getMessage());
			return cartResponse;
		}
		catch (Exception e) {
			cartResponse.setStatusCode(500);
			cartResponse.setMessage("failed to load cart:"+e.getMessage());
			return cartResponse;
		}
	}

	@Override
	public CartResponse clearCart() {
		CartResponse cartResponse = new CartResponse();
		try {
			Long loggedInUserId = userService.findByUserId();
			cartRepository.deleteByUserId(loggedInUserId);
			
			cartResponse.setStatusCode(200);
			cartResponse.setMessage("successful");
			
			return cartResponse;
		}catch (Exception e) {
			cartResponse.setStatusCode(400);
			cartResponse.setMessage("Error occured while clearing cart:"+e.getMessage());
			
			return cartResponse;
		}
	}

}












