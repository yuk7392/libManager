package library_project.admin.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class orderDao {
	
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
     
     // 데이터 받아서 row로 변환
     
     public ArrayList<String[]> getRows() {
    	 
    	 ArrayList<String[]> rowList = new ArrayList<String[]>();
    	 Connection();
    	 
    	 try {
    		 
    		 String sql = "select * from orderlist";
    		 
    		 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 
			String row[] = new String[8];
			
			row[0] = rs.getString("Oname");
			row[1] = rs.getString("Ouserid");
			row[2] = rs.getString("Otitle");
			row[3] = rs.getString("Opublisher");
			row[4] = rs.getString("Oreason");
			row[5] = rs.getString("Oemail");
			row[6] = rs.getString("Ostatus");
			row[7] = rs.getString("Oanswer");
			
			rowList.add(row);
			
			 }
    		 
    	 } catch(SQLException e) {
    		 
    		 e.printStackTrace();
    		 
    	 }
    	   return rowList;
    	   
    	 
     }
     
     
     
     public boolean doDelete(String userId,String bookName)
     {
    	 
    	 
    	 	Connection();
    	 
    	 try {
    		 
    		 String sql = "delete from orderlist where Ouserid='"+userId+"' and Otitle='"+bookName+"'";
    		 
    		 pstmt = conn.prepareStatement(sql);
			 pstmt.executeUpdate();
	
    		 
    	 } catch(SQLException e) {
    		 
    		 e.printStackTrace();
    		 return false;
    		 
    	 }
    	   return true;
    	 
     }
     
     public boolean doAnswer(String userId,String bookName,String answer)
     {
    		Connection();
       	 
       	 try {
       		 
       		 String sql = "update orderlist set Oanswer='"+answer+"' where Ouserid='"+userId+"' and Otitle='"+bookName+"'";
       		 
       		 pstmt = conn.prepareStatement(sql);
   			 pstmt.executeUpdate();
   			 
   		
       		 
       	 } catch(SQLException e) {
       		 
       		 e.printStackTrace();
       		 return false;
       		 
       	 }
       	   return true;
    	 
     }
     
     public boolean doComplete(String userId,String bookName)
     {
    		Connection();
       	 
       	 try {
       		 
       		 String sql = "update orderlist set Ostatus='처리됨' where Ouserid='"+userId+"' and Otitle='"+bookName+"'";
       		 
       		 pstmt = conn.prepareStatement(sql);
   			 pstmt.executeUpdate();
   			 
   		
       		 
       	 } catch(SQLException e) {
       		 
       		 e.printStackTrace();
       		 return false;
       		 
       	 }
       	   return true;
    	 
     }
     
     
     

}
