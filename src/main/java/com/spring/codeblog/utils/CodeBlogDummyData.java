package com.spring.codeblog.utils;

import javax.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeBlogDummyData {

    @Autowired
    CodeBlogRepository codeBlogRepository;

//    @PostConstruct//Serve para rodar algo ao iniciar a aplicação, nesse caso popular o banco
    public void savePosts() {
        List <Post> postList = new ArrayList <>();
        Post post1 = new Post();
        post1.setAutor("Gabriel");
        post1.setData(LocalDate.now());
        post1.setTitulo("Docker");
        post1.setTexto("Texto do artigo do blog");
        postList.add(post1);

        Post post2 = new Post();
        post2.setAutor("Gabriel M");
        post2.setData(LocalDate.now());
        post2.setTitulo("TimeMachine");
        post2.setTexto("Texto do artigo do blog sobre TimeMachine");
        postList.add(post2);

        for(Post post : postList) {
            Post postSaved = codeBlogRepository.save(post);
            System.out.println("Post salvo. ID: " + postSaved.getId());
        }
    }
}
