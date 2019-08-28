package com.example.application.controller;

import com.example.application.model.Product;
import com.example.application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository prodRepo;
    //store uploaded file to this folder
//    private static String upload_dir = System.getProperty("user.dir") + "/upload/";
    private static String upload_dir = System.getProperty("user.dir") + "/src/main/resources/static/images/";


    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products/insertProduct";
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
                    file.transferTo(new File(upload_dir + file.getOriginalFilename()));
                    fileNames.add(file.getOriginalFilename());
                    product.setImageName(file.getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        prodRepo.save(product);
        modelMap.put("message", "Product inserted successfully");
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
