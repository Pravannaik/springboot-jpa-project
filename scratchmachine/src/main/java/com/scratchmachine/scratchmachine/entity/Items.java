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
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal rate;
    private boolean active;

    public Items(String name, BigDecimal rate, boolean active) {
        this.name = name;
        this.rate = rate;
        this.active = active;
    }
}
