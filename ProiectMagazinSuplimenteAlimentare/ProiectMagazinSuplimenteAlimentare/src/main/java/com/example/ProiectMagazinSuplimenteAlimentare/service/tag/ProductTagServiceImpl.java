package com.example.ProiectMagazinSuplimenteAlimentare.service.tag;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CategoryAtributionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagAtributionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.ProductTagMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductTag;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductTagRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * ProductTagServiceImpl class implements the methods from the ProductTagService interface.
 */
@Service
public class ProductTagServiceImpl implements ProductTagService{
    private final ProductTagRepository productTagRepository;
    private final ProductRepository productRepository;
    public ProductTagServiceImpl(ProductTagRepository productTagRepository, ProductRepository productRepository) {
        this.productTagRepository = productTagRepository;
        this.productRepository = productRepository;
    }
    @Override
    public List<ProductTag> findAll() {
        return (List<ProductTag>)productTagRepository.findAll();
    }

    @Override
    public ProductTag findProductTagById(Long id) {
        return productTagRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductTag findProductTagByName(String name) {
        return productTagRepository.findFirstByName(name);
    }

    @Override
    public TagDTO saveProductTag(TagCreationDTO dto) {
        ProductTag entity = ProductTagMapper.toCreationEntity(dto);
        entity = productTagRepository.save(entity);
        return ProductTagMapper.toDto(entity);
    }

    @Override
    public TagDTO updateProductTag(TagDTO dto) {
        ProductTag productTag = ProductTagMapper.toEntity(dto);
        boolean exists = productTagRepository.findById(productTag.getId()).isPresent();
        if (exists) {
            productTag = productTagRepository.save(productTag);
            return ProductTagMapper.toDto(productTag);
        }
        return null;
    }

    @Override
    public boolean deleteProductTag(Long id) {
        productTagRepository.deleteById(id);
        return productTagRepository.findById(id).isEmpty();

    }

    @Override
    public ProductTag assignProduct(TagAtributionDTO dto) {
        ProductTag productTag = productTagRepository.findById(dto.getProductTagId()).orElseThrow();
        Product product = productRepository.findById(dto.getProductId()).orElseThrow() ;
        productTag.getProducts().add(product);
        productTagRepository.save(productTag);
        return productTag;
    }
}
