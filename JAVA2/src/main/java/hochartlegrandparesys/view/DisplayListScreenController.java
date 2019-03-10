package hochartlegrandparesys.view;

import java.util.List;

import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.User;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DisplayListScreenController {
	@FXML
	private Button button_update;
	@FXML 
	private Button button_delete;
	
	UserDao user;
	List<User> listUser;
	
	public DisplayListScreenController(){
		this.listUser=user.listUsers();
	}
	
	public void updateContact(){
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	public void deleteContact(){
		
	}
}
