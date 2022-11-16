package com.academy.springboot.model;

import com.academy.springboot.enums.Roles;
import com.academy.springboot.enums.Types;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;
    
    @Column
    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.USER;
    
}
