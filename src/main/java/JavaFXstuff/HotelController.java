package JavaFXstuff;

import com.javatpoint.Hotels.Hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HotelController {
	@FXML
	private TextField TextField1;
	public void save(ActionEvent event)throws Exception 
	{
	Hotel hotel1=new Hotel(TextField1.getText().toString());
	hotel1.store();
	}
}
