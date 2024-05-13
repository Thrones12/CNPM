package com.example.WebBanVe.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.entity.Station;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IRouteService;
import com.example.WebBanVe.service.interf.IStationService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class TicketPlaneController {
	@Autowired
	private ICustomerService cusService;
	@Autowired
	private IStationService stationService;
	@Autowired
	private IRouteService routeService;

	@GetMapping(value = { "home", "index", "", "ticket-plane" })
	private String getHome(HttpServletRequest request, @RequestParam(defaultValue = "0") Long departure_id,
			@RequestParam(defaultValue = "0") Long arrival_id,
			@RequestParam(defaultValue = "2024-01-01") String departure_time, ModelMap model) {
		model.addAttribute("pageName", "plane");
		String cus_id = CookieManager.getCookieValue(request, "user_id");
		if (cus_id != null) {
			Long id = Long.parseLong(cus_id);
			model.addAttribute("user", cusService.getOne(id));
		}

		List<Station> departures = stationService.getByType(eTransportType.PLANE);
		if (departure_id == 0)
			departure_id = departures.get(0).getId();
		model.addAttribute("departures", departures);
		List<Station> arrivals = stationService.getByType(eTransportType.PLANE);
		if (arrival_id == 0)
			arrival_id = arrivals.get(0).getId();
		model.addAttribute("arrivals", arrivals);

		List<Object[]> routes = routeService.search(departure_id, arrival_id, departure_time, eTransportType.PLANE);

		model.addAttribute("routes", routes);

		return "web/views/home";
	}
}
