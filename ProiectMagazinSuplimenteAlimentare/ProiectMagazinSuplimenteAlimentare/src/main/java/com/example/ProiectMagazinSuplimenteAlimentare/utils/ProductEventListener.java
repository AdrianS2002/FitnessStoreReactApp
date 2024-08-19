package com.example.ProiectMagazinSuplimenteAlimentare.utils;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import jakarta.persistence.PostPersist;

public class ProductEventListener {

    @PostPersist
    public void notifyAvailability(Product product) {
        System.out.println("New apartment created " + product.getId());
    }
}
