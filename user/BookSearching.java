package library_project.user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import library_project.util.PlaceholderTextField;
import library_project.util.RoundedButton;

public class BookSearching extends JFrame{
		
		// 로그인 정보 (회원 정보)
		public static UserDto user = HeadPanel.user;	
	
		String [] genreList = {"서양사","과학","국문","컴퓨터","국사", "소설"};
		String [] yearList = {"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021"};
		String [] publisher = {"폴리","동아","휴면큐브","바른북스","이레미디어", "한빛"};
		JComboBox<String> Searching_genre = new JComboBox<String>(genreList);
		JComboBox<String> Searching_publisher = new JComboBox<String>(publisher);
		
		Background_panel back = new Background_panel();
		JPanel headPanel = new HeadPanel();
		JPanel listPanel = new JPanel();
		JLabel bookSearch_text = new JLabel("BookSearch");
		
		JTextArea infoArea = new JTextArea("정보",23,59);
		RoundedButton rent_book = new RoundedButton("대여");
		PlaceholderTextField Searching_Field = new PlaceholderTextField("");
		
		RoundedButton Searchbtn1 = new RoundedButton("검색");
		RoundedButton Searchbtn2 = new RoundedButton("검색");
		RoundedButton Searchbtn4 = new RoundedButton("검색");
		
		// DB객체 생성
		UserDao userDao = new UserDao();
		
		// 선택 된 책
		BookDto selectedBook = null;
		
		// 검색 결과를 담을 list 객체
		List<BookDto> bookList = new ArrayList<BookDto>();
		
		Vector<String> header;
		DefaultTableModel model;
		JTable jt ;
	
		public BookSearching(){
		Container c = getContentPane();
		c.setLayout(null);
		
		c.add(headPanel);
		header = new Vector<String>();
		header.add("ISBN");
		header.add("책 이름");
		header.add("저자");
		header.add("출판사");
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
			}
		});
				
		listPanel.add("Fill",scr);
		listPanel.setBounds(490,120,470,430);
		c.add(listPanel);
		 
		back.setBounds(0,100,1000,600);
		Searchbtn1.setFont(new Font("고딕",Font.BOLD,20));
		Searchbtn1.setBounds(400, 290, 70, 30);
		Searchbtn1.setActionCommand("bTitle"); // btn 검색 종류 저장
		Searchbtn1.addActionListener(new SearchListener());
		c.add(Searchbtn1);
		
		Searchbtn2.setFont(new Font("고딕",Font.BOLD,20));
		Searchbtn2.setBounds(400, 355, 70, 30);
		Searchbtn2.setActionCommand("bGenre"); // btn 검색 종류 저장
		Searchbtn2.addActionListener(new SearchListener());
		c.add(Searchbtn2);
		
		
		Searchbtn4.setFont(new Font("고딕",Font.BOLD,20));
		Searchbtn4.setBounds(400, 420, 70, 30);
		Searchbtn4.setActionCommand("bPublisher"); // btn 검색 종류 저장
		Searchbtn4.addActionListener(new SearchListener());
		c.add(Searchbtn4);
		

		rent_book.setFont(new Font("고딕",Font.BOLD,30));
		rent_book.setBounds(680, 560, 100, 50);
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
				
				// 대여에 성공하면  refresh
				if(result.get("result").equals("success")) {
					dispose();
					new BookSearching();
				}
			}
		});
		c.add(rent_book);

		Searching_Field.setBounds(150,290, 250, 30);
		c.add(Searching_Field);
		
		Searching_genre.setBounds(150,355, 250, 30);
		c.add(Searching_genre);
		
		
		Searching_publisher.setBounds(150,420, 250, 30);
		c.add(Searching_publisher);
		
	      
		bookSearch_text.setFont(new Font("고딕",Font.BOLD,40));
		bookSearch_text.setBounds(220, 170, 300, 100);
		bookSearch_text.setForeground(Color.BLACK);
		c.add(bookSearch_text);
		
	
		c.add(back);
		setTitle("Poly Library");
		setLocation(700,250);
		setVisible(true);
		setSize(1000,700);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	// 검색 버튼 이벤트
	class SearchListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// 선택 된 책 초기화
			selectedBook = null;
			
			JButton btn = (JButton)e.getSource();
			// 버튼으로 검색 종류(제목, 장르, 연도, 출판사) 불러오기
			String searchType = btn.getActionCommand();
			
			// 검색 내용 가져오기
			String searchCont = "";
			switch(searchType) {
				case "bTitle" :
					searchCont = Searching_Field.getText();
					break;
				case "bGenre" :
					searchCont = Searching_genre.getSelectedItem().toString();
					break;
				case "bPublisher" :
					searchCont = Searching_publisher.getSelectedItem().toString();
					break;
				default:
					System.out.println("잘 못된 값");
			}
			
			// DB에서 검색내용 가져오기
			bookList = userDao.searchBook(searchType, searchCont);
			
			// 이전 검색 내용 삭제
			for(int i=model.getRowCount()-1;i>=0;i--)
				model.removeRow(i);
			
			// booklist 추가
			for(int i=0;i<bookList.size();i++) {
				BookDto book = bookList.get(i);
				Vector<String> bookVector = new Vector<String>();
				bookVector.add(Integer.toString(book.getbIsbn()));
				bookVector.add(book.getbTitle());
				bookVector.add(book.getbAuthor());
				bookVector.add(book.getbPublisher());
				bookVector.add(book.getIsRent());
				model.addRow(bookVector);
			}
			
			add(listPanel);
			
		}
	
	}
	
	public static void main(String[] args) {
		new  BookSearching();
	}

}
