package library_project.user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import library_project.login.service.dto.UserDto;
import library_project.user.service.UserDao;
import library_project.user.service.dto.OrderBookDto;
import library_project.util.PlaceholderTextField;
import library_project.util.RoundedButton;

public class BookRequest extends JFrame{
	// 로그인 정보 (회원 정보)
	public static UserDto user = HeadPanel.user;
	
	// DB객체 생성
	UserDao userDao = new UserDao();
	
	Background_panel back = new Background_panel();
	JPanel headPanel = new HeadPanel();
	JLabel BookRequest_text = new JLabel("Book Request");
	PlaceholderTextField Searching_publisher = new PlaceholderTextField("출판사를 입력하세요");
	PlaceholderTextField Searching_Name = new PlaceholderTextField("책 이름을 입력하세요");
	PlaceholderTextField Searching_reason = new PlaceholderTextField("요청하시는 이유를 입력하세요");
	PlaceholderTextField Searching_email = new PlaceholderTextField("이메일을 입력하세요");
	RoundedButton Submitbtn = new RoundedButton("전송");
	RoundedButton checkBtn = new RoundedButton("요청 목록 보기");
	
	public BookRequest(){
		
		
		Container c = getContentPane();
		c.setLayout(null);
		
		Searching_Name.setBounds(370,250, 250, 30);
		c.add(Searching_Name);

		Searching_publisher.setBounds(370,300, 250, 30);
		c.add(Searching_publisher);
		
		
		Searching_reason.setBounds(370,350, 250, 130);
		c.add(Searching_reason);
		
		Searching_email.setBounds(370,500, 250, 30);
		c.add(Searching_email);
		
		BookRequest_text.setFont(new Font("고딕",Font.BOLD,40));
		BookRequest_text.setBounds(350, 170, 300, 100);
		BookRequest_text.setForeground(Color.BLACK);
		c.add(BookRequest_text);
		
		Submitbtn.setBounds(370,550, 250, 30);
		Submitbtn.addActionListener(new requestListener());
		c.add(Submitbtn);
		
		checkBtn.setBounds(370,600, 250, 30);
        checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ArrayList<OrderBookDto> obd = userDao.getRows(user.getcId());
				String str="";
				
				for(int i = 0;i < obd.size() ; i ++) {
					
					
					str += (i+1)+".\n책이름 : "+obd.get(i).getoTitle()+"\n처리상태 : "+obd.get(i).getoStatus()+"\n답변 : "+(obd.get(i).getoAnswer() != null ? obd.get(i).getoAnswer() : "받지 않음")+"\n";
					
				}
				
				JOptionPane.showMessageDialog(null, "--요청하신 내용--\n"
						+ str);
				
			}
		});
		c.add(checkBtn);
		
		back.setBounds(0,100,1000,600);
		c.add(back);
		c.add(headPanel);
		
		
		setTitle("Poly Library");
		setLocation(700,250);
		setVisible(true);
		setSize(1000,700);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// 요청 전송 버튼 이벤트
	class requestListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			OrderBookDto order = new OrderBookDto();
			
			order.setoName(user.getCname());
			order.setoUserid(user.getcId()); 
			order.setoTitle(Searching_Name.getText());
			order.setoPublisher(Searching_publisher.getText());
			order.setoReason(Searching_reason.getText());
			order.setoEmail(Searching_email.getText());
			order.setoStatus("대기중");
			
			
			// DB에서 검색내용 가져오기
			int resultNum = userDao.requestBook(order);
			
			if(resultNum == 1) {
				JOptionPane.showMessageDialog(null, "도서 요청 메시지", "요청 성공", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "도서 요청 메시지", "요청 실패", JOptionPane.ERROR_MESSAGE);
			}
			
			new Main();
			dispose();
		}
	
	}
	
	public static void main(String[] args) {
		new BookRequest();

	}

}
