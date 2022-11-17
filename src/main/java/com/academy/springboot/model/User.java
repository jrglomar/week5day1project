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
@Table(name = "USERS")
public class User extends BaseAuditClass{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;
    
    @Column
    @Enumerated(value = EnumType.STRING)
    private Roles role;

}
