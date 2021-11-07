package library_project.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import library_project.login.service.LoginDao;
import library_project.util.PlaceholderTextField;
import library_project.util.RoundedButton;

public class Searching_Idpw extends JFrame{
	
	// db연동 클래스 생성
	LoginDao dao = new LoginDao();
	
	JLabel IdSearching = new JLabel("아이디 찾기");
	JLabel PwSearching = new JLabel("비밀번호 찾기");
	JLabel bar = new JLabel();
	JLabel bar2 = new JLabel();
	RoundedButton IdseachingButton = new RoundedButton("아이디 조회");
	RoundedButton PwseachingButton = new RoundedButton("비밀번호 조회");
	RoundedButton MoveLoginButton = new RoundedButton("로그인화면으로");
	
	PlaceholderTextField Idsearch_email = new PlaceholderTextField("회원가입한 이메일을 입력하세요");
	PlaceholderTextField Idsearch_name = new PlaceholderTextField("회원가입한 이름을 입력하세요");
	
	PlaceholderTextField Pwsearch_id = new PlaceholderTextField("회원가입한 아이디를 입력하세요");
	PlaceholderTextField Pwsearch_email = new PlaceholderTextField("회원가입한 이메일을 입력하세요");
	PlaceholderTextField Pwsearch_name = new PlaceholderTextField("회원가입한 이름을 입력하세요");
	
	Searching_Idpw(){
		
		Container c = getContentPane();
		c.setLayout(null);
		
		IdSearching.setFont(new Font("고딕",Font.BOLD,20));
		IdSearching.setBounds(280,0, 250, 130);
		c.add(IdSearching);
		
		bar.setOpaque(true);
		bar.setBackground(Color.black);
		bar.setBounds(40,90, 600, 1);
		c.add(bar);
		
		Idsearch_email.setBounds(190, 130, 300, 50);
		c.add(Idsearch_email);
		
		Idsearch_name.setBounds(190, 200, 300, 50);
		c.add(Idsearch_name);
		
		IdseachingButton.setBounds(295, 270, 80, 50);
		IdseachingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = dao.searchId(Idsearch_email.getText(), Idsearch_name.getText());
				
				// 아이디가 존재하지 않을 때
				if(Idsearch_email.getText().equals("회원가입한 이메일을 입력하세요")) {
					JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "이메일 누락", JOptionPane.WARNING_MESSAGE);
					
				}else if(Idsearch_name.getText().equals("회원가입한 이름을 입력하세요")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "이름 누락", JOptionPane.WARNING_MESSAGE);
			    	
				}else {
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.", "ID찾기 메시지", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "아이디 : " + id, "ID찾기 메시지", JOptionPane.INFORMATION_MESSAGE);
				
				}
			    }
			}
		});
		c.add(IdseachingButton);
		
		PwSearching.setFont(new Font("고딕",Font.BOLD,20));
		PwSearching.setBounds(275,290, 250, 130);
		c.add(PwSearching);
		
		bar2.setOpaque(true);
		bar2.setBackground(Color.black);
		bar2.setBounds(40,380, 600, 1);
		c.add(bar2);
		
		Pwsearch_id.setBounds(190, 420, 300, 50);
		c.add(Pwsearch_id);
		
		Pwsearch_email.setBounds(190, 490, 300, 50);
		c.add(Pwsearch_email);
		
		Pwsearch_name.setBounds(190, 560, 300, 50);
		c.add(Pwsearch_name);
		
		PwseachingButton.setBounds(300, 630, 80, 50);
		PwseachingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw = dao.searchPw(Pwsearch_id.getText(), Pwsearch_email.getText(), Pwsearch_name.getText());
				
				
				if(Pwsearch_id.getText().equals("회원가입한 아이디를 입력하세요")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "아이디 누락", JOptionPane.WARNING_MESSAGE);
			    	
				}else if(Pwsearch_email.getText().equals("회원가입한 이메일을 입력하세요")) {
					JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "이메일 누락", JOptionPane.WARNING_MESSAGE);
					
				}else if(Pwsearch_name.getText().equals("회원가입한 이름을 입력하세요")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "이름 누락", JOptionPane.WARNING_MESSAGE);
			    	
				}
				else {
				// 비밀번호 찾기 실패
				if(pw.equals("")) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다.", "비밀번호 찾기 메시지", JOptionPane.WARNING_MESSAGE);
				} 
				// 비밀번호 찾기 성공
				else {
					JOptionPane.showMessageDialog(null, "비밀번호 : " + pw, "비밀번호 찾기 메시지", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Login();
				}
				}
			}
		});
		c.add(PwseachingButton);
		
		
		MoveLoginButton.setBounds(30, 630, 100, 50);
		MoveLoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});
		c.add(MoveLoginButton);
		
	      setTitle("아이디 비밀번호 찾기");										//타이틀제목
	      setVisible(true);											//Visible값 true
	      setSize(700 , 850);
	      setLocation(700,100);  		//사이즈 설정
	      setResizable(false);										//사이즈변경불가
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//닫기시 종료
	}

	public static void main(String[] args) {
		new Searching_Idpw();

	}

}
