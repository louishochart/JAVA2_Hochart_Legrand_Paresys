package hochartlegrandparesys.view;


import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.User;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignInScreenController {

	@FXML
	private Button confirmButton;
	@FXML
	private TextField lastnameTextField;
	@FXML
	private TextField firstnameTextField;
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField passwordPasswordField;
	@FXML
	private TextField phoneNumberTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField mailTextField;
	@FXML
	private Button loginButton;

	
	@FXML
	public void saveUser(){
		User newUSer = new User();
		try{
		newUSer.setFirstname(this.firstnameTextField.getText());
		newUSer.setLastname(this.lastnameTextField.getText());
		newUSer.setUsername(this.usernameTextField.getText());
    	newUSer.setPassword(this.passwordPasswordField.getText());
    	newUSer.setPhoneNumber(this.phoneNumberTextField.getText());
    	newUSer.setAddress(this.addressTextField.getText());
    	newUSer.setEmailAddress(this.mailTextField.getText());
    		UserDao.addUser(newUSer);
    		//UserService.addUser(newUSer);
    		System.out.println("user saved");
    	}
    	catch(Exception e){
    		System.err.println("error with BDD connexion");
    	}
		StageService.showView(ViewService.getView("DisplayListScreen"));
	}
	
	@FXML
	public void goToHomeScreen() {
		StageService.showView(ViewService.getView("HomeScreen"));
	}

}
