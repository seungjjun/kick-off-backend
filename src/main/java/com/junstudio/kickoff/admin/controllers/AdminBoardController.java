package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.CreateBoardAdminService;
import com.junstudio.kickoff.dtos.BoardDto;
import com.junstudio.kickoff.exceptions.AlreadyExistingBoardName;
import com.junstudio.kickoff.exceptions.CreateBoardFailed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminBoardController {
    private final CreateBoardAdminService createBoardAdminService;

    public AdminBoardController(CreateBoardAdminService createBoardAdminService) {
        this.createBoardAdminService = createBoardAdminService;
    }

    @PostMapping("admin-board")
    @ResponseStatus(HttpStatus.CREATED)
    private void createBoard(
        @RequestBody BoardDto boardDto
    ) {
        createBoardAdminService.create(boardDto.getParentId(), boardDto.getBoardName());
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
}
