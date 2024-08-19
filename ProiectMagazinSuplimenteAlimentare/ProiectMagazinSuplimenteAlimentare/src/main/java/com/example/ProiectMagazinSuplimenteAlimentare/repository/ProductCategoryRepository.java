package com.example.ProiectMagazinSuplimenteAlimentare.repository;

import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductCategoryRepository interface is used to create the methods that will be implemented in the ProductCategoryRepository class.

 */
@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long>{
    ProductCategory findFirstByName(String name);
}
