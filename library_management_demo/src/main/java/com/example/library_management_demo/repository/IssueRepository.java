package com.example.library_management_demo.repository;

import com.example.library_management_demo.model.Issue;
import com.example.library_management_demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    public List<Issue> findByReturned(Integer returend);
    public Long countByMemberAndReturned(Member member, Integer returned);

}
