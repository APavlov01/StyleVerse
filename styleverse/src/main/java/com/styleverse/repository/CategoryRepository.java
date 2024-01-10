package com.styleverse.repository;

import com.styleverse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom query example using @Query annotation
    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findByName(String name);
}
