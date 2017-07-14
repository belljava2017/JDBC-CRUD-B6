package com.bell.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class JDBCDemo1 {

	public static void main(String[] args)  {
		
		CustData cd = new CustData();
		
		try {
			//Load the driver class
			Class.forName("org.postgresql.Driver");
			//Establish the connections
			Connection con  = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/b6", "postgres", "abcd12345");
			System.out.println("Got the connection");
			//Create a statment
			Statement stmt = con.createStatement();
			// Prepare the query & execute it
			String sql = "SELECT name, password FROM cust_info";
			ResultSet rs = stmt.executeQuery(sql);
			//Process the results
			while(rs.next()){
				String name = rs.getString(1);
				String pass = rs.getString(2);
				System.out.println("Credentails stored::" + name +" "+pass);
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't able to connect 1");
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("Couldn't able to connect 2");
		}
		
		

	}

}


class CustData{
	
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}