package library_project.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class JDBCUtil {		//데이터베이스 연동할때 사용되는 메소드들 모아둔 클래스
		
	   static Connection conn  = null;
       static Statement stmt = null;
       static PreparedStatement pstmt = null;
       static ResultSet rs = null;
       int logcheck = -1;
   
       public static void Connection() {		//MYSQL 접속
    	   try {
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   String url = "jdbc:mysql://localhost:3306/Worker_Check?serverTimezone=Asia/Seoul"; // db 이름 재설정 필요
			   String user = "root"; // 아이디 비번 상황에 맞게 재설정 필요
			   String passwd = "1234";  
	           conn = DriverManager.getConnection(url,user,passwd);
		   } catch (ClassNotFoundException e) {			
		} catch (SQLException e) {			
		}	  		
       }
       
       public void InsertInfo(String WorkerNamber, String Team, String Name, String Time, String InOut) {
    	   Connection();
    	   String Insertsql = null;
    	   try {
    		   if(InOut.equals("출근")) {
    			    Insertsql = "insert into Worker(workNumber, team, Name,inTime ) values(?,?,?,?)";
    		   }
    		   else if(InOut.equals("퇴근")) {
    			    Insertsql = "insert into Worker(workNumber, team, Name,outTime ) values(?,?,?,?)";
    		   }
			   
			   pstmt = conn.prepareStatement(Insertsql);
			   pstmt.setString(1,WorkerNamber);
			   pstmt.setString(2,Team);
			   pstmt.setString(3,Name);
			   pstmt.setString(4,Time);
			   pstmt.executeUpdate();
			   		   } 
    	  catch (SQLException e) {
				System.out.println("SQL문 오류");
				e.printStackTrace();
			}
    	   
       }
       
       public void ViewInfo() {
    	   
    	   Connection();
    	   try {
    		   String sql = "Select * from Worker";
			   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
	              System.out.println(rs.getString("workNumber")+rs.getString("team")+rs.getString("Name")
	              +rs.getString("inTime")+rs.getString("outTime"));
	             
	            }
    	   }
    		  catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
  			}
    
       }
       
       public static void DisConnection() {		//MYSQL 접속해제
    	   if(conn!= null) {
    		   try {
				conn.close();
			} catch (SQLException e) {				
			}
    	   }
    	   if(stmt != null) {
    		   try {
				stmt.close();
			} catch (SQLException e) {
			}
    	   }
    	   if(pstmt != null) {
    		   try {
				pstmt.close();
			} catch (SQLException e) {
			}
    	   }
    	   if(rs != null) {
    		   try {
				rs.close();
			} catch (SQLException e) {
			}
    	   }
       }
      
     
    
      
	
}