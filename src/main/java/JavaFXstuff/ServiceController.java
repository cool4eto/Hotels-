package JavaFXstuff;

import com.javatpoint.Hotels.Service;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ServiceController {
	  @FXML
	    private RadioButton Radio1;

	    @FXML
	    private ToggleGroup Group1;

	    @FXML
	    private RadioButton Radio2;

	    @FXML
	    private TextField TextField1;

	    @FXML
	    private TextField TextField2;

	    @FXML
	    private Button Button1;

	    public void save()
	    {
	    	Service serv1=new Service();
	    	if(Radio1.isSelected())
	    {
	    	serv1=new Service(TextField1.getText().toString(),Double.parseDouble(TextField2.getText().toString()),Radio1.getText().toString());
	    }
	    else
	    {
	    	serv1=new Service(TextField1.getText().toString(),Double.parseDouble(TextField2.getText().toString()),Radio2.getText().toString());
	    }
	    	serv1.store();
	    }
}
