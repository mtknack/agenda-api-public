package com.mtknack.agenda.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtknack.agenda.facade.TaskFacade;
import com.mtknack.agenda.model.dto.EventDTO;
import com.mtknack.agenda.model.dto.LoginDTO;
import com.mtknack.agenda.model.dto.TaskDTO;
import com.mtknack.agenda.model.entity.Task;
import com.mtknack.agenda.model.filters.TaskFilter;
import com.mtknack.agenda.service.LoginService;
import com.mtknack.agenda.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController {
    
    private final LoginService loginService;
    private final TaskService taskService;
    private final TaskFacade taskFacade;

    @GetMapping("/task/find-all")
    public ResponseEntity<List<EventDTO>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping("/task/events")
    public ResponseEntity<Page<EventDTO>> getFilteredEvents(
            @RequestBody TaskFilter filter,
            @PageableDefault(sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        
        Page<EventDTO> events = taskService.findTasks(filter, pageable);
        return ResponseEntity.ok(events);
    }


    @GetMapping("/task/find-by-id/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskFacade.findById(id));
    }

    @PostMapping("/task/create")
    public ResponseEntity<String> insert(@RequestBody EventDTO event) {
        taskService.insert(event);
        return ResponseEntity.ok("Evento criado com sucesso!");
    }

    @PostMapping("/task/update")
    public ResponseEntity<Task> update(@RequestBody EventDTO event) {    
        return ResponseEntity.ok(taskService.update(event));
    }
    
    @PostMapping("/task/modify")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO login) {
        return ResponseEntity.ok(loginService.login(login));
    }
}
