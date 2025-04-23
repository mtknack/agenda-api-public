package com.mtknack.agenda.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_config")
public class UserConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_config")
    private Long id;

    // Descomente se for implementar userKeycloak futuramente
    // @Column(name = "id_user_keycloak", nullable = false)
    // private Long userKeycloakId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_config_style")
    private UserConfigStyle userConfigStyle;

    @Column(name = "bl_integrate_google_agenda")
    private Boolean integrateGoogleAgenda = false;
}
