package com.azsoft.skbiryani.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpVerification extends JpaRepository<OtpVerification, Long>{
	
	Optional<OtpVerification> findByMobile(String mobile);

}
