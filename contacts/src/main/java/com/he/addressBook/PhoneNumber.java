package com.he.addressBook;

import com.he.exception.ValidationException;
import com.he.util.Validation;

public class PhoneNumber {

	private String label;
	private String phoneNumber;

	public PhoneNumber(String label, String phoneNumber) throws ValidationException {
		setLabel(label);
		setPhoneNumber(phoneNumber);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) throws ValidationException {
		if (Validation.isLengthValid(label, Validation.LENGTH_CONSTRAINT, !Validation.IS_SAME)
				&& Validation.isValidName(label))
			this.label = label;
		else
			throw new ValidationException("Invalid phone label");
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws ValidationException {
		if (Validation.isLengthValid(phoneNumber, Validation.DIGIT_CONSTRAINT, Validation.IS_SAME)
				&& Validation.isValidNumber(phoneNumber))
			this.phoneNumber = phoneNumber;
		else
			throw new ValidationException("Invalid phone number");

	}

	@Override
	public String toString() {
		return "PhoneNumber [label=" + label + ", phoneNumber=" + phoneNumber + "]";
	}
	
	

}
