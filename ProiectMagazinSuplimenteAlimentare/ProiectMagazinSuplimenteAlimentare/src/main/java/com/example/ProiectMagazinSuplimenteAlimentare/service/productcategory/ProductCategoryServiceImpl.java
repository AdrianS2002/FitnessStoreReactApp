package com.example.ProiectMagazinSuplimenteAlimentare.service.productcategory;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CategoryAtributionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.ProductCategoryMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductCategoryRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * ProductCategoryServiceImpl class implements the methods from the ProductCategoryService interface.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public ProductCategoryDTO save(ProductCategoryCreationDTO dto) {
        ProductCategory entity = ProductCategoryMapper.toCreationEntity(dto);
        entity = productCategoryRepository.save(entity);
        return ProductCategoryMapper.toDto(entity);
    }

    @Override
    public ProductCategory findProductCategoryByName(String name) {
        return productCategoryRepository.findFirstByName(name);
    }

    @Override
    public ProductCategory findProductCategoryById(Long id) {
        return productCategoryRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductCategoryDTO updateProductCategory(ProductCategoryDTO dto) {
        ProductCategory productCategory = ProductCategoryMapper.toEntity(dto);
        boolean exists = productCategoryRepository.findById(productCategory.getId()).isPresent();
        if(exists){
            productCategory = productCategoryRepository.save(productCategory);
            return ProductCategoryMapper.toDto(productCategory);
        }
        return null;
    }

    @Override
    public List<ProductCategory> findAll() {
        return (List<ProductCategory>) productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory assignProduct(CategoryAtributionDTO dto) {
        ProductCategory productCategory = productCategoryRepository.findById(dto.getProductCategoryId()).orElseThrow();
        Product product = productRepository.findById(dto.getProductId()).orElseThrow() ;
        productCategory.getProducts().add(product);
        productCategoryRepository.save(productCategory);
        return productCategory;
    }

    @Override
    public boolean deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
        return productCategoryRepository.findById(id).isEmpty();
    }
}
