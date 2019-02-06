package ru.javawebinar.topjava.Dao;

import java.util.List;

public interface MealDao<T> {

    void add(T t);

    T getById(String id);

    List<T> getAll();

    void update(T t);

    void delete(String id);
}

