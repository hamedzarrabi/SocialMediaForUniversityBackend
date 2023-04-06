package com.hami.service.impl;

import com.hami.entity.Post;
import com.hami.exception.ResourceNotFoundException;
import com.hami.repository.PostRepository;
import com.hami.service.PostService;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired private PostRepository postRepository;
    @Autowired private UserService userService;

    @Override
    public Post createPost(Post post) {

            return postRepository.save(post);

    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll();

        for (int i=0; i< posts.size(); i++) {
            Post postItem = posts.get(i);
            postItem.setEmail(userService.displayUserMetaDate(postItem.getUserId()).getEmail());
        }
        return posts;
    }

    @Override
    public String savePostImage(MultipartFile multipartFile, String fileName) {

        /*
         * MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
         * request; Iterator<String> it = multipartRequest.getFileNames(); MultipartFile
         * multipartFile = multipartRequest.getFile(it.next());
         */

        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get("src/main/resources/images/" + fileName + ".png");
            Files.write(path, bytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Error occured. Photo not saved!");
            return "Error occured. Photo not saved!";
        }
        System.out.println("Photo saved successfully!");
        return "Photo saved successfully!";
    }
}
