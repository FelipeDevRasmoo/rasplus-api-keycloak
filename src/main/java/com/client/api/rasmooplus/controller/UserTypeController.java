package com.client.api.rasmooplus.controller;

import com.client.api.rasmooplus.model.jpa.UserType;
import com.client.api.rasmooplus.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-type")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping
    @PreAuthorize(value = "hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<List<UserType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userTypeService.findAll());
    }
}
