package com.echo.servlet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class getTargetInfoFromMysql {
	
	
	public static String getTargetInfoFromMysql(String targetName) throws Exception{
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/ar1?"
                + "user=root&password=1234&useUnicode=true&characterEncoding=UTF8";
        String result = null;
        try {
        	 Class.forName("com.mysql.jdbc.Driver");
        	 System.out.println("MySQL");
        	 conn = (Connection) DriverManager.getConnection(url);
        	 Statement stmt = (Statement) conn.createStatement();
        	 
        	 sql ="select * from target";
        	 ResultSet rs1 = stmt.executeQuery(sql);
        	 rs1.last();
        	 int rowSize = rs1.getRow();
        	 rs1.first();
        	  // rs1.close();
        	 
	         for(int i=1; i<= rowSize ;i++){
	            sql = "select * from target where targetId="+ i;
	            ResultSet rs2 = stmt.executeQuery(sql);
	            boolean flag = rs2.next();
	            while (flag){     	
	             if (rs2.getString(2).equals(targetName)){
	            		result = rs2.getString(3) +"\t" + rs2.getString(4) + "\t" + rs2.getInt(5) + "\t" + rs2.getString(6);
	            	    System.out.println(result);	            	   
	            	    }
	             break;
	            }
	         }
	         
        }catch (SQLException e) {
            System.out.println("MySQL");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return result;
	}
}  
