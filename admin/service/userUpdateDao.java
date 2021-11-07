package library_project.admin.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library_project.login.service.dto.UserDto;


public class userUpdateDao {
	
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
    		 
    		 String sql = "select * from user";
    		 
    		 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 
			String row[] = new String[8];
			
			row[0] = rs.getString("Cname");
			row[1] = rs.getString("Cemail");
			row[2] = rs.getString("Cid");
			row[3] = rs.getString("Cpwd");
			row[4] = rs.getString("grade");
			
			
			rowList.add(row);
			
			 }
    		 
    	 } catch(SQLException e) {
    		 
    		 e.printStackTrace();
    		 
    	 }
    	   return rowList;
    	   
    	 
     }
     
     public boolean doComplete(String Name,String Email, String ID, String Pwd, String Grade,String ID2)
     {
    		Connection();
       	 
       	 try {
       		 
       		String sql = "update user set Cemail='"+Email+"', Cname='"+Name+"', Cpwd='"+Pwd+"', grade='"+Grade+"', Cid='"+ID+"' where Cid='"+ID2+"'";
       		 
       		 pstmt = conn.prepareStatement(sql);
   			 pstmt.executeUpdate();	
   			 
   		
       		 
       	 } catch(SQLException e) {
       		 
       		 e.printStackTrace();
       		 return false;
       		 
       	 }
       	   return true;
    	 
     }
     
     public List<UserDto> getUserList(){
  	   List<UserDto> userList = new ArrayList<UserDto>();
  	   Connection();
  	   try { 
  		   String sql = "select u.grade, u.Cid, u.Cpwd, u.Cname, u.Cemail "
  				 + "from user as u "; 

			   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
				  UserDto userDto = new UserDto();
	              userDto.setcId(rs.getString("Cid"));
	              userDto.setCpwd(rs.getString("Cpwd"));
	              userDto.setCname(rs.getString("Cname"));
	              userDto.setCemail(rs.getString("Cemail"));
	              userDto.setGrade(rs.getString("grade"));
	              userList.add(userDto);
	            }
  	   } catch (SQLException e) {
				System.out.println("SQL문 오류");
				e.printStackTrace();
  	   }
  	   return userList;
     }
     
     
     public ArrayList<borrowListDto> getBorrowList(){
    	   ArrayList<borrowListDto> userList = new ArrayList<borrowListDto>();
    	   Connection();
    	   try { 
    		   String sql = "select * from borrowlist"; 

  			   pstmt = conn.prepareStatement(sql);
  			   rs = pstmt.executeQuery();
  			   while(rs.next()) {
  				   borrowListDto userDto = new borrowListDto();
  	               userDto.setLid(rs.getString("lid"));
  	               userDto.setBorrower(rs.getString("Borrower"));
  	               userDto.setBid(rs.getString("bid"));
  	              userList.add(userDto);
  	            }
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   return userList;
       }
       
     public String findBookById(String id) {
    	  String str = " ";
    	 Connection();
  	   try { 
  		   String sql = "select Btitle from book where bid='"+id+"'"; 
         
			   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
				str = rs.getString("Btitle");
	            }
  	   } catch (SQLException e) {
				System.out.println("SQL문 오류");
				e.printStackTrace();
  	   }

     
  	 return str;

     }
}
