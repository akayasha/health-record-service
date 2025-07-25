package com.example.userservice.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        createCollectionIfNotExists("users");
        createCollectionIfNotExists("patient_info");
        createCollectionIfNotExists("patient_doctor");
        createCollectionIfNotExists("admin_info");
    }

    private void createCollectionIfNotExists(String collectionName) {
        if (!mongoTemplate.collectionExists(collectionName)) {
            mongoTemplate.createCollection(collectionName);
        }
    }

}