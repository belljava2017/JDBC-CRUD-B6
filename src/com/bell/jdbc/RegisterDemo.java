package com.bell.jdbc;

public class RegisterDemo {

	public static void main(String[] args) {
		
		CustInfo info = new CustInfo("siva", "abcd12342", "MALE", 34, "belljava2017@gmail.com");
		RegistrationRespository rr = new RegistrationRespository();
		rr.createRegistration();
	
		int statusResult = rr.insertCustInfo(info);
		
		System.out.println("Status Report - inserted:"+statusResult);
		
		
	}

}
