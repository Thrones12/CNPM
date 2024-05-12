package com.example.WebBanVe.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.entity.Location;
import com.example.WebBanVe.service.interf.ILocationService;

@Controller
@RequestMapping("/")
public class BlogController {
	@Autowired
	private ILocationService loService;

	@GetMapping("blog")
	public String getBlog(@RequestParam(defaultValue="") String pSelected,
			@RequestParam(defaultValue="") String dSelected, @RequestParam(defaultValue="") String wSelected,
			@RequestParam(defaultValue="") String street, ModelMap model) {
		model.addAttribute("pageName", "blog");
		List<String> provinces = loService.getProvince();
		model.addAttribute("provinces", provinces);
		if (pSelected.isEmpty())
			pSelected = provinces.get(0);
		model.addAttribute("pSelected", pSelected);
		
		List<String> districts = loService.getDistrict(pSelected);
		model.addAttribute("districts", districts);
		if (dSelected.isEmpty())
			dSelected = districts.get(0);
		model.addAttribute("dSelected", dSelected);
		
		List<String> wards = loService.getWard(pSelected, dSelected);
		model.addAttribute("wards", wards);
		if (wSelected.isEmpty())
			wSelected = wards.get(0);
		model.addAttribute("wSelected", wSelected);

		System.out.println(pSelected);
		System.out.println(dSelected);
		System.out.println(wSelected);
		
		List<Location> locations = loService.search(pSelected, dSelected, wSelected);
		model.addAttribute("locations", locations);

		return "web/views/blog";
	}
}
