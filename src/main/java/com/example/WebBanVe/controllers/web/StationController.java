package com.example.WebBanVe.controllers.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanVe.entity.Station;
import com.example.WebBanVe.service.interf.IStationService;

@Controller
@RequestMapping("/admin")
public class StationController 
{
	@Autowired
	private IStationService service;
	
	@GetMapping("/station")
	public String getAll(Model model) 
	{		
	    List<Station> STATION = service.getAll();
	    model.addAttribute("STATION", STATION);
	    return "admin/stations";
	}

	@GetMapping("/station/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        Station station = service.getOne(id);
        model.addAttribute("station", station);
        return "admin/station_detail";
    }

    @GetMapping("/station/create")
    public String createStationForm(Model model) {
        model.addAttribute("station", new Station());
        return "admin/create_station";
    }

    @PostMapping("/station/create")
    public String insert(@ModelAttribute Station station, Model model)	// nhận @ModelAttribute Station station từ form.
    {	
        service.insert(station);
        return "redirect:/admin/station";
    }

    @GetMapping("/station/edit/{id}")
    public String editStationForm(@PathVariable Long id, Model model) {
        Station station = service.getOne(id);
        model.addAttribute("station", station);
        return "admin/edit_station";
    }

    @PostMapping("/station/edit")
    public String update(@ModelAttribute Station station, Model model) {
        service.update(station);
        return "redirect:/admin/station";
    }

    @GetMapping("/station/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        service.delete(id);
        return "redirect:/admin/station";
    }
}