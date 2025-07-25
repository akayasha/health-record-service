package com.example.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "patient_info")
public class PatientInfo {

    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private String medicalHistory;
    private String address;
    private String gender;
    private String phone;
    private String dateOfBirth;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String name;
    private String bloodGroup;

}
