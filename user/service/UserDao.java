package library_project.user.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library_project.user.BookList;
import library_project.user.service.dto.BookDto;
import library_project.user.service.dto.OrderBookDto;
import library_project.user.service.dto.PageInfo;
import library_project.user.service.dto.RCBookDto;



/* 사용자 페이지 JDBC 관리 클래스*/
public class UserDao {		//데이터베이스 연동할때 사용되는 메소드들 모아둔 클래스
		
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
       
       // BookList 가져오기
       public List<BookDto> getBookList(){
    	   List<BookDto> bookList = new ArrayList<BookDto>();
    	   PageInfo pageInfo = BookList.pageInfo;
    	   
    	   // booklist 총 개수 구하기
    	   pageInfo.setTotalCount(getBookListCount());
    	   Connection();
    	   try {
    		   String sql = "select R.* from "
    				   	  + "(select * from book order by bid desc) R " 
    				      + "limit " + 
    				      		((pageInfo.getCurPage()-1) * pageInfo.getListCount()) + ", "
    		   			      + pageInfo.getListCount(); 
    		   
			   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
	              BookDto bookDto = new BookDto();
	              bookDto.setbIsbn(rs.getInt("bIsbn"));
	              bookDto.setbId(rs.getInt("bId"));
	              bookDto.setbTitle(rs.getString("bTitle"));
	              bookDto.setbGenre(rs.getString("bGenre"));
	              bookDto.setbAuthor(rs.getString("bAuthor"));
	              bookDto.setbPublisher(rs.getString("bPublisher"));
	              bookDto.setbAmount(rs.getInt("bAmount"));
	              bookDto.setIsRent(rs.getString("isRent"));
	              bookList.add(bookDto);
	            }
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   return bookList;
       }
       
      
       
       
       // BookList 총 개수 구하기
       public int getBookListCount(){
    	   Connection();
    	   
    	   int count = 0;
    	   try {
    		   
    		   String sql = "select count(*) as count from book " ;

    		   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
	              count = rs.getInt("count");
	            }
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   return count;
       }
       
       // BookList 검색
       public List<BookDto> searchBook(String searchType, String searchCont){
    	   List<BookDto> bookList = new ArrayList<BookDto>();
    	   Connection();
    	   try {
    		   String sql = "Select * from Book "
    		   			  + "where " + searchType + " like '%"+ searchCont +"%' "
    		   			  + "order by bid desc";

			   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
		              BookDto bookDto = new BookDto();
		              bookDto.setbIsbn(rs.getInt("bIsbn"));
		              bookDto.setbId(rs.getInt("bId"));
		              bookDto.setbTitle(rs.getString("bTitle"));
		              bookDto.setbGenre(rs.getString("bGenre"));
		              bookDto.setbAuthor(rs.getString("bAuthor"));
		              bookDto.setbPublisher(rs.getString("bPublisher"));
		              bookDto.setbAmount(rs.getInt("bAmount"));
		              bookDto.setIsRent(rs.getString("isRent"));
		              bookList.add(bookDto);
	            }
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   return bookList;
       }
       
       // 추천 BookList 가져오기
       public List<RCBookDto> getRecommandBookList(){
    	   List<RCBookDto> bookList = new ArrayList<RCBookDto>();
    	   Connection();
    	   try {
    		   String sql = "select rcb.*, b.bisbn, b.Bid, b.isRent, b.bAmount "
    		   		      + "from rcbook as rcb " 
    		   			  + "join book as b "
    		   			  + "on rTitle=bTitle and rAuthor = bAuthor and Rpublisher = Bpublisher "
    		   		      + "order by rnum desc";

			   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
	              RCBookDto bookDto = new RCBookDto();
	              bookDto.setrNum(rs.getInt("rNum"));
	              bookDto.setrTitle(rs.getString("rTitle"));
	              bookDto.setrAuthor(rs.getString("rAuthor"));
	              bookDto.setrPublisher(rs.getString("rPublisher"));
	              bookDto.setrReason(rs.getString("rReason"));
	              bookDto.setRecommendNum(rs.getInt("recommendNum"));
	              bookDto.setbIsbn(rs.getInt("bIsbn"));
	              bookDto.setbId(rs.getInt("bId"));
	              bookDto.setbAmount(rs.getInt("bAmount"));
	              bookDto.setIsRent(rs.getString("isRent"));
	              bookList.add(bookDto);
	            }
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   return bookList;
       }
       
       // 요청 정보 전송
       public int requestBook(OrderBookDto order) {
    	   Connection();
    	   String sql = null;
    	   int result = -1;
    	   try {
    		   sql = "insert into orderlist (oName, oUserid, oTitle, oPublisher, oReason, oEmail,oStatus,oAnswer) "
    		   	   + "values (?, ?, ?, ?, ?, ?, ?,?)";
    		   
			   pstmt = conn.prepareStatement(sql);

    		   pstmt.setString(1, order.getoName());
    		   pstmt.setString(2, order.getoUserid());
    		   pstmt.setString(3, order.getoTitle());
    		   pstmt.setString(4, order.getoPublisher());
    		   pstmt.setString(5, order.getoReason());
    		   pstmt.setString(6, order.getoEmail());
    		   pstmt.setString(7, order.getoStatus());
    		   pstmt.setString(8, order.getoAnswer());
    		   
			   result = pstmt.executeUpdate();
		 } catch (SQLException e) {
				System.out.println("SQL문 오류");
				e.printStackTrace();
		 }
    	   
    	   return result;
       }
       //데이터 수정
       public boolean doAlt(String Bisbn,String title, String author,int amount,String isRent,String publisher)
       {
      		Connection();
         	 
         	 try {
         		 
         		 String sql ="update book set Btitle = '"+title+"',"+ "Bauthor='"+author+"',"+ "BAmount='"+amount+"',"+"isRent='"+isRent+"',"+"BPublisher = '"+publisher+"'"+"where Bisbn = '"+Bisbn+"'";
         				 
         		 pstmt = conn.prepareStatement(sql);
     			 pstmt.executeUpdate();
     			 
     		
         		 
         	 } catch(SQLException e) {
         		 
         		 e.printStackTrace();
         		 return false;
         		 
         	 }
         	   return true;
      	 
       }
       
       // 데이터 받아서 row로 변환
       
       public ArrayList<String[]> getRows() {
      	 
      	 ArrayList<String[]> rowList = new ArrayList<String[]>();
      	 Connection();
      	 
      	 try {
      		 
      		 String sql = "select * from book";
      		 
      		 pstmt = conn.prepareStatement(sql);
  			 rs = pstmt.executeQuery();
  			 
  			 while(rs.next()) {
  				 
  				 
  			String row[] = new String[6];
  			
  			row[0] = rs.getString("Btitle");
  			row[1] = rs.getString("Bauthor");
  			row[2] = rs.getString("Bpublisher");
  			row[3] = rs.getString("Bamount");
  			row[4] = rs.getString("isRent");
  			row[5] = rs.getString("Bisbn");
  			rowList.add(row);
  			
  			 }
   			Collections.reverse(rowList);
      	 } catch(SQLException e) {
      		 
      		 e.printStackTrace();
      		 
      	 }
      	   return rowList;
      	   
      	 
       }
       
       //요청 정보 받아오기
       
       public ArrayList<OrderBookDto> getRows(String userid) {
      	 
      	 ArrayList<OrderBookDto> rowList = new ArrayList<OrderBookDto>();
      	 Connection();
      	 
      	 try {
      		 
      		 String sql = "select * from orderlist where Ouserid = '"+userid+"'";
      		 
      		 pstmt = conn.prepareStatement(sql);
  			 rs = pstmt.executeQuery();
  			 
  			 while(rs.next()) {
  				 
  				 
  			OrderBookDto ob = new OrderBookDto();
  			
  			ob.setoName(rs.getString("Oname"));
  			ob.setoUserid(rs.getString("Ouserid"));
  			ob.setoTitle(rs.getString("Otitle"));
  			ob.setoPublisher(rs.getString("Opublisher"));
  			ob.setoReason(rs.getString("Oreason"));
  			ob.setoEmail(rs.getString("Oemail"));
  			ob.setoStatus(rs.getString("Ostatus"));
  			ob.setoAnswer(rs.getString("Oanswer"));
  			
  			
  			rowList.add(ob);
  			
  			
  			 }

      		 
      	 } catch(SQLException e) {
      		 
      		 e.printStackTrace();
      		 
      	 }
      	   return rowList;
      	   
      	 
       }
       
       // 책 대여 하기
       public Map<String, String> borrowBook(String borrower, int book) {
    	   Connection();
    	   String sql = null;
    	   
    	   Map<String, String> map = new HashMap<String, String>();
    	   String message = "대여 실패";
    	   String result = "fail";
    	   
    	   try {
    		   sql = "insert into borrowList (Borrower, bid) "
    		   	   + "values (?, ?)";
    		   
			   pstmt = conn.prepareStatement(sql);

    		   pstmt.setString(1, borrower);
    		   pstmt.setInt(2, book);
			   
			   // 대여 성공
			   if(pstmt.executeUpdate() == 1) {
				   
				   map = borrowBook_update(book);
				   result = "success";
				   message = "대여 성공";
			   }
			   
		 } catch (SQLException e) {
				System.out.println("SQL문 오류");
				e.printStackTrace();
		 }
    	   
    	   map.put("result", result);
    	   map.put("message", message);
    	   
    	   return map;
       }
       
       // 대여하기 (수량, 대여가능여부)
       public Map<String, String> borrowBook_update(int book) {
    	   Connection();
    	   String sql = null;
    	   String message = "대여 실패";
    	   String result = "fail";
    	   
    	   Map<String, String> map = new HashMap<String, String>();
    	   
    	   try {
    		   sql = "update book set BAmount = BAmount -1 , isRent = if(bAmount = 1, 'Y', 'N') "
    		   	   + "where bid = "+book;
    		   
			   pstmt = conn.prepareStatement(sql);

			   
			   // 대여 성공
			   if(pstmt.executeUpdate() == 1) {
				   result = "success";
				   message = "대여 성공";
			   }
			   
		 } catch (SQLException e) {
				System.out.println("SQL문 수정 오류");
				e.printStackTrace();
		 }
    	   
    	   map.put("result", result);
    	   map.put("message", message);
    	   
    	   return map;
       }
       
       // 대여 목록 가져오기
       public List<BookDto> getBorrowList(String userId){
    	   List<BookDto> borrowList = new ArrayList<BookDto>();
    	   Connection();
    	   try {
    		   String sql = "select b.*, bl.borrower, bl.lid from borrowList as bl "
    				      + "join book as b " 
    				      + "on bl.bid = b.bid and bl.borrower=?";

			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, userId);
			   rs = pstmt.executeQuery();
			   while(rs.next()) {
				  BookDto bookDto = new BookDto();
	              bookDto.setbIsbn(rs.getInt("bIsbn"));
	              bookDto.setbId(rs.getInt("bId"));
	              bookDto.setbTitle(rs.getString("bTitle"));
	              bookDto.setbGenre(rs.getString("bGenre"));
	              bookDto.setbAuthor(rs.getString("bAuthor"));
	              bookDto.setbPublisher(rs.getString("bPublisher"));
	              bookDto.setbAmount(rs.getInt("bAmount"));
	              bookDto.setIsRent(rs.getString("isRent"));
	              bookDto.setLid(rs.getInt("lid"));
	              borrowList.add(bookDto);
	            }
    	   } catch (SQLException e) {
  				System.out.println("SQL문 오류");
  				e.printStackTrace();
    	   }
    	   DisConnection();
    	   return borrowList;
       }
       
       // 반납하기
       public Map<String, String> returnBook(int lid, int book) {
    	   Connection();
    	   String sql = null;
    	   
    	   Map<String, String> map = new HashMap<String, String>();
    	   String message = "반납 실패";
    	   String result = "fail";
    	   
    	   try {
    		   sql = "delete from borrowList "
    		   	   + "where lid = " + lid;
    		   
			   pstmt = conn.prepareStatement(sql);
			   
			   // 반납 성공
			   if(pstmt.executeUpdate() == 1) {
				   map = returnBook_update(book);
				   result = "success";
				   message = "반납 성공";
			   }
			   
		  } catch (SQLException e) {
				System.out.println("SQL문 오류");
				e.printStackTrace();
		  }
    	   map.put("result", result);
    	   map.put("message", message);
    	   
    	   return map;
       }
       
       //삭제하기
       
       public boolean doDelete(String Bisbn)
       {
      	 	Connection();
      	 
      	 try {
      		 
      		 String sql = "delete from book where Bisbn='"+Bisbn+"'";
      		 
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
       
       
       // 반납하기 (수량, 대여가능여부)
       public Map<String, String> returnBook_update(int book) {
    	   String sql = null;
    	   String message = "대여 실패";
    	   String result = "fail";
    	   
    	   Map<String, String> map = new HashMap<String, String>();
    	   
    	   try {
    		   sql = "update book set BAmount = BAmount + 1, isRent = 'Y' "
    		   	   + "where bid = "+book;
    		   
			   pstmt = conn.prepareStatement(sql);

			   // 대여 성공
			   if(pstmt.executeUpdate() == 1) {
				   result = "success";
				   message = "반납 성공";
			   }
			   
		 } catch (SQLException e) {
				System.out.println("SQL문 오류");
				e.printStackTrace();
		 }
    	   
    	   map.put("result", result);
    	   map.put("message", message);
    	   
    	   return map;
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