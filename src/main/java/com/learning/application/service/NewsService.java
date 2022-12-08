package com.learning.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.application.dal.entity.NewsEntity;
import com.learning.application.dal.repository.INewsRepository;
import com.learning.application.mapper.NewsEntityMapper;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class NewsService implements INewsService{

    private final String api_url = "https://newsapi.org/v2/everything?q=%22moroccan%20team%22&from=2022-11-08&sortBy=publishedAt&apiKey=34e43c5b66b740fab5cf70dbde3fb9b5";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NewsEntityMapper newsEntityMapper;

    @Autowired
    private INewsRepository newsRepository;

    @Override
    public JsonNode getNews() {
        throw new NotYetImplementedException();
    }

    @Override
    public JsonNode getAllNews() throws IOException {
        JsonNode result = objectMapper.readTree(new URL(api_url));
        var listOfNews = newsEntityMapper.CreateNewsEntities(result);
        newsRepository.saveAll(listOfNews);
        return result;
    }
}
