package com.azsoft.skbiryani.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.azsoft.skbiryani.entity.CartEntity;



@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{
	
	Optional<CartEntity> findByUserId(Long userId);
	
	@Transactional
	void deleteByUserId(Long userId);

}
