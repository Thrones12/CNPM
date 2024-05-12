package com.example.WebBanVe.Utils.Validator;

import com.example.WebBanVe.service.interf.ICustomerService;

public class UsernameValidator extends Handler {

	// data: [username, email, password, confirm-password]
	@Override
	String validate(ICustomerService service, String[] data) {
		if (data[0] == "" || data[0].isEmpty()) {
			return "Tên đăng nhập không được trống!";
		}
		if (data[0].length() == 100) {
			return "Tên đăng nhập không vượt quá 100 ký tự!";
		}
		if (!data[0].matches("[a-zA-z0-9]+")) {
			return "Tên đăng nhập chỉ chứa ký tự và số!";
		}
		if (service.getByAccountUsername(data[0]) != null) {
			return "Tên đăng nhập đã tồn tại!";
		}
		if (super.getSuccessor() != null)
			return super.getSuccessor().validate(service, data);
		return "";
	}

}
