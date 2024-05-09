package com.example.WebBanVe.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.service.interf.ICustomerService;

@Controller
@RequestMapping("/")
public class CustomerAdminController {
	@Autowired
	private ICustomerService cusService;
	
	@GetMapping("admin/customer/list")
	public String getCustomer(ModelMap model) {
		List<Customer> customers = cusService.getAll();
		model.addAttribute("customers", customers);
		return "admin/views/customer/list";
	}
}
