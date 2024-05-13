package com.example.WebBanVe.Utils.Validator;

import com.example.WebBanVe.service.interf.ICustomerService;

public class AccountValidatorManager {
	private static AccountValidatorManager instance;

	private AccountValidatorManager() {
	};

	public static AccountValidatorManager getInstance() {
		if (instance == null)
			instance = new AccountValidatorManager();
		return instance;
	}

	public String validate(ICustomerService service, String[] data) {
		Handler validator = new UsernameValidator();
		Handler validator2 = validator.setSuccessor(new EmailValidator());
		validator2.setSuccessor(new PasswordValidator());
		return validator.validate(service, data);
	}
}
