package ru.javawebinar.topjava.web;

public class SubString {
    public static void main(String[] args) {
        //sout(string.substring(0,10)); - сколько символов выведет? Что будет если всего 5 символов в string ?
        String string = "One_of_us_is_a_Murder";
        System.out.println(string.substring(0,10));
        int i = 0;
        System.out.println((i++ + i++));
        System.out.println(--i);
        System.out.println(i);
        System.out.println(--i + i-- + ++i + i++ + i++);
        System.out.println("ONE"+1+2+"TWO"+(1+2));
    }
}
