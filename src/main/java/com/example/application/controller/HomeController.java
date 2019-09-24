package com.example.application.controller;

import com.example.application.model.Contact;
import com.example.application.repositories.ContactRepository;
import com.example.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    ContactRepository contRepo;

    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public ModelAndView about(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }
    @RequestMapping(value = "/contact",method = RequestMethod.GET)
    public ModelAndView contact(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact",new Contact());
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @PostMapping("contact")
    public String message(@ModelAttribute Contact contact,ModelMap modelMap){
        contRepo.save(contact);
        contact = new Contact();
        modelMap.put("message", "Message submitted successfully");
        modelMap.put("alertClass", "alert-danger");
        return "contact";
    }
}
