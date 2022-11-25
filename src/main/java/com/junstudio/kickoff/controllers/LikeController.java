package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.LikeDto;
import com.junstudio.kickoff.dtos.LikesDto;
import com.junstudio.kickoff.dtos.SelectedLikeDto;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.services.CreateLikeService;
import com.junstudio.kickoff.services.DeleteLikeService;
import com.junstudio.kickoff.services.GetLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class LikeController {
    private final GetLikeService getLikeService;
    private final CreateLikeService createLikeService;
    private final DeleteLikeService deleteLikeService;

    public LikeController(CreateLikeService createLikeService,
                          GetLikeService getLikeService,
                          DeleteLikeService deleteLikeService) {
        this.createLikeService = createLikeService;
        this.getLikeService = getLikeService;
        this.deleteLikeService = deleteLikeService;
    }

    @GetMapping("/likes")
    public LikesDto likes() {
        List<LikeDto> likes = getLikeService.likes()
            .stream().map(Like::toDto)
            .collect(Collectors.toList());

        return new LikesDto(likes);
    }

    @PostMapping("/like")
    @ResponseStatus(HttpStatus.CREATED)
    public String like(
        @RequestBody LikeDto likeDto
    ) {
        createLikeService.countLike(likeDto.getPostId(), likeDto.getUserId());
        return "ok";
    }

    @DeleteMapping("/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void selectedLikeDto(
        @RequestBody SelectedLikeDto selectedLikeDto
    ) {
        deleteLikeService.deleteLike(selectedLikeDto.getLikeId());
    }
}
