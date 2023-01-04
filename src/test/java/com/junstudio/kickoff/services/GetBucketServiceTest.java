package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class GetBucketServiceTest {
    @Test
    void createBucket() {
        User user = spy(User.fake());

        UserRepository userRepository = mock(UserRepository.class);

        GetBucketService getBucketService = new GetBucketService(userRepository);

        given(userRepository.findByIdentification(any())).willReturn(Optional.of(user));

        Bucket bucket = getBucketService.createBucket(user.identification());

        verify(user).consume();

        assertThat(bucket.getAvailableTokens()).isEqualTo(10L);
    }
}
