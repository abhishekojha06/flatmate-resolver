package com.example.flatmate.controller;



import com.example.flatmate.model.Complaint;
import com.example.flatmate.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<Complaint> fileComplaint(@RequestBody Complaint complaint, @RequestHeader("Username") String username) {
        return ResponseEntity.ok(complaintService.fileComplaint(complaint, username));
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<Complaint> resolveComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.resolveComplaint(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.ok("Complaint deleted successfully!");
    }
}

