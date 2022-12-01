package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.ManagingUsersDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetUserAdminService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    public GetUserAdminService(UserRepository userRepository,
                               PostRepository postRepository,
                               CommentRepository commentRepository,
                               RecommentRepository recommentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
    }

    public UsersDto users() {
        List<User> foundUsers = userRepository.findAll();

        List<Long> postNumbers = new ArrayList<>();
        List<Long> commentNumbers = new ArrayList<>();

        for (User user : foundUsers) {
            postNumbers.add((long) postRepository.findAllByUserId(new UserId(user.id())).size());

            commentNumbers.add(
                (long) (commentRepository.findAllByUserId(user.id()).size() +
                    recommentRepository.findAllByUserId(user.id()).size()));
        }

        return new UsersDto(new ManagingUsersDto(foundUsers, postNumbers, commentNumbers));
    }
}
