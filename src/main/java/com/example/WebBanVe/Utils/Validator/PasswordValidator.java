package com.example.WebBanVe.Utils.Validator;

import com.example.WebBanVe.service.interf.ICustomerService;

public class PasswordValidator extends Handler{

	@Override
	String validate(ICustomerService service, String[] data) {
		if (data[2] == "" || data[2].isEmpty() || data[3] == "" || data[3].isEmpty()) {
			return "Mật khẩu không được trống!";
		}
		if (!data[2].equals(data[3])) {
			return "Mật khẩu không trùng khớp!";
		}
		if (super.getSuccessor() != null)
			return super.getSuccessor().validate(service, data);
		return "";
	}

}
