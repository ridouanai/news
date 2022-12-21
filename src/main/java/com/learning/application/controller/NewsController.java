package com.learning.application.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.learning.application.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NewsController {

    @Autowired
    private NewsService service;

    @GetMapping("/news")
    JsonNode getAllNews() {
        return service.getAllNews();
    }

    @GetMapping("/news/{id}")
    JsonNode getNews(@PathVariable long id) {
        return service.getNewsById(id);
    }

    @GetMapping("/news/delete/{id}")
    JsonNode deleteNews(@PathVariable long id) {
        return service.deleteNews(id);
    }

    @GetMapping("news/topic={title}")
    JsonNode getNewsByTopic(@PathVariable String title)
    {
        return service.getNewsByTopic(title);
    }
}
