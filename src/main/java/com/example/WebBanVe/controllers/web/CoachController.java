package com.example.WebBanVe.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.entity.Route;
import com.example.WebBanVe.entity.Station;
import com.example.WebBanVe.service.interf.IRouteService;
import com.example.WebBanVe.service.interf.IStationService;

@Controller
@RequestMapping("/")
public class CoachController {

	@Autowired
	private IStationService stationService;
	@Autowired
	private IRouteService routeService;

	@GetMapping(value = { "ticket-coach" })
	private String getTicketTrain(@RequestParam(defaultValue = "0") Long departure_id,
			@RequestParam(defaultValue = "0") Long arrival_id,
			@RequestParam(defaultValue = "2024-01-01") String departure_time, ModelMap model) {
		model.addAttribute("pageName", "coach");
		List<Station> departures = stationService.getByType(eTransportType.COACH);
		if (departure_id == 0)
			departure_id = departures.get(0).getId();
		model.addAttribute("departures", departures);
		List<Station> arrivals = stationService.getByType(eTransportType.COACH);
		if (arrival_id == 0)
			arrival_id = arrivals.get(0).getId();
		model.addAttribute("arrivals", arrivals);

		List<Object[]> routes = routeService.search(departure_id, arrival_id, departure_time, eTransportType.COACH);

		model.addAttribute("routes", routes);

		return "web/views/ticket-coach";
	}
}
