package com.styleverse.repository;

import com.styleverse.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query example using @Query annotation
    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Customer findByEmail(String email);
}
