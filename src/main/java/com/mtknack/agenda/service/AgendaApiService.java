package com.mtknack.agenda.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtknack.agenda.model.dto.EventDTO;
import com.mtknack.agenda.model.entity.AgendaApi;
import com.mtknack.agenda.repository.IAgendaApiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendaApiService {

    private final IAgendaApiRepository agendaApiRepository;

    private final ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    public AgendaApi insert(EventDTO event) {
        AgendaApi agendaApi = modelMapper.map(event, AgendaApi.class);
        agendaApi.setAgendaGoogleId(1L);
        return agendaApiRepository.save(agendaApi);
    }
}
