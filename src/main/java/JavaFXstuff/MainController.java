package JavaFXstuff;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.controlsfx.control.Notifications;
import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.CheckOutType;
import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.Hotel;
import com.javatpoint.Hotels.SessionUserHelper;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.javatpoint.Hotels.Reservation;


public class MainController {
	
	@FXML
    public void initialize()
    {
		
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
	        LocalTime currentTime = LocalTime.now();
	       // System.out.println((currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond()));
	        if(currentTime.getHour()==17&&currentTime.getMinute()==14&&currentTime.getSecond()==0)
	        {
	        	CheckOutType notCheckedOut = new CheckOutType(1,"notCheckedOut");
	    		Hotel hotel1=SessionUserHelper.getCurrentUser().getHotel();
	        	Session session = HibernateUtil.getSessionFactory().openSession();
	        	String hql = "from Reservation where  hotel=:hotel1 AND toDate=:curDate And checkOutType=:notCheckedOut";
		    	Query query = session.createQuery(hql);
		    	query.setParameter("curDate",SessionUserHelper.getCurDate());
				   query.setParameter("hotel1", hotel1);
				   query.setParameter("notCheckedOut", notCheckedOut);
				   List<Reservation> reservations = query.list();
				   String stringForNotification="";
				   for(int i =0;i <reservations.size();i++)
				   {
					   stringForNotification+=reservations.get(i).getRoom();
					   stringForNotification+="; ";
				   }
				   Notifications notificationBuilder=Notifications.create().title("Стаите които трябва да напуснат днес са:").text(stringForNotification).hideAfter(Duration.seconds(60)).position(Pos.TOP_RIGHT);
				   if(reservations.isEmpty())
				   {
					    notificationBuilder=Notifications.create().title("Днес няма стаи които трябва да напускат").hideAfter(Duration.seconds(60)).position(Pos.TOP_RIGHT);
				   }
	    		notificationBuilder.showInformation();
	        }
	    }),
	         new KeyFrame(Duration.seconds(1))
	    );
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	    
	    
    }
	
	@FXML
	private void CreateTypeRoom(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/RoomType.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,450,165);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void CreateHotel(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Hotel.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,450,165);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void CreateRoom(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Room.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,960,540);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void CreatePosition(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Position.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,450,165);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void CreateUser(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/User.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,960,540);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void CreateService(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Service.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,640,360);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void CreateGuest(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Guest.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,640,360);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	@FXML
	private void SearchGuest(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/SearchGuest.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,960,540);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void CreateReservation(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/Reservation.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,960,540);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void AddAdditionalService(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/AdditionalService.fxml"));
		primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
		Scene scene = new Scene(root,960,540);
		scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
		@FXML
		private void CheckOut(ActionEvent event) throws Exception{
			Stage primaryStage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/CheckOut.fxml"));
			primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
			Scene scene = new Scene(root,960,540);
			scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	}
		@FXML
		private void UsersData(ActionEvent event) throws Exception{
			Stage primaryStage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/UsersData.fxml"));
			primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
			Scene scene = new Scene(root,960,540);
			scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	}
		@FXML
		private void ReservationsData(ActionEvent event) throws Exception{
			Stage primaryStage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/ReservationsData.fxml"));
			primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
			Scene scene = new Scene(root,960,540);
			scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	}
		@FXML
		private void RoomsData(ActionEvent event) throws Exception{
			Stage primaryStage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/RoomsData.fxml"));
			primaryStage.initModality(Modality.APPLICATION_MODAL);//da ne moje da pipa drugade
			Scene scene = new Scene(root,960,540);
			scene.getStylesheets().add(getClass().getResource("/JavaFxstuff/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	
		
		
		
		
}
