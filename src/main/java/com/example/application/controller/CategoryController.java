package com.example.application.controller;

import com.example.application.model.Category;
import com.example.application.model.Product;
import com.example.application.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("category/new")
    public String newCategory(Model model){
        model.addAttribute("category",new Category());
        return "category/insertCats";
    }

    @PostMapping("category")
    public String saveCategory(@ModelAttribute Category category, ModelMap modelMap){
        categoryRepository.save(category);
        modelMap.put("message","Category inserted successfully");
        modelMap.put("alertClass", "alert-danger");
        return "category/insertCats";
    }

    @RequestMapping("category/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, ModelMap modelMap) {
        model.addAttribute("category", categoryRepository.getCategoryById(id));
        modelMap.put("message", "Product updated successfully");
        modelMap.put("alertClass", "alert-danger");
        return "category/insertCats";
    }

    @RequestMapping("category/delete/{id}")
    public String delete(@PathVariable Integer id, ModelMap modelMap) {
        categoryRepository.deleteById(id);
        modelMap.put("message", "Product deleted successfully");
        return "category/showCats";
    }
    @RequestMapping(value = "/category/show", method = RequestMethod.GET)
    public ModelAndView showCategory(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> list = categoryRepository.findAll();
        modelAndView.addObject("categoryList", list);
        modelAndView.setViewName("category/showCats");
        return modelAndView;
    }
}
