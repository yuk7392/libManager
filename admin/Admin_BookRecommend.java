
package library_project.admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import library_project.admin.service.bookDao;
import library_project.admin.service.rcbookDao;
import library_project.login.service.dto.AdministratorDto;
import library_project.user.service.UserDao;
import library_project.user.service.dto.RCBookDto;
import library_project.util.RoundedButton;

public class Admin_BookRecommend extends JFrame {
   public AdministratorDto admin = Admin_Main.admin; // 관리자 정보
   RCBookDto selectedBook = null;
   UserDao userDao = new UserDao();
   List<RCBookDto> bookList = userDao.getRecommandBookList();
   
   private JPanel contentPane;
   private JTable table;
   private JTextField textField_reason;
   private JTextField textField_rate;
   private rcbookDao rcbookDao = new rcbookDao();
      DefaultTableModel model,model2;
      private JTable table_allBooks;
      bookDao bookDao = new bookDao();
	ArrayList<String[]> curBookList = bookDao.getRows();
	Boolean selected =false;
	
	String title = "X";
	String author = "X";
	String pub = "X";
	
	
	

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Admin_BookRecommend frame = new Admin_BookRecommend();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public Admin_BookRecommend() {
      setTitle("관리자 페이지");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 973, 567);
      contentPane = new JPanel();
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);   
      
      JPanel northPanel = new JPanel();
      northPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
      contentPane.add(northPanel, BorderLayout.NORTH);
      northPanel.setLayout(new BorderLayout(0, 0));
      
      JPanel northPanel_east = new JPanel();
      northPanel.add(northPanel_east, BorderLayout.EAST);
      
      JLabel label_welcome = new JLabel("관리자님 환영합니다!");
      label_welcome.setFont(new Font("굴림", Font.BOLD, 20));
      northPanel_east.add(label_welcome);
      
      RoundedButton btn_logout = new RoundedButton("로그아웃");
      btn_logout.setFont(new Font("굴림", Font.PLAIN, 20));
      northPanel_east.add(btn_logout);
      
      JPanel northPanel_west = new JPanel();
      FlowLayout fl_northPanel_west = (FlowLayout) northPanel_west.getLayout();
      fl_northPanel_west.setHgap(15);
      northPanel.add(northPanel_west, BorderLayout.WEST);
      
      RoundedButton btn_memberManagement = new RoundedButton("회원 관리");
      btn_memberManagement.setFont(new Font("굴림", Font.PLAIN, 20));
      northPanel_west.add(btn_memberManagement);
      
      RoundedButton btn_bookManagement = new RoundedButton("도서 관리");
      btn_bookManagement.setFont(new Font("굴림", Font.PLAIN, 20));
      northPanel_west.add(btn_bookManagement);
      
      RoundedButton btn_bookRecommend = new RoundedButton("추천 도서 등록");
      btn_bookRecommend.setFont(new Font("굴림", Font.PLAIN, 20));
      northPanel_west.add(btn_bookRecommend);
      
      RoundedButton btn_request = new RoundedButton("요청 사항");
      btn_request.setFont(new Font("굴림", Font.PLAIN, 20));
      northPanel_west.add(btn_request);
      
      JPanel westPanel = new JPanel();
      contentPane.add(westPanel, BorderLayout.WEST);
      
      JPanel southPanel = new JPanel();
      contentPane.add(southPanel, BorderLayout.SOUTH);
      
      JPanel eastPanel = new JPanel();
      contentPane.add(eastPanel, BorderLayout.EAST);
      
      JPanel centerPanel = new JPanel();
      contentPane.add(centerPanel, BorderLayout.CENTER);
      centerPanel.setLayout(new BorderLayout(0, 0));
      
      //
      
      JPanel centerPanel_north = new JPanel();
      centerPanel.add(centerPanel_north, BorderLayout.NORTH);
      
      JLabel label_BookRecommend = new JLabel("추천 도서 관리");
      label_BookRecommend.setFont(new Font("굴림", Font.BOLD, 30));
      centerPanel_north.add(label_BookRecommend);
      
      JPanel centerPanel_west = new JPanel();
      centerPanel.add(centerPanel_west, BorderLayout.WEST);
      
      
      Vector<String> header = new Vector<String>();
      header.add("책 이름");
      header.add("저자");
      header.add("출판사");
      header.add("추천이유");
      header.add("추천도");

      
      model = new DefaultTableModel(header,0);

      for(int i=0;i<bookList.size();i++) {
            RCBookDto book = bookList.get(i);
            Vector<String> bookVector = new Vector<String>();
            bookVector.add(book.getrTitle());
            bookVector.add(book.getrAuthor());
            bookVector.add(book.getrPublisher());
            bookVector.add(book.getrReason());
            bookVector.add(Integer.toString(book.getRecommendNum()));
            model.addRow(bookVector);
         }
      
      JScrollPane scr = new JScrollPane(table);
      table = new JTable(model);
      
      table.addMouseListener(new MouseListener() {
            
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            
            @Override
            public void mouseClicked(MouseEvent e) {
             selectedBook = bookList.get(table.getSelectedRow());
                selected = true;
                title = table.getValueAt(table.getSelectedRow(), 0).toString();
                
//             textField_bookname.setText(selectedBook.getrTitle());
//             textField_author.setText(selectedBook.getrAuthor());
//             textField_publisher.setText(selectedBook.getrPublisher());
//             textField_reason.setText(selectedBook.getrReason());
//             textField_rate.setText(Integer.toString(selectedBook.getRecommendNum()));
             }
          });
               
//                infoArea.setText("");
//                    
//                     infoArea.append("책 이름 : " + selectedBook.getrTitle()+"\r\n");
//                //   infoArea.append("장르 : " + selectedBook.getbGenre()+"\r\n");
//                     infoArea.append("작가 : " + selectedBook.getrAuthor()+"\r\n");
//                     infoArea.append("출판사 : " + selectedBook.getrPublisher()+"\r\n");
//                     infoArea.append("추천도 : " + String.valueOf(selectedBook.getRecommendNum())+"\r\n");
//                     infoArea.append("대출가능여부 : " + selectedBook.getIsRent()+"\r\n");
    
      
//      table.setModel(new DefaultTableModel(
//         new Object[][] {
//            {"\uCC451", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCD94\uCC9C\uC774\uC720", "5/5"},
//         },
//         new String[] {
//            "\uCC45 \uC774\uB984", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCD94\uCC9C \uC774\uC720", "\uCD94\uCC9C\uB3C4"
//         }
//      ) {
//         boolean[] columnEditables = new boolean[] {
//            false, false, false, false, false
//         };
//         public boolean isCellEditable(int row, int column) {
//            return columnEditables[column];
//         }
//      });
      
      
      table.getColumnModel().getColumn(0).setResizable(false);
      table.getColumnModel().getColumn(1).setResizable(false);
      table.getColumnModel().getColumn(2).setResizable(false);
      table.getColumnModel().getColumn(3).setResizable(false);
      table.getColumnModel().getColumn(4).setResizable(false);
      
      JScrollPane scrollPane_table = new JScrollPane(table);
      centerPanel_west.add(scrollPane_table);
      
      JPanel centerPanel_south = new JPanel();
      centerPanel.add(centerPanel_south, BorderLayout.SOUTH);
      
      JPanel centerPanel_east = new JPanel();
      centerPanel.add(centerPanel_east, BorderLayout.EAST);
      centerPanel_east.setLayout(new GridLayout(0, 1, 0, 0));
      
      JPanel centerPanel_center = new JPanel();
      centerPanel.add(centerPanel_center, BorderLayout.CENTER);
      centerPanel_center.setLayout(new BorderLayout(0, 0));
      
      JPanel centerPanel_center_north = new JPanel();
      centerPanel_center.add(centerPanel_center_north, BorderLayout.NORTH);
      
      JPanel centerPanel_center_west = new JPanel();
      centerPanel_center.add(centerPanel_center_west, BorderLayout.WEST);
      centerPanel_center_west.setLayout(new GridLayout(0, 1, 0, 0));
      
      RoundedButton btn_add = new RoundedButton("< 추가하기");
      btn_add.setFont(new Font("굴림", Font.PLAIN, 15));
      centerPanel_center_west.add(btn_add);
      
//      RoundedButton btn_modify = new RoundedButton("수정하기 >");
//      btn_modify.setFont(new Font("굴림", Font.PLAIN, 15));
//      centerPanel_center_west.add(btn_modify);
      
      RoundedButton btn_remove = new RoundedButton("삭제하기 >");
      btn_remove.setFont(new Font("굴림", Font.PLAIN, 15));
      centerPanel_center_west.add(btn_remove);
      
      JPanel centerPanel_center_south = new JPanel();
      centerPanel_center.add(centerPanel_center_south, BorderLayout.SOUTH);
      
      JPanel centerPanel_center_east = new JPanel();
      centerPanel_center.add(centerPanel_center_east, BorderLayout.EAST);
      centerPanel_center_east.setLayout(new GridLayout(0, 1, 0, 0));
      
      JLabel label_bookInfo = new JLabel("책 정보");
      label_bookInfo.setHorizontalAlignment(SwingConstants.CENTER);
      label_bookInfo.setFont(new Font("굴림", Font.PLAIN, 20));
      centerPanel_center_east.add(label_bookInfo);
      
      JLabel label_reason = new JLabel("추천이유");
      label_reason.setHorizontalAlignment(SwingConstants.CENTER);
      label_reason.setFont(new Font("굴림", Font.PLAIN, 20));
      centerPanel_center_east.add(label_reason);
      
      JLabel label_rate = new JLabel("추천도");
      label_rate.setHorizontalAlignment(SwingConstants.CENTER);
      label_rate.setFont(new Font("굴림", Font.PLAIN, 20));
      centerPanel_center_east.add(label_rate);
      
      JPanel centerPanel_center_center = new JPanel();
      centerPanel_center.add(centerPanel_center_center, BorderLayout.CENTER);
      centerPanel_center_center.setLayout(new GridLayout(0, 1, 0, 0));
      
      
      table_allBooks = new JTable();
      JScrollPane sp = new JScrollPane(table_allBooks);
      getCurBooks();
      centerPanel_center_center.add(sp);
      
      table_allBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int r = table_allBooks.getSelectedRow();
				title = table_allBooks.getValueAt(r, 0).toString();
				author = table_allBooks.getValueAt(r, 1).toString();
				pub = table_allBooks.getValueAt(r, 2).toString();
				
			}
		});
      
      textField_reason = new JTextField();
      textField_reason.setColumns(10);
      centerPanel_center_center.add(textField_reason);
      
      textField_rate = new JTextField();
      textField_rate.setColumns(10);
      centerPanel_center_center.add(textField_rate);
      
      // 버튼 이벤트
      
      btn_logout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            dispose();
            
         }
      });
      
      btn_memberManagement.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            dispose();
            Admin_MemberManagement am = new Admin_MemberManagement();
            am.setVisible(true);
            
         }
      });
      
      btn_bookManagement.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            dispose();
            Admin_BookManagement am = new Admin_BookManagement();
            am.setVisible(true);
            
         }
      });
      
      btn_bookRecommend.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            dispose();
            Admin_BookRecommend am = new Admin_BookRecommend();
            am.setVisible(true);
            
         }
      });
      
      btn_request.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            dispose();
            Admin_Request am = new Admin_Request();
            am.setVisible(true);
            
         }
      });
      
      btn_add.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  
        	  if(title.equals("X") || author.equals("X") || pub.equals("X"))
        	  JOptionPane.showMessageDialog(null, "책 테이블을 먼저 선택하세요.");
        	  else if(textField_reason.getText().equals("") || textField_rate.getText().equals(""))
        	  JOptionPane.showMessageDialog(null, "빈칸없이 작성해주세요.");
        	  else {
             
        	  String reason=textField_reason.getText();
        	  int rate=Integer.parseInt(textField_rate.getText());
              rcbookDao.Add(title,author,pub,reason,rate);
              getrcbooks();
              JOptionPane.showMessageDialog(null, "반영되었습니다.");
          }
        	  
        	  
          }
       });
      
//      btn_modify.addActionListener(new ActionListener() {
//          public void actionPerformed(ActionEvent e) {
//             
////        	  String bookname=textField_bookname.getText();
////        	  String author=textField_author.getText();
////        	  String publisher=textField_publisher.getText();
////        	  String reason=textField_reason.getText();
////        	  int rate=Integer.parseInt(textField_rate.getText());
////              rcbookDao.doUpdate(bookname,author,publisher,reason,rate);
////              getrcbooks();
//          }
//       });
      
      btn_remove.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  
        	  if(!selected)
              JOptionPane.showMessageDialog(null, "책 테이블을 먼저 선택하세요.");
        	  else {
              rcbookDao.doDelete(title);
              getrcbooks();
              JOptionPane.showMessageDialog(null, "반영되었습니다.");
        	  }
        	  
          }
       });
   }

private void getrcbooks() {
	model.setNumRows(0);
	bookList = userDao.getRecommandBookList();
	    Vector<String> header = new Vector<String>();
	    header.add("책 이름");
	    header.add("저자");
	    header.add("출판사");
	    header.add("추천이유");
	    header.add("추천도");

	    for(int i=0;i<bookList.size();i++) {
	          RCBookDto book = bookList.get(i);
	          Vector<String> bookVector = new Vector<String>();
	          bookVector.add(book.getrTitle());
	          bookVector.add(book.getrAuthor());
	          bookVector.add(book.getrPublisher());
	          bookVector.add(book.getrReason());
	          bookVector.add(Integer.toString(book.getRecommendNum()));
	          model.addRow(bookVector);
	       }
}

private void getCurBooks() {
	
	String header[] = {"책이름","저자","출판사"};
	model2 = new DefaultTableModel(header,0);
	
	for(int i=0;i<curBookList.size();i++) {
	
	model2.addRow(curBookList.get(i));	
	System.out.println(curBookList.get(i));
	}
	table_allBooks.setModel(model2);
}
      
//      table.addMouseListener(new MouseAdapter() {
//         
//         selectedBook = bookList.get(jt.getSelectedRow());
//         @Override
//         public void mouseClicked(MouseEvent arg0) {
//            
//            int r = table.getSelectedRow();
//            textField_bookname.setText(table.getValueAt(r, 2).toString());
//            textField_author
//            textField_publisher.setText(table.getValueAt(r, 3).toString());
//            textField_reason.setText(table.getValueAt(r, 4).toString());
//            textField_rate
//         }
//      });
//      
//      
//      
//   }
//   
//   public void refreshTable() {
//      
//      String header[] = {"\uCC45 \uC774\uB984", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCD94\uCC9C \uC774\uC720", "\uCD94\uCC9C\uB3C4"};
//      tableModel = new DefaultTableModel(header,0);
//      ArrayList<String[]> rows = orderDto.getRows();
//      for(int i=0;i < rows.size();i++) {
//         
//         tableModel.addRow(rows.get(i));
//         
//         
//      }
//      
//      table.setModel(tableModel);
//   }
}