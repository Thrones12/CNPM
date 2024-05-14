package com.example.WebBanVe.controllers.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanVe.Config.Config;
import com.example.WebBanVe.Enumeration.eOrderStatus;
import com.example.WebBanVe.Enumeration.eTicketStatus;
import com.example.WebBanVe.Utils.CookieManager;
import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.IOrderService;
import com.example.WebBanVe.service.interf.IPassengerService;
import com.example.WebBanVe.service.interf.ITicketService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class PayController {
	@Autowired
	private IOrderService service;
	@Autowired
	private ICustomerService cusService;
	@Autowired
	private ITicketService ticService;
	@Autowired
	private IPassengerService pasService;

	@GetMapping("thanks")
	public String getThanks(@RequestParam Long order_id) {
		Order order = service.getOne(order_id);
		order.setStatus(eOrderStatus.PAID);
		order.getTicket().setStatus(eTicketStatus.BOOKED);
		service.update(order);
		return "web/views/thanks";
	}

	@GetMapping("check-payment")
	public String getCheck(HttpServletRequest request, ModelMap model) {
		try {
			// Get user_id từ cookie và thêm nó vào attribute
			Long id = 0L;
			String cus_id = CookieManager.getCookieValue(request, "user_id");
			if (cus_id != null) {
				id = Long.parseLong(cus_id);
			}
			new Order();
			Order order = Order.builder().customer(cusService.getOne(id)).orderDate(LocalDateTime.now())
					.totalPrice(ticService.getOne(Long.parseLong(request.getParameter("ticket_id"))).getPrice())
					.status(eOrderStatus.UNPAID)
					.passenger(pasService.getOne(Long.parseLong(request.getParameter("ticket_id"))))
					.ticket(ticService.getOne(Long.parseLong(request.getParameter("ticket_id")))).build();
			if (service.insert(order)) {
				return "redirect:pay?order_id="+service.getByTicketId(order.getTicket().getId()).getId();
			} else {
				return "web/views/404";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "web/views/404";
		}
	}

	@GetMapping("pay")
	public void getPay(@RequestParam Long order_id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "other";
		long amount = Integer.parseInt("100000") * 100;
		String bankCode = req.getParameter("bankCode");

		String vnp_TxnRef = Config.getRandomNumber(8);
		String vnp_IpAddr = Config.getIpAddress(req);

		String vnp_TmnCode = Config.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");

		if (bankCode != null && !bankCode.isEmpty()) {
			vnp_Params.put("vnp_BankCode", bankCode);
		}
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_OrderType", orderType);

		String locate = req.getParameter("language");
		if (locate != null && !locate.isEmpty()) {
			vnp_Params.put("vnp_Locale", locate);
		} else {
			vnp_Params.put("vnp_Locale", "vn");
		}
		System.out.println(Config.vnp_ReturnUrl + "?order_id=" + order_id);
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl + "?order_id=" + order_id);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		resp.sendRedirect(paymentUrl);
	}
}
