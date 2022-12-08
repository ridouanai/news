package com.learning.application.dal.repository;

import com.learning.application.dal.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRepository extends JpaRepository<NewsEntity, String> {
}
