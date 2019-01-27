package ru.javawebinar.topjava.Dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;

public class MealDaoImpl implements MealDao<Meal> {


    @Override
    public void add(Meal meal) {
        MealsUtil.mealsMap.put(getAll().size() + 1, meal);
    }

    @Override
    public Meal getById(int id) {
        return MealsUtil.mealsMap.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(MealsUtil.mealsMap.values());
    }

    @Override
    public void update(Meal meal) {
        MealsUtil.mealsMap.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        MealsUtil.mealsMap.remove(id);
    }
}