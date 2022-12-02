package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.ManagingUsersDto;
import com.junstudio.kickoff.dtos.SearchedUserDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
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
            postNumbers.add((long) findPostsByUserId(user).size());

            commentNumbers.add(
                (long) (findCommentsByUserId(user).size() +
                    findRecommentsByUserId(user).size()));
        }

        return new UsersDto(new ManagingUsersDto(foundUsers, postNumbers, commentNumbers));
    }

    public SearchedUserDto search(String userName) {
         User user = userRepository.findByName(userName)
             .orElseThrow(UserNotFound::new);

         Long postNumber = (long) findPostsByUserId(user).size();
         Long commentNumber = (long) (findCommentsByUserId(user).size() +
             findRecommentsByUserId(user).size());

        return new SearchedUserDto(user, postNumber, commentNumber);
    }

    private List<Post> findPostsByUserId(User user) {
        return postRepository.findAllByUserId(new UserId(user.id()));
    }

    private List<Comment> findCommentsByUserId(User user) {
        return commentRepository.findAllByUserId(user.id());
    }

    private List<Recomment> findRecommentsByUserId(User user) {
        return recommentRepository.findAllByUserId(user.id());
    }
}
