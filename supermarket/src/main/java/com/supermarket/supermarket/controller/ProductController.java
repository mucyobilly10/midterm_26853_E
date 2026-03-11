package com.supermarket.supermarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.supermarket.domain.Product;
import com.supermarket.supermarket.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProduct(
            @RequestBody Product product,
            @RequestParam String categoryId,
            @RequestParam(required = false) List<String> supplierIds) {

        String response = productService.saveProduct(product, categoryId, supplierIds);

        if (response.equals("Product saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            return new ResponseEntity<>("No products found.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/paginated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        Page<Product> products = productService.getProductsWithPagination(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/sorted", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsWithSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        Page<Product> products = productService.getProductsWithSorting(page, size, sortBy);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/by-category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsByCategory(@RequestParam String categoryId) {

        List<Product> products = productService.getProductsByCategory(categoryId);

        if (products.isEmpty()) {
            return new ResponseEntity<>("No products found for this category.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
}