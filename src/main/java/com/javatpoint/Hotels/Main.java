package com.javatpoint.Hotels;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import queries.query;


public class Main extends Application{

	public void start(Stage primaryStage) {
		try {
			
			//Parent root=FXMLLoader.load(getClass().getResource("JavaFXstuff/Login.fxml"));
			
			Parent root = FXMLLoader.load(getClass().getResource("/JavaFxstuff/Login.fxml"));
			
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("JavaFXstuff/application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/JavaFXstuff/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		
		
		/*Hotel ono=new Hotel("Палас");
		ono.store();
		ono.setHotelName("Марина");
		ono.store();
		ono.setHotelName("Гранда");
		ono.store();
		RoomType single=new RoomType("Single");
		single.store();
		single.setTypeRoom("Double");
		single.store();*/
		
		
		/*Hotel ono=new Hotel("Палас");
		ono.retreiveHotel("Гранда");*/
	
		//launch(args);
		/*Position own=new Position("Рецепционист");
		own.store();*/
		/*RoomType newa=new RoomType("Делукс");
		newa.store();*/
		/*Hotel ono=new Hotel("Палас");
		ono.store();*/
		/*Service serv1=new Service("Джет ски",50.43,"Лято");
		serv1.store();*/
		
		
		/*RoomType type1=new RoomType();   //pokazwa na ekran id na roomtype s ime
		type1=type1.getRoomTypeByName("Double");
		System.out.println(type1.getIdRoomType());*/
		
		/*Hotel hotel1=new Hotel("Интера");//ako e taka popalwa nowi imena na hotelite :D i staite
		RoomType roomtype1=new RoomType("Suite");
		
		Room room1=new Room(101,20,roomtype1,hotel1);
		room1.store();*/
		
		
		/*RoomType roomtype1=new RoomType();//taka tarsi w bazata i ako gi ima sazdawa
		roomtype1=roomtype1.getRoomTypeByName("Single");
		Hotel hotel1=new Hotel();
		hotel1=hotel1.getHotelByName("Марина");*/
		//Room room1=new Room(105,11,roomtype1,hotel1);
		//room1.store();
		
		
		/*Position pos1=new Position(1,"Собственик");
		Date date = new Date(); // gets the current date
		User user1=new User();
		//user1.store();
		System.out.println(user1.getUserByUsPw("angelkirov", "123"));*/
		//launch(args);
		/*CheckOutType out1 =new CheckOutType("extend");
		out1.store();*/
	}

}
