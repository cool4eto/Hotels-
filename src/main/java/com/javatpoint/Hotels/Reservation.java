package com.javatpoint.Hotels;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
import org.hibernate.query.Query;



@Entity
@Table(name="reservation")
public class Reservation {
	private int idReservation;
	private LocalDate fromDate;
	private LocalDate toDate;
	private double advPayment;
	private Boolean checkedOut;
	private CheckOutType checkOutType;
	private Guest guest;
	private Room room;
	private User user;
	private Hotel hotel;

	@Id
	@Column(name="idReservation")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public double getAdvPayment() {
		return advPayment;
	}
	public void setAdvPayment(double advPayment) {
		this.advPayment = advPayment;
	}
	public Boolean getCheckedOut() {
		return checkedOut;
	}
	public void setCheckedOut(Boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	public CheckOutType getCheckOutType() {
		return checkOutType;
	}
	public void setCheckOutType(CheckOutType checkOutType) {
		this.checkOutType = checkOutType;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.MERGE)//ako ne e taka tuk i w user dava greshka pri storvaneto na reservation
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Reservation() {
		super();
	}
	
	
	public Reservation(int idReservation, LocalDate fromDate, LocalDate toDate, double advPayment, 
			CheckOutType checkOutType, Guest guest, Room room, User user,Hotel hotel) {
		super();
		this.idReservation = idReservation;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.advPayment = advPayment;
		this.checkedOut = checkedOut;
		this.checkOutType = checkOutType;
		this.guest = guest;
		this.room = room;
		this.user = user;
		this.hotel=hotel;
		this.checkedOut=false;
	}
	public Reservation(LocalDate fromDate, LocalDate toDate, double advPayment, CheckOutType checkOutType, Guest guest, Room room,
			User user,Hotel hotel) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.advPayment = advPayment;
		this.checkOutType = checkOutType;
		this.guest = guest;
		this.room = room;
		this.user = user;
		this.hotel=hotel;
		this.checkedOut=false;
	}
	public Reservation(int idReservation, LocalDate fromDate, LocalDate toDate, double advPayment, Boolean checkedOut,
			CheckOutType checkOutType, Guest guest, Room room, User user, Hotel hotel) {
		super();
		this.idReservation = idReservation;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.advPayment = advPayment;
		this.checkedOut = checkedOut;
		this.checkOutType = checkOutType;
		this.guest = guest;
		this.room = room;
		this.user = user;
		this.hotel = hotel;
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
	public void retreive()
	{//towa si go ostawqm za da pokaja buga w hibernate @6F12881
		Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "from Reservation";
    	Query query = session.createQuery(hql);
    	List<Reservation> reservations = query.list();
    	System.out.println(reservations.toString());
    	System.out.println(reservations.get(2).getIdReservation());
    	System.out.println(reservations.get(2).getFromDate());
    	System.out.println(reservations.get(2).getToDate());
    	System.out.println(reservations.get(2).getAdvPayment());
    	System.out.println(reservations.get(2).getCheckedOut());
    	System.out.println(reservations.get(2).getCheckOutType());
    	System.out.println(reservations.get(2).getGuest());
    	System.out.println(reservations.get(2).getRoom());
    	System.out.println(reservations.get(2).getUser());
    	System.out.println(reservations.get(2).getHotel());
    	//towa tapoto bagawo neshto dawa @irandom chislo shoto mu idwa twarde golqmo da mi opi6e rezerwaciqta
    	
	}
	public void update()//po dobriq variant za store zashtoto imame samo edin session factory
	{
		   Session session = HibernateUtil.getSessionFactory().openSession();
		try {//start transaction
			   session.beginTransaction();   
			   // save into database
			   session.update(this);
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
	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", advPayment=" + advPayment + ", checkedOut=" + checkedOut + ", checkOutType=" + checkOutType
				+ ", guest=" + guest + ", room=" + room + ", user=" + user +  "]";
	}
	
	
	
}
