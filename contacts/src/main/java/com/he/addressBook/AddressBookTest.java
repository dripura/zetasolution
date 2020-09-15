package com.he.addressBook;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// tests the correctness of entire application
	@Test 
	public void testAddressBook() {
		try {
			AddressBook addressBook = new AddressBook();
			PhoneNumber phoneNumberWork = new PhoneNumber("work","1234567890");
			PhoneNumber phoneNumberHome = new PhoneNumber("home", "0987654321");
			PhoneNumber phoneNumberOffice = new PhoneNumber("office", "1234098765");
			Address addressWork = new Address("work", "HackerEarth Koramangala");
			Address addressHome = new Address("home", "New Delhi, India");
			Address addressOffice = new Address("office","USA");
			Contact contactWork = new Contact("Worker", "HackerEarth", phoneNumberWork, addressWork);
			Contact contactHome = new Contact("Home", "New Delhi", phoneNumberHome, addressHome);
			
			contactWork.addPhoneNumber(phoneNumberWork);
			contactWork.addAddress(addressWork);
			
			contactHome.addPhoneNumber(phoneNumberHome);
			contactHome.addAddress(addressHome);
			
			
			//Testing Getters Setters
			assertEquals("work",phoneNumberWork.getLabel());
			assertEquals("home",phoneNumberHome.getLabel());
			assertEquals("office",phoneNumberOffice.getLabel());
			assertEquals("1234567890",phoneNumberWork.getPhoneNumber());
			assertEquals("0987654321",phoneNumberHome.getPhoneNumber());
			assertEquals("1234098765",phoneNumberOffice.getPhoneNumber());
			assertEquals("work",addressWork.getLabel());
			assertEquals("HackerEarth Koramangala",addressWork.getAddress());
			assertEquals("home",addressHome.getLabel());
			assertEquals("New Delhi, India",addressHome.getAddress());
			assertEquals("office",addressOffice.getLabel());
			assertEquals("USA",addressOffice.getAddress());
			assertEquals("HackerEarth",contactWork.getOrganisation());
			assertEquals("Worker",contactWork.getName());
			assertEquals("New Delhi",contactHome.getOrganisation());
			assertEquals("Home",contactHome.getName());
			
			
			//addContact
			
			addressBook.addContact(contactWork);
			addressBook.addContact(contactHome);
			//adding duplicate object
			try {
				addressBook.addContact(contactWork);
				fail();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			// adding duplicate name
			try {
				Contact duplicate = new Contact("Worker", "MaybeEarth", phoneNumberOffice, addressWork);
				addressBook.addContact(duplicate);
				fail();
			}
			catch (Exception e) {
				
			}
			
			
			// searchByName
			
			List<Contact> listOfContactWork = addressBook.searchByName("Worker");
			assertEquals(1, listOfContactWork.size());
			assertEquals(contactWork, listOfContactWork.get(0));
			
			List<Contact> listOfContactHome = addressBook.searchByName("Home");
			assertEquals(1, listOfContactHome.size());
			assertEquals(contactHome, listOfContactHome.get(0));
			
			List<Contact> listOfContact = addressBook.searchByName("");
			assertEquals(2, listOfContact.size());
			assertTrue(listOfContact.contains(contactHome));
			assertTrue(listOfContact.contains(contactWork));
			
			// searchByOrganisation
			List<Contact> listOfContactWorkOrganisation = addressBook.searchByOrganisation("Hackerearth");
			assertEquals(1, listOfContactWorkOrganisation.size());
			assertEquals(contactWork, listOfContactWorkOrganisation.get(0));
			
			List<Contact> listOfContactHomeOrganisation = addressBook.searchByOrganisation("new Delhi");
			assertEquals(1, listOfContactHomeOrganisation.size());
			assertEquals(contactHome, listOfContactHomeOrganisation.get(0));
			
			List<Contact> listOfContactOrganisation = addressBook.searchByOrganisation("");
			assertEquals(2, listOfContactOrganisation.size());
			assertTrue(listOfContactOrganisation.contains(contactHome));
			assertTrue(listOfContactOrganisation.contains(contactWork));
			
			//update contact
			
			contactWork.addPhoneNumber(phoneNumberOffice);
			List<PhoneNumber> result = contactWork.getPhoneNumbers();
			List<PhoneNumber> expected = new Stack<>();
			expected.add(phoneNumberOffice);
			expected.add(phoneNumberWork);
			assertTrue(expected.size()==result.size());
			assertTrue(expected.containsAll(result));
			assertTrue(result.containsAll(expected));
			//assertEquals("1234098765",contactWork.getNumber());
			
			List<Contact> listOfContactOffice = addressBook.searchByName("Worker");
			assertEquals(1, listOfContactOffice.size());
			assertEquals(contactWork, listOfContactOffice.get(0));
			
			Contact newWorker = new Contact("worker","SurelyEarth", phoneNumberWork, addressHome);
			newWorker.addPhoneNumber(phoneNumberWork);
			newWorker.addAddress(addressHome);
			addressBook.updateContact("Worker", newWorker);
			
			List<Contact> listOfNewContactOffice = addressBook.searchByName("Worker");
			assertEquals(1, listOfNewContactOffice.size());
			assertEquals(newWorker, listOfNewContactOffice.get(0));
			
			List<Contact> listOfNewContactOfficeOrganisation = addressBook.searchByOrganisation("Worker");
			assertEquals(0, listOfNewContactOfficeOrganisation.size());
			
			List<Contact> listOfOldContactOfficeOrganisation = addressBook.searchByOrganisation("HackerEarth");
			assertEquals(0, listOfOldContactOfficeOrganisation.size());
			
			Contact contactOfficeAddress = new Contact("Officer","FBI", phoneNumberOffice,addressOffice);
			contactOfficeAddress.addPhoneNumber(phoneNumberOffice);
			contactOfficeAddress.addAddress(addressOffice);
			addressBook.updateContact("hOME", contactOfficeAddress);
			
			List<Contact> listOfContactAddressUpdate = addressBook.searchByName("office");
			assertEquals(1, listOfContactAddressUpdate.size());
			assertEquals(contactOfficeAddress, listOfContactAddressUpdate.get(0));
						
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}
	//this test focuses on searching 
	@Test
	public void testAddressBookSearchAndUpdate() {
		
		
		try {
			//Valid Phone Numbers
			PhoneNumber pn0 = new PhoneNumber("work","1234565890");
			PhoneNumber pn1 = new PhoneNumber("home","1234563890");
			PhoneNumber pn2 = new PhoneNumber("office","1234167890");
			PhoneNumber pn3 = new PhoneNumber("house","1234527890");
			PhoneNumber pn4 = new PhoneNumber("jargon","1234467890");
			PhoneNumber pn5 = new PhoneNumber("from","1234565890");
			PhoneNumber pn6 = new PhoneNumber("now","1234567690");
			PhoneNumber pn7 = new PhoneNumber("asdasd","9234567890");
			PhoneNumber pn8 = new PhoneNumber("qwewqe","9234567890");
			PhoneNumber pn9 = new PhoneNumber("zxczxc","9234567890");
			
			//Valid Addreses
			Address add0 = new Address("work","Bangalore");
			Address add1 = new Address("home","Delhi");
			Address add2 = new Address("office","Pune");
			Address add3 = new Address("vacation","Goa");
			Address add4 = new Address("relax","nanital");
			Address add5 = new Address("fun","kolkata");
			Address add6 = new Address("jargon","from");
			Address add7 = new Address("now","asdasd");
			Address add8 = new Address("zxczxc","qweqweq");
			Address add9 = new Address("tyutyu","rtyrty");
			
			//Valid Contacts
			Contact con0 = new Contact("abcdefgValid","zyxw");
			Contact con1 = new Contact("abcdefValid","zyxw");
			Contact con2 = new Contact("abcdefghValid","zyxwabcd");
			Contact con3 = new Contact("abcdefghiValid","zyxwabcd");
			Contact con4 = new Contact("abcdValid","zyxwabc");
			
			//Add phone numbers and addresses to contacts
			con0.addPhoneNumber(pn0);
			con0.addPhoneNumber(pn1);
			con0.addAddress(add0);
			con0.addAddress(add1);
			
			con1.addPhoneNumber(pn2);
			con1.addPhoneNumber(pn3);
			con1.addAddress(add2);
			con1.addAddress(add3);
			
			con2.addPhoneNumber(pn4);
			con2.addPhoneNumber(pn5);
			con2.addAddress(add4);
			con2.addAddress(add5);
			
			con3.addPhoneNumber(pn6);
			con3.addPhoneNumber(pn7);
			con3.addAddress(add6);
			con3.addAddress(add7);
			
			con4.addPhoneNumber(pn8);
			con4.addPhoneNumber(pn9);
			con4.addAddress(add8);
			con4.addAddress(add9);
			
			AddressBook addressBook = new AddressBook();
			
			//Adding contacts to address book
			addressBook.addContact(con0);
			addressBook.addContact(con1);
			addressBook.addContact(con2);
			addressBook.addContact(con3);
			addressBook.addContact(con4);
			
			List<Contact> result = addressBook.searchByName("");
			List<Contact> expected = new ArrayList<>();
			
			expected.add(con0);
			expected.add(con1);
			expected.add(con2);
			expected.add(con3);
			expected.add(con4);			
			
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
			result = addressBook.searchByName("abcd");
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
			result = addressBook.searchByOrganisation("zyxw");
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
			result = addressBook.searchByName("abcde");
			expected.remove(con4);
			
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
			result = addressBook.searchByName("abcdefg");
			expected.remove(con1);
			
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
			addressBook.deleteContact("abcdValid");
			
			result = addressBook.searchByName("abcd");
			expected.add(con1);
			
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
			expected.add(con4);
			expected.remove(con0);
			expected.remove(con1);
			
			addressBook.addContact(con4);
			result = addressBook.searchByOrganisation("zyxwa");
			
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
			expected.remove(con4);
			
			result = addressBook.searchByOrganisation("zyxwabcd");
			
			assertEquals(result.size(), expected.size());
			assertTrue(result.containsAll(expected) && expected.containsAll(result));
			
		} catch (Exception e) {
			fail();
		}
		
		
	}
	
	
	// tests Exceptions
	@Test
	public void testInvalidFields() {
		try {
			new PhoneNumber("work","123");
			
		}
		catch (Exception e) {
			
		}
		try {
			new PhoneNumber("work","123456789A");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new PhoneNumber(null,"1234567890");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new PhoneNumber("","1234567890");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new Address(null,"");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			
			new Contact("123","hackerearth");//,new PhoneNumber("work","1234567890"), new Address("work",""));
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			
			new Contact("","hackerearth");//,new PhoneNumber("work","1234567890"), new Address("work",""));
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new Address("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc","");
			fail();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			new PhoneNumber("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc","1234567890");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			
			new Contact("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc","hackerearth");//,new PhoneNumber("work","1234567890"), new Address("work",""));
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new AddressBook().deleteContact("");
			fail();
		}
		catch(Exception e){
			
		}
	
	}

}


