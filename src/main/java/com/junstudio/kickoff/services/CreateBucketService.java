package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import com.junstudio.kickoff.utils.BucketUtil;
import io.github.bucket4j.Bucket4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@EnableScheduling
public class CreateBucketService {
    private final UserRepository userRepository;

    public CreateBucketService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void createBucket() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            BucketUtil bucketUtil = BucketUtil.bucketLimit(user.grade().name());

            user.saveBucket(Bucket4j.builder().addLimit(bucketUtil.limit()).build().getAvailableTokens());
        });
    }
}
