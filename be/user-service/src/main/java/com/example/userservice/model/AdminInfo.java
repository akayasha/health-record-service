package com.example.userservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "admin_info")
public class AdminInfo {
    @Id
    private String id;
    private String userId; // Reference to User
    private String department;
    private String name;
    private String email;
    private String phone;
    private String hospital; // Hospital or clinic where the admin works
    private String address; // Address of the admin's office
    // Add more admin-specific fields
}