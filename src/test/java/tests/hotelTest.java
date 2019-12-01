package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.javatpoint.Hotels.Hotel;

class hotelTest {
	
	@Test
	void test() {
		Hotel hotel1=new Hotel();
		hotel1=hotel1.getHotelByName("Марина");
		Hotel hotel2=new Hotel(1,"Марина");
		assertEquals(hotel1.getHotelName(),hotel2.getHotelName());

	}

}
