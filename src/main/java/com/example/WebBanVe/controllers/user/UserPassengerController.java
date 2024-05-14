package com.example.WebBanVe.controllers.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IPassengerService;
import com.example.WebBanVe.service.interf.ITicketService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller 
@RequestMapping("")
public class UserPassengerController {
    @Autowired
    private IPassengerService passengerService;
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private ICustomerService cusService;


    @GetMapping("/passenger")
    public String passenger(HttpServletRequest request, @RequestParam Long ticket_id, ModelMap model, HttpSession session) {
		// Handle page đã chọn trên header
		model.addAttribute("pageName", "");
		// Get user_id từ cookie và thêm nó vào attribute
		String cus_id = CookieManager.getCookieValue(request, "user_id");
		if (cus_id != null) {
			Long id = Long.parseLong(cus_id);
			model.addAttribute("user", cusService.getOne(id));
	        Passenger passenger = new Passenger();
	        model.addAttribute("passenger", passenger);
	        Ticket ticket = ticketService.getOne(ticket_id);
	        session.setAttribute("ticket", ticket);
	        return "web/views/userPassenger";
		}
		return "redirect:login";
    }

    @PostMapping("/userpassenger")
    public String createPassengerUser(@ModelAttribute("passenger") Passenger passenger, Model model, HttpSession session) {
    
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        if (ticket != null && passengerService.insert(passenger)) {
            model.addAttribute("passenger", passenger);   
            model.addAttribute("ticket", ticket);  
            return "web/views/userOrder";
        }
        return "redirect:/userpassenger?ticket_id=" + ticket.getId();
    }
}
