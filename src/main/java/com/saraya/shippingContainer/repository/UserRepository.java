package com.saraya.shippingContainer.repository;

import java.util.Optional;  

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.saraya.shippingContainer.model.User;

@Repository("userRepository")
@Transactional(readOnly = true)
public interface  UserRepository extends JpaRepository<User,Long> {
	
	@Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
	int enableUser(String email);
	
	Optional<User> findByEmail(String email);

}
