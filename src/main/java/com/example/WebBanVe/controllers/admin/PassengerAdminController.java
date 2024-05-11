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
import com.example.WebBanVe.service.interf.IPassengerService;
import com.example.WebBanVe.entity.Passenger;

@Controller 
@RequestMapping("/admin")
public class PassengerAdminController {
	@Autowired
	private IPassengerService passengerService;

	@GetMapping("/passenger")
	public String passenger(ModelMap model) {
		List<Passenger> list= passengerService.getAll();
		model.addAttribute("list", list);
		return "admin/passenger/passenger";
	}
	@GetMapping("/update-passenger/{id}")
	public String updatePassenger(Model model, @PathVariable("id") Long id) {
		Passenger passenger = passengerService.getOne(id);
		model.addAttribute("passenger", passenger);
		return "admin/passenger/updatePassenger";		
	}
	@PostMapping("/update-passenger")
	public String updatePassenger(@ModelAttribute("passenger") Passenger passenger) {
	    if (passengerService.update(passenger)) {
	        return "redirect:/admin/passenger";
	    }	    
	    return "redirect:/admin/update-passenger";
	}
	@GetMapping("/delete-passenger/{id}")
	public String deletePassenger(@ModelAttribute("id") Long id) {
		if(passengerService.delete(id)) {
			return "redirect:/admin/passenger";
		}
		
		return "redirect:/admin/passenger";
	}
	@GetMapping("/create-passenger")
	public String addPassenger(Model model) {
		Passenger passenger = new Passenger();
		model.addAttribute("passenger", passenger);
		return "/admin/passenger/createPassenger";
	}
	
	
	@PostMapping("/create-passenger")
	public String createPassenger(@ModelAttribute("passenger") Passenger passenger ) {
		if(passengerService.insert(passenger)) {
			return "redirect:/admin/passenger";
		}
		
		return "redirect:/admin/create-passenger";
	}
}
