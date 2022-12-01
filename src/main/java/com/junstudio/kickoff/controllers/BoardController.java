package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.BoardDto;
import com.junstudio.kickoff.dtos.BoardsDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.services.GetBoardService;
import com.junstudio.kickoff.services.GetPostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    private final GetPostService getPostService;
    private final GetBoardService getBoardService;

    public BoardController(GetPostService getPostService,
                           GetBoardService getBoardService) {
        this.getPostService = getPostService;
        this.getBoardService = getBoardService;
    }

    @GetMapping("/boards")
    private BoardsDto board() {
        return getBoardService.boards();
    }

    @GetMapping("/board/{boardId}/posts")
    private PostsDto posts(
        @PathVariable Long boardId,
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return getPostService.posts(boardId, pageable);
    }

//    /boards/{boardId}/posts/search
    @GetMapping("/posts/{boardId}/search")
    private PostsDto searchedPosts(
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
        @PathVariable Long boardId,
        String keyword,
        String keywordType
    ) {
        return getPostService.search(boardId, keyword, keywordType, pageable);
    }

    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    private String createBoard(
        @RequestBody BoardDto boardDto,
        @RequestAttribute("identification") String identification
    ) {
        getPostService.createPost(boardDto.getBoardName(), identification);

        return "OK";
    }
}
