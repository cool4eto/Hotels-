package JavaFXstuff;

import java.time.LocalDate;

import com.javatpoint.Hotels.CheckOutType;
import com.javatpoint.Hotels.Guest;
import com.javatpoint.Hotels.Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservationsDataController {

	@FXML
    private TableView<Reservation> TableView1;

    @FXML
    private TableColumn<Reservation, Integer> idReservation;

    @FXML
    private TableColumn<Reservation, LocalDate> fromDate;

    @FXML
    private TableColumn<Reservation, LocalDate> toDate;

    @FXML
    private TableColumn<Guest, String> guest;

    @FXML
    private TableColumn<CheckOutType, String> checkOutType;

    @FXML
    private DatePicker DatePicker1;

    @FXML
    private DatePicker DatePicker2;
    @FXML
    public void initialize()
    {
    	 this.idReservation.setCellValueFactory(new PropertyValueFactory("idReservation"));
    	 this.fromDate.setCellValueFactory(new PropertyValueFactory("fromDate"));
    	 this.toDate.setCellValueFactory(new PropertyValueFactory("toDate"));
    	 this.guest.setCellValueFactory(new PropertyValueFactory("guest"));
    	 this.checkOutType.setCellValueFactory(new PropertyValueFactory("checkOutType"));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
