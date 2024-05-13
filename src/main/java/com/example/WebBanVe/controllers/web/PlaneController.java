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
public class PlaneController {
	@Autowired
	private ICustomerService cusService;
	@Autowired
	private IStationService stationService;
	@Autowired
	private IRouteService routeService;

	@GetMapping(value = { "home", "index", "", "ticket-plane" })
	private String getHome(HttpServletRequest request, @RequestParam(defaultValue = "0") Long departure_id,
			@RequestParam(defaultValue = "0") Long arrival_id, @RequestParam(defaultValue = "") String departure_time,
			ModelMap model) {
		// Handle page đã chọn trên header
		model.addAttribute("pageName", "plane");
		// Get user_id từ cookie và thêm nó vào attribute
		String cus_id = CookieManager.getCookieValue(request, "user_id");
		if (cus_id != null) {
			Long id = Long.parseLong(cus_id);
			model.addAttribute("user", cusService.getOne(id));
		}
		// Handle điểm đến
		List<Station> departures = stationService.getByType(eTransportType.PLANE);
		if (departure_id == 0)
			departure_id = departures.get(0).getId();
		model.addAttribute("departures", departures);
		model.addAttribute("dSelected", departure_id); // Lưu điểm đến đã chọn để hiển thị

		// Handle điểm đi
		List<Station> arrivals = stationService.getByType(eTransportType.PLANE);
		if (arrival_id == 0)
			arrival_id = arrivals.get(0).getId();
		model.addAttribute("arrivals", arrivals);
		model.addAttribute("aSelected", arrival_id); // Lưu điểm đi đã chọn để hiển thị

		// Handle thời gian khởi hành
		if (departure_time == "" || departure_time.isEmpty()) {
			model.addAttribute("message", "Vui lòng chọn ngày đi!"); // Chưa chọn thời gian thì báo lỗi
		} else {
			// Nếu đã chọn thì tìm tuyến đường nào còn vé thì hiển thị
			List<Object[]> routes = routeService.search(departure_id, arrival_id, departure_time, eTransportType.PLANE);

			model.addAttribute("tSelected", departure_time); // Lưu thời gian đã chọn để hiển thị
			if (routes.size()==0)
				model.addAttribute("message", "Không có vé phù hợp!"); // Không có tuyến nào còn vé thì thông báo
			model.addAttribute("routes", routes);
		}

		return "web/views/home";
	}
}
