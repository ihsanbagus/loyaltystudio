package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.RankDto;
import com.studio.loyalty.entities.RankEntity;
import com.studio.loyalty.services.rank.RankService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    RankService rankService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid RankDto user) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", rankService.save(user));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", rankService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid RankDto user) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", rankService.update(user, user.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid RankEntity user) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", rankService.delete(user.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
