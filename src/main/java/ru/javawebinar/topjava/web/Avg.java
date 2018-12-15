package ru.javawebinar.topjava.web;

import java.util.Arrays;
import java.util.List;

public class Avg {

    public static void main(String[] args) {
        List<Integer> listInt = Arrays.asList(1, 2, 3, 4, 5, 0);
        System.out.println(midInt(listInt));
    }

    static double midInt(List<Integer> listInt) {
        long sum = 0;
        if (listInt.size() < 1) {
            throw new UnsupportedOperationException("List size must be bigger than 0 elements");
        }
        for (Integer integer : listInt) {
            sum += integer;
        }
        double result = ((double) sum) / listInt.size();
        return result;
    }
}
