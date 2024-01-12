package com.example.blog.service;

import com.example.blog.model.Blog;
import com.example.blog.model.dto.BlogDto;
import com.example.blog.repository.BlogRepository;
import com.example.blog.util.exception.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    public BlogService(BlogRepository blogRepository,
                       ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }

    public Long addBlog(BlogDto blogDto) {
        Blog blog = modelMapper.map(blogDto, Blog.class);
        return blogRepository.save(blog).getId();
    }

    public List<BlogDto> findAll() {
        List<Blog> all = blogRepository.findAll();
        return all.stream().map(blog -> modelMapper.map(all, BlogDto.class)).collect(Collectors.toList());
    }

    public BlogDto findById(Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        Blog blog = optionalBlog.orElseThrow(() -> new BusinessException("پیدا نشد"));
        return modelMapper.map(blog, BlogDto.class);
    }

    public void update(BlogDto blogDto, Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        Blog blog = optionalBlog.orElseThrow(() -> new BusinessException("پیدا نشد"));
        blogDto.setId(blog.getId());
        if (!blogDto.getTitle().equals(blog.getTitle()))
            blog.setTitle(blogDto.getTitle());
        if (!blogDto.getContent().equals(blog.getContent()))
            blog.setContent(blogDto.getContent());
        blogRepository.save(blog);
    }

    public void delete(Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        Blog blog = optionalBlog.orElseThrow(() -> new BusinessException("پیدا نشد"));
        blogRepository.delete(blog);
    }
}
