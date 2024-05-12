package com.example.WebBanVe.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
	public static LocalDate convertStringToLocalDate(String date) {

        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi chuỗi thành LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        
        return localDate;
	}
}
