package com.example.WebBanVe.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanVe.entity.Route;
import com.example.WebBanVe.service.interf.IRouteService;
import com.example.WebBanVe.service.interf.IStationService;

@Controller
@RequestMapping("/admin")
public class RouteController {

    @Autowired
    private IRouteService routeService;

    @Autowired
    private IStationService stationService;

    @GetMapping("/route")
    public String getAllRoutes(Model model) 
	{
		model.addAttribute("pageName", "route");
        List<Route> routes = routeService.getAll();
        model.addAttribute("routes", routes);
        return "admin/views/route/routes";
    }

    @GetMapping("/route/{id}")
    public String getRouteDetails(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "route");
        Route route = routeService.getOne(id);
        model.addAttribute("route", route);
        return "admin/views/route/route_detail";
    }

    @GetMapping("/route/create")
    public String createRouteForm(Model model) {
		model.addAttribute("pageName", "route");
        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("route", new Route());
        return "admin/views/route/create_route";
    }

    @PostMapping("/route/create")
    public String createRoute(@ModelAttribute Route route, Model model) {
		model.addAttribute("pageName", "route");
        routeService.insert(route);
        return "redirect:/admin/route";
    }

    @GetMapping("/route/edit/{id}")
    public String editRouteForm(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "route");
        Route route = routeService.getOne(id);
        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("route", route);
        return "admin/views/route/edit_route";
    }

    @PostMapping("/route/edit")
    public String updateRoute(@ModelAttribute Route route, Model model) {
		model.addAttribute("pageName", "route");
        routeService.update(route);
        return "redirect:/admin/route";
    }

    @GetMapping("/route/delete/{id}")
    public String deleteRoute(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "route");
        routeService.delete(id);
        return "redirect:/admin/route";
    }
}