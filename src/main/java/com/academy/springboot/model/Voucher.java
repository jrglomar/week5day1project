package com.academy.springboot.model;

import com.academy.springboot.enums.Types;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Integer number;
    
    @Column
    private Number amount;
    
    @Column
    private String description;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Types type = Types.CHECK;

}
