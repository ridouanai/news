package com.learning.application.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.learning.application.dal.entity.NewsEntity;

import java.util.List;
import java.util.Optional;

public interface NewsEntityMapper {

    Optional<NewsEntity> CreateNewsEntity(JsonNode newsJsonNode);
    List<NewsEntity> CreateNewsEntities(JsonNode newsJsonNode);
}
