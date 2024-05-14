package com.example.WebBanVe.controllers.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Utils.DateTimeConverter;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.service.interf.ITicketService;

@Controller
@RequestMapping("/")
public class TicketController {
	@Autowired
	private ITicketService service;

	@GetMapping("ticket")
	public String getTicket(@RequestParam Long route_id, @RequestParam Long transport_id, ModelMap model) {
		try {
			Ticket ticket = service.getOne(route_id, transport_id);
			LocalDateTime date = ticket.getRoute().getDepartureTime();
			model.addAttribute("ticket", ticket);
			model.addAttribute("departure_date", DateTimeConverter.convertLocalDateTimeToDateString(date));
			model.addAttribute("departure_time", DateTimeConverter.convertLocalDateTimeToTimeString(date));
			model.addAttribute("arrival_time", DateTimeConverter.convertLocalDateTimeToTimeString(
					DateTimeConverter.addLocalTimeToLocalDateTime(date, ticket.getRoute().getDuration())));
			return "web/views/ticket";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}
}