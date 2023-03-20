package com.hami.service;


import com.hami.entity.Like;

import java.util.List;

public interface LikeService {

    public Like createLike(long postId, Like like);

    public List<Like> getLikeByPostId(Long likeId);

    public Like getLikeById(Long postId, Long likeId);

    public void removeLikeById(Long likeId);
}
