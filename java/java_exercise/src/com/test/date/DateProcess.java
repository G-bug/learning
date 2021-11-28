package com.test.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间处理
 * 1.8,使用 LocalDate,LocalTime 处理时间
 *
 *
 * @author g-bug
 * @date 2021/11/28 下午5:31
 */
public class DateProcess {

    public static void main(String[] args) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse("2021-12-26", dateTimeFormatter);

        System.out.println(LocalDate.now().compareTo(localDate));

        System.out.println(LocalDateTime.now());

        System.out.println(localDate.getYear());
    }

}
