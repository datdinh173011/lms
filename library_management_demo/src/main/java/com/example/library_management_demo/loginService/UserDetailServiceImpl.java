package com.example.library_management_demo.loginService;

import com.example.library_management_demo.model.User;
import com.example.library_management_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = usersRepository.findByUsername(username);
        if (users == null) throw new UsernameNotFoundException("User Not Found");
        return new UserPrincipal(users);
    }

}