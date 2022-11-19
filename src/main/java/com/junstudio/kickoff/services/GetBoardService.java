package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.BoardDto;
import com.junstudio.kickoff.dtos.BoardsDto;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetBoardService {
    private final BoardRepository boardRepository;

    public GetBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardsDto boards() {
        List<BoardDto> board = boardRepository.findAll()
            .stream().map(Board::toDto)
            .collect(Collectors.toList());

        return new BoardsDto(board);
    }
}
