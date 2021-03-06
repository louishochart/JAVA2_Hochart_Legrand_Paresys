package hochartlegrandparesys.view;

import java.util.List;

import hochartlegrandparesys.daos.ContactDao;
import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.*;
import hochartlegrandparesys.service.*;
import hochartlegrandparesys.util.*;
import hochartlegrandparesys.utils.VCard;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DisplayListScreenController {
	@FXML
	private Button vcardContactButton;
	@FXML
	private Button vcardAllButton;
	@FXML
	private Button button_update;
	@FXML 
	private Button button_delete;
	@FXML
	private TableView<Contact> contactTable;
	@FXML
	private TableColumn<Contact,String> idColumn;
	@FXML
	private TableColumn<Contact,String> nicknameColumn;
	@FXML
	private TableColumn<Contact,String> firstnameColumn;
	@FXML
	private TableColumn<Contact,String> lastnameColumn;
	@FXML
	private TableColumn<Contact,String> phoneNumberColumn;
	@FXML
	private static Contact actualContact;
	
	UserDao user = new UserDao();
	List<User> listUser;
	
	@FXML
	public void initialize(){
		this.nicknameColumn.setCellValueFactory(new NicknameValueFactory());
		this.firstnameColumn.setCellValueFactory(new FirstnameValueFactory());
		this.lastnameColumn.setCellValueFactory(new LastnameValueFactory());
		this.phoneNumberColumn.setCellValueFactory(new PhoneNumberValueFactory());
		this.populateList();
		this.contactTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>(){
			@Override
			public void changed(ObservableValue<? extends Contact> observable, Contact oldContact, Contact newContact) {
				actualContact=newContact;
			}
		});
		
		this.refreshList();
	}
	
	@FXML
	private void refreshList(){
		contactTable.refresh();
	}
	
	@FXML
	private void populateList(){
		ObservableList<Contact> listeContact = ContactService.getContacts();
		contactTable.setItems(ContactService.getContacts());
		this.refreshList();
	}
	
	@FXML
	public void addContact(){
		StageService.showView(ViewService.getView("AddContactScreen"));
	}
	
	@FXML
	public void updateContact(){
		StageService.showView(ViewService.getView("UpdateContactScreen"));
	}
	
	@FXML
	public void deleteContact(){
		try{
			ContactDao.deleteContact((int)actualContact.getIdContact());
			ContactService.deleteContact(actualContact);
			System.out.println("contact deleted");
		}
		catch(Exception e){
			System.err.println("error deleting the contact");
		}
	}
	
	@FXML
	public void exportAllVcard(){
		try{
			VCard.exportAllVCards(ContactService.getContacts());
		}
		catch(Exception e){
			System.err.println("error in exporting contact list to Vcard");
		}
	}
	
	@FXML 
	public void exportContactToVcard(){
		try{
			VCard.exportVCard(actualContact);
		}
		catch(Exception e){
			System.err.println("error in exporting this contact to Vcard");
		}
	}
	
	public static Contact getActualContact(){
		return actualContact;
	}
}
