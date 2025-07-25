package com.example.userservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "patient_doctor")
public class PatientDoctor {
    @Id
    private String id = UUID.randomUUID().toString();
    private String userId; // Reference to User
    private String specialization;
    private String licenseNumber;
    private String name;
    private String email;
    private String phone;
    private String hospital; // Hospital or clinic where the doctor practices
    private String address; // Address of the doctor's practice
    private String gender;

    // Add more doctor-specific fields
}