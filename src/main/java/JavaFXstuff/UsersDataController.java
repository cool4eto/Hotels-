package JavaFXstuff;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.CheckOutType;
import com.javatpoint.Hotels.Guest;
import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Position;
import com.javatpoint.Hotels.Reservation;
import com.javatpoint.Hotels.SessionUserHelper;
import com.javatpoint.Hotels.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersDataController {
	
	private Hotel hotel1=SessionUserHelper.getCurrentUser().getHotel();
	private ObservableList<User> dataList = FXCollections.observableArrayList();
	private ObservableList<Reservation> dataList1 = FXCollections.observableArrayList();
	@FXML
    private TableView<User> TableView1;

    @FXML
    private TableColumn<User, Integer> idUser;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, Boolean> loginstatus;

    @FXML
    private TableColumn<User, LocalDate> regdate;

    @FXML
    private TableColumn<Position, String> position;

    @FXML
    private Button Button1;

    @FXML
    private TableView<Reservation> TableView2;

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
    public void initialize()
    {
    	TableView2.setVisible(false);//kriem wtorata tablica dokato ne se izbere potrebitel
    	 this.idUser.setCellValueFactory(new PropertyValueFactory("idUser"));
    	 this.username.setCellValueFactory(new PropertyValueFactory("username"));
    	 this.password.setCellValueFactory(new PropertyValueFactory("password"));
    	 this.name.setCellValueFactory(new PropertyValueFactory("name"));
    	 this.loginstatus.setCellValueFactory(new PropertyValueFactory("loginstatus"));
    	 this.regdate.setCellValueFactory(new PropertyValueFactory("regdate"));
    	 this.position.setCellValueFactory(new PropertyValueFactory("position"));
    	 fillTableView1();
    	 this.idReservation.setCellValueFactory(new PropertyValueFactory("idReservation"));
    	 this.fromDate.setCellValueFactory(new PropertyValueFactory("fromDate"));
    	 this.toDate.setCellValueFactory(new PropertyValueFactory("toDate"));
    	 this.guest.setCellValueFactory(new PropertyValueFactory("guest"));
    	 this.checkOutType.setCellValueFactory(new PropertyValueFactory("checkOutType"));
    	 
    	
    	
    }
    @FXML
    public void fillTableView1()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "from User where hotel=:hotel1";
    	Query query = session.createQuery(hql);
    	query.setParameter("hotel1", hotel1);
    	List<User> users = query.list();
    	dataList=FXCollections.observableArrayList(users);
    	FilteredList<User> filteredData = new FilteredList(this.dataList, null);
    	SortedList<User> sortedData = new SortedList(filteredData);
        sortedData.comparatorProperty().bind(this.TableView1.comparatorProperty());
        this.TableView1.setItems(sortedData);
        
    }
    public void fillTableView2()
    {
    	TableView2.setVisible(true);
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "from Reservation where user=:user1";
    	Query query = session.createQuery(hql);
    	query.setParameter("user1", TableView1.getSelectionModel().getSelectedItem());
    	List<Reservation> reservations = query.list();
    	dataList1=FXCollections.observableArrayList(reservations);
    	FilteredList<Reservation> filteredData = new FilteredList(this.dataList1, null);
    	SortedList<Reservation> sortedData = new SortedList(filteredData);
        sortedData.comparatorProperty().bind(this.TableView2.comparatorProperty());
        this.TableView2.setItems(sortedData);
    }
    
    
    
    
    
    
    
    
    
    
    
}
