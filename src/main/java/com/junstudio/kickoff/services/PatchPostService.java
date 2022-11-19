package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.PostWriteDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PatchPostService {
    private final PostRepository postRepository;

    public PatchPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void patch(PostWriteDto postWriteDto, Long postId) {
        Post post = postRepository.getReferenceById(postId);

        post.patch(postWriteDto.getTitle(),
            postWriteDto.getContent(),
            postWriteDto.getBoardId(),
            postWriteDto.getImageUrl());
    }
}
