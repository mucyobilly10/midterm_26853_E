package com.supermarket.supermarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.supermarket.domain.Supplier;
import com.supermarket.supermarket.repository.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public String saveSupplier(Supplier supplier) {

        Boolean supplierExists = supplierRepository.existsByContactEmail(supplier.getContactEmail());
        if (supplierExists) {
            return "Supplier with this email already exists.";
        }

        supplierRepository.save(supplier);
        return "Supplier saved successfully.";
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}