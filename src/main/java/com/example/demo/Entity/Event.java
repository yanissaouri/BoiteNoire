package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "Events")
public class Event {
    @Id
    private String id;
    private String userId;
    private String type;
    private Map<String, Object> details;
    private Instant timestamp;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

