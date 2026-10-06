package com.example.demo.Generators;

import com.example.demo.Constants.Const;
import com.example.demo.Entity.Event;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.EventRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Component
@Profile("generator")
public class EventGenerator {

    private final EventRepository eventRepository;
    private final Random random = new Random(67);


    public EventGenerator(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void generate(List<Users> users) {
        eventRepository.deleteAll();

        List<String> pool = new ArrayList<>();
        for (Users user : users) {
            for (int i = 0; i < user.getUsersCategory().getWeight(); i++) {
                pool.add(user.getId());
            }
        }

        long now = Instant.now().toEpochMilli();
        long yearAgo = now - 365L * 24 * 60 * 60 * 1000;

        List<Event> batch = new ArrayList<>(Const.getTotalUser());

        for (int i = 0; i < Const.getTotalEvents(); i++) {
            String userId = pool.get(random.nextInt(pool.size()));
            String type = randomEventType();

            Event event = new Event();
            event.setUserId(userId);
            event.setType(type);
            event.setDetails(randomDetails(type));
            event.setTimestamp(Instant.ofEpochMilli(yearAgo + (long)(random.nextDouble() * (now - yearAgo))
            ));
            batch.add(event);

            if (batch.size() == Const.getTotalUser()) {
                eventRepository.saveAll(batch);
                batch.clear();
            }
        }
        if (!batch.isEmpty()) {
            eventRepository.saveAll(batch);
        }

        System.out.println(Const.getTotalEvents() + " événements générés.");
    }

    private String randomEventType() {
        double roll = random.nextDouble();
        if (roll < Const.getAPI())
            return "api_call";
        if (roll < Const.getLOGIN())
            return "login";
        if (roll < Const.getNOTIFICATION())
            return "notification";
        if (roll < Const.getSUBSCRIPTION())
            return "subscription";
        return "error";
    }

    private Map<String, Object> randomDetails(String type) {
        Map<String, Object> details = new HashMap<>();
        switch (type) {
            case "api_call":
                String[] endpoints = {"/api/users", "/api/messages", "/api/orders", "/api/search"};
                String[] methods = {"GET", "POST", "PUT", "DELETE"};
                int[] httpCodes = {200, 200, 200, 201, 400, 404, 500};
                details.put("endpoint", endpoints[random.nextInt(endpoints.length)]);
                details.put("method", methods[random.nextInt(methods.length)]);
                details.put("durationMs", 20 + random.nextInt(1980));
                details.put("httpCode", httpCodes[random.nextInt(httpCodes.length)]);
                break;
            case "login":
                String[] devices = {"mobile", "desktop", "tablet"};
                details.put("ip", 255 + "." + 168 + "." + random.nextInt(256) + "." + random.nextInt(256));
                details.put("device", devices[random.nextInt(devices.length)]);
                details.put("success", random.nextDouble() < 0.97);
                break;
            case "notification":
                String[] channels = {"EMAIL", "SMS"};
                details.put("channel", channels[random.nextInt(channels.length)]);
                details.put("read", random.nextBoolean());
                break;
            case "subscription":
                String[] plans = {"basic", "pro", "enterprise"};
                String[] statuses = {"active", "cancelled", "failed"};
                details.put("plan", plans[random.nextInt(plans.length)]);
                details.put("price", 5 + random.nextInt(200));
                details.put("currency", "EUR");
                details.put("status", statuses[random.nextInt(statuses.length)]);
                break;
            case "error":
                String[] errorTypes = {"TIMEOUT", "DATABASE_ERROR", "VALIDATION_ERROR"};
                String[] services = {"auth", "payment", "messaging"};
                String[] messages = {"Connection timed out", "Invalid input", "Database unreachable"};
                details.put("errorType", errorTypes[random.nextInt(errorTypes.length)]);
                details.put("service", services[random.nextInt(services.length)]);
                details.put("message", messages[random.nextInt(messages.length)]);
                break;
        }
        return details;
    }
}