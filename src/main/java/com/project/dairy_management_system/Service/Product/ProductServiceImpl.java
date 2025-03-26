package com.project.dairy_management_system.Service.Product;

import com.project.dairy_management_system.Entity.Product;
import com.project.dairy_management_system.Repository.ProductRepository;
import com.project.dairy_management_system.dto.ProductDto;
import com.project.dairy_management_system.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductDto addProduct(Product product) {
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> fetched = productRepository.findById(id);
        return fetched.map(product -> productMapper.toDto(product)).orElse(null);
    }

    @Override
    public List<ProductDto> addAllProduct(List<Product> productList) {
        return productRepository.saveAll(productList).stream().map(productMapper::toDto).toList();
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }
}
