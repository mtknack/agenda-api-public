package com.mtknack.agenda.model.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeEnum {
    
    JOB("Job"),
    HOME("Home"),
    LEISURE("Leisure"),
    FACULTY("Faculty");

    private final String value;

    public static boolean isValidType(String type) {
        return Arrays.stream(TypeEnum.values())
                     .anyMatch(e -> e.name().equalsIgnoreCase(type));
    }
}
