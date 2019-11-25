package JavaFXstuff;

import com.javatpoint.Hotels.RoomType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RoomTypeController {
	@FXML
	private TextField TextField1;
	public void save(ActionEvent event)throws Exception 
	{
		try {
		if(TextField1.getText().trim().isEmpty())
			throw new Exception();
	RoomType room1=new RoomType(TextField1.getText().toString());
	room1.store();
	Notification.showOk();
		}
		catch(Exception e)
		{
			Notification.showWarning();
		}
	
	}
}
