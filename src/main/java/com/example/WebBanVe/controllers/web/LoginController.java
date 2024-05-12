package com.example.WebBanVe.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebBanVe.Utils.Validator.AccountValidator;
import com.example.WebBanVe.service.interf.IAccountService;
import com.example.WebBanVe.service.interf.ICustomerService;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	private IAccountService accService;
	@Autowired
	private ICustomerService cusService;

	@GetMapping("login")
	public String getLogin() {
		return "web/views/login/login";
	}

	@GetMapping("register")
	public String getRegister(ModelMap model) {
	    model.addAttribute("message", "Thông báo đăng ký thành công!");
		return "web/views/login/register";
	}

	@GetMapping("forgot")
	public String getforgot() {
		return "web/views/login/forgot";
	}

	@GetMapping("vertify")
	public String getVertify() {
		return "web/views/login/vertify";
	}

	@PostMapping("login")
	public String postLogin() {
		return "web/views/login/register";
	}

	@PostMapping("register")
	public String postRegister(@RequestParam String username, @RequestParam String email, @RequestParam String password,
			@RequestParam String confirmPassword, ModelMap model) {
		String message = AccountValidator.getInstance().validate(cusService,
				new String[] { username, email, password, confirmPassword });
		if (message != null) {
			System.out.println(message);
			model.addAttribute("message", message);
			return "web/views/login/register";
		}
		else {
			if (accService.register(username, confirmPassword, email)) {
				System.out.println("successes");
			}else {

				System.out.println("failed");
			}
		}
		return "web/views/login/register";
	}
}
