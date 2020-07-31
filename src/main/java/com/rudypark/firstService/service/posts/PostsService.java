package com.rudypark.firstService.service.posts;

import com.rudypark.firstService.domain.posts.Posts;
import com.rudypark.firstService.domain.posts.PostsRepository;
import com.rudypark.firstService.web.dto.PostsResponseDto;
import com.rudypark.firstService.web.dto.PostsSaveRequestDto;
import com.rudypark.firstService.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("no such user found. id=" + id)
        );
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("no such user found. id=" + id)
        );

        return new PostsResponseDto(entity);
    }
}
