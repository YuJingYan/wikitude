package com.echo.servlet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class jdbc1 {
	 public static void main(String[] args) throws Exception{
	        Connection conn = null;
	        String sql;
	        // MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
	        // ������������Ҫָ��useUnicode��characterEncoding
	        // ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
	        // �������֮ǰ��Ҫ�ȴ������ݿ�
	        String url = "jdbc:mysql://localhost:3306/ar1?"
	                + "user=root&password=1234&useUnicode=true&characterEncoding=UTF8";
	 
	        try {
	           
	            Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
	           
	 
	            System.out.println("MySQL");
	           
	            conn = (Connection) DriverManager.getConnection(url);
	            
	            Statement stmt = (Statement) conn.createStatement();
	            int i =1;
	            while(i < 8){
	            	sql = "select * from target where targetId="+ i;
	            	ResultSet rs = stmt.executeQuery(sql);
	            	while (rs.next()) {
	                    System.out
	                            .println(rs.getInt(1) + "\t" + rs.getString(2) +"\t" + rs.getString(3) +"\t" + rs.getString(4) + "\t" + rs.getInt(5) + "\t" + rs.getString(6));
	                }
	            	i++;
	            }
	            
	            
	        } catch (SQLException e) {
	            System.out.println("MySQL");
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            conn.close();
	        }
	 
	    }
}
