package com.example.ProiectMagazinSuplimenteAlimentare.service.product;

import com.example.ProiectMagazinSuplimenteAlimentare.constants.NotificationEndpoints;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.ProductMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.CommentRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductTagRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.wrapper.ProductsWrapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

/**
 *  ProductServiceImpl class implements the methods from the ProductService interface.

 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductTagRepository productTag;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private final CommentRepository commentRepository;
    public ProductServiceImpl(ProductRepository productRepository, ProductTagRepository productTag, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.productTag = productTag;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findFirstByName(name);
    }

    @Override
    public ProductDTO saveProduct(ProductCreationDTO dto) {
        Product product = ProductMapper.toCreationEntity(dto);

        product = productRepository.save(product);
        messagingTemplate.convertAndSend("/topic/socket/product", product.getName());
        return ProductMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        boolean exists = productRepository.findById(product.getId()).isPresent();
        if (exists){
            product =  productRepository.save(product);
            return ProductMapper.toDTO(product);
        }
        return null;
    }

    @Override
    public String exportProductsToXML() throws JAXBException {
        List<Product> products = (List<Product>) productRepository.findAll();
        JAXBContext context = JAXBContext.newInstance(ProductsWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ProductsWrapper wrapper = new ProductsWrapper();
        wrapper.setProducts(products);

        StringWriter writer = new StringWriter();
        marshaller.marshal(wrapper, writer);
        return writer.toString();
    }

    @Override
    public boolean deleteProduct(Long id) {
        commentRepository.deleteAllByProductId(id);
        productRepository.deleteById(id);
        return productRepository.findById(id).isEmpty();

    }
}
