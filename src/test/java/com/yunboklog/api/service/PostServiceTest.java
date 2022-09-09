package com.yunboklog.api.service;

import com.yunboklog.api.domain.Post;
import com.yunboklog.api.repository.PostRepository;
import com.yunboklog.api.request.PostCreate;
import com.yunboklog.api.request.PostSearch;
import com.yunboklog.api.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.*;


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
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("foo" + i)
                        .content("bar" + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        Pageable pageable = PageRequest.of(0, 5, DESC, "id");

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        // when
        List<PostResponse> posts = postService.getList(postSearch);

        assertEquals(10L, posts.size());
        assertEquals("foo30", posts.get(0).getTitle());
        assertEquals("foo26", posts.get(4).getTitle());
    }

    @Test
    @DisplayName("글 1페이지 내림차순 조회")
    void getOnePage() {
        // given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("윤복 제목 - " + i)
                        .content("반포자이 - " + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        // when
        List<PostResponse> posts = postService.getList(postSearch);

        //then
        assertEquals(10L, posts.size());
        assertEquals("윤복 제목 - 30", posts.get(0).getTitle());
        assertEquals("윤복 제목 - 26", posts.get(4).getTitle());

    }
}