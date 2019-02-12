package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.Dao.MealDao;
import ru.javawebinar.topjava.Dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private MealDao<Meal> mealDao = new MealDaoImpl();
    public static final String PATTERN = "yyyy/MM/dd HH:mm";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("del") != null && request.getParameter("id") != null) {
            String id = request.getParameter("id");
            mealDao.delete(id);
        }
        if ("add".equals(request.getParameter("formtype"))) {
            Meal meal = createMealByRequest(request);
            mealDao.add(meal);
        }
        if ("edit".equals(request.getParameter("formtype")) && request.getParameter("id") != null) {
            Meal meal = createMealByRequest(request);
            mealDao.update(meal);
        }
        List<MealWithExceed> mealsWithExceed = MealsUtil.getFilteredWithExceeded(new ArrayList<>(MealsUtil.MEALS_BASE.values()), 2000);
        request.setAttribute("mealsWithExceedList", mealsWithExceed);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/meals-jsp");
        requestDispatcher.forward(request, response);
    }

    private Meal createMealByRequest(HttpServletRequest request) {
        Meal meal=new Meal();
        if (request.getParameter("id") != null) {
            meal.setId(request.getParameter("id"));
        }
        String description;
        try {
            description = request.getParameter("description");
        } catch (Exception e) {
            description = "no description";
        }
        int calories;
        try {
            calories = Integer.parseInt(request.getParameter("calories"));
        } catch (Exception e) {
            calories = 0;
        }
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(request.getParameter("date"), DateTimeFormatter.ofPattern(PATTERN));
        } catch (Exception e) {
            localDateTime = LocalDateTime.now();
        }
        meal.setDescription(description);
        meal.setCalories(calories);
        meal.setDateTime(localDateTime);
        return meal;
    }
}