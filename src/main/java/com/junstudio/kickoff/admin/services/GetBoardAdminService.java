package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.BoardRateDto;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.BoardRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetBoardAdminService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public GetBoardAdminService(BoardRepository boardRepository,
                                PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
    }

    public BoardRateDto rate() {
        AtomicInteger eplBoardPostsNumber = new AtomicInteger();
        AtomicInteger laligaBoardPostsNumber = new AtomicInteger();
        AtomicInteger serieaBoardPostsNumber = new AtomicInteger();
        AtomicInteger bundesligaBoardPostsNumber = new AtomicInteger();

        addLeagueBoardPostsNumber(eplBoardPostsNumber, 2L);
        addLeagueBoardPostsNumber(laligaBoardPostsNumber, 3L);
        addLeagueBoardPostsNumber(serieaBoardPostsNumber, 4L);
        addLeagueBoardPostsNumber(bundesligaBoardPostsNumber, 5L);

        List<Board> eplTeamBoards = addTeamBoardPostsNumber(eplBoardPostsNumber, 2L);
        List<Board> laligaTeamBoards = addTeamBoardPostsNumber(laligaBoardPostsNumber, 3L);
        List<Board> serieaTeamBoards = addTeamBoardPostsNumber(serieaBoardPostsNumber, 4L);
        List<Board> bundesligaTeamBoards = addTeamBoardPostsNumber(bundesligaBoardPostsNumber, 5L);

        addPlayerBoardPostsNumber(eplBoardPostsNumber, eplTeamBoards);
        addPlayerBoardPostsNumber(laligaBoardPostsNumber, laligaTeamBoards);
        addPlayerBoardPostsNumber(serieaBoardPostsNumber, serieaTeamBoards);
        addPlayerBoardPostsNumber(bundesligaBoardPostsNumber, bundesligaTeamBoards);

        return new BoardRateDto(eplBoardPostsNumber, laligaBoardPostsNumber, serieaBoardPostsNumber, bundesligaBoardPostsNumber);
    }

    private void addLeagueBoardPostsNumber(AtomicInteger leagueBoardPostsNumber, Long boardId) {
        List<Post> eplBoardPosts =  postRepository.findAllByBoardId_Value(boardId);
        leagueBoardPostsNumber.addAndGet(eplBoardPosts.size());
    }

    private List<Board> addTeamBoardPostsNumber(AtomicInteger teamBoardPostsNumber, Long boardId) {
        List<Board> teamBoards = boardRepository.findAllByParentId_value(boardId);

        teamBoardPostsNumber.addAndGet(teamBoards.stream()
            .filter(eplTeamBoard -> !eplTeamBoard.isDeleted())
            .collect(Collectors.toList())
            .stream()
            .mapToInt(eplTeamBoard -> postRepository.findAllByBoardId_Value(eplTeamBoard.id())
                .size()).sum());
        return teamBoards;
    }

    private void addPlayerBoardPostsNumber(AtomicInteger playerBoardPostsNumber, List<Board> teamBoards) {
        teamBoards.forEach(teamBoard -> {
            boardRepository.findAllByParentId_value(teamBoard.id())
                .stream()
                .filter(eplPlayerBoard -> !eplPlayerBoard.isDeleted())
                .forEach(eplPlayerBoard -> {
                    playerBoardPostsNumber.addAndGet(postRepository.findAllByBoardId_Value(eplPlayerBoard.id()).size());
                });
        });
    }
}
