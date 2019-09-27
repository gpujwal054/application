package com.example.application.model;

import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", columnDefinition = "serial")
    private Integer id;
    @NotEmpty
    @Column(name = "product_name",nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotEmpty
    @Column(name = "product_description",nullable = false)
    private String description;
    @Transient
    private MultipartFile[] imageUrl;
    @Column(name = "product_image")
    private String imageName;
    @Min(1)
    @Column(name = "product_price")
    private BigDecimal price;
    @Transient
    private int productCat;


    @Column(name = "product_quantity")
    private int productQuantity;

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductCat() {
        return productCat;
    }

    public void setProductCat(int productCat) {
        this.productCat = productCat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
