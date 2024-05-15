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

import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.entity.Location;
import com.example.WebBanVe.entity.Post;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.ILocationService;
import com.example.WebBanVe.service.interf.IPostService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class PostController {
	@Autowired
	private ILocationService loService;
	@Autowired
	private IPostService postService;
	@Autowired
	private ICustomerService cusService;

	@GetMapping("post")
	public String getPost(HttpServletRequest request, @RequestParam(defaultValue = "") String pSelected,
			@RequestParam(defaultValue = "") String dSelected, @RequestParam(defaultValue = "") String wSelected,
			@RequestParam(defaultValue = "") String street, @RequestParam Long location_id, ModelMap model) {
		try {
			model.addAttribute("pageName", "blog");
			model.addAttribute("datenow", LocalDate.now());
			// Get user_id từ cookie và thêm nó vào attribute
			String cus_id = CookieManager.getCookieValue(request, "user_id");
			if (cus_id != null) {
				Long id = Long.parseLong(cus_id);
				model.addAttribute("user", cusService.getOne(id));
			}
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

			List<Post> posts = postService.getAll(location_id);
			model.addAttribute("posts", posts);
			
			return "web/views/post";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}
}
