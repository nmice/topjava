package ru.javawebinar.topjava.Dao;

import ru.javawebinar.topjava.model.Meal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MealDaoImpl implements MealDao<Meal> {

    @Override
    public Optional<Meal> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Meal> getAll() {
        return null;
    }

    @Override
    public void save(Meal meal) {

    }

    @Override
    public void update(Meal meal, String[] params) {

    }

    @Override
    public void delete(Meal meal) {
    }

}