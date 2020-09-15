package com.he.addressBook;

import java.util.ArrayList;
import java.util.List;

import com.he.exception.ValidationException;
import com.he.util.Validation;

public class Contact {

	private String name;
	private String organisation;
	private List<PhoneNumber> phoneNumbers;
	private List<Address> addresses;
	
	public Contact(String name, String organisation) 
			throws ValidationException {
		setName(name);
		setOrganisation(organisation);
	}

	public Contact(String name, String organisation, PhoneNumber phoneNumber, Address address)
			throws ValidationException {
		setName(name);
		setOrganisation(organisation);
		addPhoneNumber(phoneNumber);
		addAddress(address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws ValidationException {
		if (Validation.isLengthValid(name, Validation.LENGTH_CONSTRAINT, !Validation.IS_SAME)
				&& Validation.isValidName(name))
			this.name = name;
		else
			throw new ValidationException("Invalid contact name");
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) throws ValidationException {
		if (Validation.isNullorEmpty(organisation)
				|| Validation.isLengthValid(organisation, Validation.LENGTH_CONSTRAINT, !Validation.IS_SAME))
			this.organisation = organisation;
		else
			throw new ValidationException("Invalid organisation name");
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addPhoneNumber(PhoneNumber phoneNumber) {
		if (this.phoneNumbers == null) {
			this.phoneNumbers = new ArrayList<PhoneNumber>();
		}
		this.phoneNumbers.add(phoneNumber);
	}

	public void addAddress(Address address) {
		if (this.addresses == null) {
			this.addresses = new ArrayList<Address>();
		}
		this.addresses.add(address);
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", organisation=" + organisation + ", phoneNumbers=" + phoneNumbers
				+ ", addresses=" + addresses + "]";
	}

}
