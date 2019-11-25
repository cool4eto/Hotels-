package JavaFXstuff;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Position;
import com.javatpoint.Hotels.SessionUserHelper;
import com.javatpoint.Hotels.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserController {
		private Hotel hot2;
	 @FXML
	    private TextField TextField1;

	    @FXML
	    private TextField TextField2;

	    @FXML
	    private TextField TextField3;

	    @FXML
	    private ChoiceBox<String> ChoiceBox1;

	    @FXML
	    private ChoiceBox<String> ChoiceBox2;

	    @FXML
	    private Label Label1;

	    @FXML
	    private Button Button1;
	   private List<Position> positions;//narochno sa izkarani izvan za da moje da se polzwat i ot save
	   private List<Hotel> hotels;
	    public void initialize() {
	    	
	    	
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	String hql = "from Position";
	    	Query query = session.createQuery(hql);
	    	positions = query.list();
	    	 if(SessionUserHelper.getCurrentUser().getPosition().getType().equals("Собственик"))//sobstwenika da ne moje da sazdawa sobstwenici i root
		        {
	    		 	try {
		        	positions.remove(0);
		        	positions.remove(2);
	    		 		
	    		 	}
	    		 	catch (Exception e) {};
		        }
	    	 if(SessionUserHelper.getCurrentUser().getPosition().getType().equals("Мениджър"))//sobstwenika da ne moje da sazdawa sobstwenici i root
		        {
	    		 	try {
		        	positions.remove(0);
		        	positions.remove(0);
		        	positions.remove(1);
	    		 		
	    		 	}
	    		 	catch (Exception e) {};
		        }
	    	
	    	
	    	
	    	ObservableList<String> cursors = FXCollections.observableArrayList();
	    	for(int i =0;i<positions.size();i++)//obhojda wsichki hoteli
	    	{
	    		cursors.add(positions.get(i).getType());//dobawq gi v lista
	    	}
	        ChoiceBox1.setItems(cursors);
	         hql = "from Hotel";
	    	 query = session.createQuery(hql);
	    	hotels = query.list();
	    	
	    	ObservableList<String> cursors1 = FXCollections.observableArrayList();//suzdawa ni list w koito shte slagame imenata na hotelite
	    	for(int i =0;i<hotels.size();i++)//obhojda wsichki hoteli
	    	{
	    		cursors1.add(hotels.get(i).getHotelName());//dobawq gi v lista
	    	}
	        ChoiceBox2.setItems(cursors1);//slaga lista v choiceBoxa
	        
	        
	        if(!SessionUserHelper.getCurrentUser().getUsername().equals("root"))//ako ne e root da ne moje da izbira hotel
	    	{
	    		ChoiceBox2.setDisable(true);
	    		ChoiceBox2.setVisible(false);
	    		Label1.setVisible(false);
	    		
	    	}
	       
	        
	        
	    }
	    public void save()
	    {	
	    	try {
	    		if(TextField1.getText().trim().isEmpty()||TextField2.getText().trim().isEmpty()||TextField3.getText().trim().isEmpty())
	    			throw new Exception();
	    		
	    	LocalDate regdate= LocalDate.now();
	    Position pos2=positions.get(ChoiceBox1.getSelectionModel().getSelectedIndex());//za da ne stawa pretrupan konstruktora
	    if(SessionUserHelper.getCurrentUser().getUsername().equals("root"))
	    hot2=hotels.get(ChoiceBox2.getSelectionModel().getSelectedIndex());
	    else hot2=SessionUserHelper.getCurrentUser().getHotel();
	   
//String username, String password, String name, boolean loginstatus, LocalDate regdate, Position position,Hotel hotel
	    	User user1=new User(TextField1.getText().toString(),TextField2.getText().toString(),TextField3.getText().toString(),false,regdate,pos2,hot2);
	    	
	    	user1.store();
	    	Notification.showOk();
	    	}
	    	catch(Exception e)
	    	{
	    		Notification.showWarning();
	    	}
	    }
}
