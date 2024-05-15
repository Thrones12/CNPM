package com.example.WebBanVe.controllers.admin;

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
		model.addAttribute("pageName", "station");
	    List<Station> STATION = service.getAll();
	    model.addAttribute("STATION", STATION);
	    return "admin/views/station/stations";
	}

	@GetMapping("/station/{id}")
    public String getOne(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "station");
        Station station = service.getOne(id);
        model.addAttribute("station", station);
        return "admin/views/station/station_detail";
    }

    @GetMapping("/station/create")
    public String createStationForm(Model model) {
		model.addAttribute("pageName", "station");
        model.addAttribute("station", new Station());
        return "admin/views/station/create_station";
    }

    @PostMapping("/station/create")
    public String insert(@ModelAttribute Station station, Model model)	// nhận @ModelAttribute Station station từ form.
    {	
		model.addAttribute("pageName", "station");
        service.insert(station);
        return "redirect:/admin/station";
    }

    @GetMapping("/station/edit/{id}")
    public String editStationForm(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "station");
        Station station = service.getOne(id);
        model.addAttribute("station", station);
        return "admin/views/station/edit_station";
    }

    @PostMapping("/station/edit")
    public String update(@ModelAttribute Station station, Model model) {
		model.addAttribute("pageName", "station");
        service.update(station);
        return "redirect:/admin/station";
    }

    @GetMapping("/station/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "station");
        service.delete(id);
        return "redirect:/admin/station";
    }
}