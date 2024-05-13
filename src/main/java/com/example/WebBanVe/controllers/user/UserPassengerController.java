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

import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.service.interf.IPassengerService;


	@Controller 
	@RequestMapping("/user")
public class UserPassengerController {
		@Autowired
		private IPassengerService passengerService;
		
		@GetMapping("/userpassenger")
		public String order(ModelMap model) {
			Passenger passenger = new Passenger();
			model.addAttribute("passenger", passenger);
			return "user/userPassenger";
		}
		@PostMapping("/userpassenger")
		public String createPassenger(@ModelAttribute("passenger") Passenger passenger ) {
			  if(passengerService.insert(passenger)) 
				  return "/user/userpassenger";
		        
			  return "/user/userpassenger";
		    }
	

}
