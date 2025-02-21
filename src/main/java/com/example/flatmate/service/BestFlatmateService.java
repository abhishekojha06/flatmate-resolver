package com.example.flatmate.service;



import com.example.flatmate.model.User;
import com.example.flatmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BestFlatmateService {
    @Autowired
    private UserRepository userRepository;

    // ✅ Runs on the 1st of every month at 12:00 AM
    @Scheduled(cron = "0 0 0 1 * ?")
    public void assignBestFlatmate() {
        List<User> topUsers = userRepository.findTop1ByOrderByKarmaPointsDesc();

        if (!topUsers.isEmpty()) {
            // Reset all users' badges
            userRepository.resetBestFlatmate();

            // Award best flatmate badge to the top user
            User bestUser = topUsers.get(0);
            bestUser.setBestFlatmate(true);
            userRepository.save(bestUser);
            System.out.println("🎉 " + bestUser.getUsername() + " is the Best Flatmate of the Month!");
        }

        // Reset karma points for all users at the start of the month
        userRepository.resetKarmaPoints();
    }
}
