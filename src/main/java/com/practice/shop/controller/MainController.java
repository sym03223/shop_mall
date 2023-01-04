package com.practice.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @GetMapping(value = "/")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }
    @GetMapping(value = "/232")
    public ModelAndView main2(){
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }
}
