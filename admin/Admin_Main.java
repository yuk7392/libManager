
package library_project.admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library_project.login.service.dto.AdministratorDto;
import library_project.util.RoundedButton;

public class Admin_Main extends JFrame {
	public static AdministratorDto admin = new AdministratorDto(); 

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Admin_Main() {
		setTitle("관리자 페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 973, 567);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel northPanel = new JPanel();
		northPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel_east = new JPanel();
		northPanel.add(northPanel_east, BorderLayout.EAST);
		
		JLabel label_welcome = new JLabel("관리자님 환영합니다!");
		label_welcome.setFont(new Font("굴림", Font.BOLD, 20));
		northPanel_east.add(label_welcome);
		
		RoundedButton btn_logout = new RoundedButton("로그아웃");
		btn_logout.setFont(new Font("굴림", Font.PLAIN, 20));
		northPanel_east.add(btn_logout);
		
		JPanel northPanel_west = new JPanel();
		FlowLayout fl_northPanel_west = (FlowLayout) northPanel_west.getLayout();
		fl_northPanel_west.setHgap(15);
		northPanel.add(northPanel_west, BorderLayout.WEST);
		
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
		
		JPanel westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		JPanel eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		//
		
		JLabel label_category01 = new JLabel("원하시는 카테고리를 클릭하세요!");
		label_category01.setFont(new Font("굴림", Font.BOLD, 30));
		label_category01.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(label_category01, BorderLayout.NORTH);
		
		JLabel label_category02 = new JLabel("현재 이 구역에 표시됩니다.");
		label_category02.setForeground(new Color(51, 153, 255));
		label_category02.setHorizontalAlignment(SwingConstants.CENTER);
		label_category02.setFont(new Font("굴림", Font.BOLD, 30));
		centerPanel.add(label_category02, BorderLayout.CENTER);
		
		// 버튼 이벤트
		
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
		
	}

}
