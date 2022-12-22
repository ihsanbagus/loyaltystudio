package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.BenefitDto;
import com.studio.loyalty.entities.BenefitEntity;
import com.studio.loyalty.services.benefit.BenefitService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/benefit")
public class BenefitController {

    @Autowired
    BenefitService benefitService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid BenefitDto benefit) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", benefitService.save(benefit));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", benefitService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid BenefitDto benefit) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", benefitService.update(benefit, benefit.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid BenefitEntity benefit) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", benefitService.delete(benefit.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
