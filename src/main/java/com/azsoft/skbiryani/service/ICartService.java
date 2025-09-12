package com.azsoft.skbiryani.service;

import com.azsoft.skbiryani.dto.CartRequest;
import com.azsoft.skbiryani.dto.CartResponse;

public interface ICartService {
	
	CartResponse addToCart(CartRequest cartRequest);
	CartResponse removeToCart(CartRequest cartRequest);
	CartResponse getCart();
	CartResponse clearCart();
}
