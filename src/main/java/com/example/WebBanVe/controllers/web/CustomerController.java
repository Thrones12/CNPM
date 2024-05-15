package com.example.WebBanVe.controllers.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Enumeration.eGender;
import com.example.WebBanVe.Enumeration.eOrderStatus;
import com.example.WebBanVe.Enumeration.eTicketStatus;
import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.Utils.DateTimeConverter;
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
		try {
			// Handle page đã chọn trên header
			model.addAttribute("pageName", "");
			// Get user_id từ cookie và thêm nó vào attribute
			String cus_id = CookieManager.getCookieValue(request, "user_id");
			if (cus_id != null) {
				Long id = Long.parseLong(cus_id);
				Customer customer = cusService.getOne(id);
				model.addAttribute("user", customer);
			}
			return "web/views/customer/profile";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}

	@GetMapping("order-history")
	public String getOrderHistory(HttpServletRequest request, ModelMap model) {
		try {
			// Handle page đã chọn trên header
			model.addAttribute("pageName", "");
			// Get user_id từ cookie và thêm nó vào attribute
			String cus_id = CookieManager.getCookieValue(request, "user_id");
			if (cus_id != null) {
				Long id = Long.parseLong(cus_id);
				model.addAttribute("user", cusService.getOne(id));
				List<Order> orders = new ArrayList<Order>();
				for (Order o : orderService.getAllByCustomer(id)) {
					if (o.getStatus() != eOrderStatus.REFUND)
						orders.add(o);
				}
				model.addAttribute("orders", orders);
			}
			return "web/views/customer/order-history";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}

	}

	@GetMapping("order-detail")
	public String getOrderDetail(HttpServletRequest request, @RequestParam Long order_id, ModelMap model) {
		try {
			// Handle page đã chọn trên header
			model.addAttribute("pageName", "");
			// Get user_id từ cookie và thêm nó vào attribute
			String cus_id = CookieManager.getCookieValue(request, "user_id");
			if (cus_id != null) {
				Long id = Long.parseLong(cus_id);
				model.addAttribute("user", cusService.getOne(id));
			}
			// Handle order
			Order order = orderService.getOne(order_id);
			model.addAttribute("order", order);
			LocalDateTime date = order.getTicket().getRoute().getDepartureTime();
			model.addAttribute("departure_date", DateTimeConverter.convertLocalDateTimeToDateString(date));
			model.addAttribute("departure_time", DateTimeConverter.convertLocalDateTimeToTimeString(date));
			model.addAttribute("arrival_time", DateTimeConverter.convertLocalDateTimeToTimeString(
					DateTimeConverter.addLocalTimeToLocalDateTime(date, order.getTicket().getRoute().getDuration())));
			return "web/views/customer/order-detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}

	@PostMapping("profile")
	public String postProfile(@ModelAttribute("user") Customer customer, ModelMap model) {
		try {
			String error = CustomerValidatorManager.getInstance().validate(cusService,
					new String[] { "", customer.getEmail() });
			if (cusService.getOne(customer.getId()).getEmail().equals(customer.getEmail()))
				error = null;
			if (error == null) {
				cusService.update(customer);
				model.addAttribute("message", "Cập nhập thành công");
			} else {
				model.addAttribute("error", error);
			}
			return "web/views/customer/profile";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}

	@GetMapping("order-refund")
	public String getRefundOrder(@RequestParam Long id, ModelMap model) {
		try {
			Order order = orderService.getOne(id);
			order.setStatus(eOrderStatus.REFUND);
			order.getTicket().setStatus(eTicketStatus.ACTIVE);
			orderService.update(order);
			return "redirect:order-history";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}
}