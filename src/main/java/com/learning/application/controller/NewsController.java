package com.learning.application.controller;


import com.fasterxml.jackson.databind.JsonNode;
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
    JsonNode getAllNews() throws IOException {
        return service.getAllNews();
    }

    @GetMapping("/news/{id}")
    JsonNode getNews(@PathVariable String id) {
        return service.getNews();
    }

}
