package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.dtos.RecommentsDto;
import com.junstudio.kickoff.dtos.SelectedRecommentDto;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.services.CreateRecommentService;
import com.junstudio.kickoff.services.DeleteRecommentService;
import com.junstudio.kickoff.services.GetRecommentService;
import com.junstudio.kickoff.services.PatchRecommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class RecommentController {
    private final GetRecommentService getRecommentService;
    private final CreateRecommentService createRecommentService;
    private final DeleteRecommentService deleteRecommentService;
    private final PatchRecommentService patchRecommentService;

    public RecommentController(GetRecommentService getRecommentService,
                               CreateRecommentService createRecommentService,
                               DeleteRecommentService deleteRecommentService,
                               PatchRecommentService patchRecommentService) {
        this.getRecommentService = getRecommentService;
        this.createRecommentService = createRecommentService;
        this.deleteRecommentService = deleteRecommentService;
        this.patchRecommentService = patchRecommentService;
    }

    @GetMapping("/recomments")
    private RecommentsDto recomments() {
        List<ReCommentDto> recomments = getRecommentService.recomments()
            .stream().map(Recomment::toDto)
            .collect(Collectors.toList());

        return new RecommentsDto(recomments);
    }

    @GetMapping("/posts/{postId}/recomments")
    private RecommentsDto recomment(
        @PathVariable Long postId
    ) {
        List<ReCommentDto> recomments = getRecommentService.findRecomment(postId)
            .stream().map(Recomment::toDto)
            .collect(Collectors.toList());

        return new RecommentsDto(recomments);
    }

    @PostMapping("/recomments")
    @ResponseStatus(HttpStatus.CREATED)
    private String recomment(
        @RequestBody ReCommentDto reCommentDto
    ) {
        createRecommentService.createRecomment(
            reCommentDto.getContent(),
            reCommentDto.getCommentId(),
            reCommentDto.getUserId(),
            reCommentDto.getPostId()
        );

        return "ok";
    }

    @PatchMapping("/recomments/{recommentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void patchRecomment(
        @RequestBody ReCommentDto reCommentDto,
        @PathVariable Long recommentId
    ) {
        patchRecommentService.patch(recommentId, reCommentDto.getContent());
    }

    @DeleteMapping("/recomments/{recommentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteRecomment(@PathVariable Long recommentId) {
        deleteRecommentService.delete(recommentId);
    }

    @DeleteMapping("/recomments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void  deleteRecomments(
        @RequestBody SelectedRecommentDto selectedRecommentDto
    ) {
        deleteRecommentService.deleteRecomments(selectedRecommentDto.getRecommentId());
    }
}
