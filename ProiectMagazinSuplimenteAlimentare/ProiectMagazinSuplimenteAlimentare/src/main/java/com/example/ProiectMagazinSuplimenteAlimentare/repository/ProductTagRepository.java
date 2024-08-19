package com.example.ProiectMagazinSuplimenteAlimentare.repository;

import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductTagRepository interface is used to create the methods that will be implemented in the ProductTagRepository class.

 */
@Repository
public interface ProductTagRepository extends CrudRepository<ProductTag, Long> {
    ProductTag findFirstByName (String name);
}
