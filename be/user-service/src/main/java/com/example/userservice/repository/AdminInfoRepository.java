package com.example.userservice.repository;


import com.example.userservice.model.AdminInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminInfoRepository extends MongoRepository<AdminInfo, String> {
}
