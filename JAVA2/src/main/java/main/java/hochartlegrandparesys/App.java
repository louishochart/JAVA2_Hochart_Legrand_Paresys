package main.java.hochartlegrandparesys;


import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

public class App extends Application {

	public App() {

	}

	@Override
	public void start(Stage primaryStage) {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView((Node) ViewService.getView("HomeScreen"));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
