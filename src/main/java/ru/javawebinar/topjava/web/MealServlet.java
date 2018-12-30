package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
/*    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       LOG.debug("redirect to userList");
        List<MealWithExceed> meals = Arrays.asList(
                new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, false),
                new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, false),
                new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, false),
                new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, true),
                new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, true),
                new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, true)
        );

        request.setAttribute("meals", meals);

        request.getRequestDispatcher("meals.jsp").forward(request, response);
//        response.sendRedirect("userList.jsp");
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");

        if(userid == null || userid.isEmpty())
        {
            Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")), MealsUtil.getNewId());
            MealsUtil.addMeal(meal);
        }
        else
        {
            Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")), Integer.parseInt(userid));
            MealsUtil.updateMeal(meal);
        }
        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(MealsUtil.getAllMeals(), LocalTime.of(0, 0),
                LocalTime.of(23, 59), 2000);
        request.setAttribute("meals", mealsWithExceeded);
        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(request.getParameter("userid"));
            MealsUtil.deleteMeal(userId);
            List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(MealsUtil.getAllMeals(), LocalTime.of(0, 0),
                    LocalTime.of(23, 59), 2000);
            request.setAttribute("meals", mealsWithExceeded);
        } else if (action.equalsIgnoreCase("edit")) {
            int userId = Integer.parseInt(request.getParameter("userid"));

            Meal meal = MealsUtil.getMealById(userId);
            request.setAttribute("meal", meal);
        }
        else if (action.equalsIgnoreCase("listMeals")) {
            List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(MealsUtil.getAllMeals(),
                    LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            request.setAttribute("meals", mealsWithExceeded);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}