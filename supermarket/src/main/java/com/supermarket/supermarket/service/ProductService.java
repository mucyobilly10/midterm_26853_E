package com.supermarket.supermarket.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.supermarket.supermarket.domain.Category;
import com.supermarket.supermarket.domain.Product;
import com.supermarket.supermarket.domain.Supplier;
import com.supermarket.supermarket.repository.CategoryRepository;
import com.supermarket.supermarket.repository.ProductRepository;
import com.supermarket.supermarket.repository.SupplierRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public String saveProduct(Product product, String categoryId, List<String> supplierIds) {

        Boolean productExists = productRepository.existsByName(product.getName());
        if (productExists) {
            return "Product with this name already exists.";
        }

        Category category = categoryRepository.findById(UUID.fromString(categoryId)).orElse(null);
        if (category == null) {
            return "Category not found.";
        }
        product.setCategory(category);

        if (supplierIds != null && !supplierIds.isEmpty()) {
            List<Supplier> suppliers = supplierRepository.findAllById(
                supplierIds.stream().map(UUID::fromString).toList()
            );
            product.setSuppliers(suppliers);
        }

        productRepository.save(product);
        return "Product saved successfully.";
    }

    public Page<Product> getProductsWithPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepository.findAll(pageRequest);
    }

    public Page<Product> getProductsWithSorting(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return productRepository.findAll(pageRequest);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String categoryId) {
        return productRepository.findByCategory_Id(UUID.fromString(categoryId));
    }
}