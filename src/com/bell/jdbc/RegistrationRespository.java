package com.bell.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationRespository {
	
	private static final String CREATE_QUERY = "create table register(name character varying(40) NOT NULL, password character varying(40) NOT NULL, gender character varying(40) NOT NULL, age character varying(40) NOT NULL, email character varying(40) NOT NULL)";
	private static final String  INSERT_QUERY = "INSERT INTO register (name, password, gender, age, email) values(?,?,?,?,?)";
	
	
    private Connection con = null;
	
	private void getConnection(){
		try {		
			Class.forName("org.postgresql.Driver");
			con  = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/b6", "postgres", "abcd12345");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't able to connect 1");
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("Couldn't able to connect 2");
		}	
	}
	
	public boolean createRegistration(){
		boolean result = false;
		PreparedStatement ps = null;
		Statement st = null;
		getConnection();
		try {
			st = con.createStatement();
			boolean istableexist = st.execute("SELECT EXISTS (SELECT 1 FROM pg_tables where schemaname='public' AND tablename='register')");
			if(istableexist){
				System.out.println("Table already created...skiping it");
			}else{
				ps = con.prepareStatement(CREATE_QUERY);
				result = ps.execute();
				System.out.println("successfully created");
			}
			
		} catch (SQLException e) {
			System.out.println("exception in creation");
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int insertCustInfo(CustInfo info){
		int result = 0;
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = con.prepareStatement(INSERT_QUERY);
			ps.setString(1, info.getName());
			ps.setString(2, info.getPassword());
			ps.setString(3, info.getGender());
			ps.setInt(4, info.getAge());
			ps.setString(5, info.getEmail());
			
			result = ps.executeUpdate();
			System.out.println("inserted successfully");
		} catch (SQLException e) {
			System.out.println("exception in insertion");
			e.printStackTrace();
		}finally{
		
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	

}
