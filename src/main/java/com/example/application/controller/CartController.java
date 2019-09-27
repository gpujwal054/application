package com.example.application.controller;

import com.example.application.model.Cart;
import com.example.application.model.Product;
import com.example.application.model.User;
import com.example.application.repositories.CartRepository;
import com.example.application.repositories.ProductRepository;
import com.example.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/cart")
    public String saveCart(@ModelAttribute Cart cart, ModelMap modelMap, Authentication authentication) {
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        User current_user = userRepository.findByEmail(userDetail.getUsername());
        cart.setUserId(current_user.getId());
        Product product = productRepository.getProductById(cart.getProduct_id());
        cart.setTotalPrice(product.getPrice().multiply(new BigDecimal(cart.getQuantity())));
        cartRepository.save(cart);
        List<Product> list = productRepository.findAll();
        modelMap.put("productList", list);
        cart = new Cart();
        modelMap.put("message", "Successfully added to cart");
        modelMap.put("alertClass", "alert-danger");
        return "products";
    }

    @GetMapping("/cart/{id}")
    public String addCart(@PathVariable Integer id, ModelMap modelMap, Authentication authentication) {
        Product product = productRepository.getProductById(id);
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        User current_user = userRepository.findByEmail(userDetail.getUsername());
        Cart cart = cartRepository.findByUserIdAndProductId(current_user.getId(), id);
        modelMap.put("product", product);
        modelMap.put("cart", cart == null ? new Cart() : cart);
        return "cart";
    }

    @GetMapping("/cart/showCart")
    public String showCart(ModelMap modelMap, Authentication authentication) {
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        User current_user = userRepository.findByEmail(userDetail.getUsername());
        List<Cart> list = cartRepository.findByUserId(current_user.getId());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCartProduct(productRepository.getProductById(list.get(i).getProduct_id()));
            list.get(i).setTotalPrice(productRepository.getProductById(list.get(i).getProduct_id()).getPrice().multiply(new BigDecimal(list.get(i).getQuantity())));
        }
        modelMap.addAttribute("cartList", list);
        return "cart/showCartItems";
    }
}
