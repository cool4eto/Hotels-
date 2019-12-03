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
import com.javatpoint.Hotels.SessionUserHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ReservationsDataController {
	private Hotel hotel1=SessionUserHelper.getCurrentUser().getHotel();
	private ObservableList<Reservation> dataList = FXCollections.observableArrayList();
	private LocalDate firstDate;
	private LocalDate secondDate;
	
	@FXML
    private TableView<Reservation> TableView1;

    @FXML
    private TableColumn<Reservation, Integer> idReservation;

    @FXML
    private TableColumn<Reservation, LocalDate> fromDate;

    @FXML
    private TableColumn<Reservation, LocalDate> toDate;

    @FXML
    private TableColumn<Guest, String> guest;

    @FXML
    private TableColumn<CheckOutType, String> checkOutType;

    @FXML
    private DatePicker DatePicker1;

    @FXML
    private DatePicker DatePicker2;
    @FXML
    public void initialize()
    {
    	 this.idReservation.setCellValueFactory(new PropertyValueFactory("idReservation"));
    	 this.fromDate.setCellValueFactory(new PropertyValueFactory("fromDate"));
    	 this.toDate.setCellValueFactory(new PropertyValueFactory("toDate"));
    	 this.guest.setCellValueFactory(new PropertyValueFactory("guest"));
    	 this.checkOutType.setCellValueFactory(new PropertyValueFactory("checkOutType"));
    	 listeners();
    	 
    	
    }
    public void fillTableView1()
    {
    
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "from Reservation where  hotel=:hotel1 AND fromDate>=:firstDate AND toDate<=:secondDate";
    	Query query = session.createQuery(hql);
    	query.setParameter("firstDate",firstDate);
    	query.setParameter("secondDate",secondDate);
		   query.setParameter("hotel1", hotel1);
    	List<Reservation> reservations = query.list();
    	//System.out.println(reservations.size());
    	dataList=FXCollections.observableArrayList(reservations);
    	FilteredList<Reservation> filteredData = new FilteredList(this.dataList, null);
    	SortedList<Reservation> sortedData = new SortedList(filteredData);
        sortedData.comparatorProperty().bind(this.TableView1.comparatorProperty());
        this.TableView1.setItems(sortedData);
    
    
    
    }
    public void listeners()
    {
    	 DatePicker1.valueProperty().addListener((ov, oldValue, newValue) -> {
    		 firstDate=DatePicker1.getValue();
    		 secondDate=DatePicker2.getValue();
             fillTableView1();
         });
    	 DatePicker2.valueProperty().addListener((ov, oldValue, newValue) -> {
    		 firstDate=DatePicker1.getValue();
    		 secondDate=DatePicker2.getValue();
             fillTableView1();
         });
    	 TableView1.setOnMouseClicked((MouseEvent event) -> {
             if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                // System.out.println(TableView1.getSelectionModel().getSelectedItem());
            	 calculate(TableView1.getSelectionModel().getSelectedItem());
             }
         });
    }
   
    public void calculate(Reservation reserv1)
    {
    	double consumationsSum=0;
    	List<Consumation> consumations=null;
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    	String hql = "from Consumation where reservation=:res1";
    	Query query = session.createQuery(hql);
    	query.setParameter("res1",reserv1);
    	consumations = query.list();
    	for(int i=0;i<consumations.size();i++)
    	{
    		int broi=consumations.get(i).getBroi();
    		double price=consumations.get(i).getService().getServicePrice();
    		consumationsSum=consumationsSum+(broi*price);
    	}
    	}
    	catch(Exception e) {}
    	alertBox(consumations,consumationsSum,reserv1);
    	
    	
    	
    	
    	
    }
    public void alertBox(List<Consumation> consumations,double consumationsSum,Reservation reserv1)
    {
    	String textForAlertBox="";
    	textForAlertBox+="\n Стая №";
    	textForAlertBox+=reserv1.getRoom().toString();
    	textForAlertBox+="\n Резервацията е направена от потребител:\n";
    	textForAlertBox+=reserv1.getUser().getName();
    	textForAlertBox+="\n";
    	if(consumations.isEmpty())
    		textForAlertBox+="Няма допълнителни услуги.";
    	else {
		textForAlertBox+="Разбивка за допълнителни услуги: ";
    	for(int i=0;i<consumations.size();i++)
    	{   
    		
    		textForAlertBox+="\n";
    		textForAlertBox+=Integer.toString(consumations.get(i).getBroi());
    		textForAlertBox+=" ";
    		textForAlertBox+=consumations.get(i).getService().getServiceName();
    		textForAlertBox+=" * ";
    		textForAlertBox+=Double.toString(consumations.get(i).getService().getServicePrice());
    	
    		
    	
    		
    	}
    	}
    	textForAlertBox+="\n";
    	
	
		
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("Справка: ");
    	alert.setContentText(textForAlertBox);
    	
    	alert.showAndWait();
    	
    }
    
    
    
    
    
    
    
    
    
    
    
}
