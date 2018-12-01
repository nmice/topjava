package ru.javawebinar.topjava.util;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;

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
        String actual = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).toString();
        String expected = "[{2015-05-30T10:00, Завтрак, calories = 500, not exceeded}, {2015-05-31T10:00, Завтрак, calories = 1000, exceeded}]";
        assertEquals(expected, actual);
        mealList.remove(morningMeal);
        actual = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).toString();
        expected = "[{2015-05-31T10:00, Завтрак, calories = 1000, exceeded}]";
        assertEquals(expected, actual);
    }

    @Test
    public void getFilteredWithExceededStreamTest() {
        String actual = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).toString();
        String expected = "[{2015-05-30T10:00, Завтрак, calories = 500, not exceeded}, {2015-05-31T10:00, Завтрак, calories = 1000, exceeded}]";
        assertEquals(expected, actual);
        mealList.remove(morningMeal);
        actual = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).toString();
        expected = "[{2015-05-31T10:00, Завтрак, calories = 1000, exceeded}]";
        assertEquals(expected, actual);
    }
}