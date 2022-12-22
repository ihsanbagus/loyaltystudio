package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.UserDto;
import com.studio.loyalty.entities.UserEntity;
import com.studio.loyalty.services.user.UserService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserDto user) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", userService.save(user));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", userService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", userService.getOne(id));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid UserDto user) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", userService.update(user, user.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid UserEntity user) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", userService.delete(user.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
