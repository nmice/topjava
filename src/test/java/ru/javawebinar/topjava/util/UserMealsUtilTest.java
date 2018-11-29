package ru.javawebinar.topjava.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.util.UserMealsUtil.*;


public class UserMealsUtilTest {

    private List<UserMeal> mealList = null;
    private List<UserMealWithExceed> mealList2 = null;
    private UserMeal morningMeal = null;

    @Before
    public void setUp() throws Exception {
        UserMeal morningMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        mealList = Arrays.asList(morningMeal,
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
    }

    @Test
    public void getFilteredWithExceededTest() {
        String actual = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).toString();
        String expected = "[{2015-05-30T10:00, Завтрак, calories = 500, not exceeded}, {2015-05-31T10:00, Завтрак, calories = 1000, exceeded}]";
        Assert.assertEquals(actual, expected);
        mealList.remove(morningMeal);
        String actual1 = mealList.remove(morningMeal).toString();
        String expected2 = "[{2015-05-31T10:00, Завтрак, calories = 1000, exceeded}]";
        Assert.assertEquals(actual2, expected2);
    }

    @Test
    public void getFilteredWithExceededStream() {
    }
}