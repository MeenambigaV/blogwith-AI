package com.example.topic;

// BlogController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String getTopicForm(Model model) {
        model.addAttribute("topic", new Topic());
        return "topic-form";
    }

    @PostMapping("/generate-blog")
   public String generateBlog(@RequestBody Topic topic, Model model) {
        Blog blog = blogService.generateBlog(topic.getTopicName());
        model.addAttribute("blog", blog);
        return "blog";
    }
}