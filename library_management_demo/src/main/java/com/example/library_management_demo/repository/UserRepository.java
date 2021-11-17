package com.example.library_management_demo.repository;

import com.example.library_management_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByOrderByDisplayNameAsc();
    public List<User> findAllByActiveOrderByDisplayNameAsc(Integer active);
    public User findByUsername(String username);
}
