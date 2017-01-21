package com.javaweb.model.dao;

import java.util.List;

public interface GenericDao<E> {
	E get(int id);
    List<E> getAll();
    void create(E e);
    void update(E e);
    void delete(int id);
}
