package com.example.library_management_demo.repository;

import com.example.library_management_demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
    public Long countByType(String type);
}
