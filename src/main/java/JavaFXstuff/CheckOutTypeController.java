package JavaFXstuff;

import com.javatpoint.Hotels.CheckOutType;
import com.javatpoint.Hotels.RoomType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CheckOutTypeController {
	@FXML
	private TextField TextField1;
	public void save(ActionEvent event)throws Exception 
	{
		try {
			if(TextField1.getText().trim().isEmpty())
    			throw new Exception();
	CheckOutType check1=new CheckOutType(TextField1.getText().toString());
	check1.store();
	Notification.showOk();
		}
		catch(Exception e)
		{
			Notification.showWarning();
		}
	
	}
}
