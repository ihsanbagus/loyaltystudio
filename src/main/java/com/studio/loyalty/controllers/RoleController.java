package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.RoleDto;
import com.studio.loyalty.entities.RoleEntity;
import com.studio.loyalty.services.role.RoleService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid RoleDto dto) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", roleService.save(dto));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", roleService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid RoleDto role) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", roleService.update(role, role.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid RoleEntity role) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", roleService.delete(role.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
