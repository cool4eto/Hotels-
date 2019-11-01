package com.javatpoint.Hotels;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;

@Entity
@Table(name="hotel")
public class Hotel {
	private int idHotel;
	private String hotelName;
	@Id
	@Column(name="idHotel")
	@GeneratedValue(strategy=GenerationType.IDENTITY)//auto generira novoto ID
	
	public int getIdHotel() {
		return idHotel;
	}
	
	public Hotel(int idHotel, String hotelName) {
		super();
		this.idHotel = idHotel;
		this.hotelName = hotelName;
	}

	public Hotel() {
		super();
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + idHotel + ", hotelName=" + hotelName + "]";
	}
	
	public Hotel( String hotelName) {
		super();
		this.hotelName = hotelName;
	}
	public Hotel(Hotel hotel1)
	{
		this.idHotel=hotel1.idHotel;
		this.hotelName=hotel1.hotelName;
	}
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
	void retreiveHotel(String Name)//towa tarsi hotel po dadeno ime i ne trqbwa da e w 
	//tozi class zashtoto taka moje da se wika samo chrez weche sazdaden obekt hotel
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			   session.beginTransaction();   
			   String hql = "FROM Hotel E WHERE E.hotelName = :hotel_id";
			   Query query = session.createQuery(hql);
			   query.setParameter("hotel_id",Name);
			   List<Hotel> hotels = query.list();
			   //commit transaction
			   session.getTransaction().commit();
			   System.out.println(hotels.size());
			   for(Hotel u:hotels)
			   {
				   System.out.println(u.getIdHotel()+u.getHotelName());
			   }
			   

			  } catch (Exception e) {
			   e.printStackTrace();
			  }finally{//close session
			   session.close();
			   System.out.println("Retreived correctly");
			  }
		
		
	}
	public Hotel getHotelByName(String Name) {
		  Query query = HibernateUtil.getSessionFactory().openSession().createQuery("from Hotel where hotelName = :name");
		  query.setParameter("name", Name);
		  return (Hotel) query.list().get(0);
	}
	
	
	
	
	
	
	
	
}
