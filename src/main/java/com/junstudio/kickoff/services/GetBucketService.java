package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.BucketNotEnough;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;

@Service
@Transactional
public class GetBucketService {
    private final UserRepository userRepository;

    public GetBucketService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Bucket createBucket(String identification) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        if(user.bucket() == 0) {
            throw new BucketNotEnough();
        }

        Bandwidth limit = Bandwidth.simple(user.bucket(), Duration.ofSeconds(1));

        user.consume();

        return Bucket4j.builder().addLimit(limit).build();
    }
}
