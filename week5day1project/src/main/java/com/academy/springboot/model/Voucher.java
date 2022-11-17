package com.academy.springboot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Voucher extends BaseAuditClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number;
    @Column
    private Double amount ;
    @Column
    private String type;
    @Column
    private String description;

public Voucher(){}

    public Voucher(Long number, Double amount, String type, String description) {
        this.number = number;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}




