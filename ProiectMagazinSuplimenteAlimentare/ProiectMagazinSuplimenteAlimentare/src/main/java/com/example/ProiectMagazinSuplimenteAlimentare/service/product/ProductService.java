package com.example.ProiectMagazinSuplimenteAlimentare.service.product;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProductService interface is used to create the methods that will be implemented in the ProductServiceImpl class.

 */
@Component
public interface ProductService {
    List<Product> findAll();
    Product findProductById(Long id);
    Product findProductByName(String name);
    ProductDTO saveProduct(ProductCreationDTO product);
    ProductDTO updateProduct(ProductDTO product);

    boolean deleteProduct(Long id);

    String exportProductsToXML() throws JAXBException;
}
