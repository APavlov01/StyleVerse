package com.styleverse.service;

import com.styleverse.dto.ProductDTO;
import com.styleverse.entity.Product;
import com.styleverse.mapper.ProductMapper;
import com.styleverse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .orElse(null);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.INSTANCE.productToProductDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (productRepository.existsById(id)) {
            Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            return ProductMapper.INSTANCE.productToProductDTO(updatedProduct);
        } else {
            return null; // product with the given id not exist
        }
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false; // product with the given id not exist
        }
    }
}
