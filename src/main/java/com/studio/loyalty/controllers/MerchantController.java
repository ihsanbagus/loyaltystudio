package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.MerchantDto;
import com.studio.loyalty.entities.MerchantEntity;
import com.studio.loyalty.services.merchant.MerchantService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid MerchantDto merchant) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", merchantService.save(merchant));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", merchantService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid MerchantDto merchant) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", merchantService.update(merchant, merchant.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid MerchantEntity merchant) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", merchantService.delete(merchant.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
