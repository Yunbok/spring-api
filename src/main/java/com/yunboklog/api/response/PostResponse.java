package com.yunboklog.api.response;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;

    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, 10);
        this.content = content;
    }






}
