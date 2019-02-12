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

        if (request.getParameter("id") != null) {
            String id = request.getParameter("id");
            if (request.getParameter("del") != null)
                mealDao.delete(id);
        }

        if (request.getParameter("add") != null) {
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("date"), DateTimeFormatter.ofPattern(PATTERN));
            mealDao.add(new Meal(localDateTime, description, calories));
        }
        /*if (Boolean.parseBoolean(request.getParameter("edit"))) {
            Meal meal = mealDao.getById(request.getParameter("description"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            LocalDateTime localDateTime = null;//LocalDateTime.parse(request.getParameter("date"), DateTimeFormatter.ofPattern(PATTERN));
            meal.setDescription(description);
            meal.setCalories(calories);
            meal.setDateTime(localDateTime);
            mealDao.update(meal);
        }*/
        /*add = true;
        else add = false;*/

        List<MealWithExceed> mealsWithExceed = MealsUtil.getFilteredWithExceeded(new ArrayList<>(MealsUtil.MEALS_BASE.values()), 2000);
        request.setAttribute("mealsWithExceedList", mealsWithExceed);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/meals-jsp");
        requestDispatcher.forward(request, response);
    }
}