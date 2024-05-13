package com.example.WebBanVe.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter {
	public static LocalDate convertStringToLocalDate(String date) {

        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi chuỗi thành LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        
        return localDate;
	}
	public static String convertLocalDateTimeToDateString(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, 'ngày 'd', tháng 'M', năm 'yyyy", Locale.forLanguageTag("vi-VN"));
        return localDateTime.format(formatter);
    }
	public static String convertLocalDateTimeToTimeString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }
	public static LocalDateTime addLocalTimeToLocalDateTime(LocalDateTime localDateTime, LocalTime localTime) {
        return localDateTime.plusHours(localTime.getHour())
                .plusMinutes(localTime.getMinute())
                .plusSeconds(localTime.getSecond());
    }
}
