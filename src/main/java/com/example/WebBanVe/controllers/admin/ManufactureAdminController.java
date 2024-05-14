package com.example.WebBanVe.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanVe.entity.Manufacture;
import com.example.WebBanVe.service.interf.IManufactureService;

@Controller
@RequestMapping("/admin")
public class ManufactureAdminController {
	@Autowired
	private IManufactureService service;

	@GetMapping("/manufacture")
	public String getAll(Model model) {
		model.addAttribute("pageName", "manufacture");
		List<Manufacture> MANUFACTURE = service.getAll();
		model.addAttribute("MANUFACTURE", MANUFACTURE);
		return "admin/views/manufacture/manufactures";
	}

	@GetMapping("/manufacture/{id}")
	public String getOneManufacture(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "manufacture");
		Manufacture manufacture = service.getOne(id);
		model.addAttribute("manufacture", manufacture);
		return "admin/views/manufacture/manufacture_detail";
	}

	@GetMapping("/manufacture/create")
	public String showCreateManufactureForm(Model model) {
		model.addAttribute("pageName", "manufacture");
		model.addAttribute("manufacture", new Manufacture());
		return "admin/views/manufacture/create_manufacture";
	}

	@PostMapping("/manufacture/create")
	public String createManufacture(@ModelAttribute Manufacture manufacture, Model model) {
		model.addAttribute("pageName", "manufacture");
		service.insert(manufacture);
		return "redirect:/admin/manufacture";
	}

	@GetMapping("/manufacture/edit/{id}")
	public String showEditManufactureForm(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "manufacture");
		Manufacture manufacture = service.getOne(id);
		model.addAttribute("manufacture", manufacture);
		return "admin/views/manufacture/edit_manufacture";
	}

	@PostMapping("/manufacture/edit")
	public String updateManufacture(@ModelAttribute Manufacture manufacture, Model model) {
		model.addAttribute("pageName", "manufacture");
		service.update(manufacture);
		return "redirect:/admin/manufacture";
	}

	@GetMapping("/manufacture/delete/{id}")
	public String deleteManufacture(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "manufacture");
		service.delete(id);
		return "redirect:/admin/manufacture";
	}
}
