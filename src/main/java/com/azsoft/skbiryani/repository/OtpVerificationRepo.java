package com.azsoft.skbiryani.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.azsoft.skbiryani.entity.OtpVerification;

import jakarta.transaction.Transactional;

@Repository
public interface OtpVerificationRepo extends JpaRepository<OtpVerification, Long>{
	
	Optional<OtpVerification> findByMobile(String mobile);
	
//	@Transactional
//	void deleteByMobile(String mobile);
	@Modifying
    @Transactional
    @Query("DELETE FROM OtpVerification o WHERE o.mobile = :mobile")
    void deleteByMobile(@Param("mobile") String mobile);

}
