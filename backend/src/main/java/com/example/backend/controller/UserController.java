package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping({"", "/"})
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping({"", "/"})
    public User update(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PatchMapping("/avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam("avatar") MultipartFile file) throws IOException {
        Path dir = Paths.get("avatars");
        Files.createDirectories(dir);
        Path path = dir.resolve(file.getOriginalFilename());
        Files.write(path, file.getBytes());
        return ResponseEntity.ok().build();
    }
}
