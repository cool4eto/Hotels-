package tests;

import org.junit.jupiter.api.Test;

import JavaFXstuff.LoginController;
import junit.framework.TestCase;

public class loginTest extends TestCase {
	@Test
	public void test() {
	LoginController a=new LoginController();
	assertTrue(a.CheckLogin("angelkirov","123"));
	}
	

	
}
