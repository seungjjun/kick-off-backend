package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.AdminDto;
import com.junstudio.kickoff.dtos.ManagingUsersDto;
import com.junstudio.kickoff.dtos.SearchedUserDto;
import com.junstudio.kickoff.dtos.TodaySignupUsersDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.exceptions.AdminNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Admin;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.AdminRepository;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetUserAdminService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;
    private final AdminRepository adminRepository;

    public GetUserAdminService(UserRepository userRepository,
                               PostRepository postRepository,
                               CommentRepository commentRepository,
                               RecommentRepository recommentRepository,
                               AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
        this.adminRepository = adminRepository;
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

    public AdminDto admin(String identification) {
//        adminRepository.existsByIdentification(identification);
        Admin admin = adminRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        if(!admin.identification().equals("jel1y")) {
            throw new AdminNotFound();
        }

        return new AdminDto(admin.identification(), admin.name(), admin.profileImage());
    }

    public TodaySignupUsersDto todaySignupUser() {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        List<User> users = userRepository.findByCreatedAtBetween(startDatetime, endDatetime);
        return new TodaySignupUsersDto(users);
    }

    private List<Post> findPostsByUserId(User user) {
        return postRepository.findAllByUserId_Value(user.id());
    }

    private List<Comment> findCommentsByUserId(User user) {
        return commentRepository.findAllByUserId_Value(user.id());
    }

    private List<Recomment> findRecommentsByUserId(User user) {
        return recommentRepository.findAllByUserId_Value(user.id());
    }

    public void register(String name, String identification, String password) {
        Admin admin = new Admin(name, identification, password);

        adminRepository.save(admin);
    }
}
