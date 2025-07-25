package com.example.userservice.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String role;
    private String walletAddress;

    // PatientInfo fields
    private String medicalHistory;
    private String address;
    private String gender;
    private String phone;
    private String dateOfBirth;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String name;
    private String bloodGroup;

    // Doctor fields
    private String specialization;
    private String licenseNumber;
    private String hospital;

    // Admin fields
    private String department;
}