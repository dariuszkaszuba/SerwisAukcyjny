package pl.sa.serwisaukcyjny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.sa.serwisaukcyjny.model.Product;
import pl.sa.serwisaukcyjny.model.ShoppingCart;
import pl.sa.serwisaukcyjny.model.User;
import pl.sa.serwisaukcyjny.model.dto.ProductDto;
import pl.sa.serwisaukcyjny.repository.ProductRepository;
import pl.sa.serwisaukcyjny.repository.ShoppingCartRepository;
import pl.sa.serwisaukcyjny.repository.UserRepository;

import java.util.List;

@Service
public class ShoppingCartService {
    ShoppingCartRepository shoppingCartRepository;
    UserRepository userRepository;
    UserService userService;
@Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, UserService userService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //    @Autowired
//    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
//        this.shoppingCartRepository = shoppingCartRepository;
//    }

    public ShoppingCart addProductToCart(Long id_cart, Product product) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOne(id_cart);
        shoppingCart.addProduct(product);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getProductByUserEmail(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.getUserNameByEmail(email);
        ShoppingCart shoppingCart = user.getShoppingCart();
        return shoppingCart;
    }

    public List<Product> getShoppingCart(ShoppingCart shoppingCart) {
        List<Product> products = shoppingCart.getProducts();
        return products;
    }
}
