package com.learning.application.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.learning.application.dal.entity.NewsEntity;
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
                Optional<NewsEntity> optionalNewsEntity = CreateNewsEntity(jsonNode);
                if (optionalNewsEntity.isPresent())
                {
                    listOfNews.add(optionalNewsEntity.get());
                }
            }
        }
        return listOfNews;
    }

    @Override
    public Optional<NewsEntity> CreateNewsEntity(JsonNode newsJsonNode) {
        if (newsJsonNode.get("source").get("id").toString().equals("null"))
            return Optional.empty();
        String content = newsJsonNode.get("content").toString();
        String description = newsJsonNode.get("description").toString();
        return Optional.of(new NewsEntity(
                newsJsonNode.get("source").get("id").toString(), newsJsonNode.get("source").get("name").toString(), newsJsonNode.get("author").toString(), newsJsonNode.get("title").toString(), (description.length() <= 255) ? description : description.substring(0, 255), newsJsonNode.get("url").toString(), newsJsonNode.get("publishedAt").toString(), (content.length() <= 200) ? content : content.substring(0, 200)));
    }
}
