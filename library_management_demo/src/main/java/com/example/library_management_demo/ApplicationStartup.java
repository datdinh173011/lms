//package com.example.library_management_demo;
//
//import com.example.library_management_demo.model.User;
//import com.example.library_management_demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import static com.example.library_management_demo.common.Constants.ROLE_ADMIN;
//import static com.example.library_management_demo.common.Constants.ROLE_LIBRARIAN;
//
//@Component
//public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//        initDatabaseEntities();
//    }
//
//
//    private void initDatabaseEntities() {
//
//        if( userService.getAllUsers().size() == 0) {
//            userService.addNew(new User("Mr. Admin", "admin", "admin", ROLE_ADMIN));
//            userService.addNew(new User("Mr. Librarian", "librarian", "librarian", ROLE_LIBRARIAN));
//        }
//
//    }
//}
