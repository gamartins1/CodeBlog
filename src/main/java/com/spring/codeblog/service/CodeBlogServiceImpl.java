package com.spring.codeblog.service;

import java.util.List;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeBlogServiceImpl implements CodeBlogService {

    @Autowired
    CodeBlogRepository codeBlogRepository;

    @Override
    public List <Post> findAll() {
        return codeBlogRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return codeBlogRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return codeBlogRepository.save(post);
    }
}
