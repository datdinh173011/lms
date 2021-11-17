package com.example.library_management_demo.controller;

import com.example.library_management_demo.exception.ResourceNotFoundException;
import com.example.library_management_demo.model.Category;
import com.example.library_management_demo.repository.CategoryRepository;
import com.example.library_management_demo.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String showCategoriesPage(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "/category/list";
    }

    @RequestMapping(value="/show", method = RequestMethod.GET)
    public List<Category> listCategories() throws JsonProcessingException {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "/category/form";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody Category category, UriComponentsBuilder builder) throws JsonProcessingException{
       Category addedCategory = categoryService.addNew(category);
       ObjectMapper mapper = new ObjectMapper();
       String json = mapper.writeValueAsString(addedCategory);
       return new ResponseEntity<String>(json, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCategoryPage(@PathVariable(name = "id") Long id, Model model) {
        Category category = categoryService.get(id);
        if( category != null ) {
            model.addAttribute("category", category);
            return "/category/form";
        } else {
            return "redirect:/api/category/add";
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategory(@Valid Category category, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() ) {
            return "/category/form";
        }

        if( category.getId() == null ) {
            categoryService.addNew(category);
            redirectAttributes.addFlashAttribute("successMsg", "'" + category.getName() + "' is added as a new category.");
            return "redirect:/api/category/add";
        } else {
            Category updateCategory = categoryService.save( category );
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + category.getName() + "' are saved successfully. ");
            return "redirect:/api/category/edit/"+updateCategory.getId();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeCategory(@PathVariable(name = "id") Long id, Model model) {
        Category category = categoryService.get( id );
        if( category != null ) {
            if( categoryService.hasUsage(category) ) {
                model.addAttribute("categoryInUse", true);
                return showCategoriesPage(model);
            } else {
                categoryService.delete(id);
            }
        }
        return "redirect:/api/category/list";
    }

}
