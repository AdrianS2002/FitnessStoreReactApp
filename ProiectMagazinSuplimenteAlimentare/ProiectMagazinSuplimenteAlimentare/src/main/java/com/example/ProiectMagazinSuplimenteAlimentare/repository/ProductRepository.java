package com.example.ProiectMagazinSuplimenteAlimentare.repository;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository interface is used to create the methods that will be implemented in the ProductRepository class.

 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    Product findFirstByName(String name);
}
