package com.example.WebBanVe.Utils.Validator;

import com.example.WebBanVe.service.interf.ICustomerService;

public class AccountValidator {
	private static AccountValidator instance;

	private AccountValidator() {
	};

	public static AccountValidator getInstance() {
		if (instance == null)
			instance = new AccountValidator();
		return instance;
	}

	public String validate(ICustomerService service, String[] data) {
		Handler validator = new UsernameValidator();
		Handler validator2 = validator.setSuccessor(new EmailValidator());
		validator2.setSuccessor(new PasswordValidator());
		return validator.validate(service, data);
	}
}
