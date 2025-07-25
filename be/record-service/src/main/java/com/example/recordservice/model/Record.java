package com.example.recordservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "records")
public class Record {
    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private String doctorId;
    private String dataHash;
    private String walletAddress;
    private String blockchainTxId;
    private boolean approvedByPatient;
    private String timestamp;
}