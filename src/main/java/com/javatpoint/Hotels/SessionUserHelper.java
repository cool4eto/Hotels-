package com.javatpoint.Hotels;

import java.time.LocalDate;

public class SessionUserHelper //polzwa se za da mojem da dostapim loggeduser w Reservation naprimer
{
	private static User currentUser;
	private static LocalDate curDate = LocalDate.of(2019, 11, 26);
	//private static LocalDate curDate = LocalDate.of(2019, 12, 1);//za testwane na notifikaciqta penka staq 103
	//private static LocalDate curDate = LocalDate.of(2021, 11, 26);
	public static LocalDate getCurDate() {
		return curDate;
	}

	public static void setCurDate(LocalDate curDate) {
		SessionUserHelper.curDate = curDate;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		SessionUserHelper.currentUser = currentUser;
	}
	
	
}
