package tests;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.javatpoint.Hotels.HibernateUtil;

import JavaFXstuff.LoginController;
import junit.framework.TestCase;

public class loginTest extends TestCase {
	@Test
	public void test() {
	LoginController a=new LoginController();
	assertTrue(a.CheckLogin("angelkirov","123"));
	Session session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction(); 
	a.LoggedUser.setLoginstatus(false);
	session.update(a.LoggedUser);
	  session.getTransaction().commit();
	  session.close();
	}
	

	
}
