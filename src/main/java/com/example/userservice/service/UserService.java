package com.example.userservice.service;

import com.example.userservice.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.function.Supplier;

@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserRepository userRepository;
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void followUser(Long userId, Long followedId) {
        User follower = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Cannot find user with id " + userId));
        User followed = userRepository.findById(followedId).orElseThrow(() -> new ResourceNotFoundException("Cannot find user with id " + followedId));

        String followerIds = follower.getFollowings();//关注者的关注列表
        String followedIds = followed.getFans();//被关注者的粉丝列表

        if (followerIds == null) {
            followerIds = "";
        }
        if (followedIds == null) {
            followedIds = "";
        }

        // 关注操作，更新关注者和被关注者的id列表
        String[] followerIdArr = followerIds.split(",");
        String[] followedIdArr = followedIds.split(",");
        List<String> followerIdList = new ArrayList<>(Arrays.asList(followerIdArr));
        List<String> followedIdList = new ArrayList<>(Arrays.asList(followedIdArr));

        if (!followerIdList.contains(String.valueOf(followedId))) {
            if (followerIds.isEmpty()) {
                follower.setFollowings(String.valueOf(followedId));
            } else {
                followerIdList.add(String.valueOf(followedId));
                follower.setFollowings(String.join(",", followerIdList));
            }
            userRepository.save(follower);
        }

        if (!followedIdList.contains(String.valueOf(userId))) {
            if (followedIds.isEmpty()) {
                followed.setFans(String.valueOf(userId));
            } else {
                followedIdList.add(String.valueOf(userId));
                followed.setFans(String.join(",", followedIdList));
            }
            userRepository.save(followed);
        }
    }



}