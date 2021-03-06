package JavaFXstuff;

import java.util.List;

import org.apache.log4j.Logger;
import org.controlsfx.control.Notifications;
import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.Reservation;
import com.javatpoint.Hotels.SessionUserHelper;
import com.javatpoint.Hotels.User;
import com.mysql.cj.conf.IntegerProperty;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class LoginController {
	@FXML
	private Label lbStatus;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button login1;

	 public User LoggedUser;//za da proverqwam koi user e lognat
	 final static Logger logger = Logger.getLogger(LoginController.class);
	public Boolean CheckLogin(String username,String password)//trqbwa da si go probvam kogato wkaram user
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			   session.beginTransaction();   
			   String hql = "from User where username=:name and password=:pass";
			   Query query = session.createQuery(hql);
			   query.setParameter("name",username);
			   query.setParameter("pass", password);
			   List<User> user = query.list();
			   if(user.isEmpty())
					  return false;
			  LoggedUser= user.get(0);
			  
				  if(user.size()>1)
				  {
					  LoggedUser.setLoginstatus(true);
					  logger.info("Logged in: "+LoggedUser.toString());
					  session.update(LoggedUser);
					  session.getTransaction().commit();
					  return true;
				  }
				  if(LoggedUser.isLoginstatus())
				  {
					  Notifications notification=Notifications.create().title("").text("Вече сте логнат в системата!").hideAfter(Duration.seconds(20)).position(Pos.CENTER);
						 notification.showWarning();
					  return false;
				  }
				  LoggedUser.setLoginstatus(true);
				  logger.info("Logged in: "+LoggedUser.toString());
				  session.update(LoggedUser);
				  session.getTransaction().commit();
				  return true;
				  
			 
			   

			  } catch (Exception e) {
			   e.printStackTrace();
			  }finally{//close session
				  
			   session.close();
			   
			  }
		
		return false;
	}
	/*Boolean CheckLogin(String username)//prosto za proba logvaneto da stawa s ime na hotel
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			   session.beginTransaction();   
			   String hql = "FROM Hotel E WHERE E.hotelName = :hotel_id";
			   Query query = session.createQuery(hql);
			   query.setParameter("hotel_id",username);
			   List<Hotel> hotels = query.list();
			   //commit transaction
			   session.getTransaction().commit();
			  if(hotels.isEmpty())
			  return false;
			  else return true;
			   

			  } catch (Exception e) {
			   e.printStackTrace();
			  }finally{//close session
			   session.close();
			   System.out.println("Retreived correctly");
			  }
		
		return false;
	}*/
	
	
	public void Login(ActionEvent event)throws Exception
	{
		/*if(txtUsername.getText().equals("root")&&txtPassword.getText().equals("root"))//dawa prawo za logwane s root root
		{
			lbStatus.setText("Login Succes");
			 Stage stage = (Stage) login1.getScene().getWindow();//tezi 2 reda sa za da zatworim login 
			    stage.close();
			Stage primaryStage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Main.fxml"));
			Scene scene = new Scene(root,900,500);
			scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}*/
		
		 
		
		
		 if(CheckLogin(txtUsername.getText(),txtPassword.getText()))
		{
			lbStatus.setText("Login Succes");
			Stage stage = (Stage) login1.getScene().getWindow();//tezi 2 reda sa za da zatworim login 
		    stage.close();
		    SessionUserHelper.setCurrentUser(LoggedUser);
		    
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Main.fxml"));
		Scene scene = new Scene(root,900,500);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.getIcons().add(new Image("queries/hotel.png"));
		primaryStage.setTitle("Хотелска програма");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {//kato izlizame logina da stava 0
	          public void handle(WindowEvent we) {
	        	  Session session = HibernateUtil.getSessionFactory().openSession();
	  			try {
	  				   session.beginTransaction();  
	  				   User user1= SessionUserHelper.getCurrentUser();
	  				   user1.setLoginstatus(false);
	  				 logger.info("Logged out: "+user1.toString());
	  				   session.update(user1);
	  				   session.getTransaction().commit();
	  			}
	  			catch (Exception e) {
					   e.printStackTrace();
					  }
	             
	          }
	      });   
			//System.out.println(LoggedUser.toString());
		
		}
		else
			lbStatus.setText("Login Failed");
		
		
	}
	
}
