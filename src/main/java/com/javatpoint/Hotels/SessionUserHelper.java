package com.javatpoint.Hotels;

public class SessionUserHelper //polzwa se za da mojem da dostapim loggeduser w Reservation naprimer
{
	private static User currentUser;

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		SessionUserHelper.currentUser = currentUser;
	}
	
	
}
