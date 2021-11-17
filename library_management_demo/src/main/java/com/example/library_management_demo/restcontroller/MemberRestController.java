package com.example.library_management_demo.restcontroller;

import com.example.library_management_demo.model.Member;
import com.example.library_management_demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/member")
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = {"/", "/list"})
    public List<Member> all() {
        return memberService.getAll();
    }

    @PostMapping(value="/add")
    public void addUser(@RequestBody Member member){
        memberService.save(member);
    }

}