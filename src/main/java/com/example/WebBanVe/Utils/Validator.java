package com.example.WebBanVe.Utils;

import com.example.WebBanVe.entity.Customer;

public class Validator {
	private Validator instance;

	public Validator getInstance() {
		if (instance == null)
			instance = new Validator();
		return instance;
	}

	private Validator() {
	}
	
	private boolean validate(Customer customer) {
		return true;
	}
}
