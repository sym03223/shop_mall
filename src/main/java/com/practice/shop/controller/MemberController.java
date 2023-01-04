package com.practice.shop.controller;

import com.practice.shop.dto.MemberFormDto;
import com.practice.shop.entity.Member;
import com.practice.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public ModelAndView memberForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("memberFormDto", new MemberFormDto());
        modelAndView.setViewName("member/memberForm");
        return modelAndView;
    }

    @PostMapping(value = "/news")
    public ModelAndView newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        ModelAndView mav = new ModelAndView("redirect:/");

        if(bindingResult.hasErrors()){
            mav.setViewName("member/memberForm");
            return mav;
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            mav.setViewName("member/memberForm");
            return mav;
        }

        return mav;
    }

}
