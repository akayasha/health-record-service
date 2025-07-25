package com.example.recordservice.service;

import com.example.recordservice.model.Record;
import com.example.recordservice.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    public Record getRecordById(String id) {
        return recordRepository.findById(id).orElse(null);
    }

    public List<Record> getRecordsByUserId(String userId) {
        return recordRepository.findByUserId(userId);
    }
}