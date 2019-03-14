package hochartlegrandparesys.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import hochartlegrandparesys.daos.ContactDao;
import hochartlegrandparesys.models.Contact;


public class ContactService {

	private ObservableList<Contact> contacts;
	public int userID;
	
	private ContactService(){
		userID=3;
		contacts = (ObservableList<Contact>)ContactDao.listContactsbyUserId(userID);
	}
	
	public static ObservableList<Contact> getContacts() {
		return ContactServiceHolder.INSTANCE.contacts;
	}
	private static class ContactServiceHolder {
		private static final ContactService INSTANCE = new ContactService();
	}
}
