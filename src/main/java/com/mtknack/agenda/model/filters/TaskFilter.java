package com.mtknack.agenda.model.filters;

import com.mtknack.agenda.model.enums.TypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskFilter {
    private String title;
    private TypeEnum type;
}