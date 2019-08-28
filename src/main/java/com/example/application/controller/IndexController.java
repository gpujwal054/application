package com.example.application.controller;

import com.example.application.model.Admin;
import com.example.application.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private AdminRepository adminRepository;

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
    @RequestMapping("/admin/login")
    String admin_login(Model model){
        model.addAttribute("admin",new Admin());
        return "admin/adminLogin";
    }
    @RequestMapping("/contact")
    String contact(){
        return "contact";
    }
}
