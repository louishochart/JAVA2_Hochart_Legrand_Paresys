package hochartlegrandparesys.view;

import java.util.List;

import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.Contact;
import hochartlegrandparesys.models.User;
import hochartlegrandparesys.service.ContactService;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import hochartlegrandparesys.util.NicknameValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	UserDao user = new UserDao();
	List<User> listUser;
	
	@FXML
	public void initialize(){
		//this.listUser=user.listUsers();
		this.nicknameColumn.setCellValueFactory(new NicknameValueFactory());
		
		this.contactTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>(){
			@Override
			public void changed(ObservableValue<? extends Contact> observable, Contact oldContact, Contact newContact) {
				//showQuestionDetails(newContact);
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
		contactTable.setItems(ContactService.getContacts());
		this.refreshList();
	}
	
	@FXML
	public void updateContact(){
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	public void deleteContact(){
		
	}
}
