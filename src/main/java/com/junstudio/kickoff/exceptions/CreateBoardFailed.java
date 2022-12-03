package com.junstudio.kickoff.exceptions;

public class CreateBoardFailed extends RuntimeException {
    public CreateBoardFailed() {
        super("게시판을 생성할 수 없습니다. 다시 한번 확인해주세요.");
    }
}
