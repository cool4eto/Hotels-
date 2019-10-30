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

@Entity
@Table(name="position")
public class Position {
	private int idPosition;
	private String type;
	@Id
	@Column(name="idPosition")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdPosition() {
		return idPosition;
	}
	public void setIdPosition(int idPosition) {
		this.idPosition = idPosition;
	}
	
	public Position() {
		super();
	}
	public Position(String type) {
		super();
		this.type = type;
	}
	public Position(int idPosition, String type) {
		super();
		this.idPosition = idPosition;
		this.type = type;
	}
	@Override
	public String toString() {
		return "Position [idPosition=" + idPosition + ", type=" + type + "]";
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
