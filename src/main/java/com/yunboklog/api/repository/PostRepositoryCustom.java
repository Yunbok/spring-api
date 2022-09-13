package com.yunboklog.api.repository;

import com.yunboklog.api.domain.Post;
import com.yunboklog.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
