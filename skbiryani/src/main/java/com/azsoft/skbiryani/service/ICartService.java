package com.azsoft.skbiryani.service;

import com.azsoft.skbiryani.io.CartRequest;
import com.azsoft.skbiryani.io.security.CartResponse;

public interface ICartService {
	
	CartResponse addToCart(CartRequest cartRequest);
	CartResponse removeToCart(CartRequest cartRequest);
	CartResponse getCart();
	CartResponse clearCart();
}
