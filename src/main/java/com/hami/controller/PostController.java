package com.hami.controller;

import com.hami.entity.Post;
import com.hami.entity.User;
import com.hami.service.PostService;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired private PostService postService;
    @Autowired private UserService userService;

    @PostMapping(value = "/createPost/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(@RequestParam("image") MultipartFile image, @RequestParam("title") String title, @RequestParam("description") String description, @PathVariable("userId") String userId) {

        User user = userService.findUserId(userId);

        String imageAddress = "images/Posts/" + user.getEmail(); // + "/" + imageProfile.getOriginalFilename();
        String originalAddressImage = "I:/Project/Instagram/frontend/public/images/Posts/" + user.getEmail();
        Post post = new Post();
        if (user != null) {
            SecureRandom random = new SecureRandom();
            post.setImage(imageAddress + "/" + image.getOriginalFilename());
            post.setTitle(title);
            post.setTextPost(description);
            post.setUserId(user.getUserId());
            post.setPostId(String.valueOf(random.nextInt(90000) + 1));


            savePicture(image, originalAddressImage);
            Post newPost = postService.createPost(post);
            return new ResponseEntity<>(newPost, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("UserId not found", HttpStatus.NOT_FOUND);
        }
    }

    private String savePicture(MultipartFile image, String address) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            Path path = Paths.get(address);
            Files.createDirectories(path);
            Files.copy(image.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return "Success image save";
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Post>> findAllPosts() {

        List<Post> posts = postService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/count/{userId}")
    public ResponseEntity<?> countPosts(@PathVariable("userId") String userId) {
        Long post = postService.countPosts(userId);
        System.out.println(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") String postId) {
        System.out.println(postId);
        postService.deletePost(postId);
        return new ResponseEntity<>("Delete post Successfully!", HttpStatus.OK);
    }
}
