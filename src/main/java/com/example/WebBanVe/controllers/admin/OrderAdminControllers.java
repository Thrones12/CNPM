package com.example.WebBanVe.controllers.admin;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebBanVe.Enumeration.eTicketStatus;
import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.Utils.DateTimeConverter;
import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IOrderService;
import com.example.WebBanVe.service.interf.IPassengerService;
import com.example.WebBanVe.service.interf.ITicketService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class OrderAdminControllers {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IPassengerService passgengerService;
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private ICustomerService customerService;

	@GetMapping("/order")
	public String order(ModelMap model, @ModelAttribute("thongbao") String tb) {
		model.addAttribute("pageName", "order");
		List<Order> list = orderService.getAll();
		if (!tb.isEmpty()) {
			model.addAttribute("thongbao", tb);
		}
		model.addAttribute("list", list);
		return "admin/views/order/order";
	}

	@GetMapping("detail-order")
	public String getOrderDetail(HttpServletRequest request, @RequestParam Long order_id, ModelMap model) {
		try {
			// Handle page đã chọn trên header
			model.addAttribute("pageName", "");
			// Get user_id từ cookie và thêm nó vào attribute
			String cus_id = CookieManager.getCookieValue(request, "user_id");
			if (cus_id != null) {
				Long id = Long.parseLong(cus_id);
				model.addAttribute("user", customerService.getOne(id));
			}
			// Handle order
			Order order = orderService.getOne(order_id);
			model.addAttribute("order", order);
			LocalDateTime date = order.getTicket().getRoute().getDepartureTime();
			model.addAttribute("departure_date", DateTimeConverter.convertLocalDateTimeToDateString(date));
			model.addAttribute("departure_time", DateTimeConverter.convertLocalDateTimeToTimeString(date));
			model.addAttribute("arrival_time", DateTimeConverter.convertLocalDateTimeToTimeString(
					DateTimeConverter.addLocalTimeToLocalDateTime(date, order.getTicket().getRoute().getDuration())));
			return "admin/views/order/detailOrder";
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}
}
