package com.yunboklog.api.service;


import com.yunboklog.api.domain.Post;
import com.yunboklog.api.repository.PostRepository;
import com.yunboklog.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        // PostCreate -> Post

        Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        postRepository.save(post);

    }
}
