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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IOrderService;
import com.example.WebBanVe.service.interf.IPassengerService;
import com.example.WebBanVe.service.interf.ITicketService;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.entity.Passenger;

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
	public String order(ModelMap model,@ModelAttribute("thongbao") String tb) {
		List<Order> list= orderService.getAll();
		if (!tb.isEmpty()) {
	        model.addAttribute("thongbao", tb);
	    }
		model.addAttribute("list", list);
		return "admin/order/order";
	}
	@GetMapping("/update-order/{id}")
	public String updateOrder(Model model, @PathVariable("id") Long id) {
		Order order = orderService.getOne(id);	
		model.addAttribute("order", order);
		Ticket ticket= order.getTicket();
		List<Passenger> passengers = passgengerService.getNotInOrder(order.getPassenger().getId());
		 model.addAttribute("customers", customerService.getAll());
	    model.addAttribute("passengers", passengers); 
	
	    List<Ticket> tickets = ticketService.getAllstatusCr(ticket);
		    model.addAttribute("tickets", tickets); 
		return "admin/order/updateOrder";		
	}
	@PostMapping("/update-order")
	public String updateOrder(@ModelAttribute("order") Order order, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(order.getTicket().getReservationCode());

		if (orderService.update(order)) {
	    	  
			redirectAttributes.addFlashAttribute("thongbao", "Đơn đặt được cập nhật thành công!");

	        return "redirect:/admin/order";
	    }	   
	    model.addAttribute("Update Failed", true);

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
	    List<Passenger> passengers = passgengerService.getNotInOrder();
	    model.addAttribute("passengers", passengers); 
	    List<Ticket> tickets = ticketService.getAllstatus();
	    model.addAttribute("customers", customerService.getAll());
	    model.addAttribute("tickets", tickets); 
	    return "/admin/order/createOrder";
	}
	
	
	@PostMapping("/create-order")
	public String createOrder(@ModelAttribute("order") Order order, RedirectAttributes redirectAttributes ) {
		System.out.println(order.getTicket().getReservationCode());

		if(orderService.insert(order)) {
			Ticket ticket= ticketService.getOne(order.getTicket().getId());
			 Ticket.eStatus status = Ticket.eStatus.BOOKED;
			ticket.setStatus(status);
			ticketService.update(ticket);
			   redirectAttributes.addFlashAttribute("thongbao", "Đơn đặt được tạo thành công!");
			return "redirect:/admin/order";
		}
		return "redirect:/admin/create-order";
	}
}
