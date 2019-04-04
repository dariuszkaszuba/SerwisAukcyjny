package pl.sa.serwisaukcyjny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sa.serwisaukcyjny.model.Product;
import pl.sa.serwisaukcyjny.model.User;
import pl.sa.serwisaukcyjny.model.dto.ProductDto;
import pl.sa.serwisaukcyjny.repository.ProductRepository;
import pl.sa.serwisaukcyjny.repository.UserRepository;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;
    UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product createProductByUser(ProductDto productDto){
//        User user = userRepository.findByEmail(email);
        Product product= new Product(
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getLocation(),
                productDto.getEnd_added(),
                productDto.getCategory()
        );
        return productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id){
        return productRepository.getOne(id);
    }

    public Product updateProduct(Long id, Product product){
        Product updateProduct = productRepository.getOne(id);
        updateProduct.setTitle(product.getTitle());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setCategory(product.getCategory());
        updateProduct.setLocation(product.getLocation());
        updateProduct.setEnd_added(product.getEnd_added());
        return productRepository.save(updateProduct);
    }
    // delete modyfikacja dodawnaie do koszyka

//    public
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
}
