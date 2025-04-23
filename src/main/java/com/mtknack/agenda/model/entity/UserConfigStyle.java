package com.mtknack.agenda.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_config_style")
public class UserConfigStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_config_style")
    private Long id;

    @Column(name = "tx_text_color")
    private String textColor;

    @Column(name = "tx_primary_color")
    private String primaryColor;

    @Column(name = "tx_secondary_color")
    private String secondaryColor;

    @Column(name = "tx_tertiary_color")
    private String tertiaryColor;

    @Column(name = "tx_background_color")
    private String backgroundColor;
}
