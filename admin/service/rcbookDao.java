package library_project.admin.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class rcbookDao {
	
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
    		 
    		 String sql = "select * from rcbook";
    		 
    		 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 
			String row[] = new String[8];
			
			
			row[0] = rs.getString("Rtitle");
			row[1] = rs.getString("Rauthor");
			row[2] = rs.getString("Rpublisher");
			row[3] = rs.getString("Rreason");
			row[4] = rs.getString("RecommendNum");
			
			rowList.add(row);
			
			 }
    		 
    	 } catch(SQLException e) {
    		 
    		 e.printStackTrace();
    		 
    	 }
    	   return rowList;
    	   
    	 
     }
     
public ArrayList<String[]> getBookRows() {
    	 
    	 ArrayList<String[]> rowList = new ArrayList<String[]>();
    	 Connection();
    	 
    	 try {
    		 
    		 String sql = "select * from book";
    		 
    		 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 
			String row[] = new String[8];
			
			
			row[0] = rs.getString("Rtitle");
			row[1] = rs.getString("Rauthor");
			row[2] = rs.getString("Rpublisher");
			row[3] = rs.getString("Rreason");
			row[4] = rs.getString("RecommendNum");
			
			rowList.add(row);
			
			 }
    		 
    	 } catch(SQLException e) {
    		 
    		 e.printStackTrace();
    		 
    	 }
    	   return rowList;
    	   
    	 
     }
     
     
     
     public boolean doDelete(String bookName)
     {
    	 
    	 
    	 	Connection();
    	 
    	 try {
    		 
    		 String sql = "delete from rcbook where Rtitle='"+bookName+"'";
    		 
    		 pstmt = conn.prepareStatement(sql);
			 pstmt.executeUpdate();
	
    		 
    	 } catch(SQLException e) {
    		 
    		 e.printStackTrace();
    		 return false;
    		 
    	 }
    	   return true;
    	 
     }
     
     public boolean Add(String bookname,String author,String publisher,String reason,int rate)
     {
    		Connection();
       	 
       	 try {
       		 
       		String sql = "insert into rcbook (Rtitle, Rauthor, Rpublisher, Rreason, RecommendNum) "
     		   	   + "values (?, ?, ?, ?, ?)";
     		   
 			   pstmt = conn.prepareStatement(sql);

     		   pstmt.setString(1, bookname);
     		   pstmt.setString(2, author);
     		   pstmt.setString(3, publisher);
     		   pstmt.setString(4, reason);
     		   pstmt.setInt(5, rate);
     		   
 			   pstmt.executeUpdate();
   			 
   		
       		 
       	 } catch(SQLException e) {
       		 
       		 e.printStackTrace();
       		 return false;
       		 
       	 }
       	   return true;
    	 
     }
     
     public boolean doUpdate(String bookname,String author,String publisher,String reason,int rate)
     {
    		Connection();
       	 
       	 try {
       		 
       		 String sql = "update rcbook set Rauthor='"+author+"', Rpublisher='"+publisher+"', Rreason='"+reason+"', RecommendNum='"+rate+"' where Rtitle='"+bookname+"'";
       		 
       		 pstmt = conn.prepareStatement(sql);
   			 pstmt.executeUpdate();
   			 
   		
       		 
       	 } catch(SQLException e) {
       		 
       		 e.printStackTrace();
       		 return false;
       		 
       	 }
       	   return true;
    	 
     }
     
     
     
}
