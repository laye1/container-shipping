package com.saraya.shippingContainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saraya.shippingContainer.model.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Long>{

}
