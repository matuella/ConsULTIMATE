/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riftrunners.consultimate.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author Diego Peixoto
 * @author Guilherme Matuella
 * @param <PK>
 * @param <T>
 */
public class GenericDAO<PK, T> {

    //PK = Primary Key
    //T = Tipo
    private EntityManager entityManager;

    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public List<T> findAll() {
        return entityManager.createQuery(("FROM " + getTypeClass().getName()))
                .getResultList();
    }

    private Class<?> getTypeClass() {
        Class<?> c = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return c;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}
