package com.mtknack.agenda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserConfigDTO {

    private Long id;
    private Boolean integrateGoogleAgenda;
    private UserConfigStyleDTO userConfigStyle;
}
