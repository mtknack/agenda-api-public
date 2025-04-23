package com.mtknack.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mtknack.agenda.model.entity.AgendaApi;

public interface IAgendaApiRepository extends JpaRepository<AgendaApi, Long>, JpaSpecificationExecutor<AgendaApi> {
    
}
