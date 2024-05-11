package com.example.WebBanVe.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IOrderService;
import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.entity.Order;

@Controller 
@RequestMapping("/admin")
public class OrderAdminControllers {
	@Autowired
	private IOrderService orderService;

	@GetMapping("/order")
	public String order(ModelMap model) {
		List<Order> list= orderService.getAll();
		model.addAttribute("list", list);
		return "admin/order/order";
	}
	@GetMapping("/update-order/{id}")
	public String updateOrder(Model model, @PathVariable("id") Long id) {
		Order order = orderService.getOne(id);
		model.addAttribute("order", order);
		return "admin/order/updateOrder";		
	}
	@PostMapping("/update-order")
	public String updateOrder(@ModelAttribute("order") Order order) {
	    if (orderService.update(order)) {
	        return "redirect:/admin/order";
	    }	    
	    return "redirect:/admin/update-order";
	}
	@GetMapping("/delete-order/{id}")
	public String deleteOrder(@ModelAttribute("id") Long id) {
		if(orderService.delete(id)) {
			return "redirect:/admin/order";
		}
		
		return "redirect:/admin/order";
	}
	@GetMapping("/create-order")
	public String addOrder(Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		return "/admin/order/createOrder";
	}
	
	
	@PostMapping("/create-order")
	public String createOrder(@ModelAttribute("order") Order order ) {
		if(orderService.insert(order)) {
			return "redirect:/admin/order";
		}
		
		return "redirect:/admin/create-order";
	}
}
