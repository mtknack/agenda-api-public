package com.mtknack.agenda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private String timeZone;
    private LocalDateTime endDateTime;
    private String location;
    private int minutesNotification;
    private String type;
    private int importance;

    public EventDTO(){}
    
    public EventDTO(JsonNode jsonNode) {
        this.id = jsonNode.has("id") ? jsonNode.get("id").asLong() : null;
        this.title = jsonNode.has("title") ? jsonNode.get("title").asText() : null;
        this.description = jsonNode.has("description") ? jsonNode.get("description").asText() : null;
        this.startDateTime = jsonNode.has("startDateTime") ? LocalDateTime.parse(jsonNode.get("startDateTime").asText()) : null;
        this.timeZone = jsonNode.has("timeZone") ? jsonNode.get("timeZone").asText() : null;
        this.endDateTime = jsonNode.has("endDateTime") ? LocalDateTime.parse(jsonNode.get("endDateTime").asText()) : null;
        this.location = jsonNode.has("location") ? jsonNode.get("location").asText() : null;
        this.minutesNotification = jsonNode.has("minutesNotification") ? jsonNode.get("minutesNotification").asInt() : 0;
        this.type = jsonNode.has("type") ? jsonNode.get("type").asText() : null;
        this.importance = jsonNode.has("importance") ? jsonNode.get("importance").asInt() : 0;
    }
}
