package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.BoardsDto;
import com.junstudio.kickoff.dtos.HotPostsDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.services.GetBoardService;
import com.junstudio.kickoff.services.GetPostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/boards/{boardId}/posts")
    private PostsDto posts(
        @PathVariable Long boardId,
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return getPostService.posts(boardId, pageable);
    }

    @GetMapping("/boards/posts/hot")
    private HotPostsDto hotPosts() {
        return getPostService.hotPosts();
    }

    @GetMapping("/boards/{boardId}/posts/search")
    private PostsDto searchedPosts(
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
        @PathVariable Long boardId,
        String keyword,
        String keywordType
    ) {
        return getPostService.search(boardId, keyword, keywordType, pageable);
    }
}
