package com.mtknack.agenda.model.entity;

import java.lang.ProcessBuilder.Redirect.Type;
import java.time.LocalDateTime;

import com.mtknack.agenda.model.enums.TypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long id;

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
    @Enumerated( value = EnumType.STRING )
    private TypeEnum type;

    @Column(name = "nm_importance")
    private Integer importance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda_api")
    private AgendaApi agenda;
}
