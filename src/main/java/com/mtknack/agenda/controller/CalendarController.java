package com.mtknack.agenda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtknack.agenda.model.dto.EventDTO;
import com.mtknack.agenda.service.CalendarService;
import com.mtknack.agenda.service.TaskService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
    private final TaskService taskService;


    @GetMapping("/events/{id}")
    public ResponseEntity<List<EventDTO>> getCalendarEvent(@PathParam("id") Long id ) {
        return ResponseEntity.ok(calendarService.listEvent(id));
    }

    @GetMapping("/events/today")
    public ResponseEntity<String> getTodayEvents() {
        try {
            calendarService.listEventsForToday();
            return ResponseEntity.ok("Listed events for today.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch events");
        }
    }

    @GetMapping("/events/week")
    public ResponseEntity<String> getWeekEvents() {
        try {
            calendarService.listEventsForThisWeek();
            return ResponseEntity.ok("Listed events for this week.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch events");
        }
    }

    @PostMapping("/events/create")
    public ResponseEntity<String> createEvent(@RequestBody EventDTO event) {
        taskService.insert(event);
        // calendarService.createEventWithTodayDate(event);
        return ResponseEntity.ok("Evento criado com sucesso!");
    }
}
