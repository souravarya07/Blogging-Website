package com.blog.project.BloggingWebsite.Service;

import com.blog.project.BloggingWebsite.Repository.PostRepository;
import com.blog.project.BloggingWebsite.dto.PostDto;
import com.blog.project.BloggingWebsite.exception.PostNotFoundException;
import com.blog.project.BloggingWebsite.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDto postDto) {
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User username = authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No user logged in"));
        post.setUserName(username.getUsername());
        post.setUpdatedOn(Date.from(Instant.now()));
        post.setCreatedOn(Date.from(Instant.now()));
        return post;
    }

    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setUsername(post.getUserName());
        return postDto;
    }

    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id" + id));
        return mapFromPostToDto(post);
    }
}
