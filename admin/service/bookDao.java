package library_project.admin.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class bookDao {
	
	 static Connection conn  = null;
     static Statement stmt = null;
     static PreparedStatement pstmt = null;
     static ResultSet rs = null;
 
     public void Connection() {		//MYSQL 접속
  	   try {

			   Class.forName("com.mysql.cj.jdbc.Driver");
			   String url = "jdbc:mysql://localhost:3306/bookdb?serverTimezone=Asia/Seoul"; // db 이름 재설정 필요
			   String user = "root"; // 아이디 비번 상황에 맞게 재설정 필요
			   String passwd = "1234";

	           conn = DriverManager.getConnection(url,user,passwd);
		   } catch (ClassNotFoundException e) {		
			   System.out.println("Class없음");
			} catch (SQLException e) {	
					System.out.println("SQL 오류");
			}	  		
     }
     
     
     public ArrayList<String[]> getRows(){
    	 
    	 ArrayList<String[]> rowList = new ArrayList<String[]>();
    	 Connection();
    	 
    	 try {
    		 
            String sql = "select * from book";
    		 
    		 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 
			String row[] = new String[3];
			
			
			row[0] = rs.getString("Btitle");
			row[1] = rs.getString("Bauthor");
			row[2] = rs.getString("Bpublisher");

			
			rowList.add(row);
			
			 }
    		 
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    		 
    	 }
    	 return rowList;
    	
     }
     
     
     
     
     

}
