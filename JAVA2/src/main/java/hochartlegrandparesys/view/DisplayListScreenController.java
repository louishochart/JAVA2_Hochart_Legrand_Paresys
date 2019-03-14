package hochartlegrandparesys.view;

import java.util.List;

import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.User;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DisplayListScreenController {
	@FXML
	private Button button_update;
	@FXML 
	private Button button_delete;
	@FXML
	private TableView<User> contactTable;
	@FXML
	private TableColumn<User,String> idColumn;
	@FXML
	private TableColumn<User,String> nicknameColumn;
	@FXML
	private TableColumn<User,String> firstnameColumn;
	@FXML
	private TableColumn<User,String> lastnameColumn;
	@FXML
	private TableColumn<User,String> phoneNumberColumn;
	
	UserDao user = new UserDao();
	List<User> listUser;
	
	@FXML
	public void initialize(){
		this.listUser=user.listUsers();
	}
	
	@FXML
	private void refreshList(){
		contactTable.refresh();
	}
	
	@FXML
	private void printContact(){
		contactTable.setItems(this.listUser);
	}
	
	@FXML
	public void updateContact(){
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	public void deleteContact(){
		
	}
}
