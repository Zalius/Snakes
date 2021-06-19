/******************************************************************************
 *  Programmer:  https://github.com/Zalius
 *
 ******************************************************************************/


package snakeStdDraw;
public class Paddle{
	
	private int x;
    private int y;
	private final int WIDTH = 300;
    
	public Paddle(){
        // Get paddle x and y coordinates
        x = (int)(Math.random()*280) + 10;
        y = (int)(Math.random()*280) + 10;
        // Check to make sure that paddle is not outside of border
        if(x >= WIDTH - 65){
            x -= 60;
        } else if(x <= 65){
            x+= 60;
        }
        if(y >= WIDTH - 18){
            y -= 60;
        } else if(y <= 18){
            y += 60;
        }
        StdDraw.picture(x, y, "paddle.png");
    }
	// Getters for paddle
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
}