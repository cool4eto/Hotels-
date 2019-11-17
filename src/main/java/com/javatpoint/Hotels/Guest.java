package com.javatpoint.Hotels;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.beans.property.StringProperty;

@Entity
@Table(name="Guest")
public class Guest {
	private int idGuest;
	private String Name;
	private String Adress;
	private int Rating;
	private String PIN;//Egn
	@Id
	@Column(name="idGuest")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdGuest() {
		return idGuest;
	}
	public void setIdGuest(int idGuest) {
		this.idGuest = idGuest;
	}
	public Guest() {
		super();
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAdress() {
		return Adress;
	}
	public void setAdress(String adress) {
		Adress = adress;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String PIN) {
		this.PIN = PIN;
	}
	public Guest(int idGuest, String name, String adress, String PIN) {
		super();
		this.idGuest = idGuest;
		Name = name;
		Adress = adress;
		this.PIN = PIN;
		Rating=50;
	}
	public Guest(String name, String adress, String PIN) {
		super();
		Name = name;
		Adress = adress;
		this.PIN = PIN;
		Rating=50;
	}
	@Override
	public String toString() {
		/*return "Guest [idGuest=" + idGuest + ", Name=" + Name + ", Adress=" + Adress + ", Rating=" + Rating + ", PIN="
				+ PIN + "]";*/
		return Name;//za da moga da go polzwam w AdditionalSercviceController
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
	
	
	
	
	
}
