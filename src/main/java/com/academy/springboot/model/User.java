package com.academy.springboot.model;

import com.academy.springboot.enums.Roles;
import lombok.*;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Roles role;
    
}
