package com.azsoft.skbiryani.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.azsoft.skbiryani.entity.OrderEntity;
import com.azsoft.skbiryani.io.OrderRequest;
import com.azsoft.skbiryani.io.OrderResponse;
import com.azsoft.skbiryani.mapper.OrderMapper;
import com.azsoft.skbiryani.repository.CartRepository;
import com.azsoft.skbiryani.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


@Service
public class OrderServiceImpl implements IOrderService{

	@Value("${razorpay_key}")
	private String RAZOYPAY_KEY;
	
	@Value("${razorpay_secret}")
	private String RAZORPAY_SECRET;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private IUserService userService;
	@Autowired
	private CartRepository cartRepository;
	
	
	@Override
	public OrderResponse createMethodWithPayment(OrderRequest orderRequest) {
		
		try {
			
			OrderEntity newOrder = orderMapper.orderRequestToOrderEntity(orderRequest);
			newOrder = orderRepository.save(newOrder);
			
			//create razorpayPaymentOrder
			RazorpayClient razorpayClient = new RazorpayClient(RAZOYPAY_KEY, RAZORPAY_SECRET);
			
			JSONObject razorPayOrderRequest = new JSONObject();
			razorPayOrderRequest.put("amount", newOrder.getAmount());
			razorPayOrderRequest.put("currency", "INR");
			razorPayOrderRequest.put("payment_capture", 1);
			
			Order razorPayOrder = razorpayClient.orders.create(razorPayOrderRequest);
			
			newOrder.setRazorPayOrderId(razorPayOrder.get("id"));
			newOrder.setPaymentStatus(razorPayOrder.get("status").equals("created")? "PENDING":"FAILED");
			
			Long loggedInUserId = userService.findByUserId();
			newOrder.setUserId(loggedInUserId);
			
			newOrder = orderRepository.save(newOrder);
			
			OrderResponse orderResponse = orderMapper.orderEntityToOrderResponse(newOrder);
			orderResponse.setMessage("successful");
			orderResponse.setStatusCode(200);
			return orderResponse;
			
		} catch (RazorpayException e) {
			OrderResponse orderResponse = new OrderResponse();
			orderResponse.setStatusCode(401);
			orderResponse.setMessage("failed :"+e.getMessage());
			return orderResponse;
		} catch (Exception e) {
			OrderResponse orderResponse = new OrderResponse();
			orderResponse.setStatusCode(401);
			orderResponse.setMessage("failed from server:"+e.getMessage());
			return orderResponse;
		}
		
		
	}


	@Override
	public void verifyPayment(Map<String, String> paymentData, String status) {
		try {
			String razorPayOrderId = paymentData.get("razorpay_order_id");
			OrderEntity existingOrder =  orderRepository.findByRazorPayOrderId(paymentData.get(razorPayOrderId))
											.orElseThrow(() -> new RuntimeException("Order Not found"));
			existingOrder.setPaymentStatus(status);
			existingOrder.setRazorPaySignature(paymentData.get("razorpay_signature"));
			existingOrder.setRazorPayPaymentId(paymentData.get("razorpay_payment_id"));
			
			orderRepository.save(existingOrder);
			
			if("paid".equalsIgnoreCase(status)) {
				cartRepository.deleteByUserId(existingOrder.getUserId());
			}
		} catch (Exception e) {
			System.out.println("payment failed by serever: "+e.getMessage());
		}
		
	}


	@Override
	public List<OrderResponse> getUserOrders() {
		Long loggedInUserId = userService.findByUserId();
		orderRepository.findByUserId(loggedInUserId);
		List<OrderEntity> listOfOrders = orderRepository.findByUserId(loggedInUserId);
		return listOfOrders.stream()
				.map(entity -> orderMapper.orderEntityToOrderResponse(entity))
				.collect(Collectors.toList());
	}


	@Override
	public void deleteOrderById(Long orderId) {
		orderRepository.deleteById(orderId);
	}


	@Override
	public List<OrderResponse> getOrdersOfAllUsers() {
		List<OrderEntity> allOrdersOfAllUsers = orderRepository.findAll();
		return allOrdersOfAllUsers.stream().map(orderMapper::orderEntityToOrderResponse).collect(Collectors.toList());
	}


	@Override
	public void updateOrderStatus(Long orderId, String orderStatus) {
		OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not found"));
		orderEntity.setOrderStatus(orderStatus);
		orderEntity = orderRepository.save(orderEntity);
//		OrderResponse orderResponse = orderMapper.orderEntityToOrderResponse(orderEntity);
//		orderResponse.setStatusCode(200);
//		orderResponse.setMessage("Status updated to :"+orderStatus);
//		return orderResponse;
		
	}
	

}







