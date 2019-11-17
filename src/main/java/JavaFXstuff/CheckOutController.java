package JavaFXstuff;

import java.time.LocalDate;
import java.time.Period;
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
import com.javatpoint.Hotels.Service;
import com.javatpoint.Hotels.SessionUserHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckOutController {
	private Hotel hotel1=SessionUserHelper.getCurrentUser().getHotel();//towa ukazwa za koi hotel shte barkame kato tarsim rezervaciqta
	private LocalDate curDate = LocalDate.of(2019, 11, 26);//towa trqbwa da se zameni sus dneshna data kogato se polzwa naistina programata no za testwane mi warshi rabota
	private ObservableList<Reservation> dataList = FXCollections.observableArrayList();
	private Reservation reserv1=new Reservation();//polzwam go za da zapazq selektiranata rezervaciq
	 @FXML
	    private TableView<Reservation> TableView1;

	    @FXML
	    private TableColumn<Room, Integer> NumberOfRoom;

	    @FXML
	    private TableColumn<Guest, String> GuestName;

	    @FXML
	    private TextField TextField1;

	    @FXML
	    private ChoiceBox<String> ChoiceBox1;

	    @FXML
	    private Button Button1;
	    @FXML
	    private Button Button2;
	    @FXML
	   public void initialize() {
	    	this.NumberOfRoom.setCellValueFactory(new PropertyValueFactory("room"));
	    	this.GuestName.setCellValueFactory(new PropertyValueFactory("guest"));
	    	
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
	    private List<CheckOutType> types;
	    public void fillChoiceBox1()
	    {
	    	
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	String hql = "from CheckOutType";
	    	Query query = session.createQuery(hql);
	    	types = query.list();
	    	types.remove(0);
	    	ObservableList<String> cursors = FXCollections.observableArrayList();
	    	for(int i =0;i<types.size();i++)//obhojda wsichki hoteli
	    	{
	    		cursors.add(types.get(i).getOutType());//dobawq gi v lista
	    	}
	        ChoiceBox1.setItems(cursors);
	    	
	    	
	    }
	    public void calculate()
	    {
	    	double sum;//sumata e noshtuvki + uslugi
	    	double NightsSum;
	    	double consumationsSum=0;
	    	List<Consumation> consumations=null;
	    	LocalDate fromDate=TableView1.getSelectionModel().getSelectedItem().getFromDate();
	    	int days=Period.between(fromDate, curDate).getDays();//wzema dnite na noshtuvki
	    	NightsSum=days*TableView1.getSelectionModel().getSelectedItem().getRoom().getRate();
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	try {
	    	String hql = "from Consumation where reservation=:res1";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("res1",TableView1.getSelectionModel().getSelectedItem());
	    	consumations = query.list();
	    	for(int i=0;i<consumations.size();i++)
	    	{
	    		int broi=consumations.get(i).getBroi();
	    		double price=consumations.get(i).getService().getServicePrice();
	    		consumationsSum=consumationsSum+(broi*price);
	    	}
	    	}
	    	catch(Exception e) {}
	    	
	    	sum=NightsSum+consumationsSum-TableView1.getSelectionModel().getSelectedItem().getAdvPayment();
	    	reserv1 =TableView1.getSelectionModel().getSelectedItem();
	    	alertBox(consumations,sum,NightsSum,consumationsSum);
	    	
	    	
	    	
	    	
	    }
	    public void alertBox(List<Consumation> consumations,double sum,double NightsSum,double consumationsSum)
	    {
	    	String textForAlertBox="";
	    	
	    	
    		textForAlertBox+="Разбивка: ";
	    	for(int i=0;i<consumations.size();i++)
	    	{   
	    		
	    		textForAlertBox+="\n";
	    		textForAlertBox+=Integer.toString(consumations.get(i).getBroi());
	    		textForAlertBox+=" ";
	    		textForAlertBox+=consumations.get(i).getService().getServiceName();
	    		textForAlertBox+=" * ";
	    		textForAlertBox+=Double.toString(consumations.get(i).getService().getServicePrice());
	    	
	    		
	    	
	    		
	    	}
	    	textForAlertBox+="\n";
	    	textForAlertBox+="Сума за нощувките: ";
    		textForAlertBox+=Double.toString(NightsSum);
    		textForAlertBox+="\n";
    		textForAlertBox+="ТРЯБВА ДА ПЛАТИ: "+Double.toString(sum)+"";
    		
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setHeaderText("Вземи парите на човека");
	    	alert.setContentText(textForAlertBox);
	    	
	    	alert.showAndWait();
	    	Button2.setVisible(true);
	    	TableView1.setDisable(true);
	    	TextField1.setDisable(true);
	    }
	  
	    public void update()
	    {
	    	if(reserv1.getToDate().isAfter(curDate))
	    	{
	    		reserv1.setToDate(curDate);//za da moje da si polzwame staqta ako checkOutne rano ili ako ne doide
	    		reserv1.setCheckOutType(types.get(ChoiceBox1.getSelectionModel().getSelectedIndex()));
	    		
	    	}
	    	else
	    	{
	    		reserv1.setCheckOutType(types.get(ChoiceBox1.getSelectionModel().getSelectedIndex()));
	    	}
	    	reserv1.update();
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
