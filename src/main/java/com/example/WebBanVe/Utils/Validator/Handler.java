package com.example.WebBanVe.Utils.Validator;

import com.example.WebBanVe.service.interf.ICustomerService;

public abstract class Handler {
	private Handler successor;

	abstract String validate(ICustomerService service, String[] data);

	public Handler getSuccessor() {
		return successor;
	}

	public Handler setSuccessor(Handler successor) {
		this.successor = successor;
		return successor;
	}
}
