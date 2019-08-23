//package com.example.application.services;
//
//import com.example.application.model.Product;
//import com.example.application.repositories.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductServiceImpl implements ProductService {
//    private ProductRepository productRepository;
//
//    @Autowired
//    public void setProductRepository(ProductRepository productRepository){
//        this.productRepository=productRepository;
//    }
//    @Override
//    public Product saveProduct(Product product){
//        return productRepository.save(product);
//    }
//}
