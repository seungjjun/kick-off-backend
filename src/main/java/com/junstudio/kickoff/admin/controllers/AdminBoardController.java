package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.CreateBoardAdminService;
import com.junstudio.kickoff.admin.services.DeleteBoardAdminService;
import com.junstudio.kickoff.dtos.BoardDto;
import com.junstudio.kickoff.exceptions.AlreadyExistingBoardName;
import com.junstudio.kickoff.exceptions.BoardNotFound;
import com.junstudio.kickoff.exceptions.CreateBoardFailed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminBoardController {
    private final CreateBoardAdminService createBoardAdminService;
    private final DeleteBoardAdminService deleteBoardAdminService;

    public AdminBoardController(CreateBoardAdminService createBoardAdminService,
                                DeleteBoardAdminService deleteBoardAdminService) {
        this.createBoardAdminService = createBoardAdminService;
        this.deleteBoardAdminService = deleteBoardAdminService;
    }

    @PostMapping("admin-board")
    @ResponseStatus(HttpStatus.CREATED)
    private String createBoard(
        @RequestBody BoardDto boardDto
    ) {
        createBoardAdminService.create(boardDto.getParentId(), boardDto.getBoardName());

        return "생성이 완료되었습니다.";
    }

    @DeleteMapping("admin-board/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteBoard(
        @PathVariable Long boardId
    ) {
        deleteBoardAdminService.delete(boardId);
    }

    @ExceptionHandler(CreateBoardFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private CreateBoardFailed createBoardFailed() {
        return new CreateBoardFailed();
    }

    @ExceptionHandler(AlreadyExistingBoardName.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private AlreadyExistingBoardName alreadyExistingBoardName() {
        return new AlreadyExistingBoardName();
    }

    @ExceptionHandler(BoardNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BoardNotFound boardNotFound() {
        return new BoardNotFound();
    }
}
