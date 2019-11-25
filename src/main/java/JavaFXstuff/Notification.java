package JavaFXstuff;

import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;
import javafx.util.Duration;

public class Notification {
	public static void showWarning()
	{
		Notifications notification=Notifications.create().title("Внимание").text("Грешно въведени или непълни данни").hideAfter(Duration.seconds(20)).position(Pos.CENTER);
		 notification.showWarning();
	}
	public static void showOk()
	{
		Notifications notification=Notifications.create().title("OK").text("Успешно въведохте данните в базата").hideAfter(Duration.seconds(20)).position(Pos.CENTER);
		 notification.showConfirm();
		
	}
}
