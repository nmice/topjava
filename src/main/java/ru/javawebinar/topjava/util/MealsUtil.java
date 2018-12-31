package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class MealsUtil {

    private static final Map<Integer, Meal> MEALS_BASE = new HashMap<>();

    private static List<Meal> meals = new ArrayList<>();

    static {
        initMealsBase();
    }

    public static void initMealsBase() {
        meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 1),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 2),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 3),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 4),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 5),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 6)
        );
        for (Meal meal : meals) {
            MEALS_BASE.put(meal.getId(), meal);
        }
    }


/*    public static void main(String[] args) {

    }*/

/*    public static List<MealWithExceed> getFilteredWithExceededByStream(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }*/

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<MealWithExceed> mealExceeded = new ArrayList<>();
        meals.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
                mealExceeded.add(createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return mealExceeded;
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), meal.getId(), exceeded);
    }

    public static void addMeal(Meal meal) {
        MEALS_BASE.put(meal.getId(), meal);
    }

    public static Meal getMealById(int id) {
        return MEALS_BASE.get(id);
    }

    public static List<Meal> getAllMeals() {
        Collection<Meal> c = MEALS_BASE.values();
        List<Meal> allMealsList = new ArrayList<>();
        allMealsList.addAll(c);
        return allMealsList;
    }

    public static void updateMeal(Meal meal) {
        addMeal(meal);
    }

    public static void deleteMeal(int id) {
        MEALS_BASE.remove(id);
    }

    public static int getMealsBaseSize() {
        return MEALS_BASE.size();
    }

    public static int getNewId() {
        return getMealsBaseSize() + 1;
    }
}