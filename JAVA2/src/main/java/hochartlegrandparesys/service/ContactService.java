package hochartlegrandparesys.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

import hochartlegrandparesys.daos.ContactDao;
import hochartlegrandparesys.models.Contact;


public class ContactService {

	private ObservableList<Contact> contacts;
	public int userID;
	
	private ContactService(){
		userID=1;
		contacts = FXCollections.observableArrayList();
		long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
		contacts.add(new Contact("Germain","Paresys", "0610800250"
				, "Lille","germain.paresys@gmail.com"
				, "zooph",date, 9));
		//contacts = (ObservableList<Contact>)ContactDao.listContactsbyUserId(userID);
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
	private static class ContactServiceHolder {
		private static final ContactService INSTANCE = new ContactService();
	}
}
