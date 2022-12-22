package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.ProductDto;
import com.studio.loyalty.entities.ProductEntity;
import com.studio.loyalty.services.product.ProductService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProductDto product) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", productService.save(product));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", productService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ProductDto product) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", productService.update(product, product.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid ProductEntity product) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", productService.delete(product.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
