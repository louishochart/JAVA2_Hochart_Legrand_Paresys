package hochartlegrandparesys.service;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import main.java.hochartlegrandparesys.App;

public class ViewService {

	public static <T> T getView(String id) {
		return getLoader(id).getRoot();
	}

	private static FXMLLoader getLoader(String id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			System.out.println(App.class.getClassLoader().getResource("hochartlegrandparesys/view/"+id+".fxml"));
			
			loader.setLocation(App.class.getClassLoader().getResource("hochartlegrandparesys/view/"+id+".fxml"));
			
			loader.load();
			return loader;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
