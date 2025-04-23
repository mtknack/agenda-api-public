package com.mtknack.agenda.repository.base;

import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class GenericRepositoryBaseImpl<T, ID extends Serializable> implements GenericRepositoryBase<T, ID> {
    
    @PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
