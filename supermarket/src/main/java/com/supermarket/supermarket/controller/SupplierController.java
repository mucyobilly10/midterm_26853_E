package com.supermarket.supermarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.supermarket.domain.Supplier;
import com.supermarket.supermarket.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSupplier(@RequestBody Supplier supplier) {

        String response = supplierService.saveSupplier(supplier);

        if (response.equals("Supplier saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSuppliers() {

        List<Supplier> suppliers = supplierService.getAllSuppliers();

        if (suppliers.isEmpty()) {
            return new ResponseEntity<>("No suppliers found.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        }
    }
}