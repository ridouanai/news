package com.learning.application.dal.repository;

import com.learning.application.dal.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface INewsRepository extends JpaRepository<NewsEntity, Long> {
}
