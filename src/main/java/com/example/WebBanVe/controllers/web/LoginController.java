package com.example.WebBanVe.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Enumeration.eAccountStatus;
import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.Utils.Email;
import com.example.WebBanVe.Utils.Validator.AccountValidatorManager;
import com.example.WebBanVe.entity.Account;
import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.service.interf.IAccountService;
import com.example.WebBanVe.service.interf.ICustomerService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	private IAccountService accService;
	@Autowired
	private ICustomerService cusService;

	@GetMapping("login")
	public String getLogin(ModelMap model) {
		model.addAttribute("message", "");
		return "web/views/login/login";
	}

	@GetMapping("register")
	public String getRegister(ModelMap model) {
		model.addAttribute("message", "");
		return "web/views/login/register";
	}

	@GetMapping("forgot")
	public String getforgot(ModelMap model) {
		model.addAttribute("message", "");
		return "web/views/login/forgot";
	}

	@GetMapping("vertify")
	public String getVertify(ModelMap model) {
		model.addAttribute("message", "");
		return "web/views/login/vertify";
	}

	@GetMapping("logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {
		CookieManager.deleteCookie(request, response, "user_id");
		return "web/views/home";
	}

	@PostMapping("login")
	public String postLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam String username,
			@RequestParam String password, ModelMap model) {
		Account account = accService.getOne(username);
		model.addAttribute("user_id", CookieManager.getCookieValue(request, "user_id"));
		if (account == null) {
			model.addAttribute("message", "Tài khoản không tồn tại!");
			return "web/views/login/login";
		}
		if (account.getPassword().equals(password)) {
			Cookie cookie = new Cookie("user_id", cusService.getByAccountUsername(username).getId().toString());
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			return "redirect:home";
		}

		model.addAttribute("message", "Sai mật khẩu!");
		return "web/views/login/login";
	}

	@PostMapping("register")
	public String postRegister(HttpServletResponse response, @RequestParam String username, @RequestParam String email,
			@RequestParam String password, @RequestParam String confirmPassword, ModelMap model) {
		String message = AccountValidatorManager.getInstance().validate(cusService,
				new String[] { username, email, password, confirmPassword });
		if (message != null) {
			model.addAttribute("message", message);
			return "web/views/login/register";
		} else {
			if (accService.register(username, confirmPassword, email)) {

				Cookie cookie = new Cookie("otp", Email.sendOTP(email));
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				cookie = new Cookie("username", username);
				response.addCookie(cookie);

				return "web/views/login/vertify";
			} else {
				model.addAttribute("message", "Đăng ký thất bại!");
				return "web/views/login/register";
			}
		}
	}

	@PostMapping("forgot")
	public String postForgot(HttpServletResponse response, @RequestParam String username, @RequestParam String email,
			ModelMap model) {
		Customer customer = cusService.getByAccountUsername(username);
		try {
			if (customer.getEmail().equals(email)) {
				Email.sendPassword(email, customer.getAccount().getPassword());
				return "redirect:login";
			}
			model.addAttribute("message", "Tên tài khoản và email không phù hợp!");
			return "web/views/login/forgot";
		} catch (Exception e) {
			model.addAttribute("message", "Tên tài khoản và email không phù hợp!");
			return "web/views/login/forgot";
		}
	}

	@PostMapping("vertify")
	public String postVertify(HttpServletRequest request, @RequestParam String otp, ModelMap model) {
		System.out.println(otp);
		System.out.println(CookieManager.getCookieValue(request, "otp"));
		if (otp.equals(CookieManager.getCookieValue(request, "otp"))) {
			System.out.println("success");
			Account account = accService.getOne(CookieManager.getCookieValue(request, "username").toString());
			account.setStatus(eAccountStatus.ACTIVED);
			accService.update(account);
			return "web/views/login/login";
		}
		model.addAttribute("message", "Nhập sai OTP!");
		return "web/views/login/vertify";
	}
}
