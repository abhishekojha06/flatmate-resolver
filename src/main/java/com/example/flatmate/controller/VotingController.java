package com.example.flatmate.controller;



import com.example.flatmate.model.Complaint;
import com.example.flatmate.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
public class VotingController {
    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/{id}/upvote")
    public ResponseEntity<Complaint> upvoteComplaint(@PathVariable Long id, @RequestHeader("Username") String username) {
        return ResponseEntity.ok(complaintService.voteComplaint(id, username, true));
    }

    @PostMapping("/{id}/downvote")
    public ResponseEntity<Complaint> downvoteComplaint(@PathVariable Long id, @RequestHeader("Username") String username) {
        return ResponseEntity.ok(complaintService.voteComplaint(id, username, false));
    }
}

