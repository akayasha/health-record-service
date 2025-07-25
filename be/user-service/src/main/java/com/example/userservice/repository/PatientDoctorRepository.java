package com.example.userservice.repository;

import com.example.userservice.model.PatientDoctor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientDoctorRepository extends MongoRepository<PatientDoctor, String> {
}
