package com.project.dairy_management_system.Service.Product;

import com.project.dairy_management_system.Entity.Product;
import com.project.dairy_management_system.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(Product product);
    ProductDto getProductById(Long id);
    List<ProductDto> addAllProduct(List<Product> productList);
    List<ProductDto> getAllProduct();
}
