package com.example.WebBanVe.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.Utils.Validator.CustomerValidatorManager;
import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IOrderService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class CustomerController {
	@Autowired
	private ICustomerService cusService;
	@Autowired
	private IOrderService orderService;
	
	@GetMapping("profile")
	public String getProfile(HttpServletRequest request, ModelMap model) {
		// Handle page đã chọn trên header
		model.addAttribute("pageName", "");
		// Get user_id từ cookie và thêm nó vào attribute
		String cus_id = CookieManager.getCookieValue(request, "user_id");
		if (cus_id != null) {
			Long id = Long.parseLong(cus_id);
			model.addAttribute("user", cusService.getOne(id));
		}
		return "web/views/customer/profile";
	}
	
	@GetMapping("order-history")
	public String getOrderHistory(HttpServletRequest request, ModelMap model) {
		// Handle page đã chọn trên header
		model.addAttribute("pageName", "");
		// Get user_id từ cookie và thêm nó vào attribute
		String cus_id = CookieManager.getCookieValue(request, "user_id");
		if (cus_id != null) {
			Long id = Long.parseLong(cus_id);
			model.addAttribute("user", cusService.getOne(id));
			List<Order> orders = orderService.getAllByCustomer(id);
			model.addAttribute("orders", orders);
		}
		return "web/views/customer/order-history";
	}
	
	@PostMapping("profile")
	public String postProfile(@ModelAttribute("user") Customer customer, ModelMap model) {
		String error = CustomerValidatorManager.getInstance().validate(cusService, new String[] {"", customer.getEmail()});
		if (cusService.getOne(customer.getId()).getEmail().equals(customer.getEmail()))
			error = null;
		if (error==null) {
			cusService.update(customer);
			model.addAttribute("message", "Cập nhập thành công");
		}
		else {
			model.addAttribute("error", error);
		}
		return "web/views/customer/profile";
	}

	
}
