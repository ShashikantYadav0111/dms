package com.project.dairy_management_system.Controller;

import com.project.dairy_management_system.Entity.Product;
import com.project.dairy_management_system.Service.Product.ProductService;
import com.project.dairy_management_system.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<ProductDto>> addProduct(@RequestBody List<Product> productList){
        return new ResponseEntity<>(productService.addAllProduct(productList), HttpStatus.CREATED);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getProductList(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }
}
