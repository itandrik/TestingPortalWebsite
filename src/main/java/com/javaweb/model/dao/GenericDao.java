package com.javaweb.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E> {
	Optional<E> getById(int id);
    List<E> getAll();
    int insert(E e);
    int update(E e);
    void delete(int id);
}
