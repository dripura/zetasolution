package com.he.addressBook;

import com.he.exception.ValidationException;
import com.he.util.Validation;

public class Address {

	private String label;
	private String address;
	
	public Address(String label, String address) throws ValidationException {
		setLabel(label);
		setAddress(address);
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label)  {
		if(Validation.isLengthValid(label, Validation.LENGTH_CONSTRAINT, !Validation.IS_SAME) && Validation.isValidName(label))
			this.label = label;
		else
			try {
				throw new ValidationException("Invalid address label");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Address [label=" + label + ", address=" + address + "]";
	}
	
	
	
	
}
