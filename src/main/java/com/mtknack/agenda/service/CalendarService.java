package com.mtknack.agenda.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.AclRule;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.mtknack.agenda.Exceptions.DTOs.CustomException;
import com.mtknack.agenda.model.dto.EventDTO;

import lombok.RequiredArgsConstructor;

import com.google.api.services.calendar.model.AclRule.Scope;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final Calendar calendar;
    private final TaskService taskService;

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
    .registerModule(new JavaTimeModule())
    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public List<EventDTO> listAllEvents() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        String pageToken = null;
        Events events;

        do {
            try {
                events = calendar.events().list("primary")
                        .setOrderBy("startTime")
                        .setSingleEvents(true) // Garante eventos individuais, não agrupamentos de recorrência
                        .setPageToken(pageToken) // Paginação
                        .execute();
            } catch (Exception e) {
                throw new CustomException("Usuário não encontrado", HttpStatus.NOT_FOUND,
                        "O ID fornecido não existe no banco.");
            }

            List<Event> items = events.getItems();
            if (!items.isEmpty()) {
                for (Event event : items) {
                    try {
                        String description = event.getDescription(); // JSON armazenado na descrição
                        if (description.startsWith("{")) {
                            if (description != null && !description.isBlank()) {
                                EventDTO eventDTO = new EventDTO(OBJECT_MAPPER.readTree(description));
                                eventDTOList.add(eventDTO);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao converter JSON do evento: " + event.getSummary());
                        e.printStackTrace();
                    }
                }
            }
            pageToken = events.getNextPageToken(); // Próxima página
        } while (pageToken != null);

        return eventDTOList;
    }

    public List<EventDTO> listEvent(Long id) {
        List<EventDTO> eventDTOList = new ArrayList<>();
        String pageToken = null;
        Events events;

        do {
            try {
                events = calendar.events().list("primary")
                        .setOrderBy("startTime")
                        .setSingleEvents(true) // Garante eventos individuais, não agrupamentos de recorrência
                        .setPageToken(pageToken) // Paginação
                        .execute();
            } catch (Exception e) {
                throw new CustomException("Usuário não encontrado", HttpStatus.NOT_FOUND,
                        "O ID fornecido não existe no banco.");
            }

            List<Event> items = events.getItems();
            if (!items.isEmpty()) {
                for (Event event : items) {
                    try {
                        String description = event.getDescription(); // JSON armazenado na descrição
                        if (description.startsWith("{")) {
                            if (description != null && !description.isBlank()) {
                                EventDTO eventDTO = new EventDTO(OBJECT_MAPPER.readTree(description));
                                eventDTOList.add(eventDTO);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao converter JSON do evento: " + event.getSummary());
                        e.printStackTrace();
                    }
                }
            }
            pageToken = events.getNextPageToken(); // Próxima página
        } while (pageToken != null);

        return eventDTOList;
    }

    public void listEventsForToday() throws IOException {
        // Obter a data atual e definir o início e o fim do dia
        java.util.Calendar javaCalendar = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        javaCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        javaCalendar.set(java.util.Calendar.MINUTE, 0);
        javaCalendar.set(java.util.Calendar.SECOND, 0);
        javaCalendar.set(java.util.Calendar.MILLISECOND, 0);

        DateTime startOfDay = new DateTime(javaCalendar.getTime());

        javaCalendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
        javaCalendar.set(java.util.Calendar.MINUTE, 59);
        javaCalendar.set(java.util.Calendar.SECOND, 59);

        DateTime endOfDay = new DateTime(javaCalendar.getTime());

        // Buscar os eventos entre meia-noite e 23:59
        Events events = calendar.events().list("primary")
                .setTimeMin(startOfDay)
                .setTimeMax(endOfDay)
                .setOrderBy("startTime")
                .setSingleEvents(true) // Garante que ocorrências recorrentes sejam tratadas como eventos separados
                .execute();

        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("Nenhum evento encontrado para hoje.");
        } else {
            System.out.println("Eventos de hoje:");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate(); // Para eventos de dia inteiro
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }

    public void listEventsForThisWeek() throws IOException {
        // Obter a data atual
        java.util.Calendar javaCalendar = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        // Definir o primeiro dia da semana (domingo ou segunda, dependendo da
        // configuração da sua região)
        javaCalendar.set(java.util.Calendar.DAY_OF_WEEK, javaCalendar.getFirstDayOfWeek());
        javaCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        javaCalendar.set(java.util.Calendar.MINUTE, 0);
        javaCalendar.set(java.util.Calendar.SECOND, 0);
        javaCalendar.set(java.util.Calendar.MILLISECOND, 0);

        // Início da semana (meia-noite do primeiro dia)
        DateTime startOfWeek = new DateTime(javaCalendar.getTime());

        // Definir o último dia da semana (sábado ou domingo)
        javaCalendar.add(java.util.Calendar.DAY_OF_WEEK, 6);
        javaCalendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
        javaCalendar.set(java.util.Calendar.MINUTE, 59);
        javaCalendar.set(java.util.Calendar.SECOND, 59);

        // Fim da semana (23:59 do último dia)
        DateTime endOfWeek = new DateTime(javaCalendar.getTime());

        // Buscar eventos entre o início e o fim da semana
        Events events = calendar.events().list("primary")
                .setTimeMin(startOfWeek)
                .setTimeMax(endOfWeek)
                .setOrderBy("startTime")
                .setSingleEvents(true) // Garante que ocorrências recorrentes sejam tratadas como eventos separados
                .execute();

        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("Nenhum evento encontrado para esta semana.");
        } else {
            System.out.println("Eventos desta semana:");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate(); // Para eventos de dia inteiro
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }

    public void createEventWithTodayDate(EventDTO eventDTO) {

        String jsonDescription = "";
        try {
            jsonDescription = OBJECT_MAPPER.writeValueAsString(eventDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new CustomException("Error ao corverter para JSON", HttpStatus.NOT_FOUND,
                    "O ID fornecido não existe no banco.");
        }

        // Criar um novo evento
        Event event = new Event()
                .setSummary(eventDTO.getTitle())
                .setDescription(jsonDescription)
                .setLocation(eventDTO.getLocation());

        // Definir o início e o fim do evento
        EventDateTime start = new EventDateTime()
                .setDateTime(convertToDateTime(eventDTO.getStartDateTime(), eventDTO.getTimeZone()))
                .setTimeZone("UTC");
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(convertToDateTime(eventDTO.getEndDateTime(), eventDTO.getTimeZone()))
                .setTimeZone("UTC");
        event.setEnd(end);

        // Inserir o evento no calendário
        try {
            calendar.events().insert("primary", event).execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("Error ao salvar dados no Google", HttpStatus.NOT_FOUND,
                    "O ID fornecido não existe no banco.");
        }

        taskService.insert(eventDTO);
    }

    // função para compartilhar o calendario com algum email
    public void shareCalendarWithUser(String userEmail) throws IOException {
        // Definir o escopo do compartilhamento: com uma pessoa específica
        Scope scope = new Scope().setType("user").setValue(userEmail);

        // Definir a regra ACL: permissões de proprietário ou editor
        AclRule rule = new AclRule().setScope(scope).setRole("owner"); // Pode ser "reader", "writer" ou "owner"

        // Adicionar a regra ao calendário principal da conta de serviço
        calendar.acl().insert("primary", rule).execute();

        System.out.println("Calendário compartilhado com sucesso com " + userEmail);
    }

    public static DateTime convertToDateTime(LocalDateTime startDate, String timeZone) {
        // 1. Criar um ZoneId a partir da string recebida do front-end
        ZoneId zoneId = ZoneId.of(timeZone);

        // 2. Converter LocalDateTime para ZonedDateTime usando o ZoneId
        ZonedDateTime zonedDateTime = startDate.atZone(zoneId);

        // 3. Converter ZonedDateTime para o formato DateTime do Google API
        return new DateTime(zonedDateTime.toInstant().toEpochMilli());
    }
}
