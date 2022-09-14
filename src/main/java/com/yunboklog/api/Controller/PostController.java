package com.yunboklog.api.Controller;

import com.yunboklog.api.domain.Post;
import com.yunboklog.api.domain.PostEditor;
import com.yunboklog.api.request.PostCreate;
import com.yunboklog.api.request.PostEdit;
import com.yunboklog.api.request.PostSearch;
import com.yunboklog.api.response.PostResponse;
import com.yunboklog.api.service.PostService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        postService.write(request);
    }


    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long postId) {
        PostResponse postResponse = postService.get(postId);
        return postResponse;
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }


    @PatchMapping("/posts/{postId}")
    public void patchPost(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
