/******************************************************************************
 *  Programmer:  https://github.com/Zalius
 *
 ******************************************************************************/
// java Snake n
// n = 1 , 2 , 3 , 4 , ...



import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Graphics; 
import java.awt.Font;


public class Snake{
	public static void main(String[] args){
		int n = Integer.parseInt(args[0]); 
		JFrame obj = new JFrame();
		Gameplay gameplay = new Gameplay(n);
		ImageIcon image1 = new ImageIcon("snake-icon.png");
		obj.setIconImage(image1.getImage());
		obj.setBounds(10 , 10 , 905 , 700);
		obj.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}
	
	

}