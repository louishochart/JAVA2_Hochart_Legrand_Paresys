package hochartlegrandparesys.view;

import java.util.List;

import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.*;
import hochartlegrandparesys.service.*;
import hochartlegrandparesys.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
		this.nicknameColumn.setCellValueFactory(new NicknameValueFactory());
		this.firstnameColumn.setCellValueFactory(new FirstnameValueFactory());
		this.lastnameColumn.setCellValueFactory(new LastnameValueFactory());
		this.phoneNumberColumn.setCellValueFactory(new PhoneNumberValueFactory());
		this.populateList();
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
		ObservableList<Contact> listeContact = ContactService.getContacts();
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
