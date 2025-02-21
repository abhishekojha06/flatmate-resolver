package com.example.flatmate.repository;



import com.example.flatmate.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByResolved(boolean resolved);  // Fetch complaints based on resolution status
}
