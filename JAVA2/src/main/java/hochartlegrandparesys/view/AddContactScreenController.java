package hochartlegrandparesys.view;

import java.sql.Date;

import hochartlegrandparesys.daos.ContactDao;
import hochartlegrandparesys.models.Contact;
import hochartlegrandparesys.service.ContactService;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddContactScreenController {
	@FXML
	private Button confirmButton;
	@FXML
	private TextField lastnameText;
	@FXML
	private TextField firstnameText;
	@FXML
	private TextField nicknameText;
	@FXML
	private TextField phoneNumberText;
	@FXML
	private TextField addressText;
	@FXML
	private TextField mailText;
	@FXML
	private TextField birthdateText;
	
	@FXML
	public void saveContact(){
		Contact newContact = new Contact();
		try{
		newContact.setFirstname(this.firstnameText.getText());
    	newContact.setLastname(this.lastnameText.getText());
    	newContact.setNickname(this.nicknameText.getText());
    	newContact.setPhoneNumber(this.phoneNumberText.getText());
    	newContact.setAddress(this.addressText.getText());
    	newContact.setEmailAddress(this.mailText.getText());
    	newContact.setBirthdate(Date.valueOf(this.birthdateText.getText()));
    		ContactDao.addContact(newContact);
    		ContactService.addContact(newContact);
    		System.out.println("contact saved");
    	}
    	catch(Exception e){
    		System.err.println("error with BDD connexion");
    	}
		StageService.showView(ViewService.getView("DisplayListScreen"));
	}
	
}
