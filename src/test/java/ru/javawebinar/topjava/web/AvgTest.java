package ru.javawebinar.topjava.web;

import org.junit.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.web.Avg.midInt;

public class AvgTest {


    @org.junit.Test
    public void midIntTest1() {
        List<Integer> listInt = Arrays.asList(1, 2, 3, 4, 5, 0);
        Double expected = BigDecimal.valueOf(((double) 15) / 6)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        Double actual = BigDecimal.valueOf(midInt(listInt))
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }
}