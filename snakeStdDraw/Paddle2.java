/******************************************************************************
 *  Programmer:  https://github.com/Zalius
 *
 ******************************************************************************/


package snakeStdDraw;
public class Paddle2{
	
	private int x;
    private int y;
	private final int WIDTH = 300;
    
	public Paddle2(){
        // Get paddle x and y coordinates
        x = (int)(Math.random()*280) + 10;
        y = (int)(Math.random()*280) + 10;
        // Check to make sure that paddle is not outside of border
        if(x >= WIDTH - 30){
            x -= 30;
        } else if(x <= 30){
            x+= 30;
        }
        if(y >= WIDTH - 40){
            y -= 40;
        } else if(y <= 40){
            y += 40;
        }
        StdDraw.picture(x, y, "paddle2.png");
    }
	// Getters for paddle
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
}