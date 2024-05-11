package com.example.WebBanVe.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.service.interf.ICustomerService;

@Controller
@RequestMapping("/")
public class CustomerAdminController {
	@Autowired
	private ICustomerService cusService;

	@GetMapping("admin/customer/list")
	public String getCustomer(ModelMap model) {
		List<Customer> customers = cusService.getAll();
		model.addAttribute("customers", customers);
		return "admin/views/customer/list";
	}

	@GetMapping("admin/customer/insert")
	public String getInsertCustomer(ModelMap model) {
		model.addAttribute("customer", new Customer());
		return "admin/views/customer/add";
	}

	@PostMapping("admin/customer/insert")
	public String postCustomer(@ModelAttribute("customer") Customer customer, ModelMap model) {
		if (cusService.insert(customer)) {
			return "redirect:list";
		}

		return "redirect:insert";
	}

	@GetMapping("admin/customer/update")
	public String getDetailCustomer(@RequestParam Long id, ModelMap model) {
		Customer customer = cusService.getOne(id);
		model.addAttribute("customer", customer);
		return "admin/views/customer/update";
	}

	@PostMapping("admin/customer/update")
	public String putCustomer(@ModelAttribute("customer") Customer customer, ModelMap model) {
		if (cusService.update(customer)) {
			return "redirect:list";
		}

		return "redirect:list";
	}

	@DeleteMapping("admin/customer/delete")
	public ResponseEntity<String> deleteCustomer(@RequestParam Long id, ModelMap model) {
		if (cusService.delete(id)) {
			String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
					+ "			icon : 'success',\r\n" + "			title : 'Xóa thành công!',\r\n"
					+ "			showConfirmButton : false,\r\n" + "			timer : 1500\r\n" + "		});\r\n"
					+ "	}, 500); ";
			return ResponseEntity.ok(responseScript);
		} else {
			String responseScript = "setTimeout(function() {\r\n" + "		Swal.fire({\r\n"
					+ "			icon : 'error',\r\n" + "			title : 'Thất bại!',\r\n"
					+ "			showConfirmButton : false,\r\n" + "			timer : 1500\r\n" + "		});\r\n"
					+ "	}, 500); ";
			return ResponseEntity.ok(responseScript);
		}
	}
}
