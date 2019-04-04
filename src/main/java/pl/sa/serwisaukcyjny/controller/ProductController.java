package pl.sa.serwisaukcyjny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sa.serwisaukcyjny.model.Category;
import pl.sa.serwisaukcyjny.model.Product;
import pl.sa.serwisaukcyjny.model.dto.ProductDto;
import pl.sa.serwisaukcyjny.repository.ProductRepository;
import pl.sa.serwisaukcyjny.service.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/addproduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "blog";
    }

    @PostMapping("addproduct")
    public String addProduct(@ModelAttribute("product") @Valid ProductDto productDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addpostForm";
        }
        System.out.println("Dodano produkt: " +
                productService.createProductByUser(productDto));
        return "redirect:/";
    }

    @GetMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/";
    }

    @GetMapping("/updateproduct/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @PostMapping("/allproduct/{id}")
    public String updatePost(@ModelAttribute @Valid Product product, Model model) {
        Long id = product.getId();
        Product updateProduct = productService.updateProduct(id, product);
        model.addAttribute("product", updateProduct);
        return "product";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products =productService.getAllProduct();
        model.addAttribute("products", products);
        return "index";
    }
}
