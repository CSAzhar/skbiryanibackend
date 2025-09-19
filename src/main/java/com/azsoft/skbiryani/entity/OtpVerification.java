package com.azsoft.skbiryani.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "otp_verification")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String mobile;
	
	@Column(nullable = false)
	private String otp;
	
	@Column(nullable = false)
	private LocalDateTime expiryTIme;
	
	private boolean used;
	

}
