package com.scratchmachine.scratchmachine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "scratchcards")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scratchcards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scratchCardId;

    private BigDecimal discount;
    private LocalDate scratchCardExpiryDate;
    private boolean active = true;
    private boolean used;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public Scratchcards(BigDecimal discount, LocalDate scratchCardExpiryDate, boolean active) {
        this.discount = discount;
        this.scratchCardExpiryDate = scratchCardExpiryDate;
        this.active = active;
    }
}
