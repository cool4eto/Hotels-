package JavaFXstuff;

import java.time.LocalDate;

import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Position;
import com.javatpoint.Hotels.SessionUserHelper;
import com.javatpoint.Hotels.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HotelController {
	@FXML
	private TextField TextField1;
	private Hotel hotel1;
	public void save(ActionEvent event)throws Exception 
	{
		try {
	hotel1=new Hotel(TextField1.getText().toString());
	if(TextField1.getText().trim().isEmpty())
		throw new Exception();
	hotel1.store();
	createUsers();
	Notification.showOk();
		}
		catch(Exception e)
		{
			Notification.showWarning();
		}
	
	}
	public void createUsers()
	{
		LocalDate a = LocalDate.now();
		Position pos1=new Position(4,"root");
		User user1=new User("root","root","root",false,a,pos1,hotel1);
		user1.store();
		if(SessionUserHelper.getCurrentUser().getPosition().getType().equals("Собственик"))
		{
			User user2=new User(SessionUserHelper.getCurrentUser().getUsername(),SessionUserHelper.getCurrentUser().getPassword(),SessionUserHelper.getCurrentUser().getName(),false,a,SessionUserHelper.getCurrentUser().getPosition(),hotel1);
			user2.store();
		}
	}
}
