
package library_project.admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import library_project.admin.service.orderDao;
import library_project.login.service.dto.AdministratorDto;
import library_project.util.RoundedButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin_Request extends JFrame {
	public AdministratorDto admin = Admin_Main.admin; // 관리자 정보

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_book;
	private JTextField textField_publisher;
	private JTextField textField_reason;
	private DefaultTableModel tableModel;
	private orderDao orderDto = new orderDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Request frame = new Admin_Request();
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
	public Admin_Request() {
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
		
		JLabel label_request = new JLabel("요청 목록");
		label_request.setFont(new Font("굴림", Font.BOLD, 30));
		label_request.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel_north.add(label_request);
		
		JPanel centerPanel_west = new JPanel();
		centerPanel.add(centerPanel_west, BorderLayout.WEST);
		
		table = new JTable();
		refreshTable();

		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel_west.add(scrollPane);
		
		JPanel centerPanel_south = new JPanel();
		centerPanel.add(centerPanel_south, BorderLayout.SOUTH);
		centerPanel_south.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel centerPanel_east = new JPanel();
		centerPanel.add(centerPanel_east, BorderLayout.EAST);
		centerPanel_east.setLayout(new GridLayout(0, 1, 0, 0));
		
		RoundedButton btn_answer = new RoundedButton("답변하기");

		centerPanel_east.add(btn_answer);
		btn_answer.setFont(new Font("굴림", Font.PLAIN, 20));
		
		RoundedButton btn_complete = new RoundedButton("완료하기");
		centerPanel_east.add(btn_complete);
		btn_complete.setFont(new Font("굴림", Font.PLAIN, 20));
		
		RoundedButton btn_remove = new RoundedButton("삭제하기");
		centerPanel_east.add(btn_remove);
		btn_remove.setFont(new Font("굴림", Font.PLAIN, 20));
		
		JPanel centerPanel_center = new JPanel();
		centerPanel.add(centerPanel_center, BorderLayout.CENTER);
		centerPanel_center.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel_center_north = new JPanel();
		centerPanel_center.add(centerPanel_center_north, BorderLayout.NORTH);
		
		JLabel label_info = new JLabel("정보");
		label_info.setHorizontalAlignment(SwingConstants.CENTER);
		label_info.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_north.add(label_info);
		
		JPanel centerPanel_center_west = new JPanel();
		centerPanel_center.add(centerPanel_center_west, BorderLayout.WEST);
		centerPanel_center_west.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_book = new JLabel("책 이름");
		label_book.setHorizontalAlignment(SwingConstants.CENTER);
		label_book.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_book);
		
		JLabel label_publisher = new JLabel("출판사");
		label_publisher.setHorizontalAlignment(SwingConstants.CENTER);
		label_publisher.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_publisher);
		
		JLabel label_reason = new JLabel("요청사유");
		label_reason.setHorizontalAlignment(SwingConstants.CENTER);
		label_reason.setFont(new Font("굴림", Font.PLAIN, 20));
		centerPanel_center_west.add(label_reason);
		
		JPanel centerPanel_center_east = new JPanel();
		centerPanel_center.add(centerPanel_center_east, BorderLayout.EAST);
		
		JPanel centerPanel_center_south = new JPanel();
		centerPanel_center.add(centerPanel_center_south, BorderLayout.SOUTH);
		
		JPanel centerPanel_center_center = new JPanel();
		centerPanel_center.add(centerPanel_center_center, BorderLayout.CENTER);
		centerPanel_center_center.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField_book = new JTextField();
		textField_book.setEditable(false);
		textField_book.setHorizontalAlignment(SwingConstants.LEFT);
		centerPanel_center_center.add(textField_book);
		textField_book.setColumns(10);
		
		textField_publisher = new JTextField();
		textField_publisher.setEditable(false);
		textField_publisher.setColumns(10);
		centerPanel_center_center.add(textField_publisher);
		
		textField_reason = new JTextField();
		textField_reason.setEditable(false);
		textField_reason.setColumns(10);
		centerPanel_center_center.add(textField_reason);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int r = table.getSelectedRow();
				textField_book.setText(table.getValueAt(r, 2).toString());
				textField_publisher.setText(table.getValueAt(r, 3).toString());
				textField_reason.setText(table.getValueAt(r, 4).toString());
			}
		});
		
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
		
		btn_answer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				
				if(r == -1) JOptionPane.showMessageDialog(null, "처리할 테이블을 먼저 선택하세요.");
				else {
				
				String answer=JOptionPane.showInputDialog(null, "답변 내용을 입력해주세요.");
				if(answer.equals("")) {
					
					JOptionPane.showMessageDialog(null, "빈 메시지는 전송 불가능합니다.");
					
				} else {
					
				
				
				if(orderDto.doAnswer(table.getValueAt(r, 1).toString(), table.getValueAt(r, 2).toString(), answer))	{
				JOptionPane.showMessageDialog(null, "반영되었습니다.");
				refreshTable();
				} else 
				
				JOptionPane.showMessageDialog(null, "반영에 실패하였습니다.");
				
				}
				}
			}
		});
		
		btn_complete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                int r = table.getSelectedRow();
                
                if(r == -1) JOptionPane.showMessageDialog(null, "처리할 테이블을 먼저 선택하세요.");
				else {
				
				if(orderDto.doComplete(table.getValueAt(r, 1).toString(), table.getValueAt(r, 2).toString()))	{
				JOptionPane.showMessageDialog(null, "반영되었습니다.");
				refreshTable();
				} else 
				
				JOptionPane.showMessageDialog(null, "반영에 실패하였습니다.");
				
				}
			}
				
			
		});
		
		btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				
				if(r == -1) JOptionPane.showMessageDialog(null, "처리할 테이블을 먼저 선택하세요.");
				else {
				
				int t = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?","경고",JOptionPane.YES_NO_OPTION);
				
				if(t == 0) {
				
				 
					
					if(orderDto.doDelete(table.getValueAt(r, 1).toString(), table.getValueAt(r, 2).toString()))	{
					JOptionPane.showMessageDialog(null, "반영되었습니다.");
					refreshTable();
					} else 
					
					JOptionPane.showMessageDialog(null, "반영에 실패하였습니다.");
					
					}
				}
			}
		});
	
	}
	
	public void refreshTable() {
		
		String header[] = {"성명","아이디","책이름","출판사","요청사유","이메일","처리여부","답변"};
		tableModel = new DefaultTableModel(header,0);
		ArrayList<String[]> rows = orderDto.getRows();
		for(int i=0;i < rows.size();i++) {
			
			tableModel.addRow(rows.get(i));
			
			
		}
		
		table.setModel(tableModel);
	}
	

}
