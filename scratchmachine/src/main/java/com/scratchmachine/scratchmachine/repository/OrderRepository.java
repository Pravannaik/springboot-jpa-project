package com.scratchmachine.scratchmachine.repository;

import com.scratchmachine.scratchmachine.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
