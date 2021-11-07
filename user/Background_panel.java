package library_project.user;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

	public class Background_panel extends JPanel{
	      ImageIcon icon = new ImageIcon("images/2.JPG");	// ImageIcon 객체 icon 생성
	      Image img = icon.getImage();		//Image 객체 img를 icon 으로부터 받아옴
	      
	      public void paintComponent(Graphics g) {
	         super.paintComponent(g);		
	         g.drawImage(img, 0,0, this.getWidth(),this.getHeight(),this);		//이미지 그리기
	      }
	   }

