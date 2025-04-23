package com.mtknack.agenda.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mtknack.agenda.Exceptions.DTOs.CustomException;
import com.mtknack.agenda.model.dto.EventDTO;
import com.mtknack.agenda.model.dto.ModifyTaskDTO;
import com.mtknack.agenda.model.entity.Task;
import com.mtknack.agenda.model.enums.TypeEnum;
import com.mtknack.agenda.model.filters.TaskFilter;
import com.mtknack.agenda.repository.ITaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final AgendaApiService agendaApiService;
    private final ITaskRepository iTaskRepository;

    private final ModelMapper modelMapper;

    public Task findById(Long id) {
        return iTaskRepository.findById(id)
                .orElseThrow(() -> new CustomException("Task not found in database", HttpStatus.NOT_FOUND, ""));
    }

    @Transactional(rollbackFor = Exception.class)
    public Task insert(EventDTO event) {
        validate(event);
        Task task = modelMapper.map(event, Task.class);

        // rever mais para frente
        // AgendaApi agendaApi = agendaApiService.insert(event);
        // task.setAgenda(agendaApi);

        return iTaskRepository.save(task);
    }

    @Transactional(rollbackFor = Exception.class)
    public Task update(EventDTO event) {

        findById(event.getId());
        Task task = modelMapper.map(event, Task.class);
        return iTaskRepository.save(task);
    }

    public List<EventDTO> findAll() {
        return iTaskRepository.findAll().stream().map(task -> modelMapper.map(task, EventDTO.class)).toList();
    }

    public Page<EventDTO> findTasks(TaskFilter filter,Pageable pageable) {
        return iTaskRepository.findTasks( filter, pageable);
    }

    public Task tomorrow(ModifyTaskDTO modifyTaskDTO) {

        checkModification(modifyTaskDTO);
        Task task = findById(modifyTaskDTO.getTask().getId());

        task.setStartDateTime(task.getStartDateTime().plusDays(1));
        task.setEndDateTime(task.getEndDateTime().plusDays(1));

        return iTaskRepository.save(task);
    }

    private void validate(EventDTO event){
        if(event.getType() != null && !TypeEnum.isValidType(event.getType())){
            throw new CustomException("The argument: " + event.getType() + " is invalid", HttpStatus.NOT_FOUND, "");
        }
    }

    private void checkModification(ModifyTaskDTO modifyTaskDTO) {
        if (modifyTaskDTO.getTask().getId() == null) {
            throw new CustomException("Id cannot be null for operation", HttpStatus.NOT_FOUND, "");
        }
    }
}
