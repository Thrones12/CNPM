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

import com.example.WebBanVe.service.interf.IPassengerService;
import com.example.WebBanVe.service.interf.IRouteService;
import com.example.WebBanVe.service.interf.ITicketService;
import com.example.WebBanVe.service.interf.ITransportService;
import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.entity.Ticket;

@Controller 
@RequestMapping("/admin")
public class TicketAdminController {
	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private IRouteService routeService;
	@Autowired
	private ITransportService transportService;
	@GetMapping("/ticket")
	public String ticket(ModelMap model) {
		List<Ticket> list = ticketService.getAll();
		model.addAttribute("list", list);
		return "admin/ticket/ticket";
	}
	
	@GetMapping("/update-ticket/{id}")
	public String updateTicket(Model model, @PathVariable("id") Long id) {
		model.addAttribute("routes",routeService.getAll());
		model.addAttribute("transports",transportService.getAll());
		Ticket ticket = ticketService.getOne(id);
		model.addAttribute("ticket", ticket);
		return "admin/ticket/updateTicket";		
	}
	
	@PostMapping("/update-ticket")
	public String updateTicket(@ModelAttribute("ticket") Ticket ticket) {
	    if (ticketService.update(ticket)) {
	        return "redirect:/admin/ticket";
	    }	    
	    return "redirect:/admin/update-ticket";
	}
	
	@GetMapping("/delete-ticket/{id}")
	public String deleteTicket(@ModelAttribute("id") Long id) {
		if(ticketService.delete(id)) {
			return "redirect:/admin/ticket";
		}
		
		return "redirect:/admin/ticket";
	}
	
	@GetMapping("/create-ticket")
	public String addTicket(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("routes",routeService.getAll());
		model.addAttribute("transports",transportService.getAll());
		model.addAttribute("ticket", ticket);
		return "admin/ticket/createTicket";
	}
	
	@PostMapping("/create-ticket")
	public String createTicket(@ModelAttribute("ticket") Ticket ticket ) {
		if(ticketService.insert(ticket)) {
			return "redirect:/admin/ticket";
		}
		
		return "redirect:/admin/createTicket";
	}
}
