package com.example.library_management_demo.service;

import com.example.library_management_demo.common.Constants;
import com.example.library_management_demo.exception.ResourceNotFoundException;
import com.example.library_management_demo.model.Member;
import com.example.library_management_demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.library_management_demo.common.Constants.MEMBER_TEACHER;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private IssueService issueService;

    public Long getTotalCount(){
        return memberRepository.count();
    }

    public Long getTeachersCount() {
        return memberRepository.countByType(MEMBER_TEACHER);
    }

    public Long getStudentsCount() {
        return memberRepository.countByType(Constants.MEMBER_STUDENT);
    }

    public List<Member> getAll() {
        return memberRepository.findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
    }

    public List<Member> listAll(){
        return memberRepository.findAll();
    }

    public Member get(long id) {
        return memberRepository.findById(id).get();
    }

    public Member addNew(Member member) {
        member.setJoiningDate( new Date() );
        return memberRepository.save( member );
    }

    public Member save(Member member) {
        return memberRepository.save( member );
    }

    public Iterable<Member> delete(Member member) {
        memberRepository.delete(member);
        return memberRepository.findAll();
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public boolean hasUsage(Member member) {
        return issueService.getCountByMember(member) > 0;
    }

    public void updateMember(Member member) {
    }

}
