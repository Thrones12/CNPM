package com.example.WebBanVe.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Enumeration.eAccountStatus;
import com.example.WebBanVe.Enumeration.eRole;
import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.Utils.Email;
import com.example.WebBanVe.Utils.Validator.AccountValidatorManager;
import com.example.WebBanVe.entity.Account;
import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.service.interf.IAccountService;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IUserService;

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
	@Autowired
	private IUserService userService;

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
		return "redirect:home";
	}

	@PostMapping("login")
	public String postLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam String username,
			@RequestParam String password, ModelMap model) {
		try {
			Account account = accService.getOne(username);
			model.addAttribute("user_id", CookieManager.getCookieValue(request, "user_id"));
			if (account == null) {
				model.addAttribute("error", "Tài khoản không tồn tại!");
				return "web/views/login/login";
			}

			if (account.getPassword().equals(password)) {
				Cookie cookie = new Cookie("user_id", userService.getByAccountUsername(username).getId().toString());
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				if (account.getRole() == eRole.CUSTOMER)
					return "redirect:home";
				else if (account.getRole() == eRole.ADMIN)
					return "redirect:admin/customer/list";
			}

			model.addAttribute("error", "Sai mật khẩu!");
			return "web/views/login/login";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}

	@PostMapping("register")
	public String postRegister(HttpServletResponse response, @RequestParam String username, @RequestParam String email,
			@RequestParam String password, @RequestParam String confirmPassword, ModelMap model) {
		
		try {
			// Kiểm tra dữ liệu
			String message = AccountValidatorManager.getInstance().validate(cusService,
					new String[] { username, email, password, confirmPassword });
			
			if (message != null) { // message == null --> success
				model.addAttribute("message", message);
				return "web/views/login/register";
			} else {
				if (accService.register(username, confirmPassword, Email.sendOTP(email))) {
					// Thêm cookie otp để vertify
					Cookie cookie = new Cookie("otp", Email.sendOTP(email));
					cookie.setMaxAge(3600);
					response.addCookie(cookie);
					// Thêm cookie username để getAccount và set status
					cookie = new Cookie("username", username);
					response.addCookie(cookie);

					return "web/views/login/vertify";
				} else {
					model.addAttribute("message", "Đăng ký thất bại!");
					return "web/views/login/register";
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "web/views/404";
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
		try {
			if (otp.equals(CookieManager.getCookieValue(request, "otp"))) {
				Account account = accService.getOne(CookieManager.getCookieValue(request, "username").toString());
				account.setStatus(eAccountStatus.ACTIVED);
				accService.update(account);
				model.addAttribute("message", "Đăng ký tài khoản thành công!");
				return "web/views/login/login";
			}
			model.addAttribute("error", "Nhập sai OTP!");
			return "web/views/login/vertify";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}
}
