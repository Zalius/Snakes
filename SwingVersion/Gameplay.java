/******************************************************************************
 *  Compilation:  javac Gameplay.java
 *  Execution:    java Gameplay n
 *  Programmer: https://github.com/Zalius
 ******************************************************************************/






import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Font;


public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakeXlength =  new int[750];
	private int[] snakeYlength =  new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private boolean one = false;
	private boolean two = false;
	private boolean three = false;
	
	private boolean lose = false;
	
	private ImageIcon leftMouth;
	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	
	
	private int lengthOfSnake = 3;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeImage;
	
	private int difficulty;
	
	private int appleXpos = 25*(int)(Math.random()*34) + 25;
	private int appleYpos = 25*(int)(Math.random()*23) + 75;
	
	private int[] brickHXpos;
	private int[] brickHYpos;
	private int[] brickVXpos;
	private int[] brickVYpos;
	
	private ImageIcon appleImage;
	
	private ImageIcon brickHImage;
	private ImageIcon brickVImage;
	
	private int score = 0;
	
	private int moves = 0;
	
	
	private ImageIcon titleImage;
	
	public Gameplay(int n){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		difficulty = n;
		timer = new Timer(delay/difficulty, this);
		timer.start();
		brickHXpos = new int[difficulty];
		brickHYpos = new int[difficulty];
		brickVXpos = new int[difficulty];
		brickVYpos = new int[difficulty];
		for(int i=0 ; i<difficulty ; i++){
			brickHXpos[i] = 25*(int)(Math.random()*29) + 50;
			brickHYpos[i] = 25*(int)(Math.random()*22) + 100;
			brickVXpos[i] = 25*(int)(Math.random()*33) + 50;
			brickVYpos[i] = 25*(int)(Math.random()*19) + 100;
		}
		
	}
	
	
     
	
	public void paint(Graphics g){
		
		if(moves == 0){
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;
			
			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		}
		
		
			
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24 , 10 , 851 , 55);
		
		//draw title image
		titleImage =  new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this , g, 25 , 11);
		
		//draw border for gameplay
		g.setColor(Color.RED);
		g.drawRect(24 , 74 , 851 ,  577);
		
		//draw background for gameplay
		g.setColor(Color.black);
		g.fillRect(25 , 75 , 850 , 575);
		
		//draw scores
		g.setColor(Color.white);
		g.setFont(new Font("arial" , Font.PLAIN , 14));
		g.drawString("Scores: " + score , 780 , 30);
		
		//draw length
		g.setColor(Color.white);
		g.setFont(new Font("arial" , Font.PLAIN , 14));
		g.drawString("Length: " + lengthOfSnake , 780 , 50);
		
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this , g , snakeXlength[0] , snakeYlength[0]);
		
		for(int i=0 ; i<lengthOfSnake ; i++){
			if(i==0 && right){
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this , g , snakeXlength[i] , snakeYlength[i]);
			}
			if(i==0 && left){
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this , g , snakeXlength[i] , snakeYlength[i]);
			}
			if(i==0 && down){
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this , g , snakeXlength[i] , snakeYlength[i]);
			}
			if(i==0 && up){
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this , g , snakeXlength[i] , snakeYlength[i]);
			}
			if(i!=0){
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this , g , snakeXlength[i] , snakeYlength[i]);
			}
			
		}
		
		//paint and check apple
		appleImage = new ImageIcon("apple.png");
		
		if(appleXpos == snakeXlength[0] && appleYpos == snakeYlength[0]){
			score++;
			lengthOfSnake++;
			appleXpos = 25*(int)(Math.random()*34) + 25;
			appleYpos = 25*(int)(Math.random()*23) + 75;
		}
		
		appleImage.paintIcon(this, g , appleXpos , appleYpos);
		
		//paint and check Horizontal brick
		brickHImage = new ImageIcon("brickH.png");
		for(int k=0 ; k<difficulty; k++){
			for (int i = 0 ; i < 75; i++) {
				for(int j = 0 ; j < 25 ; j++){
					if(brickHXpos[k] + i == snakeXlength[0] && brickHYpos[k] + j == snakeYlength[0]){
						lose = true;
					}
				}
			}
		}
		for(int i = 0 ; i<difficulty ; i++){
			brickHImage.paintIcon(this, g , brickHXpos[i] , brickHYpos[i]);
		}
		
		//paint and check Vertical brick
		brickVImage = new ImageIcon("brickV.png");
		for(int k=0 ; k<difficulty; k++){
			for (int i = 0; i < 25; i++) {
				for(int j = 0 ; j < 75 ; j++){
					if(brickVXpos[k] + i == snakeXlength[0] && brickVYpos[k] + j == snakeYlength[0]){
						lose = true;
					}
				}
			}
		}
		for(int i = 0 ; i<difficulty ; i++){
			brickVImage.paintIcon(this, g , brickVXpos[i] , brickVYpos[i]);
		}
		
	    //gameover by head overlaps body
		for(int k=1; k<lengthOfSnake; k++){
			if(snakeXlength[k] == snakeXlength[0] && snakeYlength[k] == snakeYlength[0]){
				lose = true;	
			}
		}
		
		if(lose){
			gameOver(g);
		}
		
		g.dispose();
		
	}
	
	public void gameOver(Graphics g) {
		lose = false;
		right = false;
		left = false;
	    up = false;
		down = false;
		if (score >= 100 ){
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD , 50));
			g.drawString("You Win!" , 300 , 300);
			g.setFont(new Font("arial", Font.BOLD , 20));
			g.drawString("Try Again? Y/N" , 360 , 390);
		}
		else{
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD , 50));
			g.drawString("Game Over!" , 300 , 300);
			g.drawString("Better luck next time!" , 200 , 350);
				
			g.setFont(new Font("arial", Font.BOLD , 20));
			g.drawString("Try Again? Y/N" , 360 , 390);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		timer.start();
		if(right){
			for(int j= lengthOfSnake-1 ;  j>=0; j--){
				snakeYlength[j+1] = snakeYlength[j] ;
			}
			for(int j= lengthOfSnake ;  j>=0; j--){
				if(j==0){
					snakeXlength[j] = snakeXlength[j] + 25;
				}
				else{
				snakeXlength[j] = snakeXlength[j-1] ;
				}
				if(snakeXlength[j] > 850){
					right = false;
					left = false;
					up = false;
					down = false;
					lose = true;
				}
			}
			
			repaint();
		}
		if(left){
			for(int j= lengthOfSnake-1 ;  j>=0; j--){
				snakeYlength[j+1] = snakeYlength[j] ;
			}
			for(int j= lengthOfSnake ;  j>=0; j--){
				if(j==0){
					snakeXlength[j] = snakeXlength[j] - 25;
				}
				else{
				snakeXlength[j] = snakeXlength[j-1] ;
				}
				if(snakeXlength[j] < 25){
					right = false;
					left = false;
					up = false;
					down = false;
					lose = true;
				}
			}
			
			repaint();
		}
		if(up){
			for(int j= lengthOfSnake-1 ;  j>=0; j--){
				snakeXlength[j+1] = snakeXlength[j] ;
			}
			for(int j= lengthOfSnake ;  j>=0; j--){
				if(j==0){
					snakeYlength[j] = snakeYlength[j] - 25;
				}
				else{
				snakeYlength[j] = snakeYlength[j-1] ;
				}
				if(snakeYlength[j] < 75){
					right = false;
					left = false;
					up = false;
					down = false;
					lose = true;
				}
			}
			
			repaint();
		}
		if(down){
			for(int j= lengthOfSnake-1 ;  j>=0; j--){
				snakeXlength[j+1] = snakeXlength[j] ;
			}
			for(int j= lengthOfSnake ;  j>=0; j--){
				if(j==0){
					snakeYlength[j] = snakeYlength[j] + 25;
				}
				else{
				snakeYlength[j] = snakeYlength[j-1] ;
				}
				if(snakeYlength[j] > 625){
					right = false;
					left = false;
					up = false;
					down = false;
					lose = true;
				}
			}
			
			repaint();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	//keyPressed
	@Override
	public void keyPressed(KeyEvent e){
		
		if(e.getKeyCode() == KeyEvent.VK_Y){
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			for(int i=0 ; i<difficulty ; i++){
			brickHXpos[i] = 25*(int)(Math.random()*29) + 50;
			brickHYpos[i] = 25*(int)(Math.random()*22) + 100;
			brickVXpos[i] = 25*(int)(Math.random()*33) + 50;
			brickVYpos[i] = 25*(int)(Math.random()*19) + 100;
		    }
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_N){
			System.exit(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moves++;
			right = true;
			if(!left){
				right = true;
			}
			else{
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			moves++;
			left = true;
			if(!right){
				left = true;
			}
			else{
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			moves++;
			up = true;
			if(!down){
				up = true;
			}
			else{
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			moves++;
			down = true;
			if(!up){
				down = true;
			}
			else{
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		
	}
	
}