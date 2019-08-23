package com.example.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    String index(){
        return "index";
    }
    @RequestMapping("/home")
    String home(){
        return "home";
    }
    @RequestMapping("/about")
    String about(){
        return "about";
    }
    @RequestMapping("/products")
    String products(){
        return "products";
    }
    @RequestMapping("/admin/login")
    String adminLogin(){
        return "admin/adminLogin";
    }
    @RequestMapping("/admin")
    String admin(){
        return "admin/admin";
    }
    @RequestMapping("/contact")
    String contact(){
        return "contact";
    }
}
