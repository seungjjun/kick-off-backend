package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.TodayWrittenCommentsDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class GetCommentAdminService {
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    public GetCommentAdminService(CommentRepository commentRepository,
                                  RecommentRepository recommentRepository) {
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
    }

    public TodayWrittenCommentsDto todayWrittenComments() {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        List<Comment> comments = commentRepository.findByCommentDateBetween(startDatetime, endDatetime);

        List<Recomment> recomments = recommentRepository.findByCommentDateBetween(startDatetime, endDatetime);

        return new TodayWrittenCommentsDto(comments.size() + recomments.size());
    }
}
