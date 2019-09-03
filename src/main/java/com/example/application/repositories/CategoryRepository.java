package com.example.application.repositories;

import com.example.application.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAll();
    Category getCategoryById(Integer id);
}
