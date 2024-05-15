package com.example.WebBanVe.controllers.web;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Enumeration.eTicketClass;
import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.Utils.DateTimeConverter;
import com.example.WebBanVe.entity.Route;
import com.example.WebBanVe.entity.Station;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IRouteService;
import com.example.WebBanVe.service.interf.IStationService;
import com.example.WebBanVe.service.interf.ITicketService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class CoachController {
	@Autowired
	private ICustomerService cusService;
	@Autowired
	private IStationService stationService;
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IRouteService routeService;

	@GetMapping(value = { "ticket-coach" })
	private String getTicketTrain(HttpServletRequest request, @RequestParam(defaultValue = "0") Long departure_id,
			@RequestParam(defaultValue = "0") Long arrival_id, @RequestParam(defaultValue = "") String departure_time,
			@RequestParam(defaultValue = "") String tcSelected, ModelMap model) {
		try {
			// Handle page đã chọn trên header
			model.addAttribute("pageName", "coach");
			model.addAttribute("datenow", LocalDate.now());
			// Get user_id từ cookie và thêm nó vào attribute
			String cus_id = CookieManager.getCookieValue(request, "user_id");
			if (cus_id != null) {
				Long id = Long.parseLong(cus_id);
				model.addAttribute("user", cusService.getOne(id));
			}
			// Handle điểm đến
			List<Station> departures = stationService.getByType(eTransportType.COACH);
			if (departure_id == 0)
				departure_id = departures.get(0).getId();
			model.addAttribute("departures", departures);
			model.addAttribute("dSelected", departure_id); // Lưu điểm đến đã chọn để hiển thị

			// Handle điểm đi
			List<Station> arrivals = stationService.getByType(eTransportType.COACH);
			if (arrival_id == 0)
				arrival_id = arrivals.get(0).getId();
			model.addAttribute("arrivals", arrivals);
			model.addAttribute("aSelected", arrival_id); // Lưu điểm đi đã chọn để hiển thị

			// Handle hạng vé
			List<String> ticketClass = new ArrayList<>();
			for (eTicketClass c : eTicketClass.values()) {
				ticketClass.add(c.name());
			}
			if (tcSelected.equals("") || tcSelected.isEmpty())
				tcSelected = ticketClass.get(0);
			model.addAttribute("ticketClass", ticketClass);
			model.addAttribute("tcSelected", tcSelected); // Lưu hạng vé đã chọn để hiển thị

			// Handle thời gian khởi hành
			if (departure_time == "" || departure_time.isEmpty()) {
				departure_time = LocalDate.now().toString();
			} else {
				// Nếu đã chọn thì tìm tuyến đường nào còn vé thì hiển thị
				// Object[]: {route_id, transport_id, route_name, transport_name}
				List<Object[]> objects = routeService.search(departure_id, arrival_id, departure_time,
						eTransportType.COACH, tcSelected);

				// Chuyển objects thành đối tượng phù hợp để đưa lên views
				// results: {transport_name, route_name, departure_name, arrival_name}
				List<Object[]> results = new ArrayList<>();
				for (Object[] ob : objects) {
					Object[] temp = new Object[9];
					temp[0] = ob[3];
					temp[1] = ob[2];
					Route route = routeService.getOne((Long) ob[0]);
					temp[2] = DateTimeConverter.convertLocalDateTimeToTimeString(route.getDepartureTime());
					temp[3] = DateTimeConverter.convertLocalDateTimeToTimeString(
							DateTimeConverter.addLocalTimeToLocalDateTime(route.getDepartureTime(), route.getDuration()));
					temp[4] = route.getDuration();
					Ticket ticket = ticketService.getOne(Long.parseLong(ob[0].toString()), Long.parseLong(ob[1].toString()));
					temp[5] = ticket.getPrice();
					temp[6] = ticket.getTicketClass().toString();
					temp[7] = ob[0];
					temp[8] = ob[1];
					results.add(temp);
				}
				model.addAttribute("tSelected", departure_time); // Lưu thời gian đã chọn để hiển thị
				if (results.size() == 0)
					model.addAttribute("message", "Không có vé phù hợp!"); // Không có tuyến nào còn vé thì thông báo
				model.addAttribute("objects", results);
			}

			return "web/views/ticket-coach";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}
}
