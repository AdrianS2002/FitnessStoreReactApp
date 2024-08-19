//package com.example.ProiectMagazinSuplimenteAlimentare.serviceTest;
//
//import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;
//import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductCategoryRepository;
//import com.example.ProiectMagazinSuplimenteAlimentare.service.productcategory.ProductCategoryServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class ProductCategoryServiceImplTest {
//
//    @Mock
//    private ProductCategoryRepository productCategoryRepository;
//
//    private ProductCategoryServiceImpl productCategoryService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        productCategoryService = new ProductCategoryServiceImpl(productCategoryRepository);
//    }
//
//    @Test
//    public void testSaveProductCategory() {
//        ProductCategory productCategory = new ProductCategory();
//        when(productCategoryRepository.save(any(ProductCategory.class))).thenReturn(productCategory);
//
//        productCategoryService.save(productCategory);
//
//        verify(productCategoryRepository, times(1)).save(productCategory);
//    }
//
//    @Test
//    public void testDeleteProductCategory() {
//        Long id = 1L;
//        doNothing().when(productCategoryRepository).deleteById(id);
//
//        productCategoryService.deleteProductCategory(id);
//
//        verify(productCategoryRepository, times(1)).deleteById(id);
//    }
//}
//
