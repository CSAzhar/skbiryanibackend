package com.azsoft.skbiryani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
	
	@Id
	private Long userId;
	private String name;
	private String email;
	private String mobile;
	private String password;
	
	@Enumerated(EnumType.STRING)
    private Role role;

}
