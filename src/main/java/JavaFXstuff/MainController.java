package JavaFXstuff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
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
}
