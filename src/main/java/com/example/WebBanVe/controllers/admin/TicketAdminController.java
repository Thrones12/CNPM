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
	public String ticket(ModelMap model, @ModelAttribute("thongbao") String tb) {
		model.addAttribute("pageName", "ticket");
		List<Ticket> list = ticketService.getAll();
		if (!tb.isEmpty()) {
			model.addAttribute("thongbao", tb);
		}
		model.addAttribute("list", list);
		return "admin/views/ticket/list";
	}

	@GetMapping("/update-ticket/{id}")
	public String updateTicket(Model model, @PathVariable("id") Long id,@ModelAttribute("thongbao") String tb) {
		model.addAttribute("pageName", "ticket");
		model.addAttribute("routes", routeService.getAll());
		model.addAttribute("transports", transportService.getAll());
		if (!tb.isEmpty()) {
	        model.addAttribute("thongbao", tb);
	    }
		Ticket ticket = ticketService.getOne(id);
		model.addAttribute("ticket", ticket);
		return "admin/views/ticket/updateTicket";
	}

	@PostMapping("/update-ticket")
	public String updateTicket(@ModelAttribute("ticket") Ticket ticket, RedirectAttributes redirectAttributes,
			ModelMap model) {
		model.addAttribute("pageName", "ticket");
		if (ticketService.update(ticket)) {
			redirectAttributes.addFlashAttribute("thongbao", "Vé được sửa thành công!");
			return "redirect:/admin/ticket";
		}
		return "redirect:/admin/update-ticket";
	}

	@GetMapping("/delete-ticket/{id}")
	public String deleteTicket(@ModelAttribute("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("pageName", "ticket");
		if (ticketService.delete(id)) {
			redirectAttributes.addFlashAttribute("thongbao", "Xoa thanh cong!");
			return "redirect:/admin/ticket";
		}
		redirectAttributes.addFlashAttribute("thongbao", "vé không hợp lệ!");
		return "redirect:/admin/ticket";
	}

	@GetMapping("/create-ticket")
	public String addTicket(Model model, @ModelAttribute("thongbao") String tb) {
		model.addAttribute("pageName", "ticket");
		Ticket ticket = new Ticket();
		if (!tb.isEmpty()) {
	        model.addAttribute("thongbao", tb);
	    }
		model.addAttribute("routes", routeService.getAll());
		model.addAttribute("transports", transportService.getAll());
		model.addAttribute("ticket", ticket);
		return "admin/views/ticket/createTicket";
	}

	@PostMapping("/create-ticket")
	public String createTicket(@ModelAttribute("ticket") Ticket ticket, RedirectAttributes redirectAttributes,
			Model model) {
		model.addAttribute("pageName", "ticket");
		if (ticketService.insert(ticket)) {
			redirectAttributes.addFlashAttribute("thongbao", "Vé được thêm thành công!");
			return "redirect:/admin/ticket";
		}
		redirectAttributes.addFlashAttribute("thongbao", "vé không hợp lệ!");
		return "redirect:/admin/createTicket";
	}
}
