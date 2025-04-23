package com.mtknack.agenda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserConfigStyleDTO {

    private Long id;
    private String textColor;
    private String primaryColor;
    private String secondaryColor;
    private String tertiaryColor;
    private String backgroundColor;
}

