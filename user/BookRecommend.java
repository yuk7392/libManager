package library_project.user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import library_project.login.service.dto.UserDto;
import library_project.user.service.UserDao;
import library_project.user.service.dto.BookDto;
import library_project.user.service.dto.RCBookDto;
import library_project.util.RoundedButton;

public class BookRecommend extends JFrame {
	// 로그인 정보 (회원 정보)
	public static UserDto user = HeadPanel.user;
	// 선택 된 책
	RCBookDto selectedBook = null;
	
	JPanel headPanel = new HeadPanel();
	Background_panel back = new Background_panel();
	JLabel bookRecommend_text = new JLabel("Book Recommend");
	Vector<String> header;
	DefaultTableModel model;
	JTable jt ;
	JPanel listPanel = new JPanel();
	JTextArea infoArea = new JTextArea("정보",23,59);
	RoundedButton rent_book = new RoundedButton("대여");
	
	public BookRecommend(){
		Container c = getContentPane();
		// DB에 있는 BookList 가져오기
		UserDao userDao = new UserDao();
		List<RCBookDto> bookList = userDao.getRecommandBookList();
		
		header = new Vector<String>();
//		header.add("ISBN");
		header.add("책 이름");
		header.add("저자");
		header.add("출판사");
		header.add("추천도");
		header.add("대여 가능 여부");
		model = new DefaultTableModel(header,0);
		jt = new JTable(model);
		JScrollPane scr = new JScrollPane(jt);
		
		// 셀 선택 이벤트 
		jt.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedBook = bookList.get(jt.getSelectedRow());
				
				 infoArea.setText("");
		           
		            infoArea.append("책 이름: " + selectedBook.getrTitle()+"\r\n");
		       //   infoArea.append("장르 : " + selectedBook.getbGenre()+"\r\n");
		            infoArea.append("작가 : " + selectedBook.getrAuthor()+"\r\n");
		            infoArea.append("출판사 : " + selectedBook.getrPublisher()+"\r\n");
		            infoArea.append("추천도 : " + String.valueOf(selectedBook.getRecommendNum())+"\r\n");
		            infoArea.append("대출가능여부 : " + selectedBook.getIsRent()+"\r\n");
			}
		});
		
		listPanel.add("Fill",scr);
		listPanel.setBounds(100,200,470,430);
		
		// 추천 booklist 추가
		for(int i=0;i<bookList.size();i++) {
			RCBookDto book = bookList.get(i);
			Vector<String> bookVector = new Vector<String>();
			bookVector.add(book.getrTitle());
			bookVector.add(book.getrAuthor());
			bookVector.add(book.getrPublisher());
			bookVector.add(Integer.toString(book.getRecommendNum()));
			bookVector.add(book.getIsRent());
			model.addRow(bookVector);
		}
		
		c.add(listPanel);
		
		bookRecommend_text.setOpaque(true);
		bookRecommend_text.setFont(new Font("고딕",Font.BOLD,40));
		bookRecommend_text.setBounds(350, 130, 350, 40);
		bookRecommend_text.setForeground(Color.BLACK);
		bookRecommend_text.setBackground(Color.WHITE);
		c.add(bookRecommend_text);
		
		infoArea.setBounds(600,200,300,300);
		infoArea.setBackground(Color.LIGHT_GRAY);
		c.add(infoArea);
		 
		rent_book.setFont(new Font("고딕",Font.BOLD,30));
		rent_book.setBounds(680, 530, 100, 50);
		// 대여 버튼 이벤트 추가
		rent_book.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedBook == null) {
					JOptionPane.showMessageDialog(null, "대여할 책을 선택해 주세요", "대여 메시지", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(selectedBook.getIsRent().equals("N")) {
					JOptionPane.showMessageDialog(null, "대여할 수 없는 책입니다.", "대여 메시지", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				// 대여 할 책 DB에 넣기
				Map<String, String> result = userDao.borrowBook(user.getcId(), selectedBook.getbId());
				
				JOptionPane.showMessageDialog(null, result.get("message"), "대여 메시지", JOptionPane.INFORMATION_MESSAGE);
				
				// 대여에 성공하면  메인페이지 이동
				if(result.get("result").equals("success")) {
					dispose();
					new Main();
				}
			}
		});
		c.add(rent_book);
		
		back.setBounds(0,100,1000,600);
		c.add(back);
		c.add(headPanel);
		
		
		
		setTitle("Poly Library");
		setLocation(700,250);
		setVisible(true);
		setSize(1000,700);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new BookRecommend();
	}

}
