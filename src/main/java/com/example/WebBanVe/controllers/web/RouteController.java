package com.example.WebBanVe.controllers.web;

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
    public String getAllRoutes(Model model) {
        List<Route> routes = routeService.getAll();
        model.addAttribute("routes", routes);
        return "admin/routes";
    }

    @GetMapping("/route/{id}")
    public String getRouteDetails(@PathVariable Long id, Model model) {
        Route route = routeService.getOne(id);
        model.addAttribute("route", route);
        return "admin/route_detail";
    }

    @GetMapping("/route/create")
    public String createRouteForm(Model model) {
        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("route", new Route());
        return "admin/create_route";
    }

    @PostMapping("/route/create")
    public String createRoute(@ModelAttribute Route route, Model model) {
        routeService.insert(route);
        return "redirect:/admin/route";
    }

    @GetMapping("/route/edit/{id}")
    public String editRouteForm(@PathVariable Long id, Model model) {
        Route route = routeService.getOne(id);
        model.addAttribute("stations", stationService.getAll());
        model.addAttribute("route", route);
        return "admin/edit_route";
    }

    @PostMapping("/route/edit")
    public String updateRoute(@ModelAttribute Route route, Model model) {
        routeService.update(route);
        return "redirect:/admin/route";
    }

    @GetMapping("/route/delete/{id}")
    public String deleteRoute(@PathVariable Long id, Model model) {
        routeService.delete(id);
        return "redirect:/admin/route";
    }
}
