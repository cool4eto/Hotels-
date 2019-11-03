package JavaFXstuff;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.Guest;
import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GuestController {
	
	 @FXML
	    private TextField TextField1;

	    @FXML
	    private TextField TextField2;

	    @FXML
	    private TextField TextField3;
	    public boolean contains() {//funkciq koqto se startira s otwarqneto na prozoreca(awtomatichno e)
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	String hql = "from Guest where PIN=:pin1";
	    	Query query = session.createQuery(hql);
	    	query.setParameter("pin1", TextField3.getText().toString());
	    	List<Guest> guests = query.list();
	    	session.close();
	    	if(guests.isEmpty())
	    	{
	    		return false;
	    	}
	    	return true;
	    	
	    }
	    @FXML
	    void save()throws Exception {
	    	
	    	if(contains())
	    	{
	    		//System.out.println("Weche go ima v bazata");
	    		Stage primaryStage=new Stage();
				Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/alreadyInDatabase.fxml"));
				primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
				Scene scene = new Scene(root,400,135);
				scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
	    	}
	    	else
	    	{
	    		Guest guest1=new Guest(TextField1.getText().toString(),TextField2.getText().toString(),TextField3.getText().toString());
		    	guest1.store();
	    	}
	    }
	   
}
