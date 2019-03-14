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

public class UpdateContactScreenController {
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
	
	private Contact contact;
	
	@FXML
	public void initialize(){
		System.out.println("test");
		this.contact = DisplayListScreenController.getActualContact();
		this.firstnameText.setText(contact.getFirstname());
		this.lastnameText.setText(contact.getLastname());
		this.nicknameText.setText(contact.getNickname());
		this.phoneNumberText.setText(contact.getPhoneNumber());
		this.addressText.setText(contact.getAddress());
		this.mailText.setText(contact.getEmailAddress());
		this.birthdateText.setText(contact.getBirthdate().toString());
	}
	
	@FXML
	public void updateContact(){
		Contact newContact = new Contact();
		try{
		newContact.setFirstname(this.firstnameText.getText());
    	newContact.setLastname(this.lastnameText.getText());
    	newContact.setNickname(this.nicknameText.getText());
    	newContact.setPhoneNumber(this.phoneNumberText.getText());
    	newContact.setAddress(this.addressText.getText());
    	newContact.setEmailAddress(this.mailText.getText());
    	newContact.setBirthdate(Date.valueOf(this.birthdateText.getText()));
    		ContactDao.updateContact((int)this.contact.getIdUser(), newContact);
    		ContactService.updateContact(contact,newContact);
    		System.out.println("contact updatedd");
    	}
    	catch(Exception e){
    		System.err.println("error with BDD connexion");
    	}
		StageService.showView(ViewService.getView("DisplayListScreen"));
	}
}
