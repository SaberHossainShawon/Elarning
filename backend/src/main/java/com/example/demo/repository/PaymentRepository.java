package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("SELECT s FROM Payment s WHERE s.email LIKE %:search% OR s.amount LIKE %:search% OR s.paymenttype LIKE %:search% OR s.number LIKE %:search%")
    Page<Payment> search(@Param("search") String search, Pageable pageable);
    
} 