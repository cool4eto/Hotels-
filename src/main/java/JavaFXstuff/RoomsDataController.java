package JavaFXstuff;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Reservation;
import com.javatpoint.Hotels.Room;
import com.javatpoint.Hotels.SessionUserHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class RoomsDataController {
	private Hotel hotel1=SessionUserHelper.getCurrentUser().getHotel();
	private List<Reservation> reservations;
	private LocalDate firstDate;
	private LocalDate secondDate;
	private List<Room> rooms;
	 @FXML
	    private GridPane GridPane1;

	    @FXML
	    private DatePicker DatePicker1;

	    @FXML
	    private DatePicker DatePicker2;
	    @FXML
	    private Label label1;

	    public void initialize()
	    {
	    //	getAllRooms();
	    	listeners();
	    	
	    }
	    
	    @FXML 
	    public void getReservations()
	    {
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	//String hql = "from Reservation where  hotel=:hotel1 AND fromDate>=:firstDate AND toDate<=:secondDate";
	    	
	    	String hql = "from Reservation where  hotel=:hotel1 AND fromDate<=:secondDate AND toDate>=:firstDate";
	    	
	    	
	    	
	    	Query query = session.createQuery(hql);
	    	query.setParameter("firstDate",firstDate);
	    	query.setParameter("secondDate",secondDate);
			   query.setParameter("hotel1", hotel1);
			    reservations = query.list();
			    getAllRooms();
	    }
	    
	    @FXML
	    public void getAllRooms()
	    {
	    	label1.setText(Long.toString(ChronoUnit.DAYS.between(firstDate,secondDate)));
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	String hql="FROM Room where hotel=:hotel1 ORDER BY roomNumber ASC";
	    	Query query = session.createQuery(hql);
	        query.setParameter("hotel1", hotel1);
	        rooms = query.list();
	      
	        int n=0;
	        int oldNumber=1;
	        int col=0;
	        int timesReservated=0;
	        int daysReservated=0;
	        for(int i=0;i<rooms.size();i++)//obhozhda staite
	        {
	        		
	        		for(int j=0;j<reservations.size();j++)
	        		{
	        			//if(reservations.get(j).getRoom().equals(rooms.get(i)))towa ne raboti nz zashto
	        			if(reservations.get(j).getRoom().getIdRoom()==(rooms.get(i).getIdRoom()))
	        			{
	        				timesReservated++;
	        				//daysReservated+=ChronoUnit.DAYS.between(reservations.get(j).getFromDate(), reservations.get(j).getToDate());
	        				
	        				LocalDate maxStart = reservations.get(j).getFromDate().isAfter(firstDate) ? reservations.get(j).getFromDate() : firstDate;
	        		        LocalDate minEnd = reservations.get(j).getToDate().isBefore(secondDate) ? reservations.get(j).getToDate() : secondDate;
	        		        daysReservated+=  ChronoUnit.DAYS.between(maxStart,minEnd);
	        		        
	        				
	        				
	        			}
	        		}
	        	
	        	
	        		n=rooms.get(i).getRoomNumber();
	        		int digits = (int)(Math.log10(rooms.get(i).getRoomNumber())); 
	                n = (int)(n / (int)(Math.pow(10, digits))); 
	        			if(n!=oldNumber)
	        			{
	        			col=0;
	        			oldNumber=n;
	        			}
	        			String str=stringForLabel(rooms.get(i),timesReservated,daysReservated);
	        			Label label1=new Label(str);
	        			
	        			GridPane.setConstraints(label1,col,n-1);
	        			col++;
	        			if(daysReservated>=ChronoUnit.DAYS.between(firstDate,secondDate)/2)//Swetwat ni w zeleno staite koito sa zaeti nad 50% ot wremeto
	        				label1.setStyle("-fx-background-color:LightGreen");
	        			GridPane1.getChildren().add(label1);
	        			timesReservated=0;
	        		    daysReservated=0;
	        }
	        
	    }
	    @FXML
	    public void listeners()
	    {
	    	DatePicker1.valueProperty().addListener((ov, oldValue, newValue) -> {
	    		try {
	    			GridPane1.getChildren().clear();
	    		 firstDate=DatePicker1.getValue();
	    		 secondDate=DatePicker2.getValue();
	             getReservations();
	    		}
	    		catch(Exception e) {};
	         });
	    	 DatePicker2.valueProperty().addListener((ov, oldValue, newValue) -> {
	    		 try {
	    			 GridPane1.getChildren().clear();
	    		 firstDate=DatePicker1.getValue();
	    		 secondDate=DatePicker2.getValue();
	            getReservations();
	    		 }
		    		catch(Exception e) {};
	         });
	    	 
	    }
	    public String stringForLabel(Room room,int times,int days)
	    {
	    	String str1="";
	    	str1+="Стая №: ";
	    	str1+=room.getRoomNumber();
	    	str1+="\nЕ наемана: ";
	    	str1+=times;
	    	str1+=" пъти";
	    	str1+="\nОбщо дни заета: ";
	    	str1+=days;
	    	return str1;
	    }
	    
}
