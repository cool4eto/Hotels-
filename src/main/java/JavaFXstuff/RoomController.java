package JavaFXstuff;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Room;
import com.javatpoint.Hotels.RoomType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class RoomController {
	/*ObservableList<String> availableChoices = ObservableArrayList("apples", "oranges"); 
	ChoiceBox1.setItems(availableChoices);*/


	@FXML
	private TextField TextField1;
	@FXML
	private TextField TextField2;
	@FXML
	private ChoiceBox<String> ChoiceBox1;
	@FXML
	private ChoiceBox<String>  ChoiceBox2;
	@FXML
	private Button button1;
    @FXML
    public void initialize() {//funkciq koqto se startira s otwarqneto na prozoreca(awtomatichno e)
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "from Hotel";
    	Query query = session.createQuery(hql);
    	List<Hotel> hotels = query.list();
    	
    	ObservableList<String> cursors = FXCollections.observableArrayList();//suzdawa ni list w koito shte slagame imenata na hotelite
    	for(int i =0;i<hotels.size();i++)//obhojda wsichki hoteli
    	{
    		cursors.add(hotels.get(i).getHotelName());//dobawq gi v lista
    	}
        ChoiceBox1.setItems(cursors);//slaga lista v choiceBoxa
        
        hql="FROM RoomType";
        query = session.createQuery(hql);
        List<RoomType> roomtypes = query.list();
        ObservableList<String> cursors1 = FXCollections.observableArrayList();//ako polzwam pak cursors mi slaga w hotels i w roomtype roomtypes
        for(int i=0;i<roomtypes.size();i++)
        {
        	cursors1.add(roomtypes.get(i).getTypeRoom());
        }
        ChoiceBox2.setItems(cursors1);
        
    }
    public void save(ActionEvent event)throws Exception 
	{//tuk trqbwa da dobawq prowerka dali e izbrano neshto i dali e wawedena staq i cena!!!!!!!!!!!!!
    		//Room room1=new Room(Integer.parseInt(TextField1.getText().toString()),Double.parseDouble(TextField2.getText().toString()),ChoiceBox1.getSelectionModel().getSelectedItem(),ChoiceBox2.getSelectionModel().getSelectedItem());
	Session session = HibernateUtil.getSessionFactory().openSession();
	Hotel hotel=new Hotel();
    hotel =  (Hotel) session.get(Hotel.class, (ChoiceBox1.getSelectionModel().getSelectedIndex()+1));
    RoomType roomtype1=new RoomType();
    roomtype1 =  (RoomType) session.get(RoomType.class, (ChoiceBox2.getSelectionModel().getSelectedIndex()+1));
    Room room1=new Room(Integer.parseInt(TextField1.getText().toString()),Double.parseDouble(TextField2.getText().toString()),roomtype1,hotel);
    room1.store();
	}
	
}
