package ru.javawebinar.topjava.Dao;

import java.util.List;

public interface MealDao<T> {

    void add(T t);

    T getById(int id);

    List<T> getAll();

    void update(T t);

    void delete(int id);
}

