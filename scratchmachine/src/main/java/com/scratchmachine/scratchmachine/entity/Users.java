package com.scratchmachine.scratchmachine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;
    private String firstName;
    private String lastName;
    private boolean active;
    private String password;

    public Users(Long userId, String firstName, String lastName, boolean active, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.password = password;
    }
}
