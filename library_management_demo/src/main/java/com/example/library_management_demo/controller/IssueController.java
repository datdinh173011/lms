package com.example.library_management_demo.controller;

import com.example.library_management_demo.model.*;
import com.example.library_management_demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.library_management_demo.common.Constants.*;

@Controller
@RequestMapping(value = "/api/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private IssuedBookService issuedBookService;

    @ModelAttribute(name="memberTypes")
    public List<String> memberTypes(){
        return MEMBER_TYPES;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories(){
        return categoryService.getAllBySort();
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listIssuePage(Model model) {
        model.addAttribute("issues", issueService.getAllUnreturned());
        return "/issue/list";
    }
    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newIssuePage(Model model){
        return "/issue/form";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public List<Issue> addIssue(@RequestBody Issue issue){
        issueService.save(issue);
        return issueService.getAll();
    }

}
