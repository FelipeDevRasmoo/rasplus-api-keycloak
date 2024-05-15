package com.client.api.rasmooplus.controller;

import com.client.api.rasmooplus.dto.UserDto;
import com.client.api.rasmooplus.dto.oauth.UserRepresentationDto;
import com.client.api.rasmooplus.model.jpa.User;
import com.client.api.rasmooplus.service.UserDetailsService;
import com.client.api.rasmooplus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    @PreAuthorize(value = "hasAnyAuthority('CLIENT_READ_WRITE','ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<User> create(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @PatchMapping(value = "/{id}/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasAnyAuthority('USER_READ','USER_WRITE','ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<User> uploadPhoto(@PathVariable("id") Long id, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.uploadPhoto(id, file));
    }


    @GetMapping(value = "/{id}/photo", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @PreAuthorize(value = "hasAnyAuthority('USER_READ','USER_WRITE','ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.downloadPhoto(id));
    }


    @PostMapping("/credentials")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<User> createAuthUser(@Valid @RequestBody UserRepresentationDto dto) {
        userDetailsService.createAuthUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/credentials/{email}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<User> updateAuthUser(@Valid @RequestBody UserRepresentationDto dto, @PathVariable("email") String email) {
        userDetailsService.updateAuthUser(dto, email);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
