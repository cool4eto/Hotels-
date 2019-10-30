package com.javatpoint.Hotels;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import queries.query;
@Entity
@Table(name="user")
public class User extends query{
	private int idUser;
	private String username;
	private String password;
	private String name;
	private boolean loginstatus;
	private Date regdate;
	private Position position;
	private Hotel hotel;
	
	@Id
	@Column(name="idUser")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(boolean loginstatus) {
		this.loginstatus = loginstatus;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", loginstatus=" + loginstatus + ", regdate=" + regdate + ", position=" + position + ", hotel="
				+ hotel + "]";
	}
	public User(int idUser, String username, String password, String name, boolean loginstatus, Date regdate,
			Position position, Hotel hotel) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.name = name;
		this.loginstatus = loginstatus;
		this.regdate = regdate;
		this.position = position;
		this.hotel = hotel;
	}
	public User(String username, String password, String name, boolean loginstatus, Date regdate, Position position,
			Hotel hotel) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.loginstatus = loginstatus;
		this.regdate = regdate;
		this.position = position;
		this.hotel = hotel;
	}
	public User() {
		super();
	}

	// w tozi klas sum polzwal query za store
	
	
}
