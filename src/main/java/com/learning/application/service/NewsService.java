package com.learning.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class NewsService implements INewsService{

    private final String api_url = "https://newsapi.org/v2/everything?q=%22moroccan%20team%22&from=2022-11-08&sortBy=publishedAt&apiKey=34e43c5b66b740fab5cf70dbde3fb9b5";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public JsonNode getNews() {
        return null;
    }

    @Override
    public JsonNode getAllNews() throws IOException {
        return objectMapper.readTree(new URL(api_url));
    }
}
