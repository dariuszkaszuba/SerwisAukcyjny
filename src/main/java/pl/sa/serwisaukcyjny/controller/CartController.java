package pl.sa.serwisaukcyjny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sa.serwisaukcyjny.model.Product;
import pl.sa.serwisaukcyjny.model.ShoppingCart;
import pl.sa.serwisaukcyjny.service.ProductService;
import pl.sa.serwisaukcyjny.service.ShoppingCartService;

import java.util.List;

@Controller
public class CartController {

    ShoppingCartService shoppingCartService;
    ProductService productService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    //    Service service;
//
//    public FrontController(Service service) {
//        this.service = service;
//    }

    @GetMapping("/cart")
    public String cartView(Model model, Authentication authentication) {
        ShoppingCart shoppingCart = shoppingCartService.getProductByUserEmail(authentication);
        List<Product> products = shoppingCartService.getShoppingCart(shoppingCart);
        model.addAttribute("products", products);
        return "cart";
    }

    @GetMapping("/allproducts/{id}")
    public String addProductToCart(Model model, Authentication authentication, Long id) {
        model.addAttribute("product", productService.getProductById(id) );
        model.addAttribute("auth", authentication);

        return "product";
    }

//    @PostMapping("/")
//    public String addProductToCart(
//            @ModelAttribute("post")
//            Authentication authentication,
//            Model model,
//            Long id){
//        shoppingCartService.addProductToCart(id, new)
//
//    }
}