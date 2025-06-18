package com.example.backend.controller;

import com.example.backend.model.Reading;
import com.example.backend.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    @Autowired
    private ReadingRepository repository;

    @PostMapping
    public Reading createReading(@RequestBody Reading reading) {
        if (reading.getTimestamp() == null) {
            reading.setTimestamp(LocalDateTime.now());
        }
        return repository.save(reading);
    }

    @GetMapping
    public List<Reading> getAllReadings() {
        return repository.findAll();
    }

    @GetMapping("/sensor/{sensorId}")
    public List<Reading> getBySensorId(@PathVariable String sensorId) {
        return repository.findBySensorId(sensorId);
    }
}
