package com.example.boardservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/members/save")
    public String membersSave() {
        return "members-save";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
