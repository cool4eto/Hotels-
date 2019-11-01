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
@Table(name="consumations")
public class Consumation {
	private int idConsumation;
	private int broi;
	private Reservation reservation;
	private Service service;
	@Id
	@Column(name="idConsumation")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdConsumation() {
		return idConsumation;
	}
	public void setIdConsumation(int idConsumation) {
		this.idConsumation = idConsumation;
	}
	public int getBroi() {
		return broi;
	}
	public void setBroi(int broi) {
		this.broi = broi;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	@Override
	public String toString() {
		return "Consumation [idConsumation=" + idConsumation + ", broi=" + broi + ", reservation=" + reservation
				+ ", service=" + service + "]";
	}
	public Consumation(int idConsumation, int broi, Reservation reservation, Service service) {
		super();
		this.idConsumation = idConsumation;
		this.broi = broi;
		this.reservation = reservation;
		this.service = service;
	}
	public Consumation(int broi, Reservation reservation, Service service) {
		super();
		this.broi = broi;
		this.reservation = reservation;
		this.service = service;
	}
	public Consumation() {
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
