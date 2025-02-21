package com.example.flatmate.service;


import com.example.flatmate.model.User;
import com.example.flatmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getTopUsersByKarma() {
        return userRepository.findTop5ByOrderByKarmaPointsDesc();
    }

    public List<User> getMostComplaintsFiled() {
        return userRepository.findTop5ByOrderByComplaintsFiledDesc();
    }
}
