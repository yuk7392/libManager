package library_project.admin;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import library_project.user.service.UserDao;
import library_project.user.service.dto.BookDto;
import library_project.util.RoundedButton;

public class Admin_BookManagement extends JFrame {
	private JPanel contentPane;
	private JTextField textField_bookname;
	private JTextField textField_author;
	private JTextField textField_publisher;
	private JTextField textField_amout;
	private JTextField textField_isRend;
	private JTextField textField_isbn;
	Vector<String> header;
	DefaultTableModel model;
	JTable jt ;


	
	Admin_BookManagement(){

		UserDao userDao = new UserDao();
		List<BookDto> bookList = userDao.getBookList();
//		BookDto selectedBook =  bookList.get(jt.getSelectedRow());
		
		
	setTitle("관리자 페이지");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 973, 567);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
	contentPane.setLayout(new BorderLayout(0, 0));
	setContentPane(contentPane);
	

	//상단패널 구조
	JPanel northPanel = new JPanel();
	northPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
	contentPane.add(northPanel, BorderLayout.NORTH);
	northPanel.setLayout(new BorderLayout(0, 0));
	
	JPanel northPanel_west = new JPanel();
	FlowLayout fl_northPanel_west = (FlowLayout) northPanel_west.getLayout();
	fl_northPanel_west.setHgap(15);
	northPanel.add(northPanel_west, BorderLayout.WEST);
	
	
	JPanel centerPanel = new JPanel();
	contentPane.add(centerPanel, BorderLayout.CENTER);
	centerPanel.setLayout(new BorderLayout(0, 0));
	
	
	JPanel northPanel_east = new JPanel();
	northPanel.add(northPanel_east, BorderLayout.EAST);
	//상단 패널 구조
	
	//가운데 패널
	JPanel centerPanel_east = new JPanel();
	centerPanel.add(centerPanel_east, BorderLayout.EAST);
	centerPanel_east.setLayout(new GridLayout(0, 1, 0, 0));
	
	JPanel centerPanel_center = new JPanel();
	centerPanel.add(centerPanel_center, BorderLayout.CENTER);
	centerPanel_center.setLayout(new BorderLayout(0, 0));
	
	JPanel centerPanel_west = new JPanel();
	centerPanel.add(centerPanel_west, BorderLayout.WEST);
	
	JPanel centerPanel_center_west = new JPanel();
	centerPanel_center.add(centerPanel_center_west, BorderLayout.WEST);
	centerPanel_center_west.setLayout(new GridLayout(0, 1, 0, 0));
	
	JPanel centerPanel_center_center = new JPanel();
	centerPanel_center.add(centerPanel_center_center, BorderLayout.CENTER);
	centerPanel_center_center.setLayout(new GridLayout(0, 1, 0, 0));
	

	JPanel centerPanel_north = new JPanel();
	centerPanel.add(centerPanel_north, BorderLayout.NORTH);

	
	//가운데 패널
	
	//책 테이블
	header = new Vector<String>();
	header.add("책 이름");
	header.add("저자");
	header.add("출판사");
	header.add("수량");
	header.add("대출 가능");
	header.add("ISBN");
	

	model = new DefaultTableModel(header,0);
	jt = new JTable(model);
	// 셀 선택 이벤트 
	
	jt.addMouseListener(new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int r = jt.getSelectedRow();
			
			textField_bookname.setText(jt.getValueAt(r, 0).toString());
			textField_author.setText(jt.getValueAt(r, 1).toString());
			textField_publisher.setText( jt.getValueAt(r, 2).toString());
			textField_amout.setText(jt.getValueAt(r, 3).toString());
			textField_isRend.setText(jt.getValueAt(r, 4).toString());
			textField_isbn.setText(jt.getValueAt(r, 5).toString());

			
		}
	});
	
	JScrollPane scrollPane_table = new JScrollPane(jt);
	centerPanel_west.add(scrollPane_table);
	
	// booklist 추가
	for(int i=0;i<bookList.size();i++) {
		BookDto book = bookList.get(i);
		Vector<String> bookVector = new Vector<String>();
		bookVector.add(book.getbTitle());
		bookVector.add(book.getbAuthor());
		bookVector.add(book.getbPublisher());
		bookVector.add(Integer.toString(book.getbAmount()));
		bookVector.add(book.getIsRent());
		bookVector.add(Integer.toString(book.getbIsbn()));
		model.addRow(bookVector);
	}

	
	//책테이블
	
	
	
	
	//텍스트레이블
	JLabel label_bookname = new JLabel("책이름");
	label_bookname.setHorizontalAlignment(SwingConstants.CENTER);
	label_bookname.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_center_west.add(label_bookname);
	
	JLabel label_author = new JLabel("저자");
	label_author.setHorizontalAlignment(SwingConstants.CENTER);
	label_author.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_center_west.add(label_author);
	
	JLabel label_publisher = new JLabel("출판사");
	label_publisher.setHorizontalAlignment(SwingConstants.CENTER);
	label_publisher.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_center_west.add(label_publisher);
	
	JLabel label_amount = new JLabel("수량");
	label_amount.setHorizontalAlignment(SwingConstants.CENTER);
	label_amount.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_center_west.add(label_amount);
	
	
	JLabel label_borrow = new JLabel("대출가능여부");
	label_borrow.setHorizontalAlignment(SwingConstants.CENTER);
	label_borrow.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_center_west.add(label_borrow);
	
	JLabel label_isbn = new JLabel("ISBN");
	label_isbn.setHorizontalAlignment(SwingConstants.CENTER);
	label_isbn.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_center_west.add(label_isbn);
	

	
	JLabel label_BookManagement = new JLabel("도서 관리");
	label_BookManagement.setFont(new Font("굴림", Font.BOLD, 30));
	centerPanel_north.add(label_BookManagement);
	
	
	//텍스트 레이블
	
	//텍스트 필드

	textField_bookname = new JTextField();
	centerPanel_center_center.add(textField_bookname);
	textField_bookname.setColumns(10);
	
	textField_author = new JTextField();
	textField_author.setColumns(10);
	centerPanel_center_center.add(textField_author);
	
	textField_publisher = new JTextField();
	textField_publisher.setColumns(10);
	centerPanel_center_center.add(textField_publisher);
	
	textField_amout = new JTextField();
	textField_amout.setColumns(10);
	centerPanel_center_center.add(textField_amout);
	
	textField_isRend = new JTextField();
	textField_isRend.setColumns(10);
	centerPanel_center_center.add(textField_isRend);
	
	textField_isbn= new JTextField();
	textField_isbn.setColumns(10);
	centerPanel_center_center.add(textField_isbn);
	//텍스트필드
	
	//중앙 버튼
	RoundedButton btnNewButton_1 = new RoundedButton("수정하기");
	btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_east.add(btnNewButton_1);
	
	RoundedButton btnNewButton_2 = new RoundedButton("삭제하기");
	btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 20));
	centerPanel_east.add(btnNewButton_2);
	
	//중앙버튼
	//상단패널 요소
	
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
	
	JLabel label_welcome = new JLabel("관리자님 환영합니다!");
	label_welcome.setFont(new Font("굴림", Font.BOLD, 20));
	northPanel_east.add(label_welcome);
	
	RoundedButton btn_logout = new RoundedButton("로그아웃");
	btn_logout.setFont(new Font("굴림", Font.PLAIN, 20));
	northPanel_east.add(btn_logout);
	
	
	//상단패널 버튼 및 요소
	
	// 버튼 이벤트

	
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			int r = jt.getSelectedRow();
			
			if(r == -1) JOptionPane.showMessageDialog(null, "처리할 테이블을 먼저 선택하세요.");
			else {
			
			int t = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?","경고",JOptionPane.YES_NO_OPTION);
			
			 if(t == 0) {
			 
				
			  if(userDao.doDelete(jt.getValueAt(r, 5).toString())) {
				  System.out.println(jt.getValueAt(r, 5).toString());
			  JOptionPane.showMessageDialog(null, "반영되었습니다.");
			  refreshTable();
			  }
			  else
			  
			 JOptionPane.showMessageDialog(null, "반영에 실패하였습니다.");
			  
			  }
			 
			}
		}
		public void refreshTable() {
			
			model = (DefaultTableModel)jt.getModel();
			model.setNumRows(0);
			
			ArrayList<String[]> bookList = userDao.getRows();
			// booklist 추가
			for(int i=0;i<bookList.size();i++) {
				model.addRow(bookList.get(i));
			}

				
				jt.setModel(model);
			}
			
	});
	
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int r = jt.getSelectedRow();
			
			if(r == -1) JOptionPane.showMessageDialog(null, "처리할 테이블을 먼저 선택하세요.");
			
			else {
			String getTitle =  textField_bookname.getText();
			String getAuthor = textField_author.getText();
			String getPublisher = textField_publisher.getText();
			int getAmount = Integer.parseInt(textField_amout.getText());
			String getisRent = textField_isRend.getText();
			String getIsbn = textField_isbn.getText();
			
			userDao.doAlt(getIsbn,getTitle,getAuthor,getAmount,getisRent,getPublisher);
			JOptionPane.showMessageDialog(null, "반영되었습니다.");
		  	refreshTable(); 
			}
			
			  
				
			  
		  
		  
		  
		 
		}
	public void refreshTable() {
		model = (DefaultTableModel)jt.getModel();
		model.setNumRows(0);
		
		ArrayList<String[]> bookList = userDao.getRows();
		// booklist 추가
		for(int i=0;i<bookList.size();i++) {
			model.addRow(bookList.get(i));
		}

			
			jt.setModel(model);
		}
	});
	
	
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

	
	}//버튼이벤트
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Main frame = new Admin_Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
