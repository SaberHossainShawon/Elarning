package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
    @Column(name = "Email")
    private String email;
    @Column(name = "Amount")
    private String amount;
    @Column(name = "PaymentType")
    private String paymenttype;
    @Column(name = "Number")
    private String number;    
    @Column(name = "CardNumber")
    private String cardNumber;
    @Column(name = "TransationId")
    private String transationid;
    
}
