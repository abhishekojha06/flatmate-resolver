package com.example.flatmate.controller;



import com.example.flatmate.model.User;
import com.example.flatmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/best-flatmate")
public class BestFlatmateController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getBestFlatmate() {
        return ResponseEntity.ok(userRepository.findByBestFlatmateTrue());
    }
}
