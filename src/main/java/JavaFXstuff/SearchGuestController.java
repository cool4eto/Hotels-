package JavaFXstuff;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.Guest;
import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchGuestController {
	@FXML
    private TextField TextField1;

    @FXML
    private TableView<Guest> TableView1;

    @FXML
    private TableColumn<Guest, Integer> idGuest;

    @FXML
    private TableColumn<Guest, String> Name;

    @FXML
    private TableColumn<Guest, String> Adress;

    @FXML
    private TableColumn<Guest, Integer> Rating;

    @FXML
    private TableColumn<Guest, String> PIN;

    @FXML
    private Button Button1;
    private ObservableList<Guest> dataList = FXCollections.observableArrayList();
    public void initialize()
    {
    	this.idGuest.setCellValueFactory(new PropertyValueFactory("idGuest"));
    	this.Name.setCellValueFactory(new PropertyValueFactory("Name"));
    	this.Adress.setCellValueFactory(new PropertyValueFactory("Adress"));
    	this.Rating.setCellValueFactory(new PropertyValueFactory("Rating"));
    	this.PIN.setCellValueFactory(new PropertyValueFactory("PIN"));
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "from Guest";
    	Query query = session.createQuery(hql);
    	List<Guest> guests = query.list();
    
    	dataList=FXCollections.observableArrayList(guests);
    	
    	//filteredList se polzwa za da moje da filtrirame wsichko koeto ne otgowarq na Predictate se izhwarlq
    	FilteredList<Guest> filteredData = new FilteredList(this.dataList, null);
    	 this.TextField1.textProperty().addListener((observable, oldValue, newValue) -> {//ako se promeni TextField1
             filteredData.setPredicate((guest1) -> {//setPredictate filtrira realno lambdata sochi kam test
                if (newValue == null || newValue.isEmpty()) //ako oshte ni e prazen textfielda ne prawi nishto
                {
                return true;
                }
                   String lowerCaseFilter = newValue.toLowerCase();//za da stane case insensitive
                   if (guest1.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                      return true;
                   } else if (guest1.getPIN().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                      return true;
                   } 
                   else return false;
             });
          });
    	 SortedList<Guest> sortedData = new SortedList(filteredData);
         sortedData.comparatorProperty().bind(this.TableView1.comparatorProperty());
         this.TableView1.setItems(sortedData);
    }
}
