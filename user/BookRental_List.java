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
import library_project.util.RoundedButton;

public class BookRental_List extends JFrame {
	// 로그인 정보 (회원 정보)
	public static UserDto user = HeadPanel.user;
	
	BookDto selectedBook = null;// 선택 된 책
	
	JPanel headPanel = new HeadPanel();
	Background_panel back = new Background_panel();
	JLabel bookRental_text = new JLabel("Book Rental Rist");
	Vector<String> header;
	DefaultTableModel model;
	JTable jt ;
	JPanel listPanel = new JPanel();
	JTextArea infoArea = new JTextArea("정보",23,59);
	RoundedButton return_book = new RoundedButton("반납");
	
	public BookRental_List(){
		Container c = getContentPane();
		
		// DB에 있는 빌린 목록 가져오기
		UserDao userDao = new UserDao();
		List<BookDto> bookList = userDao.getBorrowList(user.getcId());
		
		
		header = new Vector<String>();
		header.add("ISBN");
		header.add("책 이름");
		header.add("저자");
		header.add("출판사");
		model = new DefaultTableModel(header,0);
		
		// 사용자가 빌린 책 목록 추가
		for(int i=0;i<bookList.size();i++) {
			BookDto book = bookList.get(i);
			Vector<String> bookVector = new Vector<String>();
			bookVector.add(Integer.toString(book.getbIsbn()));
			bookVector.add(book.getbTitle());
			bookVector.add(book.getbAuthor());
			bookVector.add(book.getbPublisher());
			model.addRow(bookVector);
		}
		
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
				System.out.println("=================================");
				System.out.println(selectedBook.getbIsbn());
				System.out.println(selectedBook.getbId());
				System.out.println(selectedBook.getbTitle());
				System.out.println(selectedBook.getbGenre());
				System.out.println(selectedBook.getbAuthor());
				System.out.println(selectedBook.getbPublisher());
				System.out.println(selectedBook.getbAmount());
				System.out.println(selectedBook.getIsRent());
				System.out.println(selectedBook.getLid());
			}
		});
		
		listPanel.add("Fill",scr);
		listPanel.setBounds(100,200,470,430);

		c.add(listPanel);
		
		bookRental_text.setOpaque(true);
		bookRental_text.setFont(new Font("고딕",Font.BOLD,40));
		bookRental_text.setBounds(350, 130, 350, 40);
		bookRental_text.setForeground(Color.BLACK);
		bookRental_text.setBackground(Color.WHITE);
		c.add(bookRental_text);
		
		infoArea.setBounds(600,200,300,300);
		infoArea.setBackground(Color.LIGHT_GRAY);
		c.add(infoArea);
		 
		return_book.setFont(new Font("고딕",Font.BOLD,30));
		return_book.setBounds(680, 530, 100, 50);
		// 반납 버튼 이벤트 추가
		return_book.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedBook == null) {
					JOptionPane.showMessageDialog(null, "반납할 책을 선택해 주세요", "반납 메시지", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				// 반납 할 책 DB에서 삭제
				Map<String, String> result = userDao.returnBook(selectedBook.getLid(), selectedBook.getbId());
				
				JOptionPane.showMessageDialog(null, result.get("message"), "반납 메시지", JOptionPane.INFORMATION_MESSAGE);
				
				// 반납 후 refresh
				dispose();
				new BookRental_List();
			}
		});
		c.add(return_book);
		
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
		new BookRental_List();
	}

}
