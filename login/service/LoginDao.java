package library_project.login.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library_project.admin.Admin_Main;
import library_project.login.service.dto.AdministratorDto;
import library_project.login.service.dto.BorrowList;
import library_project.login.service.dto.UserDto;
import library_project.user.HeadPanel;



/* 사용자 페이지 JDBC 관리 클래스*/
public class LoginDao {		//데이터베이스 연동할때 사용되는 메소드들 모아둔 클래스
	   // 로그인 정보 (회원 정보)
	   public UserDto user = HeadPanel.user;
	   public AdministratorDto admin = Admin_Main.admin; // 관리자 정보

	   static Connection conn  = null;
       static Statement stmt = null;
       static PreparedStatement pstmt = null;
       static ResultSet rs = null;
       int logcheck = -1;
   
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
       
       // id 중복체크
       public boolean idCheck(String id){
    	   Connection();
    	   boolean isExist = true;
    	   
    	   try {
    		   String sql = "Select count(*) as count from user "
    		   		      + "where cid = ?";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, id);
			   rs = pstmt.executeQuery();
			   rs.next();
			   
			   // 존재 하지 않으면 false 리턴
			   if(rs.getInt("count") == 0)
				   isExist = false;
				   
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   
    	   return isExist;
       }
       
       //회원 가입
	   public Map<String, String> sign_up(UserDto user) {
	   	  Map<String, String> map = new HashMap<String, String>();	  
	   	  String result = "fail";
	   	  String message = "가입 실패";
	   	  
	   	  // 이미 존재하는 이메일
	   	  if(eMailCheck(user.getCemail())) {
	   		  message = "이미 존재하는 이메일 입니다.";
	   	  } 
	   	  // 존재하지 않으면 가입 진행
	   	  else {
	   		Connection();
	   		String sql = null;
		   	  
		   	  try {
		   		   sql = "insert into user (cId, Cpwd, Cname, Cage, cEmail, grade) "
		   		   	   + "values (?, ?, ?, ?, ?, '사용자')";
		   		   
		   		   pstmt = conn.prepareStatement(sql);
		
		   		   pstmt.setString(1, user.getcId());
		   		   pstmt.setString(2, user.getCpwd());
		   		   pstmt.setString(3, user.getCname());
		   		   pstmt.setInt(4, user.getcAge());
		   		   pstmt.setString(5, user.getCemail());
					   
				   if(pstmt.executeUpdate()==1) {
					   result="success";
					   message="가입에 성공했습니다.";
				   }
				} catch (SQLException e) {
						System.out.println("SQL문 오류");
						e.printStackTrace();
				}
		   	   
	   	   }
	   	   map.put("result", result);
	   	   map.put("message", message);
	   	   
	   	  
	   	   return map;
	   }
	   
	   // 이메일 체크
	   public boolean eMailCheck(String email){
    	   Connection();
    	   boolean isExist = true;
    	   
    	   try {
    		   String sql = "Select count(*) as count from user "
    		   		      + "where cEmail = ?";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, email);
			   rs = pstmt.executeQuery();
			   rs.next();
			   
			   // 존재 하지 않으면 false 리턴
			   if(rs.getInt("count") == 0)
				   isExist = false;
				   
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   
    	   return isExist;
       }
	   
       
	   // 사용자 로그인
	   public UserDto userLogin(String id, String pw) {
		   Connection();
    	   
    	   try {
    		   String sql = "Select * from user "
    		   		      + "where cid = ? and Cpwd = ?";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, id);
			   pstmt.setString(2, pw);;
			   rs = pstmt.executeQuery();

			   while(rs.next()) {
				   user.setcId(rs.getString("cId"));
				   user.setCpwd(rs.getString("Cpwd"));
				   user.setCname(rs.getString("Cname"));
				   user.setCemail(rs.getString("Cemail"));
				   user.setcTel(rs.getString("cTel"));
				   user.setcAge(rs.getInt("cAge"));
				   user.setGrade(rs.getNString("grade"));
				   
			   }
			   
			   // 사용자 대여 목록 저장
			   user.setCborrowList(getBorrowList(user.getcId()));
			  
				   
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   
    	   return user;
	   }
	   
	   // 대여 목록 가져오기
       public List<BorrowList> getBorrowList(String userId){
    	   List<BorrowList> borrowList = new ArrayList<BorrowList>();
    	   Connection();
    	   try {
    		   String sql = "select * from borrowList "
    				      + "where borrower=? "
    				      + "order by lid ";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, userId);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
				  BorrowList bookDto = new BorrowList();
	              bookDto.setLid(rs.getInt("lid"));
	              bookDto.setBid(rs.getInt("bId"));
	              bookDto.setBorrower(rs.getString("borrower"));
	              borrowList.add(bookDto);
	            }
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   return borrowList;
       }
	   
	   // 관리자 로그인
	   public AdministratorDto adminLogin(String id, String pw) {
		   Connection();
    	   
    	   try {
    		   String sql = "Select * from Administrator "
    		   		      + "where aid = ? and apwd = ?";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, id);
			   pstmt.setString(2, pw);
			   rs = pstmt.executeQuery();

			   while(rs.next()) {
				   admin.setaId(rs.getString("aId"));
				   admin.setApwd(rs.getString("apwd"));
			   }
				   
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   
    	   return admin;
	   }
	   
	   // 사용자 id 찾기
	   public String searchId(String email, String name) {
		   Connection();
    	   String id = "";
		   
    	   try {
    		   String sql = "Select cid from user "
    		   		      + "where cemail = ? and cname = ?";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, email);
			   pstmt.setString(2, name);
			   rs = pstmt.executeQuery();

			   while(rs.next()) 
				   id = rs.getString("cId");
			   
				   
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   
    	   return id;
	   }
	   
	// 사용자 pw 찾기
	   public String searchPw(String id, String email, String name) {
		   Connection();
    	   String pw = "";
		   
    	   try {
    		   String sql = "Select cPwd from user "
    		   		      + "where cid = ? and cemail = ? and cname = ?";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, id);
			   pstmt.setString(2, email);			   
			   pstmt.setString(3, name);
			   rs = pstmt.executeQuery();

			   while(rs.next()) 
				   pw = rs.getString("cPwd");
			   
				   
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   
    	   return pw;
	   }
       
       public void DisConnection() {		//MYSQL 접속해제
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