//package com.example.library_management_demo;
//
//import com.example.library_management_demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpSession;
//
//@Component
//public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private HttpSession httpSession;
//
//    @Override
//    public void onApplicationEvent(AuthenticationSuccessEvent event) {
//        User user = (User) event.getAuthentication().getPrincipal();
//        String displayName = userService.getByUsername(user.getUsername()).getDisplayName();
//        httpSession.setAttribute("loggedInUserName", displayName);
//    }
//}