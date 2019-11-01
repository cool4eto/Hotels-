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
@Table(name="Service")
public class Service {
	private int idService;
	private String ServiceName;
	private Double ServicePrice;
	private String ServiceSeason;
	@Id
	@Column(name="idService")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public Service() {
		super();
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public Double getServicePrice() {
		return ServicePrice;
	}
	public void setServicePrice(Double servicePrice) {
		ServicePrice = servicePrice;
	}
	public String getServiceSeason() {
		return ServiceSeason;
	}
	public void setServiceSeason(String serviceSeason) {
		ServiceSeason = serviceSeason;
	}
	public Service(int idService, String serviceName, Double servicePrice, String serviceSeason) {
		super();
		this.idService = idService;
		ServiceName = serviceName;
		ServicePrice = servicePrice;
		ServiceSeason = serviceSeason;
	}
	public Service(String serviceName, Double servicePrice, String serviceSeason) {
		super();
		ServiceName = serviceName;
		ServicePrice = servicePrice;
		ServiceSeason = serviceSeason;
	}
	@Override
	public String toString() {
		return "Service [idService=" + idService + ", ServiceName=" + ServiceName + ", ServicePrice=" + ServicePrice
				+ ", ServiceSeason=" + ServiceSeason + "]";
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
