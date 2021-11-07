package library_project.user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import library_project.login.service.dto.UserDto;
import library_project.user.service.UserDao;
import library_project.user.service.dto.BookDto;
import library_project.user.service.dto.PageInfo;

public class BookList extends JFrame {
	// 로그인 정보 (회원 정보)
	public static UserDto user = HeadPanel.user;
	
	// 선택 된 책
	BookDto selectedBook = null;
	
	public static PageInfo pageInfo = new PageInfo();
	
	Background_panel back = new Background_panel();
	JPanel headPanel = new HeadPanel();
	JPanel listPanel = new JPanel();
	JLabel bookList_text = new JLabel("Book List");
	Vector<String> header;
	JTextArea infoArea = new JTextArea("정보",23,59);
	JButton nextbtn = new JButton("다음");
	JButton prevbtn = new JButton("이전");
	JLabel Nowtext = new JLabel("현재 페이지 : " + pageInfo.getCurPage());
	
	DefaultTableModel model;
	JTable jt ;
	
	public BookList(){
		
		// DB에 있는 BookList 가져오기
		UserDao userDao = new UserDao();
		List<BookDto> bookList = userDao.getBookList();
		
		Container c = getContentPane();
		c.setLayout(null);
		
		c.add(headPanel);
		header = new Vector<String>();
		header.add("ISBN");
		header.add("책 이름");
		header.add("저자");
		header.add("출판사");
		header.add("대여 가능 여부");
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
				selectedBook = bookList.get(jt.getSelectedRow());
		            infoArea.setText("");
		            infoArea.append("ISBN : " + String.valueOf(selectedBook.getbIsbn())+"\r\n");
		            infoArea.append("책 ID : " + String.valueOf(selectedBook.getbId())+"\r\n");
		            infoArea.append("제목 : " + selectedBook.getbTitle()+"\r\n");
		            infoArea.append("장르 : " + selectedBook.getbGenre()+"\r\n");
		            infoArea.append("작가 : " + selectedBook.getbAuthor()+"\r\n");
		            infoArea.append("출판사 : " + selectedBook.getbPublisher()+"\r\n");
		            infoArea.append("수량 : " + String.valueOf(selectedBook.getbAmount())+"\r\n");
		            infoArea.append("대출가능여부 : " + selectedBook.getIsRent()+"\r\n");
		         }
		      });
		JScrollPane scr = new JScrollPane(jt);
		
		listPanel.add("Fill",scr);
		listPanel.setBounds(50,150,510,430);
		
		// booklist 추가
		for(int i=0;i<bookList.size();i++) {
			BookDto book = bookList.get(i);
			Vector<String> bookVector = new Vector<String>();
			bookVector.add(Integer.toString(book.getbIsbn()));
			bookVector.add(book.getbTitle());
			bookVector.add(book.getbAuthor());
			bookVector.add(book.getbPublisher());
			bookVector.add(book.getIsRent());
			model.addRow(bookVector);
		}
		
		c.add(listPanel);
		
		infoArea.setBounds(600,200,300,300);
		infoArea.setBackground(Color.LIGHT_GRAY);
		c.add(infoArea);
		 
		Nowtext.setBounds(250,580,200,40);
		c.add(Nowtext);
		
		nextbtn.setBounds(350,590,100,30);
		// 다음 페이지 버튼
		nextbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int curPage = pageInfo.getCurPage();
				// 현재 페이지가 총 페이지보다 작으면 다음페이지로 이동
				if(pageInfo.getTotalPage() > pageInfo.getCurPage()) {
					pageInfo.setCurPage(curPage+1);
					dispose();
					new BookList();
				}
			}
		});
		c.add(nextbtn);
		
		prevbtn.setBounds(140,590,100,30);
		prevbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int curPage = pageInfo.getCurPage();
				// 현재 페이지가 1보다 크면 이전 페이지로 이동
				if(pageInfo.getCurPage()>1) {
					pageInfo.setCurPage(curPage-1);
					dispose();
					new BookList();
				}
			}
		});
		c.add(prevbtn);
	
		
		bookList_text.setFont(new Font("고딕",Font.BOLD,40));
		bookList_text.setForeground(Color.YELLOW);
		bookList_text.setBounds(200, 70, 200, 100);
		c.add(bookList_text);
		
		back.setBounds(0,100,1000,600);
		c.add(back);
		
		setTitle("Poly Library");
		setLocation(700,250);
		setVisible(true);
		setSize(1000,700);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    

	}
	
	class MainPanel extends JPanel{
	      ImageIcon icon = new ImageIcon("images/2.JPG");	// ImageIcon 객체 icon 생성
	      Image img = icon.getImage();		//Image 객체 img를 icon 으로부터 받아옴
	      
	      public void paintComponent(Graphics g) {
	         super.paintComponent(g);		
	         g.drawImage(img, 0,0, this.getWidth(),this.getHeight(),this);		//이미지 그리기
	      }
	   }

	public static void main(String[] args) {
		new BookList();
	}
}
