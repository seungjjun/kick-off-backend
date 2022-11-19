package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.BoardNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.BoardRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreatePostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public CreatePostService(PostRepository postRepository,
                             UserRepository userRepository,
                             BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    public Post write(String title, String content,
                      String imageUrl, Long userId, Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        Board board = boardRepository.findById(categoryId).orElseThrow(BoardNotFound::new);

        Post post = new Post(new PostInformation(title, content), 0L, imageUrl, user.id(), board.id());

        postRepository.save(post);
        return post;
    }
}
