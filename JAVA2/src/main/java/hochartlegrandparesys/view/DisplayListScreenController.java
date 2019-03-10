package hochartlegrandparesys.view;

import hochartlegrandparesys.service.StageService;
import hochartlegrandparesys.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DisplayListScreenController {
	@FXML
	private Button button_update;
	@FXML 
	private Button button_delete;
	
	public void updateContact(){
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	public void deleteContact(){
		
	}
}
