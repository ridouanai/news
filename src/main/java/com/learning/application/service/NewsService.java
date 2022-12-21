package com.learning.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.learning.application.dal.repository.INewsRepository;
import com.learning.application.mapper.NewsEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Service
public class NewsService implements INewsService{

    private final Date xDaysAgo = Date.from( Instant.now().minus( Duration.ofDays( 10 ) ) );
    private final SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy");
    private final String date = sdf.format(xDaysAgo);
    private final String api_url = "https://newsapi.org/v2/everything?from=" + date + "&sortBy=publishedAt&apiKey=34e43c5b66b740fab5cf70dbde3fb9b5&q=";
    private final String default_topic = "%22moroccan%20team%22";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NewsEntityMapper newsEntityMapper;

    @Autowired
    private INewsRepository newsRepository;

    @Override
    public JsonNode getNewsById(long id) {
        var newEntity = newsRepository.findById(id);
        return JsonNodeFactory.instance.pojoNode(newEntity);
    }

    @Override
    public JsonNode getAllNews(){
        try
        {
            JsonNode result = objectMapper.readTree(new URL(api_url + default_topic));
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

    @Override
    public JsonNode deleteNews(long id)
    {
        var result = JsonNodeFactory.instance.objectNode();
        try
        {
            newsRepository.deleteById(id);
            result.put("Done", "The entity was deleted successfully");
        } catch (Exception exception)
        {
            result.put("Error", "The entity was not deleted, please make sure you used its correct id");
        }
        return result;
    }

    @Override
    public JsonNode getNewsByTopic(String title) {
        try
        {
            JsonNode result = objectMapper.readTree(new URL(api_url + title));
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
