package com.mtknack.agenda.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "agenda_api")
public class AgendaApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda_api")
    private Long id;

    @Column(name = "id_agenda_google", nullable = false)
    private Long agendaGoogleId;

    @Column(name = "tx_title", nullable = false, length = 100)
    private String title;

    @Column(name = "tx_description", length = 10000)
    private String description;

    @Column(name = "dt_start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "tx_time_zone", nullable = false, length = 15)
    private String timeZone;

    @Column(name = "dt_end_date_time", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "tx_location", length = 50)
    private String location;

    @Column(name = "nm_minutes_notification")
    private Integer minutesNotification;

    @Column(name = "tx_type", length = 30)
    private String type;

    @Column(name = "nm_importance")
    private Integer importance;
}
