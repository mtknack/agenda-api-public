package com.mtknack.agenda.model.dto;

import com.mtknack.agenda.model.entity.Task;
import com.mtknack.agenda.model.enums.ModificationTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyTaskDTO {
    
    private Task task;
    private ModificationTypeEnum modificationTypeEnum;

}
