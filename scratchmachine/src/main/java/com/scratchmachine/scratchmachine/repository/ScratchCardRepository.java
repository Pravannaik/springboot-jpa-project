package com.scratchmachine.scratchmachine.repository;

import com.scratchmachine.scratchmachine.entity.Scratchcards;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScratchCardRepository extends JpaRepository<Scratchcards, Long> {

    @Query("SELECT sc FROM scratchcards sc WHERE sc.users is NULL AND sc.scratchCardExpiryDate < ?1")
    Optional<List<Scratchcards>> findByExpiryDateLessThanToday(LocalDate currentDate);

    @Query("SELECT sc FROM scratchcards sc WHERE sc.users is NULL ORDER BY sc.id ASC")
    Optional<List<Scratchcards>> findByUserAndLimit(Pageable pageable);

    Optional<List<Scratchcards>> findByUsersUserId(Long userId);

    Optional<Scratchcards> findByScratchCardIdAndUsed(Long id, boolean used);

    @Query("SELECT sc FROM scratchcards sc INNER JOIN users")
    Optional<List<Scratchcards>> findByScratchCardUserId();

}
