package com.learning.application.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.learning.application.dal.entity.NewsEntity;

import java.util.List;

public interface NewsEntityMapper {

    NewsEntity CreateNewsEntity(JsonNode newsJsonNode);
    List<NewsEntity> CreateNewsEntities(JsonNode newsJsonNode);
}
