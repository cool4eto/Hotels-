package queries;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javatpoint.Hotels.HibernateUtil;
import com.javatpoint.Hotels.RoomType;
import com.javatpoint.Hotels.User;

public class query {
	public void store()//po dobriq variant za store zashtoto imame samo edin session factory
	{
		   Session session = HibernateUtil.getSessionFactory().openSession();
		try {//start transaction
			   session.beginTransaction();   
			   // save into database
			   session.save(this);
			   //commit transaction
			   session.getTransaction().commit();
				} 
		catch (Exception e) {
			   e.printStackTrace();
			  }
		finally{//close session
			   session.close();
			   System.out.println("Storred correctly");
			  }
	}
	public User getUserByUsPw(String usern,String pass)//wrashta user po dadeno ime i parola
		{
			  Query query = HibernateUtil.getSessionFactory().openSession().createQuery("from User where username=:name and password=:pass");
			  query.setParameter("name", usern);
			  query.setParameter("pass", pass);
			  return (User) query.list().get(0);
		}
		
	
}
