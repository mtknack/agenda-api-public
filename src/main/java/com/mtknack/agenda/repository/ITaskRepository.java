package com.mtknack.agenda.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mtknack.agenda.model.dto.EventDTO;
import com.mtknack.agenda.model.dto.TaskDTO;
import com.mtknack.agenda.model.entity.Task;
import com.mtknack.agenda.model.filters.TaskFilter;
import com.mtknack.agenda.repository.base.GenericRepositoryBase;

public interface ITaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task>, GenericRepositoryBase<Task, Long> {
    
    @SuppressWarnings("unchecked")
	public default Page<EventDTO> findTasks( TaskFilter filter,Pageable pageable ) {
		var hql = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		hql.append( " SELECT NEW com.mtknack.agenda.model.dto.TaskDTO( " );
		hql.append( "     t.id, " );
		hql.append( "     t.title, " );
        hql.append( "     t.description, " );
        hql.append( "     t.startDateTime, " );
        hql.append( "     t.timeZone, " );
        hql.append( "     t.endDateTime, " );
        hql.append( "     t.location, " );
        hql.append( "     t.minutesNotification, " );
        hql.append( "     t.type, " );
        hql.append( "     t.importance " );
		hql.append( " ) " );
		hql.append( " FROM Task t " );
        hql.append( " WHERE 1 = 1 " );

        buildSearchFindTasks(filter, hql, params);

		var query = createPageableQuery( hql.toString(), pageable, TaskDTO.class );
		params.forEach( query::setParameter );

		var count = countFindTasks( filter );
		return new PageImpl<>( query.getResultList(), pageable, count );
	}

    private Long countFindTasks( TaskFilter filter ) {
		var hql = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		hql.append( " SELECT count(1) " );
		hql.append( " FROM Task t " );
		hql.append( " WHERE 1 = 1 " );

		buildSearchFindTasks(filter, hql, params);

		var query = getEntityManager().createQuery( hql.toString(), Long.class );
		params.forEach( query::setParameter );

		return query.getSingleResult();
	}

    private void buildSearchFindTasks( TaskFilter filter, StringBuilder hql, Map<String, Object> params) {

		if( filter.getTitle() != null &&  filter.getTitle() != "" ) { // fazer as verificações melhor
			hql.append( " AND CAST(UNACCENT(UPPER(t.title)) AS String) LIKE CAST(UNACCENT(UPPER(:title)) AS String) " );
			params.put( "title", "%" + filter.getTitle() + "%" );
		}

        if( filter.getType() != null ) { // fazer as verificações melhor
			hql.append( " AND t.type = :type " );
			params.put( "type", filter.getType() );
		}
	}
}
