package com.example.flatmate.service;

import com.example.flatmate.model.Complaint;
import com.example.flatmate.model.User;
import com.example.flatmate.repository.ComplaintRepository;
import com.example.flatmate.repository.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Corrected: File a complaint and link it to the correct user
    public Complaint fileComplaint(Complaint complaint, String username) {
        Optional<User> user = userRepository.findByUsername(username); // ✅ Fetch user correctly
        if (user.isPresent()) {
            complaint.setUser(user.get());
            return complaintRepository.save(complaint);
        } else {
            throw new RuntimeException("User not found!");
        }
    }

    // ✅ Corrected: Resolve complaint and award karma points
    public Complaint resolveComplaint(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found!"));

        if (complaint.isResolved()) {
            throw new RuntimeException("Complaint is already resolved!");
        }

        complaint.setResolved(true);

        // Award Karma Points to the resolver
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User resolver = user.get();
            resolver.setKarmaPoints(resolver.getKarmaPoints() + 10);
            userRepository.save(resolver);
        }

        return complaintRepository.save(complaint);
    }

    // ✅ Corrected: Voting system to upvote/downvote complaints
    public Complaint voteComplaint(Long complaintId, String username, boolean isUpvote) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found!"));

        // Prevent users from voting multiple times
        if (complaint.getVotedUsers().contains(user.get().getId())) {
            throw new RuntimeException("User has already voted on this complaint!");
        }

        if (isUpvote) {
            complaint.setUpvotes(complaint.getUpvotes() + 1);
        } else {
            complaint.setDownvotes(complaint.getDownvotes() + 1);
        }

        complaint.getVotedUsers().add(user.get().getId());

        // Auto-archive complaints with 5+ downvotes
        if (complaint.getDownvotes() >= 5) {
            complaint.setResolved(true);
        }

        return complaintRepository.save(complaint);
    }

    // ✅ Fetch all complaints
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // ✅ Delete a complaint
    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}
