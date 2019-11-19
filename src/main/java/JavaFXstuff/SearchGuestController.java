package JavaFXstuff;

import java.util.List;

import org.controlsfx.control.Notifications;
import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.Guest;
import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
    @FXML
    private Button Button2;
    private ObservableList<Guest> dataList = FXCollections.observableArrayList();
    public void initialize()
    {
    this.idGuest.setCellValueFactory(new PropertyValueFactory("idGuest"));
	this.Name.setCellValueFactory(new PropertyValueFactory("Name"));
	this.Adress.setCellValueFactory(new PropertyValueFactory("Adress"));
	this.Rating.setCellValueFactory(new PropertyValueFactory("Rating"));
	this.PIN.setCellValueFactory(new PropertyValueFactory("PIN"));
    	fill();
    	
    }
    public void fill()//izkarano e kato funkciq za da moje da se polzwa pri zatwarqneto na prozoreca
    {
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "from Guest";
    	Query query = session.createQuery(hql);
    	List<Guest> guests = query.list();
    
    	dataList=FXCollections.observableArrayList(guests);
    	
    	//filteredList se polzwa za da moje da filtrirame wsichko koeto ne otgowarq na Predictate se izhwarlq
    	FilteredList<Guest> filteredData = new FilteredList(this.dataList, null);
    	 this.TextField1.textProperty().addListener((observable, oldValue, newValue) -> {
//ako se promeni TextField1
             filteredData.setPredicate((guest1) -> 
             {//setPredictate filtrira realno lambdata sochi kam test
                if (newValue == null || newValue.isEmpty()) //ako oshte ni e prazen textfielda ne prawi nishto
                {Button2.setVisible(false);//ako iztrivame da se skriva butona
                return true;
                }
                
                   String lowerCaseFilter = newValue.toLowerCase();//za da stane case insensitive
                   if (guest1.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                	   Button2.setVisible(false);//ako iztrivame da se skriva butona
                      return true;
                   } else if (guest1.getPIN().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                	   Button2.setVisible(false);//ako iztrivame da se skriva butona
                      return true;
                   } 
                   else 
                	   {
                	   return false;
                	   }
                  
          		
             });
             if(this.TableView1.getItems().isEmpty())Button2.setVisible(true);//za da izskochi butona 
          });
    	 SortedList<Guest> sortedData = new SortedList(filteredData);
         sortedData.comparatorProperty().bind(this.TableView1.comparatorProperty());
         this.TableView1.setItems(sortedData);
        
         
    }
   
    
    
    
    public void addGuest(ActionEvent event)throws Exception
    {		
    	Stage childStage=new Stage();
		Parent SearchGuestController=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Guest.fxml"));
		childStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(SearchGuestController,640,360);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		childStage.setScene(scene);
		childStage.showAndWait();//polzwam go wmesto prosto show za da moje da se refreshne tableview-to
		//muchih se s nqkwi listeneri koito ne prorabotiha
		this.fill();//popalva se ot bazata danni s novite danni
		TextField1.setText("");
		
		TableView1.getSelectionModel().select((TableView1.getItems().size()-1));//fokusira posledno wawedeniq guest
		
    }
    
    
    
    
    public Guest send()throws Exception
    {
    	Guest guest1=TableView1.getSelectionModel().getSelectedItem();
    	if(guest1.getRating()<=45)
    	{
    		 Notifications notification=Notifications.create().title("Внимание").text("Гостът който избрахте е с лош рейтинг задължително 50% капаро:").hideAfter(Duration.seconds(20)).position(Pos.CENTER);
    		 notification.showWarning();
    	}
    	Stage stage = (Stage) Button1.getScene().getWindow();
    	stage.close();
    	return guest1;
    }
    
}
