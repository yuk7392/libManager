package library_project.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library_project.login.service.LoginDao;
import library_project.login.service.dto.UserDto;
import library_project.util.RoundedButton;

public class Sign_up extends JFrame{		//회원가입겸 캐릭터선택창인 selectCharacter 클래스

   jp jp = new jp();
   
   JLabel headLine = new JLabel();
   String [] ageList = {"14","15","16","17","18","19","20","21","22","23","24","25","26"};
   
   RoundedButton submitButton = new RoundedButton("Submit");		//선택완료버튼
   RoundedButton backButton = new RoundedButton("back");		//선택완료버튼
   JLabel headLineText = new JLabel("Enter User Info");		//패널 소개
   JComboBox<String> ageSelect = new JComboBox<String>(ageList);
   RoundedButton Lb1 = new RoundedButton("ID중복 확인");
  
   
   JTextField userId=new JTextField();		//userId 입력텍스트필드
   JPasswordField userPswd=new JPasswordField();	//userPswd 입력텍스트필드
   JPasswordField userPwCheck=new JPasswordField();	//패스워드 체크 입력텍스트필드
   JTextField userName=new JTextField();	//userName 입력텍스트필드
   JTextField userAge=new JTextField();		//userAge 입력텍스트필드
   JTextField userEmail=new JTextField();		//userEmail 입력텍스트필드
  
   JLabel IdLabel = new JLabel("Id");				//userId 텍스트필드 소개
   JLabel PasswordLabel = new JLabel("Password");	//userPswd 텍스트필드 소개
   JLabel PasswordCheckLabel = new JLabel("Pw Check"); // 비밀번호 체크 텍스트필드 소개
   JLabel UserNameLabel = new JLabel("Name");		//userName 텍스트필드 소개
   JLabel UserAgeLabel = new JLabel("Age");			//userAge 텍스트필드 소개
   JLabel EmailLabel = new JLabel("EMail");				//userEmail 텍스트필드 소개

   // db연동 클래스 생성
   LoginDao dao = new LoginDao();
   
   String checkedId = ""; // 체크  된 아이디
    
   public Sign_up(){
      Container c = getContentPane();
   
      headLineText.setFont(new Font("Serif",Font.BOLD,50));	//SignUp 폰트설정
      headLineText.setForeground(Color.BLACK);				//SignUp 글자색 설정
      headLineText.setLocation(50, 30);						//SignUp 위치 설정
      headLineText.setSize(450,50);							//SignUp 사이즈 설정
      c.add(headLineText);									//컨테이너에 SignUp 부착
       
      IdLabel.setFont(new Font("Serif",Font.BOLD,40));		//IdLabel 폰트 설정
      IdLabel.setForeground(Color.BLACK);					//IdLabel 글자색 설정
      IdLabel.setLocation(250, 140);						//IdLabel 위치 설정
      IdLabel.setSize(450,50);								//IdLabel 사이즈 설정
      c.add(IdLabel);//컨테이너에 IdLabel 부착
     
      //Id중복체크 버튼
	  //컨테이너에 IdLabel 부착
      Lb1.setBounds(710, 150, 100, 30);
      Lb1.addActionListener(new IdCheckListener());
      c.add(Lb1);
      
      PasswordLabel.setFont(new Font("Serif",Font.BOLD,40));	//PasswordLabel 폰트 설정
      PasswordLabel.setForeground(Color.BLACK);					//PasswordLabel 글자색 설정
      PasswordLabel.setLocation(250, 190);						//PasswordLabel 위치 설정
      PasswordLabel.setSize(450,50);							//PasswordLabel 사이즈 설정
      c.add(PasswordLabel);										//컨테이너에 PasswordLabel 부착
      
      //비밀번호 체크
      PasswordCheckLabel.setFont(new Font("Serif",Font.BOLD,40));	//PasswordCheckLabel 폰트 설정
      PasswordCheckLabel.setForeground(Color.BLACK);				//PasswordCheckLabel 글자색 설정
      PasswordCheckLabel.setLocation(250, 240);							//PasswordCheckLabel 위치 설정
      PasswordCheckLabel.setSize(450,50);								//PasswordCheckLabel 사이즈 설정
      c.add(PasswordCheckLabel);											//컨테이너에 PasswordCheckLabel 부착
      
      UserNameLabel.setFont(new Font("Serif",Font.BOLD,40));	//UserNameLabel 폰트 설정
      UserNameLabel.setForeground(Color.BLACK);					//UserNameLabel 글자색 설정
      UserNameLabel.setLocation(250, 290);						//UserNameLabel 위치 설정
      UserNameLabel.setSize(450,50);							//UserNameLabel 사이즈 설정
      c.add(UserNameLabel);										//컨테이너에 UserNameLabel 부착
      
      UserAgeLabel.setFont(new Font("Serif",Font.BOLD,40));		//UserAgeLabel 폰트 설정
      UserAgeLabel.setForeground(Color.BLACK);					//UserAgeLabel 글자색 설정
      UserAgeLabel.setLocation(250, 340);						//UserAgeLabel 위치 설정
      UserAgeLabel.setSize(450,50);								//UserAgeLabel 사이즈 설정
      c.add(UserAgeLabel);										//컨테이너에 UserAgeLabel 부착
      
      EmailLabel.setFont(new Font("Serif",Font.BOLD,40));			//EmailLabel 폰트 설정
      EmailLabel.setForeground(Color.BLACK);						//EmailLabel 글자색 설정
      EmailLabel.setLocation(250, 390);							//EmailLabel 위치 설정
      EmailLabel.setSize(450,50);									//EmailLabel 사이즈 설정
      c.add(EmailLabel);											//컨테이너에 EmailLabel 부착
      
      jp.add(userId);											//JPanel jp 에 userId 텍스트필드 부착
      userId.setLocation(450, 150);								//텍스트필드 userId 위치 설정
      userId.setSize(250,40);									//텍스트필드 userId 사이즈 설정
      
      jp.add(userPswd);											//JPanel jp 에 userPswd 텍스트필드 부착
      userPswd.setLocation(450, 200);							//텍스트필드 userPswd 위치 설정
      userPswd.setSize(250,40);									//텍스트필드 userPswd 사이즈 설정
      
      jp.add(userPwCheck);										//JPanel jp 에 userPswdCheck 텍스트필드 부착
      userPwCheck.setLocation(450, 250);						//텍스트필드 userPswdCheck 위치 설정
      userPwCheck.setSize(250,40);								//텍스트필드 userPswdCheck 사이즈 설정
      
      jp.add(userName);											//JPanel jp 에 userName 텍스트필드 부착
      userName.setLocation(450, 300);							//텍스트필드 userName 위치 설정
      userName.setSize(250,40);									//텍스트필드 userName 사이즈 설정
      
      ageSelect.setBounds(450, 350, 250, 30);
      c.add(ageSelect);
     
      jp.add(userEmail);											//JPanel jp 에 userEmail 텍스트필드 부착
      userEmail.setLocation(450, 400);							//텍스트필드 userEmail 위치 설정
      userEmail.setSize(250,40);									//텍스트필드 userEmail 사이즈 설정
      
      jp.setLayout(null);										//JPanel jp 레이아웃 널값으로 설정
  	  headLine.setOpaque(true);
  	  headLine.setBackground(Color.LIGHT_GRAY);
  	  headLine.setBounds(0,0,1000,100);
  	  c.add(headLine);
  
      add(jp);													//컨테이너에 jp 부착
      
      submitButton.setLocation(550, 450);						//selectButton 위치 설정
      submitButton.setSize(100,30);								//selectButton 사이즈 설정
      submitButton.setVisible(true);							//selectButton 구현
      submitButton.addActionListener(new FormCheckListener());
      
      jp.add(submitButton);										//JPanel jp 에 selectButton 부착      
      
     
      backButton.setLocation(300, 450);						//selectButton 위치 설정
      backButton.setSize(100,30);								//selectButton 사이즈 설정
      backButton.setVisible(true);							//selectButton 구현
      backButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new Login();
			dispose();
		}
	});
      jp.add( backButton);		
      
      setTitle("회원가입");										//타이틀제목
      setVisible(true);											//Visible값 true
      setSize(1000 , 700);
      setLocation(700,250);  		//사이즈 설정
      setResizable(false);										//사이즈변경불가
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//닫기시 종료

   }
   
   //배경 설정
   class jp extends JPanel{
      ImageIcon icon = new ImageIcon("images/2.jpg");
      Image img1 = icon.getImage();
 
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(img1, 0,0, this.getWidth(),this.getHeight(),this);
     
      }
   }
   
    // id 중복 체크
	class IdCheckListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// id 존재 여부 확인

			// 존재할 때
			if(dao.idCheck(userId.getText())) {
				checkedId = "";				
				JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.", "ID중복 확인", JOptionPane.WARNING_MESSAGE);
			} 
			// false(존재하지 않을 때)일 때
			else {
				checkedId = userId.getText(); // checkedId에 사용 가능 id 저장 
				JOptionPane.showMessageDialog(null, "사용 가능한 ID입니다.","ID중복 확인", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	 // 회원가입 
	class FormCheckListener implements ActionListener {
		
		// 버튼 클릭 후 textField의 폼체크
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// id를 입력하지 않았을 때
			if(userId.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요", "회원가입 메시지", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 중복체크를 하지 않을 경우(checkedId == "")  
			if(checkedId.equals("") || !checkedId.equals(userId.getText())) {
				JOptionPane.showMessageDialog(null,  "ID 중복체크를 해주세요","회원가입 메시지", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 패스워드를 입력하지 않았을 때
			if(userPswd.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요","회원가입 메시지", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 비밀번호 확인
			String pw = new String(userPswd.getPassword());
			String pwCheck = new String(userPwCheck.getPassword());
			
			if(!pw.equals(pwCheck)) {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주세요","회원가입 메시지", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 이름을 입력하지 않았을 때
			if(userName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해 주세요","회원가입 메시지", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 이메일을 입력하지 않았을 때
			if(userEmail.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요", "회원가입 메시지", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 유저 정보 객체 생성 후 DB에 저장할 준비
			UserDto user = new UserDto();
			user.setcId(userId.getText());
			user.setCpwd(pw);
			user.setCname(userName.getText());
			user.setcAge(Integer.parseInt(ageSelect.getSelectedItem().toString()));
			user.setCemail(userEmail.getText());
			
			submit(user);
		}
		
		// 회원 가입 진행
		public void submit(UserDto user) {

			Map<String, String> result = dao.sign_up(user);

			if(result.get("result").equals("success")) {
				new Login(user.getcId(),user.getCpwd());
				dispose();
			} 
			
			JOptionPane.showMessageDialog(null, result.get("message"), "회원가입 메시지", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
   
   public static void main(String[] args) {
		new Sign_up();
	}
 
}