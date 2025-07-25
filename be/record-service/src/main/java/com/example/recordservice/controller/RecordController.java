package com.example.recordservice.controller;

import com.example.recordservice.model.Record;
import com.example.recordservice.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Record record) {
        return ResponseEntity.ok(recordService.createRecord(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecord(@PathVariable String id) {
        return ResponseEntity.ok(recordService.getRecordById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Record>> getRecordsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(recordService.getRecordsByUserId(userId));
    }
}