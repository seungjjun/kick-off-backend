package com.junstudio.kickoff.dtos;

import java.util.concurrent.atomic.AtomicInteger;

public class BoardRateDto {
    private final AtomicInteger eplBoardPostsNumber;
    private final AtomicInteger laligaBoardPostsNumber;
    private final AtomicInteger serieaBoardPostsNumber;
    private final AtomicInteger bundesligaBoardPostsNumber;

    public BoardRateDto(AtomicInteger eplBoardPostsNumber,
                        AtomicInteger laligaBoardPostsNumber,
                        AtomicInteger serieaBoardPostsNumber,
                        AtomicInteger bundesligaBoardPostsNumber) {

        this.eplBoardPostsNumber = eplBoardPostsNumber;
        this.laligaBoardPostsNumber = laligaBoardPostsNumber;
        this.serieaBoardPostsNumber = serieaBoardPostsNumber;
        this.bundesligaBoardPostsNumber = bundesligaBoardPostsNumber;
    }

    public AtomicInteger getEplBoardValue() {
        return eplBoardPostsNumber;
    }

    public AtomicInteger getLaligaBoardValue() {
        return laligaBoardPostsNumber;
    }

    public AtomicInteger getSerieaBoardValue() {
        return serieaBoardPostsNumber;
    }

    public AtomicInteger getBundesligaBoardValue() {
        return bundesligaBoardPostsNumber;
    }
}
