package hochartlegrandparesys.view;


import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.User;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomeScreenController {

	@FXML
	private Button loginButton;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordPasswordField;
	@FXML
	private Button signInButton;
	
	@FXML
	public void goToSignInScreen(){
		StageService.showView(ViewService.getView("SignInScreen"));
	}
	
	@FXML
	public void checkLogin(){
		//User checkedUser = new User();
		User storedUser = new User();
		try {
			storedUser = UserDao.getUserByUsername(this.usernameTextField.getText());
			if(storedUser.getPassword().equals(this.passwordPasswordField.getText())) {
				System.out.println("Good password!");
				StageService.showView(ViewService.getView("DisplayListScreen"));
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Username or Password is false !");

				alert.showAndWait();
				StageService.showView(ViewService.getView("HomeScreen"));
			}
		}
		catch(Exception e){
    		System.err.println("error with BDD connexion");
    	}
		

	}

}
