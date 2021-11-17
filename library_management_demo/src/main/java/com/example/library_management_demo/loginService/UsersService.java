package com.example.library_management_demo.loginService;


import com.example.library_management_demo.model.User;
import com.example.library_management_demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsersService {

    private UserRepository usersRepository ;

    public UsersService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public List<User> getAllUsers() {
        return usersRepository.findAllByOrderByDisplayNameAsc();
    }

    public List<User> getAllActiveUsers(){
        return usersRepository.findAllByActiveOrderByDisplayNameAsc(1);
    }

    public User getByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public User getById(Long id){
        return usersRepository.findById(id).get();
    }

    public User addNew(User users){
        users.setActive(1);
        users.setCreatedDate(new Date());
        users.setLastModifiedDate(users.getCreatedDate());
        usersRepository.save(users);
        return users;
    }

    public User updateDate(User users){
        users.setLastModifiedDate(new Date());
        return usersRepository.save(users);
    }
}
