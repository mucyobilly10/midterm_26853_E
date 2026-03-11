package com.supermarket.supermarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.supermarket.domain.Category;
import com.supermarket.supermarket.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String saveCategory(Category category) {

        Boolean categoryExists = categoryRepository.existsByName(category.getName());
        if (categoryExists) {
            return "Category with this name already exists.";
        }

        categoryRepository.save(category);
        return "Category saved successfully.";
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}