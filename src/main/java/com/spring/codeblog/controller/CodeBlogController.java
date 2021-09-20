package com.spring.codeblog.controller;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CodeBlogController {

    @Autowired
    CodeBlogService codeBlogService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView modelAndView = new ModelAndView("posts");//Cria a ModelAndView, indica que a view é a posts, no caso posts.html
        List<Post> posts = codeBlogService.findAll();

        modelAndView.addObject("posts", posts);//Retorna a view desejada com model, no caso os atributos da model são acessados através da chave = posts
        return modelAndView;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("postDetails");//Cria a ModelAndView, indica que a view é a postDetails, no caso postDetails.html
        Post post = codeBlogService.findById(id);

        modelAndView.addObject("post", post);//Retorna a view desejada com model, no caso os atributos da model são acessados através da chave = post
        return modelAndView;
    }

    //Método para chamar uma nova tela hmtl
    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm() {
        return "postForm";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios estão preenchidos");
            return "redirect:/newpost";
        }

        post.setData(LocalDate.now());
        codeBlogService.save(post);

        return "redirect:/posts";
    }
}
