package JavaFXstuff;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.CheckOutType;
import com.javatpoint.Hotels.Consumation;
import com.javatpoint.Hotels.Guest;
import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Reservation;
import com.javatpoint.Hotels.Room;
import com.javatpoint.Hotels.RoomType;
import com.javatpoint.Hotels.Service;
import com.javatpoint.Hotels.SessionUserHelper;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdditionalServiceController {
		private Hotel hotel1=SessionUserHelper.getCurrentUser().getHotel();//towa ukazwa za koi hotel shte barkame kato tarsim rezervaciqta
		private LocalDate curDate = LocalDate.of(2019, 11, 26);//towa trqbwa da se zameni sus dneshna data kogato se polzwa naistina programata no za testwane mi warshi rabota
		private ObservableList<Reservation> dataList = FXCollections.observableArrayList();
	 @FXML
	    private TableView<Reservation> TableView1;

	    @FXML
	    private TableColumn<Room, Integer> NumberOfRoom;

	    @FXML
	    private TableColumn<Guest, String>GuestName;

	    @FXML
	    private TextField TextField1;
	    @FXML
	    private TextField TextField2;

	    @FXML
	    private ChoiceBox<String> ChoiceBox1;

	    @FXML
	    private Button Button1;
	    @FXML
	    void initialize() {
	    	this.NumberOfRoom.setCellValueFactory(new PropertyValueFactory("room"));
	    	this.GuestName.setCellValueFactory(new PropertyValueFactory("guest"));
	    	//this.GuestName.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
	    	fillTableView1();
	    	fillChoiceBox1();
	    	
	    }
	    public void fillTableView1()
	    {
	    	CheckOutType notCheckedOut = new CheckOutType(1,"notCheckedOut");
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	String hql = "from Reservation where  hotel=:hotel1 AND fromDate<=:curDate AND toDate>=:curDate And checkOutType=:notCheckedOut";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("curDate",curDate);
			   query.setParameter("hotel1", hotel1);
			   query.setParameter("notCheckedOut", notCheckedOut);
	    	List<Reservation> reservations = query.list();
	    	
	    	for(int i =0;i<reservations.size();i++)
	    	{
	    		
	    	}
	    	dataList=FXCollections.observableArrayList(reservations);
	    	FilteredList<Reservation> filteredData = new FilteredList(this.dataList, null);
	    	this.TextField1.textProperty().addListener((observable, oldValue, newValue) -> {
	    		             filteredData.setPredicate((reserv1) -> 
	    		             {//setPredictate filtrira realno lambdata sochi kam test
	    		                if (newValue == null || newValue.isEmpty()) //ako oshte ni e prazen textfielda ne prawi nishto
	    		                {
	    		                return true;
	    		                }
	    		                   String lowerCaseFilter = newValue.toLowerCase();//za da stane case insensitive
	    		                   if (reserv1.getGuest().getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	    		                	  
	    		                      return true;
	    		                   }
	    		                   if (Integer.toString(reserv1.getRoom().getRoomNumber()).toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
		    		                      return true;
		    		                   }
	    		                   else 
	    		                	   {
	    		                	   return false;
	    		                	   }
	    		             });
	    		          });
	    	SortedList<Reservation> sortedData = new SortedList(filteredData);
	         sortedData.comparatorProperty().bind(this.TableView1.comparatorProperty());
	         this.TableView1.setItems(sortedData);
	    	
	    }
	    private List<Service> services;
	    public void fillChoiceBox1()
	    {
	    	 LocalDate start = LocalDate.of(curDate.getYear(), 03, 31);//data koga zapozhwa lqtnoto vreme
	    	 LocalDate end = LocalDate.of(curDate.getYear(), 10, 27);//data koga zawarshwa lqtnoto vreme
	    	 String a;
	    	 if(curDate.isAfter(start)&&curDate.isBefore(end))
	    		 a ="Лято";
	    	 else
	    		 a="Зима";
	    	 Session session = HibernateUtil.getSessionFactory().openSession();
		    	String hql = "from Service where ServiceSeason=:a";
		    	Query query = session.createQuery(hql);
		    	 query.setParameter("a", a);
		    	services = query.list();
		    	
		    	
		    	ObservableList<String> cursors = FXCollections.observableArrayList();//suzdawa ni list w koito shte slagame imenata na roomtypes
		    	for(int i =0;i<services.size();i++)//obhojda wsichki hoteli
		    	{
		    		cursors.add(services.get(i).getServiceName());//dobawq gi v lista
		    	}
		        ChoiceBox1.setItems(cursors);//slaga lista v choiceBoxa
	    	
	    }
	    @FXML 
	    public void save()
	    {
	    	Reservation reserv1=TableView1.getSelectionModel().getSelectedItem();
	    	Service serv1=services.get(ChoiceBox1.getSelectionModel().getSelectedIndex());
	    	Consumation cons1=new Consumation(Integer.parseInt(TextField2.getText().toString()),reserv1,serv1);
	    	cons1.store();
	    	
	    }
}









