package com.example.library_management_demo.controller;

import com.example.library_management_demo.loginService.UsersService;
import com.example.library_management_demo.model.User;
import com.example.library_management_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterPage {
    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private UsersService usersService;

    public RegisterPage() {
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "login/Register";
    }

    @PostMapping("/register")
    public String showSuccessPage(User users){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        User users1 = usersService.addNew(users);
        return "login";
    }

}
