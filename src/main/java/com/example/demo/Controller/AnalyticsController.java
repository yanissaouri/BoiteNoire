package com.example.demo.Controller;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/analytics")
public class
AnalyticsController {

    private final MongoTemplate mongoTemplate;

    public AnalyticsController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @GetMapping("/top-users")
    public List<Document> topUsers(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        Instant from = start.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant to = end.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("timestamp").gte(from).lt(to)),
                Aggregation.group("userId").count().as("eventCount"),
                Aggregation.sort(Sort.Direction.DESC, "eventCount"),
                Aggregation.limit(10)
        );

        return mongoTemplate.aggregate(aggregation, "Events", Document.class).getMappedResults();
    }

    @GetMapping("/errors")
    public List<Document> errorsByTypeAndDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        Instant from = start.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant to = end.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("type").is("error").and("timestamp").gte(from).lt(to)),
                Aggregation.project()
                        .and("details.errorType").as("errorType")
                        .and(DateOperators.dateOf("timestamp").toString("%Y-%m-%d")).as("day"),
                Aggregation.group("day", "errorType").count().as("count"),
                Aggregation.sort(Sort.Direction.ASC, "day", "errorType")
        );

        return mongoTemplate.aggregate(aggregation, "Events", Document.class).getMappedResults();
    }
}