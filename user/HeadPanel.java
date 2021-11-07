package library_project.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import library_project.login.Login;
import library_project.login.service.dto.UserDto;
import library_project.util.RoundedButton;

public class HeadPanel extends JPanel{
	// 로그인 정보 (회원 정보)
	public static UserDto user = new UserDto();
	
	JFrame parentFrame; // 헤드 패널의 부모 패널을 담을 변수
	
	ImageIcon image = new ImageIcon("images/book.png");
	JLabel iconLabel = new JLabel(image);
	JLabel headLine = new JLabel();
	
	RoundedButton bookListbtn = new RoundedButton("도서 목록");
	RoundedButton bookSearchbtn = new RoundedButton("도서 검색");
	RoundedButton bookRequestbtn=new RoundedButton("도서 요청");
	RoundedButton bookRecomendbtn =new RoundedButton("추천 도서");
	RoundedButton bookRental_listbtn =new RoundedButton("대여 목록");
	
	JLabel welcome_message = new JLabel("님 환영합니다");
	JLabel UserName = new JLabel(user.getCname());
	RoundedButton LogOut =new RoundedButton("로그 아웃");
	  
	HeadPanel(){
		
		setLayout(null);
		setBounds(0,0,1000,100);
		headLine.setOpaque(true);
		headLine.setBackground(Color.LIGHT_GRAY);
		headLine.setBounds(0,0,1000,100);
		
		iconLabel.setBounds(30, 35, 50, 50);
		add(iconLabel);
		
		bookListbtn.setBounds(100,35,100,40);
		bookListbtn.addActionListener(new HeaderListener()); //페이지 이동 이벤트 리스너 등록
		add(bookListbtn);
		
		bookSearchbtn.setBounds(220,35,100,40);
		bookSearchbtn.addActionListener(new HeaderListener()); //페이지 이동 이벤트 리스너 등록
		add(bookSearchbtn);
		
		bookRequestbtn.setBounds(340,35,100,40);
		bookRequestbtn.addActionListener(new HeaderListener()); //페이지 이동 이벤트 리스너 등록
		add(bookRequestbtn);
		
		bookRecomendbtn.setBounds(460,35,100,40);
		bookRecomendbtn.addActionListener(new HeaderListener()); //페이지 이동 이벤트 리스너 등록
		add(bookRecomendbtn);
		
		bookRental_listbtn.setBounds(580,35,100,40);
		bookRental_listbtn.addActionListener(new HeaderListener()); //페이지 이동 이벤트 리스너 등록
		add(bookRental_listbtn);
		
		welcome_message.setFont(new Font("고딕",Font.BOLD,20));
		welcome_message.setBounds(750,50,300,30);
		add(welcome_message);
		
		UserName.setFont(new Font("고딕",Font.BOLD,20));
		UserName.setBounds(690,50,300,30);
		add(UserName); 
		
		LogOut.setBounds(890,50,90,30);
		LogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parentFrame = (JFrame) getTopLevelAncestor();
				parentFrame.dispose();
				Login.main(null);
			}
		});
		add(LogOut);
		
		headLine.setOpaque(true);
		headLine.setBackground(Color.LIGHT_GRAY);
		headLine.setBounds(0,0,1000,100);
		
		add(headLine);
		

  }
	
	// 헤더 버튼 클릭 시 페이지 이동 이벤트리스너
	class HeaderListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			String btnText = btn.getText();
			parentFrame = (JFrame) getTopLevelAncestor();
			
			switch(btnText) {
				case "도서 목록": 
					new BookList();
					break;
				case "도서 검색": 
					new BookSearching();
					break;
				case "도서 요청":
					new BookRequest();
					break;
				case "추천 도서":
					new BookRecommend();
					break;
				case "대여 목록": 
					new BookRental_List();
					break;
				default : 
					System.out.println("잘 못된 정보");
					break;
			}
			
			parentFrame.dispose();

		}
	
	}
  
}
