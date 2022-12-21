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
        String content = newsJsonNode.get("content").asText();
        String description = newsJsonNode.get("description").asText();

        var newsEntity = new NewsEntity();
        newsEntity.setName(newsJsonNode.get("source").get("name").asText());
        newsEntity.setAuthor(newsJsonNode.get("author").asText());
        newsEntity.setTitle(newsJsonNode.get("title").asText());
        newsEntity.setDescription((description.length() <= 255) ? description : description.substring(0, 255));
        newsEntity.setUrl(newsJsonNode.get("url").asText());
        newsEntity.setPublishedAt(newsJsonNode.get("publishedAt").asText());
        newsEntity.setContent((content.length() <= 200) ? content : content.substring(0, 200));

        return Optional.of(newsEntity);
    }
}
