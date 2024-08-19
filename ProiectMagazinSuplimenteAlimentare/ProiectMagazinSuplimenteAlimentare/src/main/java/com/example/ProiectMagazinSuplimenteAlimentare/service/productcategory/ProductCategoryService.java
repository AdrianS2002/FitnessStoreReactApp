package com.example.ProiectMagazinSuplimenteAlimentare.service.productcategory;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CategoryAtributionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProductCategoryService interface is used to create the methods that will be implemented in the ProductCategoryServiceImpl class.
 */
@Component
public interface ProductCategoryService {
    ProductCategoryDTO save(ProductCategoryCreationDTO productCategory);
    ProductCategory findProductCategoryByName(String name);
    ProductCategory findProductCategoryById(Long id);

    ProductCategoryDTO updateProductCategory(ProductCategoryDTO productCategory);

    List<ProductCategory> findAll();

    ProductCategory assignProduct(CategoryAtributionDTO dto);

    boolean deleteProductCategory(Long id);

}
