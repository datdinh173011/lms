package com.example.library_management_demo.service;

import com.example.library_management_demo.model.Issue;
import com.example.library_management_demo.model.Member;
import com.example.library_management_demo.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.library_management_demo.common.Constants.BOOK_NOT_RETURNED;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    public Issue get(Long id) {
        return issueRepository.findById(id).get();
    }

    public List<Issue> getAllUnreturned() {
        return issueRepository.findByReturned( BOOK_NOT_RETURNED );
    }

    public Issue addNew(Issue issue) {
        issue.setIssueDate( new Date() );
        issue.setReturned( BOOK_NOT_RETURNED );
        return issueRepository.save(issue);
    }

    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    public Long getCountByMember(Member member) {
        return issueRepository.countByMemberAndReturned(member, BOOK_NOT_RETURNED);
    }
}
