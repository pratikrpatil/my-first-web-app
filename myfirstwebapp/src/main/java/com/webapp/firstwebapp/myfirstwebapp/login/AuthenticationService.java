package com.webapp.firstwebapp.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	public boolean authenticateUserCredentials(
			String name, String password) {
		
		boolean isValidName = name.equalsIgnoreCase("pratik");
		boolean isValidPassword = password.equalsIgnoreCase("password");
				
		return isValidName && isValidPassword;
	}
}
