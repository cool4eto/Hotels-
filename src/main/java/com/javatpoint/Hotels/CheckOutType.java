package com.javatpoint.Hotels;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@Entity
@Table(name="CheckOutType")
public class CheckOutType {
	
	private int idCheckOutType;
	private String outType;
	@Id
	@Column(name="idCheckOutType")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdCheckOutType() {
		return idCheckOutType;
	}
	public void setIdCheckOutType(int idCheckOutType) {
		this.idCheckOutType = idCheckOutType;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	@Override
	public String toString() {
		return "CheckOutType [idCheckOutType=" + idCheckOutType + ", outType=" + outType + "]";
	}
	public CheckOutType(int idCheckOutType, String outType) {
		super();
		this.idCheckOutType = idCheckOutType;
		this.outType = outType;
	}
	public CheckOutType(String outType) {
		super();
		this.outType = outType;
	}
	public CheckOutType() {
		super();
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
