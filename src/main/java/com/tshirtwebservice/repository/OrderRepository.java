package com.tshirtwebservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.tshirtwebservice.entity.OrderEntity;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

}