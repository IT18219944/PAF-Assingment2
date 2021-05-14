package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




public class Product {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pafgadget?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProduct(String pName, String pDate, String pPrice, String pDes)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into product(pId,pName,pDate,pPrice,pDes)"
					 + " values (?, ?, ?, ?, ?)";
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, pName);
			 preparedStmt.setString(3, pDate);
			 preparedStmt.setString(4, pPrice);
			 preparedStmt.setString(5, pDes);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newproduct = readproduct(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newproduct + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the product.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	
	public String readproduct()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Product Name</th><th>Date</th><th>Price</th><th>Description</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from product";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String pId = Integer.toString(rs.getInt("pId"));
				 String pName = rs.getString("pName");
				 String pDate = rs.getString("pDate");
				 String pPrice = rs.getString("pPrice");
				 String pDes = rs.getString("pDes");
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidproductIDUpdate\' name=\'hidproductIDUpdate\' type=\'hidden\' value=\'" + pId + "'>" 
							+ pName + "</td>"; 
				output += "<td>" + pDate + "</td>";
				output += "<td>" + pPrice + "</td>";
				output += "<td>" + pDes + "</td>";
				

				  
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-producttid='" + pId + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the product.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	
	public String updateproduct(String pId, String pName, String pDate, String pPrice, String pDes)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE product SET pName=?,pDate=?,pPrice=?,pDes=?" 
					   + "WHERE pId=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, pName);
			 preparedStmt.setString(2, pDate);
			 preparedStmt.setString(3, pPrice);
			 preparedStmt.setString(4, pDes);
			 preparedStmt.setInt(5, Integer.parseInt(pId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newproduct = readproduct();    
			output = "{\"status\":\"success\", \"data\": \"" + newproduct + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the product.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteproduct(String pId)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "delete from product where pId=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(pId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newproduct = readproduct();    
			output = "{\"status\":\"success\", \"data\": \"" +  newproduct + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the product.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}

