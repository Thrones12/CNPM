package com.example.WebBanVe.controllers.user;


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

import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.service.interf.IPassengerService;
import com.example.WebBanVe.service.interf.ITicketService;

import jakarta.servlet.http.HttpSession;
@Controller 
@RequestMapping("/user")
public class UserPassengerController {
    @Autowired
    private IPassengerService passengerService;
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/userpassenger/{ticket_id}")
    public String passenger(ModelMap model, @PathVariable("ticket_id") Long id, HttpSession session) {
        Passenger passenger = new Passenger();
        model.addAttribute("passenger", passenger);
        Ticket ticket = ticketService.getOne(id);
        session.setAttribute("ticket", ticket);
        return "user/userPassenger";
    }

    @PostMapping("/userpassenger")
    public String createPassengerUser(@ModelAttribute("passenger") Passenger passenger, Model model, HttpSession session) {
    
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        if (ticket != null && passengerService.insert(passenger)) {
            model.addAttribute("passenger", passenger);   
            model.addAttribute("ticket", ticket);  
            return "user/userOrder";
        }
        return "redirect:/user/userpassenger/" + ticket.getId();
    }
}
