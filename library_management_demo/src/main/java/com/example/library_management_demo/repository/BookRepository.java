package com.example.library_management_demo.repository;

import com.example.library_management_demo.model.Book;
import com.example.library_management_demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByTag(String tag);
    public List<Book> findByCategory(Category category);
    public  List<Book> findByCategoryAndStatus(Category category, Integer status);
    public Long countByStatus(Integer status);
}
