package com.tshirtwebservice.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

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

		//check if order exists for email
		Iterable<OrderEntity> emailOrders = orderRepository.findByEmail(request.getEmail());
		List<OrderEntity> emailOrdersList = new ArrayList<OrderEntity>();
	    emailOrders.forEach(emailOrdersList::add);
	    if (emailOrdersList.size() > 0) {
	        System.out.println("Order with email " + request.getEmail() + " already exists");
	        OrderEntity entity = emailOrdersList.get(0); //get first value (should only be one)
	        response.setOrderId(String.valueOf(entity.getOrderId()));
		    return response;
		}
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
		orderEntity.setStatus("CREATED");
		OrderEntity createdEntity = orderRepository.save(orderEntity);
		response.setOrderId(String.valueOf(createdEntity.getOrderId()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "TrackOrderRequest")
	@Action("http://ws.tshirtwebservice.com/track-order")
	@ResponsePayload
	public TrackOrderResponse trackOrder(@RequestPayload TrackOrderRequest request) {
		TrackOrderResponse response = new TrackOrderResponse();
		try {
		    Optional<OrderEntity> optionalOrder = orderRepository.findById(
				    Long.parseLong(request.getOrderId()));
		    if (optionalOrder.isPresent()) {
			    OrderEntity entity = optionalOrder.get();
			    response.setSize(entity.getSize());
		    	response.setOrderId(String.valueOf(entity.getOrderId()));
		    	response.setStatus(entity.getStatus());
		    }
		} catch (NumberFormatException e) {
			Iterable<OrderEntity> emailOrders = orderRepository.findByEmail(request.getEmail());
			List<OrderEntity> emailOrdersList = new ArrayList<OrderEntity>();
			emailOrders.forEach(emailOrdersList::add);
			if (emailOrdersList.size() > 0) {
			    OrderEntity entity = emailOrdersList.get(0); //get first value (should only be one)
			    response.setSize(entity.getSize());
			    response.setOrderId(String.valueOf(entity.getOrderId()));
			    response.setStatus(entity.getStatus());
			}
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListInventoryRequest")
	@Action("http://ws.tshirtwebservice.com/list-inventory")
	@ResponsePayload
	public ListInventoryResponse listInventory(ListInventoryRequest request) {
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