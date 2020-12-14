package com.tshirtwebservice.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.addressing.server.annotation.Action;

import com.tshirtwebservice.entity.InventoryEntity;
import com.tshirtwebservice.entity.OrderEntity;
import com.tshirtwebservice.repository.InventoryRepository;
import com.tshirtwebservice.repository.OrderRepository;
import com.tshirtwebservice.ws.InventoryItem;
import com.tshirtwebservice.ws.ListInventoryRequest;
import com.tshirtwebservice.ws.ListInventoryResponse;
import com.tshirtwebservice.ws.OrderTshirtRequest;
import com.tshirtwebservice.ws.OrderTshirtResponse;
import com.tshirtwebservice.ws.TrackOrderRequest;
import com.tshirtwebservice.ws.TrackOrderResponse;

@Endpoint
public class TshirtEndpoint {
	private static final String NAMESPACE_URI = "http://ws.tshirtwebservice.com";

	private OrderRepository orderRepository;
	private InventoryRepository inventoryRepository;
	
	@Autowired
	public TshirtEndpoint(OrderRepository orderRepository, InventoryRepository inventoryRepository) {
		this.orderRepository = orderRepository;
		this.inventoryRepository = inventoryRepository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "OrderTshirtRequest")
	@Action("http://ws.tshirtwebservice.com/order-tshirt")
	@ResponsePayload
	public OrderTshirtResponse orderTshirt(@RequestPayload OrderTshirtRequest request) {
		OrderTshirtResponse response = new OrderTshirtResponse();
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setEmail(request.getEmail());
		orderEntity.setName(request.getName());
		orderEntity.setAddress1(request.getAddress1());
		orderEntity.setAddress2(request.getAddress2());
		orderEntity.setCity(request.getCity());
		orderEntity.setStateOrProvince(request.getStateOrProvince());
		orderEntity.setCountry(request.getCountry());
		orderEntity.setPostalCode(request.getPostalCode());
		orderEntity.setSize(request.getSize());
		OrderEntity createdEntity = orderRepository.save(orderEntity);
		response.setOrderId(String.valueOf(createdEntity.getOrderId()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "TrackOrderRequest")
	@Action("http://ws.tshirtwebservice.com/track-order")
	@ResponsePayload
	public TrackOrderResponse trackOrder(@RequestPayload TrackOrderRequest request) {
		TrackOrderResponse response = new TrackOrderResponse();
		Optional<OrderEntity> optionalOrder = orderRepository.findById(request.getOrderId());
		if(optionalOrder.isPresent()) {
		    response.setOrderId(String.valueOf(optionalOrder.get().getOrderId()));
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListInventoryRequest")
	@Action("http://ws.tshirtwebservice.com/list-inventory")
	@ResponsePayload
	public ListInventoryResponse listInventory(@RequestPayload ListInventoryRequest request) {
		ListInventoryResponse response = new ListInventoryResponse();
		for (InventoryEntity entity : inventoryRepository.findAll()) {
			InventoryItem item = new InventoryItem();
			item.setCount(entity.getCount());
			item.setDescription(entity.getDescription());
			item.setProductCode(entity.getProductCode());
			item.setSize(entity.getSize());
			response.getInventory().add(item);
		}
		return response;
	}
}