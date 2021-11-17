package com.example.library_management_demo.controller;

import com.example.library_management_demo.exception.ResourceNotFoundException;
import com.example.library_management_demo.model.Member;
import com.example.library_management_demo.repository.MemberRepository;
import com.example.library_management_demo.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.library_management_demo.common.Constants.MEMBER_TYPES;

@Controller
@RequestMapping(value="/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @ModelAttribute(name="memberTypes")
    public List<String> memberTypes(){
        return MEMBER_TYPES;
    }

    @RequestMapping(value = {"/","/list"},  method = RequestMethod.GET)
    public String showMembersPage(Model model) {
        model.addAttribute("members", memberService.getAll());
        return "/member/memberlist";
    }

    @RequestMapping(value="/create/new", method = RequestMethod.POST)
    public ResponseEntity<String> addMemberPages(@RequestBody Member member, UriComponentsBuilder builder) throws JsonProcessingException {
        Member addedMember = memberService.addNew(member);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(addedMember);
        return new ResponseEntity<String>(json, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addMemeberPage(Model model) {
        model.addAttribute("member", new Member());
        return "/member/memberform";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String editMemberPage(@PathVariable(name="id") Long id, Model model) {
        Member member = memberService.get(id);
        if(member != null){
            model.addAttribute("member", member);
            return "member/memberform";
        }else {
            return "redirect:/api/member/add";
        }
    }


    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveMember(@Valid Member member, BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if ( bindingResult.hasErrors()){
            return "/member/memberform";
        }
        if (member.getId() == null) {
            memberService.addNew(member);
            redirectAttributes.addFlashAttribute("successMsg", "'" + member.getFirstName()+" "+member.getMiddleName() + "' is added as a new member.");
            return "redirect:/api/member/add";
        }else{
            Member updateMember = memberService.save(member);
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + member.getFirstName()+" "+member.getMiddleName() + "' are saved successfully. ");
            return "redirect:/api/member/edit/" + updateMember.getId();
        }
    }

    @RequestMapping(value = "/delete/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteMember(@PathVariable Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Memeber not exit with id:"+id));
        memberRepository.delete(member);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/remove/{id}", method = RequestMethod.GET)
    public String removeMember(@PathVariable(name="id") Long id, Model model){
        Member member = memberService.get(id);
        if( member != null){
            if( memberService != null){
                model.addAttribute("memberInUse", true);
                return showMembersPage(model);
            }else{
                memberService.delete(id);
            }
        }
        return "redirect:/api/member/list";
    }
}
