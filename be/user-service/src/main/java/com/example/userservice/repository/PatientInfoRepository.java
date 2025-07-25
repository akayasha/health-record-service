package com.example.userservice.repository;

import com.example.userservice.model.PatientInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientInfoRepository extends MongoRepository<PatientInfo, String> {
}
