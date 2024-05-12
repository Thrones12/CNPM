package com.example.WebBanVe.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.entity.Route;
import com.example.WebBanVe.entity.Station;
import com.example.WebBanVe.service.interf.IRouteService;
import com.example.WebBanVe.service.interf.IStationService;

@Controller
@RequestMapping("/")
public class TicketPlaneController {
	@Autowired
	private IStationService stationService;
	@Autowired
	private IRouteService routeService;

	@GetMapping(value = { "home", "index", "", "ticket-plane" })
	private String getHome(@RequestParam(defaultValue = "0") Long departure_id,
			@RequestParam(defaultValue = "0") Long arrival_id, ModelMap model) {
		List<Station> departures = stationService.getAll();
		model.addAttribute("departures", departures);
		List<Station> arrivals = stationService.getAll();
		model.addAttribute("arrivals", arrivals);

		List<Route> routes = routeService.search(departure_id, arrival_id);
		if (routes.size() > 0) {
			model.addAttribute("routes", routes);
			System.out.println(routes.get(0).getName());
		}
		System.out.println(departure_id);

		return "web/views/home";
	}
}
