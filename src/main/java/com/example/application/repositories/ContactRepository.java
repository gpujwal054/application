package com.example.application.repositories;

import com.example.application.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    Contact getMessageById(Integer id);
}
