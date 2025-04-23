package com.mtknack.agenda.model.dto;

import java.time.LocalDateTime;

import com.mtknack.agenda.model.enums.TypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private String timeZone;
    private LocalDateTime endDateTime;
    private String location;
    private Integer minutesNotification;
    private TypeEnum type;
    private Integer importance;
    // private AgendaApi agenda;
}
