package com.umang;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
	  public static void main(String[] args) {
		  	int i=0;
		  	while(i++<10)
		  	{
				String password = "dhruva";
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(password);

				System.out.println(hashedPassword);
		  	}


		  }
}
