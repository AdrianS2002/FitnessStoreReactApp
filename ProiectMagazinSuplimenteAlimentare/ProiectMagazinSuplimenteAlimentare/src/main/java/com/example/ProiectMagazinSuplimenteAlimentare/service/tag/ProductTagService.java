package com.example.ProiectMagazinSuplimenteAlimentare.service.tag;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CategoryAtributionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagAtributionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductTag;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * ProductTagService interface is used to create the methods that will be implemented in the ProductTagServiceImpl class.
 */
@Component
public interface ProductTagService {
    List<ProductTag> findAll();
    ProductTag findProductTagById(Long id);

    ProductTag findProductTagByName(String name);
    TagDTO saveProductTag(TagCreationDTO productTag);
    TagDTO updateProductTag(TagDTO productTag);
    boolean deleteProductTag(Long id);
    ProductTag assignProduct(TagAtributionDTO dto);

}
