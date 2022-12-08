package com.learning.application.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.learning.application.dal.entity.NewsEntity;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JsonToNewsEntityMapper implements NewsEntityMapper {

    @Override
    public List<NewsEntity> CreateNewsEntities(JsonNode newsJsonNode) {

        List<NewsEntity> listOfNews = new ArrayList<>();

        JsonNode articles = newsJsonNode.get("articles");
        if (articles.isArray())
        {
            for (JsonNode jsonNode : articles)
            {
                listOfNews.add(CreateNewsEntity(jsonNode));
            }
        }

        return listOfNews;
    }

    @Override
    public NewsEntity CreateNewsEntity(JsonNode newsJsonNode) {
        return new NewsEntity(
            newsJsonNode.get("source").get("id").toString(), newsJsonNode.get("source").get("name").toString(), newsJsonNode.get("author").toString(), newsJsonNode.get("title").toString(), newsJsonNode.get("description").toString(), newsJsonNode.get("url").toString(), newsJsonNode.get("publishedAt").toString(), newsJsonNode.get("content").toString());
    }
}
