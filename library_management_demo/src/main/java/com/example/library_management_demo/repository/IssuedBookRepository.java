package com.example.library_management_demo.repository;

import com.example.library_management_demo.model.Book;
import com.example.library_management_demo.model.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBookRepository extends JpaRepository<IssuedBook,Long> {
    public Long countByBookAndReturned(Book book, Integer returned);
}
