package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate dateOfMeals = userMeal.getDateTime().toLocalDate();
            int oneMealCalories = userMeal.getCalories();
            int caloriesOfMeals = caloriesPerDayMap.getOrDefault(dateOfMeals, 0) + oneMealCalories;
            caloriesPerDayMap.put(dateOfMeals, caloriesOfMeals);
        }
        List<UserMealWithExceed> mealWithExceedList = new LinkedList<>();
        for (UserMeal userMeal : mealList) {
            LocalTime userMealTime = userMeal.getDateTime().toLocalTime();
            if (userMealTime.compareTo(startTime) >= 0 && userMealTime.compareTo(endTime) <= 0) {
                boolean exceed = caloriesPerDayMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay;
                UserMealWithExceed userMealWithExceed = new UserMealWithExceed(userMeal, exceed);
                mealWithExceedList.add(userMealWithExceed);
            }
        }
        return mealWithExceedList;
    }


    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime,
                                                                         LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();

        mealList.forEach(userMeal -> {
            LocalDate dateOfMeals = userMeal.getDateTime().toLocalDate();
            int oneMealCalories = userMeal.getCalories();
            int caloriesOfMeals = caloriesPerDayMap.getOrDefault(dateOfMeals, 0) + oneMealCalories;
            caloriesPerDayMap.put(dateOfMeals, caloriesOfMeals);
        });

        return mealList.stream().filter(userMeal -> {
            LocalTime userMealTime = userMeal.getDateTime().toLocalTime();
            return userMealTime.compareTo(startTime) >= 0 && userMealTime.compareTo(endTime) <= 0;
        }).map(userMeal -> new UserMealWithExceed(userMeal, caloriesPerDayMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
