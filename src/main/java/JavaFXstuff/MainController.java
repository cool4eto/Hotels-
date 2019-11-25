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
import com.javatpoint.Hotels.User;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.javatpoint.Hotels.Reservation;


public class MainController {
	 private List<User> users;
	@FXML
    private Label Label1;

    @FXML
    private Label Label2;
    
    @FXML
    private MenuItem typeRoom;

    @FXML
    private MenuItem Hotel;

    @FXML
    private MenuItem Room;

    @FXML
    private MenuItem Position;

    @FXML
    private MenuItem CheckOutType;

    @FXML
    private MenuItem User;

    @FXML
    private MenuItem Service;

    @FXML
    private MenuItem Guest;

    @FXML
    private MenuItem Reservation;

    @FXML
    private MenuItem additionalService;

    @FXML
    private MenuItem checkOut;

    @FXML
    private MenuItem SearchGuest;

    @FXML
    private MenuItem SearchReservation;

    @FXML
    private MenuItem SearchUser;

    @FXML
    private MenuItem roomsRating;

    @FXML
    private ChoiceBox<String> ChoiceBox1;
	@FXML
    public void initialize()
    {
		//Label1.setText("ZDR");
		
		Label1.setText(SessionUserHelper.getCurrentUser().getUsername());
		Label2.setText(SessionUserHelper.getCurrentUser().getHotel().getHotelName());
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
	        LocalTime currentTime = LocalTime.now();
	       // System.out.println((currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond()));
	        if(currentTime.getHour()==11&&currentTime.getMinute()==00&&currentTime.getSecond()==0)
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
	    
	    removeElementsFromUi();
	    fillHotelsChoiceBox();
	    listeners();
	    
    }
	
	void fillHotelsChoiceBox()
	{
		 if(SessionUserHelper.getCurrentUser().getPosition().toString().equals("root")||SessionUserHelper.getCurrentUser().getPosition().toString().equals("Собственик"))
		    {
		    	ChoiceBox1.setVisible(true);
		    	
		    	
		    	Session session = HibernateUtil.getSessionFactory().openSession();
		    	String hql = "from User where username=:username1 and password=:pass1";
		    	Query query = session.createQuery(hql);
		    	query.setParameter("username1",SessionUserHelper.getCurrentUser().getUsername());
		    	query.setParameter("pass1",SessionUserHelper.getCurrentUser().getPassword());
		    	
		    	users = query.list();
		    	
		    	ObservableList<String> cursors1 = FXCollections.observableArrayList();//suzdawa ni list w koito shte slagame imenata na hotelite
		    	for(int i =0;i<users.size();i++)//obhojda wsichki hoteli
		    	{
		    		cursors1.add(users.get(i).getHotel().getHotelName());//dobawq gi v lista
		    	}
		    	ChoiceBox1.setItems(cursors1);
		    	ChoiceBox1.getSelectionModel().selectFirst();
		    	
		    }
	}
	@FXML
	public void removeElementsFromUi()
	{
		
		if(SessionUserHelper.getCurrentUser().getPosition().getType().equals("Собственик"))
		{
			
			typeRoom.setDisable(true);
			Hotel.setDisable(true);
			Room.setDisable(true);
			Position.setDisable(true);
			CheckOutType.setDisable(true);
			Service.setDisable(true);
			Guest.setDisable(true);
			Reservation.setDisable(true);
			additionalService.setDisable(true);
			checkOut.setDisable(true);
			
			
			typeRoom.setVisible(false);
			Hotel.setVisible(false);
			Room.setVisible(false);
			Position.setVisible(false);
			CheckOutType.setVisible(false);
			Service.setVisible(false);
			Guest.setVisible(false);
			Reservation.setVisible(false);
			additionalService.setVisible(false);
			checkOut.setVisible(false);
			
		}
		if(SessionUserHelper.getCurrentUser().getPosition().getType().equals("Мениджър"))
		{
			typeRoom.setDisable(true);
			Hotel.setDisable(true);
			Room.setDisable(true);
			Position.setDisable(true);
			CheckOutType.setDisable(true);
			
			typeRoom.setVisible(false);
			Hotel.setVisible(false);
			Room.setVisible(false);
			Position.setVisible(false);
			CheckOutType.setVisible(false);
		}
		if(SessionUserHelper.getCurrentUser().getPosition().getType().equals("Рецепционист"))
		{
			typeRoom.setDisable(true);
			Hotel.setDisable(true);
			Room.setDisable(true);
			Position.setDisable(true);
			CheckOutType.setDisable(true);
			User.setDisable(true);
			Service.setDisable(true);
			SearchUser.setDisable(true);
			roomsRating.setDisable(true);

			typeRoom.setVisible(false);
			Hotel.setVisible(false);
			Room.setVisible(false);
			Position.setVisible(false);
			CheckOutType.setVisible(false);
			User.setVisible(false);
			Service.setVisible(false);
			SearchUser.setVisible(false);
			roomsRating.setVisible(false);
			
		}
		
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
	private void CreateCheckOutType(ActionEvent event) throws Exception{
		Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("/JavaFxstuff/CheckOutType.fxml"));
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
		primaryStage.showAndWait();
		fillHotelsChoiceBox();
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
	@FXML
	public void listeners()
	{
		ChangeListener<String> changeListener = new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, //
                    String oldValue, String newValue) {
                if (newValue != null) {
                	for(int i =0;i<users.size();i++)
            		{
            			if(users.get(i).getHotel().getHotelName().equals(newValue))
            				SessionUserHelper.setCurrentUser(users.get(i));
            			Label2.setText(newValue);
            		}
                }
            }
        };
        ChoiceBox1.getSelectionModel().selectedItemProperty().addListener(changeListener);
		
		
	}
	
		
		
		
		
}
