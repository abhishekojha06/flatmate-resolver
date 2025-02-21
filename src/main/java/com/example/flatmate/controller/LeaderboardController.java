package com.example.flatmate.controller;



import com.example.flatmate.model.User;
import com.example.flatmate.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/top-karma")
    public ResponseEntity<List<User>> getTopUsersByKarma() {
        return ResponseEntity.ok(leaderboardService.getTopUsersByKarma());
    }

    @GetMapping("/top-complaints")
    public ResponseEntity<List<User>> getMostComplaintsFiled() {
        return ResponseEntity.ok(leaderboardService.getMostComplaintsFiled());
    }
}
