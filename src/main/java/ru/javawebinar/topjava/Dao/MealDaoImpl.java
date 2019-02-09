package ru.javawebinar.topjava.Dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;

public class MealDaoImpl implements MealDao<Meal> {

    @Override
    public void add(Meal meal) {
        MealsUtil.MEALS_BASE.put(meal.getId(), meal);
    }

    @Override
    public Meal getById(String id) {
        return MealsUtil.MEALS_BASE.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(MealsUtil.MEALS_BASE.values());
    }

    @Override
    public void update(Meal meal) {
        MealsUtil.MEALS_BASE.put(meal.getId(), meal);
    }

    @Override
    public void delete(String id) {
        MealsUtil.MEALS_BASE.remove(id);
    }
}