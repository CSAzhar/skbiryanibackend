package com.azsoft.skbiryani.entity;

import java.util.HashMap;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    
	public CartEntity(Long loggedInUserId, Map<Long, Integer> items) {
		this.userId = loggedInUserId;
		this.items = items;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    
	
    private Long userId;
    
    @ElementCollection
    @CollectionTable(
        name = "cart_items",
        joinColumns = @JoinColumn(name = "cart_id")
    )
    @MapKeyColumn(name = "item_id")
    @Column(name = "quantity")
    private Map<Long, Integer> items = new HashMap<>();
    
}
