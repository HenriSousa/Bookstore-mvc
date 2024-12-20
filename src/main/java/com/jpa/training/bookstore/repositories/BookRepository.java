package com.jpa.training.bookstore.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.training.bookstore.models.BookModel;

public interface BookRepository extends JpaRepository <BookModel, UUID> {
    
}
