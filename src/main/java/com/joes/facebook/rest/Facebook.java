package com.joes.facebook.rest;

import java.util.Scanner;

import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookJsonRestClient;

public class Facebook {
	public static final String API_KEY = "f3df8da6b021e145f5a060421e67aaa4";
	public static final String SECRET = "dee15bb47ccee4138efd744a9d7a0a6c";
	
	public void TestWallPost() throws FacebookException{
		
		FacebookJsonRestClient client = new FacebookJsonRestClient(API_KEY, SECRET);
		String auth_token = client.auth_createToken();
		//Do browser offline thing..
		System.out.println(getLoginUrl(auth_token));
		System.out.println("Press enter when you have logged in.");
		Scanner sc = new Scanner(System.in);
		while(!sc.nextLine().equals(""));

		String session = client.auth_getSession(auth_token); 
		System.out.println(session);
	}
	
	public static void main(String[] args){
		Facebook face = new Facebook();
		try {
			face.TestWallPost();
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLoginUrl(String authToken){
		return "http://www.facebook.com/login.php?api_key=" + API_KEY + "&v=1.0&auth_token=" + authToken;
	}
	
	public String getOfflineAccessLoginUrl(String authToken){
		return "http://www.facebook.com/authorize.php?api_key=" + API_KEY + "&ext_perm=offline_access";
	}
}
