package com.proto.serre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String redirect(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model){
        return "index";
    }
}
