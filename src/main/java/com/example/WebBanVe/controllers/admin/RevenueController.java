package com.example.WebBanVe.controllers.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.service.interf.IOrderService;

@Controller
@RequestMapping("/")
public class RevenueController {
    @Autowired
    private IOrderService orderService;
	@GetMapping("admin/revenue")
	public String getRevenue(@RequestParam(value = "year", required = false) Integer year, ModelMap model) {
		model.addAttribute("pageName", "revenue");
		if (year == null) {
            year = LocalDate.now().getYear(); // Default to the current year if no year is selected
        }
        
        Map<String, Double> monthlyRevenue = orderService.getMonthlyRevenueByYear(year);
        List<Integer> years = orderService.getAvailableYears();

        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("years", years);
        model.addAttribute("selectedYear", year);
		return "admin/views/revenue/revenue";
	}
}
