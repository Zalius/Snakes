/******************************************************************************
 *  Programmer:  https://github.com/Zalius
 *
 ******************************************************************************/



package snakeStdDraw;
import java.util.Random;


public class Apple {
    private int x;
    private int y;
    private double startTime;
    private double endTime;
    private final int WIDTH = 300;
    private final int LIMIT = WIDTH - 20;
    private final int TIMELIMIT = 30;
    private static final Random RNG = new Random(Long.getLong("seed",
            System.nanoTime()));
    // Create all attributes of apple
    public Apple(int difficulty){
        startTime = System.currentTimeMillis();
        // Difficulty decides amount of time apple will be available
        if(difficulty == 0){
            endTime = RNG.nextInt(TIMELIMIT) * 2000;
        }else if(difficulty == 1){
            endTime = RNG.nextInt(TIMELIMIT) * 1000;
        }else {
            endTime = RNG.nextInt(TIMELIMIT) * 500;
        }
        // Get apple x and y coordinates
        x = (10 * (RNG.nextInt(LIMIT) / 10));
        y = (10 * (RNG.nextInt(LIMIT) / 10));
        // Check to make sure that apple is not outside of border
        if(x >= WIDTH - 20){
            x -= 30;
        } else if(x <= 20){
            x+= 30;
        }
        if(y >= WIDTH - 20){
            y -= 30;
        } else if(y <= 20){
            y += 30;
        }
        StdDraw.picture(x, y, "applescaled.png");
    }
    // Getters for apple
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public double getStartTime(){
        return startTime;
    }
    public double getEndTime(){
        return endTime;
    }
}
