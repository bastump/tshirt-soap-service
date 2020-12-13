package com.tshirtwebservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.addressing.server.annotation.Action;

import com.tshirtwebservice.entity.InventoryEntity;
import com.tshirtwebservice.entity.OrderEntity;
import com.tshirtwebservice.repository.OrderRepository;
import com.tshirtwebservice.ws.ListInventoryRequest;
import com.tshirtwebservice.ws.OrderTshirtRequest;
import com.tshirtwebservice.ws.TrackOrderRequest;
import com.tshirtwebservice.ws.TrackOrderResponse;

@Endpoint
public class TshirtEndpoint {
	private static final String NAMESPACE_URI = "http://ws.tshirtwebservice.com";

	private OrderRepository orderRepository;
	
	@Autowired
	public TshirtEndpoint(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "order-tshirt")
	@Action("http://ws.tshirtwebservice.com/order-tshirt")
	@ResponsePayload
	public @ResponseBody String orderTshirt(@RequestPayload OrderTshirtRequest request) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setEmail(request.getEmail());
		orderEntity.setName(request.getName());
		orderRepository.save(orderEntity);
		return "Saved";
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "track-order")
	@Action("http://ws.tshirtwebservice.com/track-order")
	@ResponsePayload
	public TrackOrderResponse trackOrder(@RequestPayload TrackOrderRequest request) {
		TrackOrderResponse response = new TrackOrderResponse();
		response.setOrderId(orderRepository.findById(Integer.valueOf(request.getOrderId())).toString());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "list-inventory")
	@Action("http://ws.tshirtwebservice.com/list-inventory")
	@ResponsePayload
	public @ResponseBody String listInventory(@RequestPayload ListInventoryRequest request) {
		return "all";
	}
}