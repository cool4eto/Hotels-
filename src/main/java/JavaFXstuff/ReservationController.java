package JavaFXstuff;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.CheckOutType;
import com.javatpoint.Hotels.Guest;
import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Reservation;
import com.javatpoint.Hotels.Room;
import com.javatpoint.Hotels.RoomType;
import com.javatpoint.Hotels.SessionUserHelper;
import com.javatpoint.Hotels.User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ReservationController {
	 @FXML
	    private DatePicker Date1;

	    @FXML
	    private DatePicker Date2;

	    @FXML
	    private TextField TextField1;

	    @FXML
	    private Button Button1;

	    @FXML
	    private ChoiceBox<String> ChoiceBox1;
	    
	    @FXML
	    private TableView<Room> TableView1;

	    @FXML
	    private TableColumn<Room,Integer> roomNumber;

	    @FXML
	    private TableColumn<RoomType, String> typeRoom;

	    @FXML
	    private Button Button2;
	    
	    private List<Room> rooms ;
	    private ObservableList<Room> dataList = FXCollections.observableArrayList();
	    private Guest guest1=new Guest();
	    private User user1=new User();
	    private Hotel hotel1=new Hotel();
	    private RoomType roomt1=new RoomType();
	    private LocalDate fromDate1;
	    private LocalDate toDate1;
	    
	    
	    public void initialize()
	    {
	    	this.roomNumber.setCellValueFactory(new PropertyValueFactory("roomNumber"));
	    	this.typeRoom.setCellValueFactory(new PropertyValueFactory("roomtype"));
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	String hql = "from RoomType";
	    	Query query = session.createQuery(hql);
	    	List<RoomType> roomtypes = query.list();
	    	
	    	ObservableList<String> cursors = FXCollections.observableArrayList();//suzdawa ni list w koito shte slagame imenata na roomtypes
	    	for(int i =0;i<roomtypes.size();i++)//obhojda wsichki hoteli
	    	{
	    		cursors.add(roomtypes.get(i).getTypeRoom());//dobawq gi v lista
	    	}
	        ChoiceBox1.setItems(cursors);//slaga lista v choiceBoxa
	        
	        ChoiceBox1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() //listener za promqna w choicebox 
	        {
		        @Override
		        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
		        	
		        	try {//tozi try catch e za da moje kato se nulira choiceboxa sled promqna na data da ne hwarlq null pointer exception
		        System.out.println(ChoiceBox1.getItems().get((Integer) number2));
		          roomt1=(roomtypes.get(ChoiceBox1.getSelectionModel().getSelectedIndex()));
		          save();
		        	}
		        	catch(Exception e) {
		        		  
		        	}
		          
		        }
		      });
	        user1=SessionUserHelper.getCurrentUser();
	        hotel1=user1.getHotel();
	        Date1.valueProperty().addListener((observable, oldDate, newDate)->{TableView1.setItems(null);ChoiceBox1.getSelectionModel().clearSelection();});//ako se promeni datata da se restartirat choicebox i izbora na tip na staq za da se prezaredi na nowo tableview sled towa
	        Date2.valueProperty().addListener((observable, oldDate, newDate)->{TableView1.setItems(null);ChoiceBox1.getSelectionModel().clearSelection();});
	    }
	    public void save()
	    {
	    	fromDate1 = Date1.getValue();
	    	toDate1 = Date2.getValue();
	    	//Hotel hotel1=new Hotel(1,"Марина");//towa trqbwa da go zamenq s wzemane na hotela ot usera*/
	    	//RoomType roomt1=new RoomType(2,"Double");//towa trqbwa da go zamenq s izbora ot choiceboxa
	    	
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	try {
	    	String hql = "from Reservation where hotel=:hotel1 and toDate>:from and fromDate<=:to";//tursi staite na koito range navliza ili suvpada s nashiq
	    	//toDate>:from  narochno nqma ravno za da moje w denq w koito checkoutwa starata rezervaciq da se nastani nowa w tazi staq
	    	Query query = session.createQuery(hql);
	    	query.setParameter("from",fromDate1);
			   query.setParameter("to", toDate1);
			   query.setParameter("hotel1", hotel1);
	    	List<Reservation> reservations = query.list();//list sus rezervacii na koito perioda im e chast ot perioda na novata rezervaciq
	    	
	    	
	    	hql="FROM Room where roomtype=:roomt1 and hotel=:hotel1";
	        query = session.createQuery(hql);
	        query.setParameter("roomt1",roomt1);
	        query.setParameter("hotel1", hotel1);
	        rooms = query.list();//list sus stai koito otgowarqt na roomtype koito e izbran ot textfielda
	        for(Reservation res:reservations)
	        {
	        	rooms.remove(res.getRoom());
	        }
	        
	    	//System.out.println(reservations.get(0).getRoom().toString());
	    	 } catch (Exception e) {
				   e.printStackTrace();
				  }finally{//close session
				   session.close();
				   System.out.println("Retreived correctly");
				 }
	    	dataList=FXCollections.observableArrayList(rooms);
	    	this.TableView1.setItems(dataList);
	    	
	    	
	    }
	    @FXML
		private void SearchGuest(ActionEvent event) throws Exception{
			Stage primaryStage=new Stage();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFxstuff/SearchGuest.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			SearchGuestController guestController = fxmlLoader.getController();
			
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.showAndWait();
			this.guest1=guestController.send();
			//System.out.println(this.guest1);
			//System.out.println(SessionUserHelper.getCurrentUser());
			
			
	    }
	    public void zapazwane()
	    {
	    	CheckOutType checkout1=new CheckOutType(1,"notCheckedOut");
	    	double advPayment=Double.parseDouble(TextField1.getText().toString());
	    	Room room1=rooms.get(TableView1.getSelectionModel().getSelectedIndex());
	    	Reservation reserv1=new Reservation(fromDate1,toDate1,advPayment,checkout1,guest1,room1,user1,hotel1);
	    	guest1.setRating(guest1.getRating()+2);
	    	System.out.println(reserv1);
	    	reserv1.store();
	    	Date1.setValue(null);
	    	Date2.setValue(null);
	    	TextField1.setText("");
	    	ChoiceBox1.setValue("");
	    	TableView1.setItems(null);
	    	
	    	
	    }
}
