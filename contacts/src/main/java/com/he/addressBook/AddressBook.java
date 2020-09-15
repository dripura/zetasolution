package com.he.addressBook;

import java.util.ArrayList;
import java.util.List;

import com.he.exception.ValidationException;

public class AddressBook {

	public static List<Contact> contactsList;

	public AddressBook() throws ValidationException {
	}

	public void addContact(Contact contact) throws ValidationException {
		try {
			String contactName = contact.getName();
			if (AddressBook.contactsList == null) {
				AddressBook.contactsList = new ArrayList<Contact>();
			}
			for (Contact contactList : contactsList) {
				if (contactList.getName().equalsIgnoreCase(contactName)) {
					 throw new ValidationException("Adding contact will lead to duplicate contact name in address book");
				}
			}
			AddressBook.contactsList.add(contact);
			viewAddressBook();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new ValidationException("Adding contact will lead to duplicate contact name in address book");
		}
	}

	public List<Contact> searchByName(String name) {
		try {
			List<Contact> searchNameList = new ArrayList<Contact>();
			if (null != name && null != contactsList) {
				if (name.equals("")) {
					return contactsList;
				} else {
					for (Contact contactList : contactsList) {
						if (contactList.getName().toLowerCase().contains(name.toLowerCase())) {
							searchNameList.add(contactList);
						}
					}
				}
			}
			viewAddressBook();
			return searchNameList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public List<Contact> searchByOrganisation(String organisation) {
		try {
			List<Contact> searchOrganisationList = new ArrayList<Contact>();
			if (null != organisation && null != contactsList) {
				if (organisation.equals("")) {
					return contactsList;
				} else {
					for (Contact contactList : contactsList) {
						if (contactList.getOrganisation().toLowerCase().startsWith(organisation.toLowerCase())) {
							searchOrganisationList.add(contactList);
						}
					}
				}
			}
			viewAddressBook();
			return searchOrganisationList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public void updateContact(String name, Contact contact) {
		try {
			if (null != name && null != contactsList) {
				String contactName = contact.getName();
				Boolean isValid = false;
				if (name.equalsIgnoreCase(contactName)) {
					throw new ValidationException("Updating contact failed as new name and old name are same");
				} else {
					for (Contact contactList1 : contactsList) {
						if (contactList1.getName().equalsIgnoreCase(contactName)) {
							for (Contact contactList2 : contactsList) {
								if (contactList2.getName().equalsIgnoreCase(name)) {
									throw new ValidationException(
											"Updating contact will lead to duplicate contact name in address book");
								}
							}
							isValid = true;
							contactList1.setName(name);
						}
					}
				}
				if (!isValid) {
					throw new ValidationException(
							"Updating contact failed as contact specified is not present in address book");
				}
			}
			viewAddressBook();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void deleteContact(String name)  {
		try {
			System.out.println("name"+name);
			if (null != name && null != contactsList) {
				for (Contact contactList : contactsList) {
					System.out.println("contactList"+contactList);
					if (contactList.getName().equalsIgnoreCase(name)) {
						System.out.println("contactList.getName()"+contactList.getName());
						System.out.println("Before Delete");
						viewAddressBook();
						contactsList.remove(contactList);
						System.out.println(" AfterDelete");
						viewAddressBook();
						return;
					}
				}
			} else
				throw new ValidationException(
						"Deleting contact failed as contact specified is not present in address book");
			viewAddressBook();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				throw new ValidationException(
						"Deleting contact failed as contact specified is not present in address book");
			} catch (ValidationException e1) {
				System.err.println(e.getMessage());
				e1.printStackTrace();
			}
		}
	}

	public static void viewAddressBook() {
		if (null != contactsList && contactsList.size() > 0) {
			System.out.println("Address book contains " + contactsList.size() + " number of contacts");
			System.out.println("Address book:--->" + contactsList.toString());
		} else
			System.out.println("Address book is empty");
	}
}