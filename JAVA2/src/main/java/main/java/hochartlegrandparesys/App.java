package main.java.hochartlegrandparesys;


import java.awt.List;
import java.sql.Date;
import java.util.ArrayList;

import hochartlegrandparesys.daos.ContactDao;
import hochartlegrandparesys.daos.UserDao;
import hochartlegrandparesys.models.Contact;
import hochartlegrandparesys.models.User;
import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import hochartlegrandparesys.utils.VCard;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	public App() {

	}

	@Override
	public void start(Stage primaryStage) {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView(ViewService.getView("HomeScreen"));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
