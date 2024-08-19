package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
     
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByUsernameAndPassword(
       @Param("email") String email,
       @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:search% OR u.name LIKE %:search% OR u.email LIKE %:search% OR u.password LIKE %:search%")
    Page<User> search( @Param("search") String search, Pageable pageable);

    
} 
