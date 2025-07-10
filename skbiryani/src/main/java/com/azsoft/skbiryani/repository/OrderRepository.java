package com.azsoft.skbiryani.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azsoft.skbiryani.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	List<OrderEntity>findByUserId(Long userId);
	Optional<OrderEntity> findByRazorPayOrderId(String razorPayOrderId);
}
