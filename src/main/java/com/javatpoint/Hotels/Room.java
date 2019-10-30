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
@Table(name="room")
public class Room {
	private int idRoom;
	private int roomNumber;
	private double Rate;
	private RoomType roomtype;
	private Hotel hotel;
	@Id
	@Column(name="idRoom")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public int getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public double getRate() {
		return Rate;
	}
	public void setRate(double rate) {
		Rate = rate;
	}
	
	@Override
	public String toString() {
		return "Room [idRoom=" + idRoom + ", roomNumber=" + roomNumber + ", Rate=" + Rate + ", roomtype=" + roomtype
				+ ", hotel=" + hotel + "]";
	}
	public Room(int idRoom, int roomNumber, double rate, RoomType roomtype, Hotel hotel) {
		super();
		this.idRoom = idRoom;
		this.roomNumber = roomNumber;
		Rate = rate;
		this.roomtype = roomtype;
		this.hotel = hotel;
	}
	public Room(int roomNumber, double rate, RoomType roomtype, Hotel hotel) {
		super();
		this.roomNumber = roomNumber;
		Rate = rate;
		this.roomtype = roomtype;
		this.hotel = hotel;
	}
	public Room() {
		super();
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public Hotel getHotel() {
		return hotel;
	}
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public RoomType getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(RoomType roomtype) {
		this.roomtype = roomtype;
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
