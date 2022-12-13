package com.learning.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.learning.application.dal.entity.NewsEntity;

import java.io.IOException;
import java.net.MalformedURLException;

public interface INewsService {
    JsonNode getNewsById(String id);

    JsonNode getAllNews() throws IOException;
}
