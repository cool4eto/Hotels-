package JavaFXstuff;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Position;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PositionController {
	@FXML
	private TextField TextField1;
	private  boolean check=false;
	
		public void save(ActionEvent event)throws Exception 
		{
			 Session session = HibernateUtil.getSessionFactory().openSession();
		    	String hql = "from Position where type=:type1";
		    	Query query = session.createQuery(hql);
		    	 query.setParameter("type1", TextField1.getText().toString());
		    	List<Position> positions = query.list();
			

			if(positions.isEmpty())//prowerqwa dali lista po zadaden kriterii poziciqta koqto sme zadali e prazen i ako e towa oznachawa che nqmame takaw obekt i go sazdawa
			{
		Position pos1=new Position(TextField1.getText().toString());
		pos1.store();
			}
			else //towa otwarq pop-up ako veche imame obekta w bazata danni
				{
				//System.out.println("WECHE E V BAZATA FE");
				Stage primaryStage=new Stage();
				Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/alreadyInDatabase.fxml"));
				primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
				Scene scene = new Scene(root,400,135);
				scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
				}
			
		}	
	
	
	
}
