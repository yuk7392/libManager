package library_project.user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import library_project.login.service.dto.UserDto;

public class Main extends JFrame{
	// 로그인 정보 (회원 정보)
	public UserDto user = HeadPanel.user;

	Vector<String> header;
	DefaultTableModel model;
	ImageIcon image = new ImageIcon("images/book.png");
	JPanel mainPanel = new Background_panel();
	
	JLabel iconLabel = new JLabel(image);
	JLabel Main_message = new JLabel("원하시는 기능을 선택하세요");
	
	JLabel headLine = new JLabel();
	HeadPanel headPanel = new HeadPanel();
	
	public Main(){
		
		Container c = getContentPane();
		c.setLayout(null);
		
		c.add(headPanel);
		mainPanel.setLayout(null);
		
		Main_message.setFont(new Font("고딕",Font.BOLD,30));
		Main_message.setForeground(Color.BLUE);
		Main_message.setBounds(300,220,500,50);
		mainPanel.add(Main_message);

		mainPanel.setBounds(0,100,1000,600);
		c.add(mainPanel);
		
		setTitle("Poly Library");
		setLocation(700,250);
		setVisible(true);
		setSize(1000,700);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
