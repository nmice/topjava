package ru.javawebinar.topjava.util;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.util.UserMealsUtil.*;


public class UserMealsUtilTest {

    private List<UserMeal> mealList = null;
    private UserMeal morningMeal = null;
    private static final UserMealWithExceed EXPECTED_USER_MEAL_WITH_EXCEED_1 = new UserMealWithExceed(
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500,false);
    private static final UserMealWithExceed EXPECTED_USER_MEAL_WITH_EXCEED_2 = new UserMealWithExceed(
            LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000,true);

    @Before
    public void setUp() throws Exception {
        morningMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        mealList = new LinkedList<>();
        mealList.add(morningMeal);
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Test
    public void getFilteredWithExceededTest() {
        UserMealWithExceed actual = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).get(0);
        assertEquals(EXPECTED_USER_MEAL_WITH_EXCEED_1, actual);
        mealList.remove(morningMeal);
        actual = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).get(0);
        assertEquals(EXPECTED_USER_MEAL_WITH_EXCEED_2, actual);
    }

    @Test
    public void getFilteredWithExceededStreamTest() {
        UserMealWithExceed actual = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).get(0);
        assertEquals(EXPECTED_USER_MEAL_WITH_EXCEED_1, actual);
        mealList.remove(morningMeal);
        actual = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).get(0);
        assertEquals(EXPECTED_USER_MEAL_WITH_EXCEED_2, actual);
    }
}