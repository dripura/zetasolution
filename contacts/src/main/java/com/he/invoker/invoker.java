package com.he.invoker;

import java.util.List;

import com.he.addressBook.Address;
import com.he.addressBook.AddressBook;
import com.he.addressBook.Contact;
import com.he.addressBook.PhoneNumber;
import com.he.exception.ValidationException;
import com.he.util.Validation;

public class invoker {
	public static void main(String[] args) {
		try {
			AddressBook addressBook = new AddressBook();
			validateFields();
			AddressBook.viewAddressBook();
			// Add first contact
			addressBook.addContact(createContact("Darren Shiv", "International Hospital - 68937239",
					createPhoneNumber("Work A", "9487899567"), createAddress("Residential A", "Chennai - 67778788")));
			addressBook.addContact(createContact("Yughan Krishna", "International Hospital - 899888",
					createPhoneNumber("Work A", "9875444698"), createAddress("Residential A", "Dubai - 099999")));
			// Adding more phone number to existing contact - positive
			addContactPhoneNumber("Darren Shiv", createPhoneNumber("Home A", "9907899571"));
			// Adding more phone number to non-existing contact - negative
			addContactPhoneNumber("Ramya", createPhoneNumber("Home A", "9907899571"));
			// Adding more address to the existing contact - positive
			addContactAddress("Darren Shiv", createAddress("Office", "Chennai - 600004"));
			// Adding more address to the non-existing contact - negative
			addContactAddress("Dhanya Sri", createAddress("Office", "Chennai - 600004"));

			// Positive scenarios:
			// Add new contact without duplicate contact name - positive
			addressBook.addContact(createContact("Dhanya Sri", "Indian Hospital - 6777784",
					createPhoneNumber("Home A", "9487899567"), createAddress("Residential", "Chennai - 6777784")));
			// Search by name present returns contacts - positive
			List<Contact> searchList = addressBook.searchByName("s");
			System.out.println("SearchList----->" + searchList);
			System.out.println("SearchList contains " + searchList.size() + " contacts");
			// Search by name not present returns no contacts - positive
			searchList = addressBook.searchByName("z");
			System.out.println("SearchList----->" + searchList);
			System.out.println("SearchList contains " + searchList.size() + " contacts");
			// Search with empty string returns all contacts - positive
			searchList = addressBook.searchByName("");
			System.out.println("SearchList----->" + searchList);
			System.out.println("SearchList contains " + searchList.size() + " contacts");
			// Search by organisation present returns contacts - positive
			searchList = addressBook.searchByOrganisation("In");
			System.out.println("SearchList----->" + searchList);
			System.out.println("SearchList contains " + searchList.size() + " contacts");
			// Search organisation with no matching string returns no contacts
			searchList = addressBook.searchByOrganisation("Ho");
			System.out.println("SearchList----->" + searchList);
			System.out.println("SearchList contains " + searchList.size() + " contacts");
			// Searching organisation with empty string returns all contacts
			searchList = addressBook.searchByOrganisation("");
			System.out.println("SearchList----->" + searchList);
			System.out.println("SearchList contains " + searchList.size() + " contacts");
			// Update contact with unique name - positive
			addressBook.updateContact("Shiyamala", createContact("Dhanya Sri", "International Hospital - 6777784",
					createPhoneNumber("Home A", "9487899567"), createAddress("Residential", "Chennai - 6777784")));
			// Delete contact with name present - positive
			addressBook.deleteContact("Shiyamala");

			// Negative scenarios:
	
			// Add contact with duplicate name - 1. negative
			addressBook.addContact(createContact("Darren Shiv", "International Hospital - 68937239",
					createPhoneNumber("Work", "9487899567"), createAddress("Office A", "Chennai - 67778788")));
			// Update contact with contact name not present - 2. negative
			addressBook.updateContact("Darren Shiv", createContact("Dhanya Sri", "International Hospital - 6777784",
					createPhoneNumber("Home A", "9487899567"), createAddress("Office", "Chennai - 6777784")));
			// Update contact with contact name leads to duplicate - 3. negative
			addressBook.updateContact("Darren Shiv", createContact("Darren Shiv", "International Hospital - 6777784",
					createPhoneNumber("Home A", "9487899567"), createAddress("Residential", "Chennai - 6777784")));
			// Delete contact with name not present - 4. negative
			addressBook.deleteContact("Dhanya");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	private static void validateFields() {
		try {
			// Test data:
			// System.out.println(Validation.STRING_WITH_SPACE.length());
			// System.out.println(Validation.STRING_WITHOUT_SPACE.length());

			System.out.println("Validating test cases");

			// 1** Creating PhoneNumber class test cases
			System.out.println("1. PhoneNumber class:");
			// Field: 1. label
			System.out.println("Field: 1. label");
			System.out.println("Negative scenarios returns exception Invalid phone label");
			// Null scenario:
			// 1 : Is null - negative
			createPhoneNumber(null, null);
			// Not null scenarios:
			// 2 : Is empty - negative
			createPhoneNumber("", null);
			// 3 : Within 255 char with whitespace in non English - negative
			createPhoneNumber(Validation.NON_ENG_WITH_SPACE, "");
			// 4 : Within 255 char without space in non English - negative
			createPhoneNumber(Validation.NON_ENG_WITHOUT_SPACE, "");
			// 5 : Exceeds 255 char with whitespace - negative
			createPhoneNumber(Validation.STRING_WITH_SPACE + "test", "");
			// 6 : Exceeds 255 char without whitespace - negative
			createPhoneNumber(Validation.STRING_WITHOUT_SPACE + "test", "");
			System.out.println("Postive scenarios returns no exception Invalid phone label");
			// 7 : Within 255 char with whitespace in English - positive
			createPhoneNumber("Home A", "");
			// 8 : Within 255 char without whitespace in English - positive
			createPhoneNumber("Home", "");
			// 9 : Exactly 255 char with whitespace - positive
			createPhoneNumber(Validation.STRING_WITH_SPACE, "");
			// 10 : Exactly 255 char without whitespace - positive
			createPhoneNumber(Validation.STRING_WITHOUT_SPACE, "");

			// Field: 2. phoneNumber
			System.out.println("Field: 2. phoneNumber");
			System.out.println("Negative scenarios returns exception Invalid phone number");
			// Null scenario:
			// 1 : Is null - negative
			createPhoneNumber("Home", null);
			// Not null scenarios:
			// 2 : Is empty - negative
			createPhoneNumber("Home", "");
			// 3 : Is contains 10 char and not digits - negative
			createPhoneNumber("Home", "abcdefghij");
			// 4 : within 10 char and not digits - negative
			createPhoneNumber("Work", "abcdefghi");
			// 5 : within 10 char and digits - negative
			createPhoneNumber("Work", "987654321");
			// 6 : exceeds 10 char and not digits - negative
			createPhoneNumber("Work", "abcdefghijk");
			// 7 : exceeds 10 char and digits - negative
			createPhoneNumber("Work", "98765432100");
			System.out.println("Postive scenarios returns no exception Invalid phone number");
			// 8 : Is contains 10 char and digits -positive
			createPhoneNumber("Work", "9876543210");

			// Actual input:
			System.out.println("Sample input for the Phone Number class is tested");
			createPhoneNumber("Home A", "9487899567");
			System.out.println("Sample input for the Phone Number class passed");

			// 2** Creating Address class test cases
			System.out.println("2. Address class:");
			// Field: 1. label
			System.out.println("Field: 1. label");
			System.out.println("Negative scenarios returns exception Invalid address label");
			// Null scenario:
			// 1 : Is null - negative
			createAddress(null, null);
			// Not null scenarios:
			// 2 : Is empty - negative
			createAddress("", null);
			// 3 : Within 255 char with whitespace in non English - negative
			createAddress(Validation.NON_ENG_WITH_SPACE, "");
			// 4 : Within 255 char without space in non English - negative
			createAddress(Validation.NON_ENG_WITHOUT_SPACE, "");
			// 5 : Exceeds 255 char with whitespace - negative
			createAddress(Validation.STRING_WITH_SPACE + "test", "");
			// 6 : Exceeds 255 char without whitespace - negative
			createAddress(Validation.STRING_WITHOUT_SPACE + "test", "");
			System.out.println("Postive scenarios returns no exception Invalid address label");
			// 7 : Within 255 char with whitespace in English - positive
			createAddress("Office A", "");
			// 8 : Within 255 char without whitespace in English - positive
			createAddress("Office", "");
			// 9 : Exactly 255 char with whitespace - positive
			createAddress(Validation.STRING_WITH_SPACE, "");
			// 10 : Exactly 255 char without whitespace - positive
			createAddress(Validation.STRING_WITHOUT_SPACE, "");

			// Field: 2. address
			System.out.println("Field: 2. address");
			System.out.println("Negative scenarios are not tested --- field has no validation");
			System.out.println("Postive scenarios are tested");
			// Null scenario:
			// 1 : Is null - positive
			createAddress("Office", null);
			// Not null scenarios:
			// 2 : Is empty - positive
			createAddress("Office", "");
			// 3 : English char with whitespace of any length - positive
			createAddress("Office", Validation.STRING_WITH_SPACE);
			// 4 : English char without whitespace of any length - positive
			createAddress("Office", Validation.STRING_WITHOUT_SPACE);
			// 5 : Non English char with whitespace of any length - positive
			createAddress("Residential", Validation.NON_ENG_WITH_SPACE);
			// 6 : Non English char without space of any length - positive
			createAddress("Residential", Validation.NON_ENG_WITHOUT_SPACE);
			// 7 : Digits and char with whitespace of any length - positive
			createAddress("Residential", Validation.NON_ENG_WITH_SPACE + Validation.DIGIT_CONSTRAINT);
			// 8 : Digits and char without space of any length - positive
			createAddress("Residential", Validation.NON_ENG_WITHOUT_SPACE + Validation.DIGIT_CONSTRAINT);

			// Actual input:
			System.out.println("Sample input for the Address class is tested");
			createAddress("Residential A", "Chennai - 67778788");
			System.out.println("Sample input for the Address class passed");

			// 3** Creating Contact class test cases
			System.out.println("3. Contact class:");
			// Field: 1. name
			System.out.println("Field: 1. name");
			System.out.println("Negative scenarios returns exception Invalid contact name");
			// Null scenario:
			// 1 : Is null - negative
			createContact(null, "", null, null);
			// Not null scenarios:
			// 2 : Is empty - negative
			createContact("", "", null, null);
			// 3 : Within 255 char with whitespace in non English - negative
			createContact(Validation.NON_ENG_WITH_SPACE, "", null, null);
			// 4 : Within 255 char without space in non English - negative
			createContact(Validation.NON_ENG_WITHOUT_SPACE, "", null, null);
			// 5 : Exceeds 255 char with whitespace - negative
			createContact(Validation.STRING_WITH_SPACE + "test", "", null, null);
			// 6 : Exceeds 255 char without whitespace - negative
			createContact(Validation.STRING_WITHOUT_SPACE + "test", "", null, null);
			System.out.println("Postive scenarios returns no exception Invalid contact number");
			// 7 : Within 255 char with whitespace in English - positive
			createContact("contact name", "", null, null);
			// 8 : Within 255 char without whitespace in English - positive
			createContact("name", "", null, null);
			// 9 : Exactly 255 char with whitespace - positive
			createContact(Validation.STRING_WITH_SPACE, "", null, null);
			// 10 : Exactly 255 char without whitespace - positive
			createContact(Validation.STRING_WITHOUT_SPACE, "", null, null);

			// Field: 2. organisation
			System.out.println("Field: 2. organisation");
			System.out.println("Postive scenarios returns no exception Invalid organisation name");
			// Null scenario:
			// 1 : Is null - positive
			createContact("name", null, null, null);
			// Not null scenarios:
			// 2 : Is empty - positive
			createContact("name", "", null, null);
			// 3 : Within 255 char with whitespace in English - positive
			createContact("name", " Capgemini India private limited ", null, null);
			// 4 : Within 255 char without whitespace in English - positive
			createContact("name", " Capgemini ", null, null);
			// 5 : Within 255 char with whitespace in non English - positive
			createContact("name", Validation.NON_ENG_WITH_SPACE, null, null);
			// 6 : Within 255 char without space in non English - positive
			createContact("name", Validation.NON_ENG_WITHOUT_SPACE, null, null);
			// 7 : Exactly 255 char with whitespace - positive
			createContact("name", Validation.STRING_WITH_SPACE, null, null);
			// 8 : Exactly 255 char without whitespace - positive
			createContact("name", Validation.STRING_WITHOUT_SPACE, null, null);
			// 9 : Within 255 char in digits and char with space - positive
			createContact("name", Validation.NON_ENG_WITH_SPACE + Validation.DIGIT_CONSTRAINT, null, null);
			// 10 : Within 255 char in digits and char without space - positive
			createContact("name", Validation.NON_ENG_WITHOUT_SPACE + Validation.DIGIT_CONSTRAINT, null, null);
			System.out.println("Negative scenarios returns exception Invalid organisation name");
			// 11 : Exceeds 255 char with whitespace - negative
			createContact("name", Validation.STRING_WITH_SPACE + "test", null, null);
			// 12 : Exceeds 255 char without whitespace - negative
			createContact("name", Validation.STRING_WITHOUT_SPACE + "test", null, null);

			// Field: 3. phoneNumbers
			System.out.println("Field: 3. phoneNumbers");
			System.out.println("Scenarios of PhoneNumber class is already covered ");
			createContact("name", " Capgemini India private limited ", null, null);

			// Field: 4. addresses
			System.out.println("Field: 4. addresses");
			System.out.println("Scenarios of Address class is already covered");

			// Actual input:
			System.out.println("Sample input for the Contact class is tested");
			createContact("Darren Shiv", "International Hospital - 68937239", null, null);
			System.out.println("Sample input for the Contact class passed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static PhoneNumber createPhoneNumber(String label, String phoneNumber) {
		try {
			PhoneNumber phone = new PhoneNumber(label, phoneNumber);
			return phone;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	private static void addContactPhoneNumber(String name, PhoneNumber phoneNumber) {
		try {
			if (null != name && null != phoneNumber) {
				for (Contact contact : AddressBook.contactsList) {
					if (contact.getName().toLowerCase().equals(name.toLowerCase())) {
						contact.addPhoneNumber(phoneNumber);
						return;
					}
				}
				throw new ValidationException("Adding phone number failed as contact name is invalid");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static Address createAddress(String label, String address) {
		try {
			Address addr = new Address(label, address);
			return addr;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	private static void addContactAddress(String name, Address address) {
		try {
			if (null != name && null != address) {
				for (Contact contact : AddressBook.contactsList) {
					if (contact.getName().toLowerCase().equals(name.toLowerCase())) {
						contact.addAddress(address);
						return;
					}
				}
				throw new ValidationException("Adding address failed as contact name is invalid");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static Contact createContact(String name, String organisation, PhoneNumber phoneNumber, Address address) {
		try {
			Contact contact = new Contact(name, organisation, phoneNumber, address);
			return contact;
		} catch (Exception e) {			
			System.err.println(e.getMessage());
		}
		return null;
	}
}
