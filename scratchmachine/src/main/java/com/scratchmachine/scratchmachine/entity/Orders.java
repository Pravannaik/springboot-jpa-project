package com.scratchmachine.scratchmachine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long userId;
    private BigDecimal totalOrderAmount;
    private int totalScratchCard;

    public Orders(Long userId, BigDecimal totalOrderAmount, int totalScratchCard) {
        this.userId = userId;
        this.totalOrderAmount = totalOrderAmount;
        this.totalScratchCard = totalScratchCard;
    }
}
