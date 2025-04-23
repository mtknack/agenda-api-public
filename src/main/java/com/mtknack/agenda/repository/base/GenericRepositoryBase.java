package com.mtknack.agenda.repository.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;

public interface GenericRepositoryBase<T, ID extends Serializable> {

    EntityManager getEntityManager();

    default Query createPageableQuery(String hql, Pageable pageable) {
        StringBuilder queryBuilder = new StringBuilder(hql);

        if (pageable.getSort().isSorted()) {
            queryBuilder.append(" order by ");
            queryBuilder.append(
                pageable.getSort().stream()
                    .map(order -> order.getProperty() + " " + order.getDirection())
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("")
            );
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query;
    }

    default Query createPageableQuery(String hql, Pageable pageable, Class<?> entityClass) {
        StringBuilder queryBuilder = new StringBuilder(hql);

        if (pageable.getSort().isSorted()) {
            queryBuilder.append(" order by ");
            queryBuilder.append(
                pageable.getSort().stream()
                    .map(order -> order.getProperty() + " " + order.getDirection())
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("")
            );
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString(), entityClass);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query;
    }

    default Query createNativePageableQuery(String sql, Pageable pageable) {
        StringBuilder queryBuilder = new StringBuilder(sql);

        if (pageable.getSort().isSorted()) {
            queryBuilder.append(" order by ");
            queryBuilder.append(
                pageable.getSort().stream()
                    .map(order -> order.getProperty() + " " + order.getDirection())
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("")
            );
        }

        Query query = getEntityManager().createNativeQuery(queryBuilder.toString());
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query;
    }

    default Query createNativePageableQuery(String sql, Pageable pageable, Class<?> resultClass) {
        StringBuilder queryBuilder = new StringBuilder(sql);

        if (pageable.getSort().isSorted()) {
            queryBuilder.append(" order by ");
            queryBuilder.append(
                pageable.getSort().stream()
                    .map(order -> order.getProperty() + " " + order.getDirection())
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("")
            );
        }

        Query query = getEntityManager().createNativeQuery(queryBuilder.toString(), resultClass);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query;
    }
}
