package com.example.library_management_demo.restcontroller;

import java.util.List;

import com.example.library_management_demo.model.Book;
import com.example.library_management_demo.model.Category;
import com.example.library_management_demo.service.BookService;
import com.example.library_management_demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rest/book")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = {"/", "/list"})
    public List<Book> all() {
        return bookService.getAll();
    }

    @GetMapping(value = "/{id}/list")
    public List<Book> get(@PathVariable(name = "id") Long id) {
        Category category = categoryService.get(id);
        return bookService.getByCategory( category );
    }

    @GetMapping(value = "/{id}/available")
    public List<Book> getAvailableBooks(@PathVariable(name = "id") Long id) {
        Category category = categoryService.get(id);
        return bookService.geAvailabletByCategory( category );
    }
}
