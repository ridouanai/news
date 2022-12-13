package com.learning.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.learning.application.dal.repository.INewsRepository;
import com.learning.application.mapper.NewsEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class NewsService implements INewsService{

    private final String api_url = "https://newsapi.org/v2/everything?q=%22moroccan%20team%22&from=12-08-2022&sortBy=publishedAt&apiKey=34e43c5b66b740fab5cf70dbde3fb9b5";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NewsEntityMapper newsEntityMapper;

    @Autowired
    private INewsRepository newsRepository;

    @Override
    public JsonNode getNewsById(String id) {
        var newEntity = newsRepository.findById(id);
        var doesExist = newEntity.isPresent();
        return JsonNodeFactory.instance.pojoNode(newEntity);
    }

    @Override
    public JsonNode getAllNews(){
        try
        {
            JsonNode result = objectMapper.readTree(new URL(api_url));
            var listOfNews = newsEntityMapper.CreateNewsEntities(result);
            newsRepository.saveAll(listOfNews);
            return result;
        }
        catch (IOException exception)
        {
            var result = JsonNodeFactory.instance.objectNode();
            result.put("Error", "The data requested can not be retrieved, please check the log for more information!");
            result.put("Exception message", exception.getMessage());
            return result;
        }
    }
}
