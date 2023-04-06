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
    public ResponseEntity<?> createPost(@RequestBody Post post,@RequestParam("image") MultipartFile image, @PathVariable("userId") String userId) {
        String imageAddress = image.getOriginalFilename();
        User user = userService.findUserId(userId);

        if (user != null) {
            SecureRandom random = new SecureRandom();
            post.setImage(imageAddress);
            post.setUserId(user.getUserId());
            savePicture(image);
            Post newPost = postService.createPost(post);
            newPost.setPostId(String.valueOf(random.nextInt(90000) + 1));
            return new ResponseEntity<>(newPost, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("UserId not found", HttpStatus.NOT_FOUND);
        }
    }


    private String savePicture(MultipartFile image) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            Path path = Paths.get("src/main/resources/images/");
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
}
