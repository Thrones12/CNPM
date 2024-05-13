package com.example.WebBanVe.Utils.Validator;

import com.example.WebBanVe.service.interf.ICustomerService;

public class CustomerValidatorManager {
	private static CustomerValidatorManager instance;

	private CustomerValidatorManager() {
	};

	public static CustomerValidatorManager getInstance() {
		if (instance == null)
			instance = new CustomerValidatorManager();
		return instance;
	}

	public String validate(ICustomerService service, String[] data) {
		Handler validator = new EmailValidator();
		return validator.validate(service, data);
	}
}
