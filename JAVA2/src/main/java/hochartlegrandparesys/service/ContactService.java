package hochartlegrandparesys.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

import hochartlegrandparesys.daos.ContactDao;
import hochartlegrandparesys.models.Contact;


public class ContactService {

	private ObservableList<Contact> contacts;
	public int userId;
	
	private ContactService(){
		userId=1;
		contacts = FXCollections.observableArrayList();
		long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
        for(Contact contact : ContactDao.listContactsbyUserId(userId)) {
        	contacts.add(contact);
        }
	}
	
	public static ObservableList<Contact> getContacts() {
		return ContactServiceHolder.INSTANCE.contacts;
	}
	public static void addContact(Contact newContact) {
		ContactServiceHolder.INSTANCE.contacts.add(newContact);
	}
	public static void deleteContact(Contact contact){
		ContactServiceHolder.INSTANCE.contacts.remove(contact);
	}
	public static void updateContact(Contact oldContact, Contact newContact){
		ContactServiceHolder.INSTANCE.contacts.remove(oldContact);
		ContactServiceHolder.INSTANCE.contacts.add(newContact);
	}
	private static class ContactServiceHolder {
		private static final ContactService INSTANCE = new ContactService();
	}
}
