package com.example.flatmate.repository;



import com.example.flatmate.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findTop5ByOrderByKarmaPointsDesc();  // Top users by karma
    List<User> findTop5ByOrderByComplaintsFiledDesc();
    List<User> findTop1ByOrderByKarmaPointsDesc();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.bestFlatmate = false")
    void resetBestFlatmate();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.karmaPoints = 0")
    void resetKarmaPoints();

    List<User> findByBestFlatmateTrue();
}
