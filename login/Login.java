package library_project.login;
	
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library_project.admin.Admin_Main;
import library_project.login.service.LoginDao;
import library_project.login.service.dto.UserDto;
import library_project.user.Background_panel;
import library_project.user.Main;
import library_project.util.RoundedButton;

public class Login extends JFrame{
	
		// db연동 클래스 생성
	    LoginDao dao = new LoginDao();
	
		Background_panel jp = new Background_panel();	//JPanel을 상속받은 클래스 객체 jp 생성
	  	
	    JLabel headLine = new JLabel();
		JLabel Title = new JLabel("Poly 도서관에 오신것을 환영합니다.");
		JTextField ID_Field = new JTextField();
		JPasswordField Password_Field = new JPasswordField();
		RoundedButton Login_Button = new  RoundedButton("로그인");
		JLabel IdLabel = new JLabel("아이디");
		JLabel PwLabel = new JLabel("비밀 번호");
		JLabel WecomeLabel = new JLabel("처음 방문 하셨나요?");
		RoundedButton Sign_up_Label = new RoundedButton( "여기를 눌러 회원가입");
		RoundedButton searching_Button = new RoundedButton("계정 찾기");
	
		public Login(){
			this("", "");
		}
		
		public Login(String id, String pw){
			jp.setLayout(null);

			Title.setFont(new Font("고딕",Font.ITALIC,40));
			IdLabel.setFont(new Font("고딕",Font.PLAIN,30));
			PwLabel.setFont(new Font("고딕",Font.PLAIN,30));
			WecomeLabel.setFont(new Font("고딕",Font.PLAIN,20));
			Sign_up_Label.setFont(new Font("고딕",Font.PLAIN,20));
			searching_Button.setFont(new Font("고딕",Font.PLAIN,20));
			Title.setForeground(Color.BLACK);
			
			//폰트 글자 설정
			
			headLine.setOpaque(true);
			headLine.setBackground(Color.LIGHT_GRAY);
			
			//헤드라인 
			
			headLine.setBounds(0,0,1000,100);
			Title.setBounds(200, 0, 650, 100);
			IdLabel.setBounds(200, 200, 300, 100);
			ID_Field.setBounds(350, 230, 300, 50);
			PwLabel.setBounds(200, 280, 300, 100);
			Password_Field.setBounds(350, 300, 300, 50);
			Login_Button.setBounds(660, 260, 100, 50);
			WecomeLabel.setBounds(680, 360, 300, 100);
			Sign_up_Label.setBounds(680, 430, 200, 30);
			searching_Button.setBounds(395, 400, 200, 30);
			
			/* 버튼 이벤트 추가  */
			
			// 사용자 로그인
			Login_Button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String id = ID_Field.getText();
					String pw = new String(Password_Field.getPassword());
					
					// id를 입력하지 않았을 때
					if(id.equals("")) {
						JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요", "로그인 메시지", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// 패스워드를 입력하지 않았을 때
					if(pw.equals("")) {
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요", "로그인 메시지", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					UserDto userDto = dao.userLogin(id, pw);
					
					// 로그인 성공
					if(id.equals(userDto.getcId())) {
						if(pw.equals(userDto.getCpwd())) {
						JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 메시지", JOptionPane.INFORMATION_MESSAGE);
						
						if(userDto.getGrade().equals("관리자")) {
				            System.out.println("관리자 로그인");
				            dispose();
				            EventQueue.invokeLater(new Runnable() { public void run() { try { Admin_Main
				   			  frame = new Admin_Main(); frame.setVisible(true); dispose(); } catch
				   			  (Exception e) { e.printStackTrace(); } } });
				         }
						else if(userDto.getGrade().equals("사용자")){
				        	 dispose();
							new Main();
				            System.out.println("유저 로그인");
				         }
						}
					}
					// 로그인 실패
					else {
						JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 메시지", JOptionPane.ERROR_MESSAGE);
					}

				}
			});
			
			/*
			 * // 관리자 로그인 Admin_Button.addActionListener(new ActionListener() {
			 * 
			 * @Override public void actionPerformed(ActionEvent e) { String id =
			 * ID_Field.getText(); String pw = new String(Password_Field.getPassword());
			 * 
			 * // id를 입력하지 않았을 때 if(id.equals("")) { JOptionPane.showMessageDialog(null,
			 * "아이디를 입력해 주세요", "로그인 메시지", JOptionPane.WARNING_MESSAGE); return; }
			 * 
			 * // 패스워드를 입력하지 않았을 때 if(pw.equals("")) { JOptionPane.showMessageDialog(null,
			 * "비밀번호를 입력해 주세요", "로그인 메시지", JOptionPane.WARNING_MESSAGE); return; }
			 * 
			 * AdministratorDto adminDto = dao.adminLogin(id, pw);
			 * 
			 * // 로그인 성공 if(id.equals(adminDto.getaId())) {
			 * JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 메시지",
			 * JOptionPane.INFORMATION_MESSAGE);
			 * 
			 * EventQueue.invokeLater(new Runnable() { public void run() { try { Admin_Main
			 * frame = new Admin_Main(); frame.setVisible(true); dispose(); } catch
			 * (Exception e) { e.printStackTrace(); } } });
			 * 
			 * } // 로그인 실패 else { JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 메시지",
			 * JOptionPane.ERROR_MESSAGE); }
			 * 
			 * } });
			 */
			
			// 회원가입 창으로 이동
			Sign_up_Label.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Sign_up();
				}
			});
			
			// 아이디, 비밀번호 찾기 창으로 이동
			searching_Button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Searching_Idpw();
				}
			});
			
			
			//위치, 크기 
			jp.add(searching_Button);

			jp.add(WecomeLabel);
			jp.add(Sign_up_Label);
			jp.add(IdLabel);
			jp.add(PwLabel);
			jp.add(Title);
			jp.add(headLine);
			jp.add(ID_Field);
			jp.add(Password_Field);
			jp.add(Login_Button);
			add(jp);
			
			//추가
			setTitle("Poly Library");
			setLocation(700,250);
			setVisible(true);
			setSize(1000,700);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//종료시 꺼짐
         
		}
		
		public static void main(String[] args) {
			new Login();
		}
	}


