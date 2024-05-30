package com.scratchmachine.scratchmachine.repository;

import com.scratchmachine.scratchmachine.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
}
