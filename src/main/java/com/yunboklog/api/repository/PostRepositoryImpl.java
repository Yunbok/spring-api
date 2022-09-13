package com.yunboklog.api.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yunboklog.api.domain.Post;
import com.yunboklog.api.domain.QPost;
import com.yunboklog.api.request.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.yunboklog.api.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }
}
