package com.example.WebBanVe.Utils.Validator;

import com.example.WebBanVe.service.interf.ICustomerService;

public class EmailValidator extends Handler{

	@Override
	String validate(ICustomerService service, String[] data) {
		if (data[1] == "" || data[1].isEmpty()) {
			return "Email không được trống!";
		}
		if (!data[1].matches("^(.+)@(.+)$")) {
			return "Email sai định dạng!";
		}
		if (service.getOne(data[1]) != null) {
			return "Email đã tồn tại!";
		}
		if (super.getSuccessor() != null)
			return super.getSuccessor().validate(service, data);
		return null;
	}

}
