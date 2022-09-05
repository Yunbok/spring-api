package com.yunboklog.api.response;


import com.yunboklog.api.domain.Post;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 11));
        this.content = content;
    }

}
