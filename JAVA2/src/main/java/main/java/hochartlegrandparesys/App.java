package main.java.hochartlegrandparesys;


import java.sql.Date;

import hochartlegrandparesys.daos.ContactDao;
import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.Contact;
import hochartlegrandparesys.models.User;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	public App() {

	}

	@Override
	public void start(Stage primaryStage) {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView(ViewService.getView("DisplayListScreen"));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
