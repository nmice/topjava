package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.Dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

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
    private String id;
    private boolean add;
    private MealDaoImpl mealDao = new MealDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // set encoding (only for method Post!)
        try {
            LocalDateTime localDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(request.getParameter("date").trim(), LocalDateTime::from);
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            if (add)
                mealDao.add(new Meal(localDateTime, description, calories));
            else {
                Meal meal = mealDao.getById(id);
                meal.setDateTime(localDateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
                mealDao.update(meal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("id") != null) {
            id = request.getParameter("id");
            if (request.getParameter("del") != null)
                mealDao.delete(id);
                MealsUtil.MEALS_BASE.remove(id);
        }/* else id = -1;*/

        if (request.getParameter("add") != null)
            add = true;
        else add = false;

        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(new ArrayList<>(MealsUtil.MEALS_BASE.values()), 2000);
        request.setAttribute("meals", mealWithExceeds);


        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}