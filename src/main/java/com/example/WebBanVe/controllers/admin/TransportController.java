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

import com.example.WebBanVe.entity.Transport;
import com.example.WebBanVe.service.interf.ITransportService;
import com.example.WebBanVe.service.interf.IManufactureService;

@Controller
@RequestMapping("/admin")
public class TransportController {

    @Autowired
    private ITransportService transportService;
    
    @Autowired
    private IManufactureService manufactureService;
    

    @GetMapping("/transport")
    public String getAllTransports(Model model) 
    {
		model.addAttribute("pageName", "transport");
        List<Transport> transports = transportService.getAll();
        model.addAttribute("transports", transports);
        return "admin/views/transport/transports";
    }

    @GetMapping("/transport/{id}")
    public String getTransportDetails(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "transport");
        Transport transport = transportService.getOne(id);
        model.addAttribute("transport", transport);
        return "admin/views/transport/transport_detail";
    }

    @GetMapping("/transport/create")
    public String createTransportForm(Model model) {
		model.addAttribute("pageName", "transport");
    	model.addAttribute("manufactures", manufactureService.getAll());
        model.addAttribute("transport", new Transport());
        return "admin/views/transport/create_transport";
    }

    @PostMapping("/transport/create")
    public String createTransport(@ModelAttribute Transport transport, Model model) {
		model.addAttribute("pageName", "transport");
        transportService.insert(transport);
        return "redirect:/admin/transport";
    }

    @GetMapping("/transport/edit/{id}")
    public String editTransportForm(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "transport");
        Transport transport = transportService.getOne(id);
        model.addAttribute("manufactures", manufactureService.getAll());
        model.addAttribute("transport", transport);
        return "admin/views/transport/edit_transport";
    }

    @PostMapping("/transport/edit")
    public String updateTransport(@ModelAttribute Transport transport, Model model) {
		model.addAttribute("pageName", "transport");
        transportService.update(transport);
        return "redirect:/admin/transport";
    }

    @GetMapping("/transport/delete/{id}")
    public String deleteTransport(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "transport");
        transportService.delete(id);
        return "redirect:/admin/transport";
    }
}
