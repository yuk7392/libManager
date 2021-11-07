package library_project.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import library_project.admin.service.borrowListDto;
import library_project.admin.service.userUpdateDao;
import library_project.login.service.dto.AdministratorDto;
import library_project.login.service.dto.UserDto;
import library_project.util.RoundedButton;

public class Admin_MemberManagement extends JFrame {
	public AdministratorDto admin = Admin_Main.admin; // 관리자 정보
	userUpdateDao UpdateDao = new userUpdateDao();
	List<UserDto> userList = UpdateDao.getUserList();
	private userUpdateDao userUpdateDao = new userUpdateDao();
	ArrayList<borrowListDto> borrowList = userUpdateDao.getBorrowList();
    String bid = "";
    
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_name;
	private JTextField textField_email;
	private JTextField textField_id;
	private JTextField textField_password;
	private JTextField textField_booklist;
	private JTextField textField_role;
	private JLabel label_nameTitle = new JLabel();
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_MemberManagement frame = new Admin_MemberManagement();
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
	public Admin_MemberManagement() {
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
		
		JPanel centerPanel_north = new JPanel();
		centerPanel.add(centerPanel_north, BorderLayout.NORTH);

		JLabel label_userManagement = new JLabel("회원 관리");
		label_userManagement.setFont(new Font("굴림", Font.BOLD, 30));
		centerPanel_north.add(label_userManagement);

		JPanel centerPanel_west = new JPanel();
		centerPanel.add(centerPanel_west, BorderLayout.WEST);

		JPanel centerPanel_south = new JPanel();
		centerPanel.add(centerPanel_south, BorderLayout.SOUTH);

		JPanel centerPanel_east = new JPanel();
		centerPanel.add(centerPanel_east, BorderLayout.EAST);
		centerPanel_east.setLayout(new GridLayout(0, 1, 0, 0));

		RoundedButton btn_modify = new RoundedButton("확정하기");
		btn_modify.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_east.add(btn_modify);

		JPanel centerPanel_center = new JPanel();
		centerPanel.add(centerPanel_center, BorderLayout.CENTER);
		centerPanel_center.setLayout(new BorderLayout(0, 0));

		JPanel centerPanel_center_north = new JPanel();
		centerPanel_center.add(centerPanel_center_north, BorderLayout.NORTH);

		
		label_nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		label_nameTitle.setFont(new Font("굴림", Font.PLAIN, 20));
		

		JPanel centerPanel_center_west = new JPanel();
		centerPanel_center.add(centerPanel_center_west, BorderLayout.WEST);
		centerPanel_center_west.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label_name = new JLabel("성명");
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_name);

		JLabel label_email = new JLabel("이메일");
		label_email.setHorizontalAlignment(SwingConstants.CENTER);
		label_email.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_email);

		JLabel label_id = new JLabel("아이디");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_id);

		JLabel label_password = new JLabel("비밀번호");
		label_password.setHorizontalAlignment(SwingConstants.CENTER);
		label_password.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_password);

		JLabel label_role = new JLabel("역할");
		label_role.setHorizontalAlignment(SwingConstants.CENTER);
		label_role.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_role);

		JLabel label_booklist = new JLabel("대출목록");
		label_booklist.setHorizontalAlignment(SwingConstants.CENTER);
		label_booklist.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_booklist);

		JPanel centerPanel_center_south = new JPanel();
		centerPanel_center.add(centerPanel_center_south, BorderLayout.SOUTH);

		JPanel centerPanel_center_east = new JPanel();
		centerPanel_center.add(centerPanel_center_east, BorderLayout.EAST);

		JPanel centerPanel_center_center = new JPanel();
		centerPanel_center.add(centerPanel_center_center, BorderLayout.CENTER);
		centerPanel_center_center.setLayout(new GridLayout(0, 1, 0, 0));

		textField_name = new JTextField();
		centerPanel_center_center.add(textField_name);
		textField_name.setColumns(10);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		centerPanel_center_center.add(textField_email);

		textField_id = new JTextField();
		textField_id.setColumns(10);
		centerPanel_center_center.add(textField_id);

		textField_password = new JTextField();
		textField_password.setColumns(10);
		centerPanel_center_center.add(textField_password);

		textField_role = new JTextField();
		textField_role.setColumns(10);
		centerPanel_center_center.add(textField_role);
		
		textField_booklist = new JTextField();
		textField_booklist.setColumns(10);
		centerPanel_center_center.add(textField_booklist);

		//



		Vector<String> header = new Vector<String>();
		header.add("유저아이디");
		header.add("비밀번호");
		header.add("이름");
		header.add("이메일");
		header.add("등급");
		header.add("대출한 책 목록");

		table = new JTable();
		
		JScrollPane scrollPane_table = new JScrollPane(table);
		centerPanel_west.add(scrollPane_table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int r = table.getSelectedRow();
				textField_id.setText(table.getValueAt(r, 0).toString());
				textField_password.setText(table.getValueAt(r, 1).toString());
				textField_name.setText(table.getValueAt(r, 2).toString());
				textField_email.setText(table.getValueAt(r, 3).toString());
				textField_role.setText(table.getValueAt(r, 4).toString());
				textField_booklist.setText(table.getValueAt(r, 5).toString());
				label_nameTitle.setText(table.getValueAt(r, 0).toString());

			}	
		});

		model = new DefaultTableModel(header, 0);


		/*
		 * table.setModel(new DefaultTableModel( new Object[][] { {"\uD64D\uAE38\uB3D9",
		 * "hgd@hgd.com", "hgd", "hgdpw123", "\uAD00\uB9AC\uC790", "\uCC451"}, }, new
		 * String[] { "\uC131\uBA85", "\uC774\uBA54\uC77C", "\uC544\uC774\uB514",
		 * "\uBE44\uBC00\uBC88\uD638", "\uC5ED\uD560", "\uB300\uCD9C \uBAA9\uB85D" } ) {
		 * boolean[] columnEditables = new boolean[] { true, true, true, true, true,
		 * false }; public boolean isCellEditable(int row, int column) { return
		 * columnEditables[column]; } });
		 */

		refreshBorrowList();
		
		table.setModel(model);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		


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

		btn_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Name = textField_name.getText();
				String Email = textField_email.getText();
				String ID = textField_id.getText();
				String Pwd = textField_password.getText();
				String Grade = textField_role.getText();
				String ID2 = label_nameTitle.getText();
				//String bid = textField_booklist.getText();

				userUpdateDao.doComplete(Name, Email, ID, Pwd, Grade, ID2);
				
				dispose();
	               EventQueue.invokeLater(new Runnable() {
	                  public void run() { 
	                     try { 
	                        Admin_MemberManagement frame = new Admin_MemberManagement();
	                        frame.setVisible(true);
	                        dispose();
	                     } catch(Exception e) { 
	                        e.printStackTrace();
	                        } 
	                     } 
	                  });

			}
		});

	}
	
	public void refreshBorrowList() {
		
		for (int i = 0; i < userList.size(); i++) {
			UserDto user = userList.get(i);
			
			for(int j=0;j < borrowList.size(); j++) 
			if(user.getcId().equals(borrowList.get(j).getBorrower()))
			bid += userUpdateDao.findBookById(borrowList.get(j).getBid())+" ";
			
			
			Vector<String> userVector = new Vector<String>();
			userVector.add(user.getcId());
			userVector.add(user.getCpwd());
			userVector.add(user.getCname());
			userVector.add(user.getCemail());
			userVector.add(user.getGrade());
			userVector.add(bid);
			model.addRow(userVector);
			textField_booklist.setText(bid);
			
			bid = "";
			
		}
	}
	
	

}
