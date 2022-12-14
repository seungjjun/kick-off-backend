package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.AlreadyAppliedUser;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Applicant;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.models.CreationNumber;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CreateApplicationPostService {
    private final ApplicationPostRepository applicationPostRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    public CreateApplicationPostService(ApplicationPostRepository applicationPostRepository,
                  UserRepository userRepository,
                  PostRepository postRepository,
                  CommentRepository commentRepository,
                  RecommentRepository recommentRepository) {
        this.applicationPostRepository = applicationPostRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
    }

    public void createApplicationPost(Long userId, String grade, String reason) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

       List<ApplicationPost> applicationPosts =
           applicationPostRepository.findAllByApplicant_Name(user.name());

       applicationPosts.forEach(applicationPost -> {
           if(applicationPost.state().equals("processing")) {
               throw new AlreadyAppliedUser();
           }
       });

        Long postNumber = (long) postRepository.findAllByUserId_Value(user.id()).size();

        Long commentNumber = (long) commentRepository.findAllByUserId_Value(user.id()).size();

        Long recommentNumber = (long) recommentRepository.findAllByUserId_Value(user.id()).size();

        ApplicationPost applicationPost =
            new ApplicationPost(
                reason,
                new Applicant(user.name(), user.grade().name(), grade),
                new CreationNumber(postNumber, commentNumber + recommentNumber)
            );

        applicationPostRepository.save(applicationPost);
    }
}
