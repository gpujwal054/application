package com.example.application.repositories;

import com.example.application.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByUserId(Integer id);
    @Query("SELECT c FROM Cart c WHERE user_id= ?1 AND product_id= ?2")
    Cart findByUserIdAndProductId(int user_id, int product_id);
}
