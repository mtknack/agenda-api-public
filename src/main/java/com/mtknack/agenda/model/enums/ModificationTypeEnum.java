package com.mtknack.agenda.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ModificationTypeEnum {
    
    TOMORROW("Tomorrow"),
    FINISH("Finish"),
    RESCHEDULE("Reschedule"),
    FACULTY("Faculty");

    private final String value;
}
