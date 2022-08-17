package com.blog.project.BloggingWebsite.Repository;

import com.blog.project.BloggingWebsite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
