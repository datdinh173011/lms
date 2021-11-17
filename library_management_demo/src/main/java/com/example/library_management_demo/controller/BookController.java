package com.example.library_management_demo.controller;


import com.example.library_management_demo.model.Book;
import com.example.library_management_demo.model.Category;
import com.example.library_management_demo.service.BookService;
import com.example.library_management_demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute(name="categories")
    public List<Category> categories(){
        return categoryService.getAllBySort();
    }

    @RequestMapping(value={"","/list"}, method = RequestMethod.GET)
    public String showBooksPage(Model model){
        model.addAttribute("books", bookService.getAll());
        return "book/list";
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String addBookPage(Model model){
        model.addAttribute("book", new Book());
        return "/book/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBookPage(@PathVariable(name = "id") Long id, Model model) {
        Book book = bookService.get(id);
        if( book != null ) {
            model.addAttribute("book", book);
            return "/book/form";
        } else {
            return "redirect:/api/book/add";
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@Valid Book book, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() ) {
            return "/book/form";
        }

        if( book.getId() == null ) {
            if( bookService.getByTag(book.getTag()) != null ) {
                bindingResult.rejectValue("tag", "tag", "Tag already exists");
                return "/book/form";
            } else {
                bookService.addNew(book);
                redirectAttributes.addFlashAttribute("successMsg", "'" + book.getTitle() + "' is added as a new Book.");
                return "redirect:/api/book/add";
            }
        } else {
            Book updatedBook = bookService.save(book);
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + book.getTitle() + "' are saved successfully. ");
            return "redirect:/api/book/edit/"+updatedBook.getId();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeBook(@PathVariable(name = "id") Long id, Model model) {
        Book book = bookService.get( id );
        if( book != null ) {
            if( bookService.hasUsage(book) ) {
                model.addAttribute("bookInUse", true);
                return showBooksPage(model);
            } else {
                bookService.delete(id);
            }
        }
        return "redirect:/api/book/list";
    }

}
