package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.dtos.RecommentsDto;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.services.CreateRecommentService;
import com.junstudio.kickoff.services.GetRecommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecommentController {
    private final GetRecommentService getRecommentService;
    private final CreateRecommentService createRecommentService;

    public RecommentController(GetRecommentService getRecommentService,
                               CreateRecommentService createRecommentService) {
        this.getRecommentService = getRecommentService;
        this.createRecommentService = createRecommentService;
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

    @PostMapping("/recomment")
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
}

