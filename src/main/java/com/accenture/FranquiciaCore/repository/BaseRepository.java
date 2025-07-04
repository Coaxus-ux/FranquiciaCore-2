package com.accenture.FranquiciaCore.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends ReactiveMongoRepository<T, ID> {
} 
