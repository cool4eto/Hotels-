package com.javatpoint.Hotels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="roomtype")
public class RoomType{
		private int idRoomType;
		private String typeRoom;
		@Id
		@Column(name="idRoomType")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		
		
		public int getIdRoomType() {
			return idRoomType;
		}
		public RoomType() {
			super();
		}
		public void setIdRoomType(int idRoomType) {
			this.idRoomType = idRoomType;
		}
		public String getTypeRoom() {
			return typeRoom;
		}
		public void setTypeRoom(String typeRoom) {
			this.typeRoom = typeRoom;
		}
		@Override
		public String toString() {
			return "RoomType [idRoomType=" + idRoomType + ", typeRoom=" + typeRoom + "]";
		}
		public RoomType(String typeRoom) {
			super();
			this.typeRoom = typeRoom;
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
		public RoomType getRoomTypeByName(String Name) {
			  Query query = HibernateUtil.getSessionFactory().openSession().createQuery("from RoomType where typeRoom = :name");
			  query.setParameter("name", Name);
			  return (RoomType) query.list().get(0);
		}
		
		
		
		
		
}
