package com.mtknack.agenda.facade;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mtknack.agenda.Exceptions.DTOs.CustomException;
import com.mtknack.agenda.model.dto.ModifyTaskDTO;
import com.mtknack.agenda.model.dto.TaskDTO;
import com.mtknack.agenda.model.entity.Task;
import com.mtknack.agenda.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskFacade {
    
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public Task modify(ModifyTaskDTO modifyTaskDTO){
        
        switch (modifyTaskDTO.getModificationTypeEnum()) {
            case TOMORROW:
                return taskService.tomorrow(modifyTaskDTO);
            case FINISH:
                // return taskService.tomorrow(modifyTaskDTO);
        
            default:
                throw new CustomException("Error converting to JSON", HttpStatus.NOT_FOUND, "The provided ID does not exist in the database.");
        }
    }

    public TaskDTO findById(Long id){
        return modelMapper.map(taskService.findById(id), TaskDTO.class);
    }
}
