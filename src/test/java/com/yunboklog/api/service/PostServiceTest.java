package com.yunboklog.api.service;

import com.yunboklog.api.domain.Post;
import com.yunboklog.api.repository.PostRepository;
import com.yunboklog.api.request.PostCreate;
import com.yunboklog.api.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clear() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void writePost() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(postCreate);

        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void getPost() {
        // given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        Long postId = 1L;

        // when
        PostResponse post = postService.get(requestPost.getId());

        // then
        assertNotNull(post);
        assertEquals("foo", post.getTitle());
        assertEquals("bar", post.getContent());

    }

    @Test
    @DisplayName("글 여러개 조회")
    void getList() {
        // given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        Post requestPost2 = Post.builder()
                .title("foo2")
                .content("bar2")
                .build();
        postRepository.save(requestPost2);

        // when
        List<PostResponse> posts = postService.getList();

        assertEquals(2L, posts.size());

    }
}