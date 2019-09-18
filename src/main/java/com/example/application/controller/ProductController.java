package com.example.application.controller;

import com.example.application.model.Category;
import com.example.application.model.Product;
import com.example.application.repositories.CategoryRepository;
import com.example.application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.ZipOutputStream;
//
//import static com.example.application.Application.compressImage;
//import static com.example.application.Application.multipartToFile;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private CategoryRepository catRepo;

    //store uploaded file to this folder
    private static String upload_dir = System.getProperty("user.dir") + "/src/main/resources/static/images/";


    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        List<Category> categoryList = catRepo.findAll();

        model.addAttribute("categoryList", categoryList);
        return "products/insertProduct";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, ModelMap modelMap) {
        model.addAttribute("product", prodRepo.getProductById(id));
        modelMap.put("message", "Product updated successfully");
        modelMap.put("alertClass", "alert-danger");
        return "products/insertProduct";
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id, ModelMap modelMap) {
        try {
            File file = new File(upload_dir + prodRepo.getProductById(id).getImageName());
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            System.out.println("Failed to Delete image !!");
        }
        prodRepo.deleteById(id);
        modelMap.put("message", "Product deleted successfully");
        return "products/showProducts";
    }

    @PostMapping("product")
    public String saveProduct(@ModelAttribute Product product, ModelMap modelMap) {
        ArrayList<String> fileNames = null;
        if (product.getImageUrl().length > 0) {
            fileNames = new ArrayList<String>();
            for (MultipartFile file : product.getImageUrl()) {
                if (file.isEmpty()) {
                    modelMap.put("message", "Please select the file");
                }
                try {
                    //compressImage(multipartToFile(file,file.getOriginalFilename()),new File(upload_dir + file.getOriginalFilename()),0.5f);
                    file.transferTo(new File(upload_dir + file.getOriginalFilename()));
                    fileNames.add(file.getOriginalFilename());
                    product.setImageName(file.getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        product.setCategory(catRepo.getCategoryById(product.getProductCat()));
        prodRepo.save(product);
        product = new Product();
        modelMap.put("message", "Product inserted successfully");
        modelMap.put("alertClass", "alert-danger");
        modelMap.put("files", fileNames);
        return "products/insertProduct";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView Products(Model model) {

        ModelAndView modelAndView = new ModelAndView();

        List<Product> list = prodRepo.findAll();

        modelAndView.addObject("productList", list);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @RequestMapping(value = "/product/show", method = RequestMethod.GET)
    public ModelAndView showProduct(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> list = prodRepo.findAll();
        modelAndView.addObject("productList", list);
        modelAndView.setViewName("products/showProducts");
        return modelAndView;
    }
}
