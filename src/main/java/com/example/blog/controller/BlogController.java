package com.example.blog.controller;

import com.example.blog.model.dto.BlogDto;
import com.example.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(path = "/blogs")
    public ResponseEntity<Long> saveBlog(@RequestBody BlogDto request) {
        Long blogId = blogService.addBlog(request);
        return new ResponseEntity<>(blogId, HttpStatus.CREATED);
    }

    @PutMapping(path = "/blogs/{id}")
    public ResponseEntity<Void> updateBlogs(@PathVariable Long id,
                                            @RequestBody BlogDto request) {
        blogService.update(request, id);
        return ResponseEntity.noContent().build();
    }
}
