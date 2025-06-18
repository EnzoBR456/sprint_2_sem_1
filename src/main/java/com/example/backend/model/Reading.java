package com.example.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorId;
    private String nome;           // Novo campo: nome do sensor
    private String status;         // Novo campo: status (ex: "OK")

    @Column(name = "reading_value")
    private Double readingValue;

    private LocalDateTime timestamp;

    // Construtor vazio obrigatório
    public Reading() {}

    // Construtor personalizado
    public Reading(String sensorId, String nome, Double readingValue, String status) {
        this.sensorId = sensorId;
        this.nome = nome;
        this.readingValue = readingValue;
        this.status = (status != null && !status.isEmpty()) ? status : "OK";
        this.timestamp = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(Double readingValue) {
        this.readingValue = readingValue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

