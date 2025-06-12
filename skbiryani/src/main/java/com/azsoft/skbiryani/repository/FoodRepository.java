package com.azsoft.skbiryani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azsoft.skbiryani.entity.FoodEntity;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long>{
	FoodEntity findByName(String name);
}
