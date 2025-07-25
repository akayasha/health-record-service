package com.example.recordservice.repository;

import com.example.recordservice.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecordRepository extends MongoRepository<Record, String> {
    List<Record> findByUserId(String userId);
}