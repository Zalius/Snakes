/******************************************************************************
 *  Programmer:  https://github.com/Zalius
 *
 ******************************************************************************/



package snakeStdDraw;


import java.awt.Font;
import java.awt.event.KeyEvent;
import java.applet.Applet;

public class Snake extends Applet {

    //private static final long serialVersionUID = 1L;
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int DOTSIZE = 5;
    private final int NUMBEROFSPACES = 900;
    private final int MIDDLE = 150;

    private final int[] x = new int[NUMBEROFSPACES];
    private final int[] y = new int[NUMBEROFSPACES];

    private int dots;
    private int difficulty;
    private int appleNum;
	private int paddleNum;
	private int paddle2Num;
    private Apple[] apples;
	private Paddle[] paddles;
	private Paddle2[] paddles2;
    private int score = 0;
    private final String printScore = "Score: ";
    private final Font tMN = new Font("Times New Roman", Font.BOLD, 12);
    private final Font endGame = new Font("Times New Roman", Font.BOLD, 16);
    private final int left = KeyEvent.VK_LEFT;
    private final int up = KeyEvent.VK_UP;
    private final int down = KeyEvent.VK_DOWN;
    private final int right = KeyEvent.VK_RIGHT;
    private boolean dirRight = true;
    private boolean dirLeft = false;
    private boolean dirUp = false;
    private boolean dirDown = false;
    private boolean firstMove = false;
    private boolean inGame = true;

    public Snake() {
        // Create window for Snake game.
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenColor(StdDraw.WHITE);
        // Create border around black screen.
        StdDraw.square(MIDDLE, MIDDLE, MIDDLE);
        splashScreen();
    }

    public void splashScreen() {
        StdDraw.clear(StdDraw.BLACK);
        boolean selected = false;
        int option = 0;
        while (!selected) {
            // Draw menu for splash screen.
            StdDraw.text(150, 150, "1. Start");
            StdDraw.text(150, 140, "2. Controls");
            StdDraw.text(150, 130, "3. Exit");
            StdDraw.show();
            // Take in input for splash screen menu.
            if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
                option = 1;
                selected = true;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
                option = 2;
                selected = true;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_3)) {
                option = 3;
                selected = true;
            }
        }
        // Go to screen respective to option selected
        if (option == 1) {
            startMenu();
        } else if (option == 2) {
            controlScreen();
        } else if (option == 3) {
            exitScreen();
        }
    }

    // Menu for difficulty selection.
    public void startMenu() {
        StdDraw.clear(StdDraw.BLACK);
        boolean selected = false;
        int option = 0;
        // Select difficulty using 3 keys.
        while (!selected) {
            StdDraw.text(150, 150, "4. Easy");
            StdDraw.text(150, 140, "5. Medium");
            StdDraw.text(150, 130, "6. Hard");
            if (StdDraw.isKeyPressed(KeyEvent.VK_4)) {
                selected = true;
                option = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_5)) {
                selected = true;
                option = 2;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_6)) {
                selected = true;
                option = 3;
            }
        }
        // Difficulty will change the amount of apples in game and the amount of
        // time they are available
        if (option == 1) {
            difficulty = 0;
        } else if (option == 2) {
            difficulty = 1;
        } else {
            difficulty = 2;
        }
        gameStart();
    }
    // Display controls
    public void controlScreen() {
        StdDraw.clear(StdDraw.BLACK);
        boolean selected = false;
        while (!selected) {
            StdDraw.picture(150, 150, "controlScreen.jpg");
            // Escape key to exit screen.
            if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                selected = true;
            }
        }
        splashScreen();
    }
    // Display exit screen.
    public void exitScreen() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(150, 150, "Thank you for playing!");
    }

    public void gameStart() {
        // Clear screen.
        StdDraw.clear(StdDraw.BLACK);
        dots = 1;
        // Put head at the middle of board.
        for (int i = 0; i < dots; i++) {
            x[i] = MIDDLE;
            y[i] = MIDDLE;
        }
        // 2 apples 2 paddles slow speed - Easy---- 4 apples 4paddles fast speed Medium----- 8 apples 8 paddles very fast speed Hard
        if (difficulty == 0) {
            appleNum = 2;
			paddleNum = 1;
			paddle2Num = 1;
			paddles2 = new Paddle2[paddleNum];
			paddles = new Paddle[paddleNum];
            apples = new Apple[appleNum];
        } else if (difficulty == 1) {
            appleNum = 4;
			paddleNum = 2;
			paddle2Num = 2;
			paddles2 = new Paddle2[paddleNum];
			paddles = new Paddle[paddleNum];
            apples = new Apple[appleNum];
        } else {
            appleNum = 8;
			paddleNum = 4;
			paddle2Num = 4;
			paddles2 = new Paddle2[paddleNum];
			paddles = new Paddle[paddleNum];
            apples = new Apple[appleNum];
        }
        // Create apples.
        for (int i = 0; i < appleNum; i++) {
            apples[i] = new Apple(difficulty);
        }
		// Create paddles
		for (int i = 0; i < paddleNum; i++) {
            paddles[i] = new Paddle();
        }
		// Create paddles2
		for (int i = 0; i < paddleNum; i++) {
            paddles2[i] = new Paddle2();
        }
        while (inGame) {
            // Do not display snake until button is pressed.
            if (StdDraw.isKeyPressed(left) || StdDraw.isKeyPressed(right)
                    || StdDraw.isKeyPressed(up) || StdDraw.isKeyPressed(down) ) {
                checks();
				firstMove = true;
            }
            while (firstMove) {
                // Calculates frames per second and speed of snake
                StdDraw.show(1000 / (20+difficulty*10) );
                StdDraw.clear(StdDraw.BLACK); 
                checks();
				
            }
        }
    }
    // Method to update screen after each move.
    public void paint() {
        StdDraw.setFont(tMN);
        // Draw apples on board.
        for (int i = 0; i < appleNum; i++) {
            StdDraw.picture(apples[i].getX(), apples[i].getY(),
                    "applescaled.png");
        }
		for (int i = 0; i < paddleNum; i++) {
            StdDraw.picture(paddles[i].getX(), paddles[i].getY(),
                    "paddle.png");
        }
		for (int i = 0; i < paddle2Num; i++) {
            StdDraw.picture(paddles2[i].getX(), paddles2[i].getY(),
                    "paddle2.png");
        }
        // Draw head followed by body.
        for (int i = 0; i < dots; i++) {
            if (i == 0) {
                StdDraw.picture(x[i], y[i], "headSprite.png", 7, 7);
            } else {
                StdDraw.picture(x[i], y[i], "bodySprite.png", 7, 7);
            }
        }
        // Display score at top left.
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(15, 305, printScore + score);
        StdDraw.square(MIDDLE, MIDDLE, MIDDLE);
	 
    }
    // Checks tick through game.
    public void checks() {
        checkKey();
        move();
        paint();
        checkApple();
		checkPaddle();
		checkPaddle2();
        collisionDetect();
        // Check to see if apple needs to be replaced.
        for (int i = 0; i < appleNum; i++) {
            if (System.currentTimeMillis() >= apples[i].getStartTime()
                    + apples[i].getEndTime()) {
                apples[i] = new Apple(difficulty);
            }
        }
    }
    // Check to see if a key was pressed and change direction relative to key pressed.
    public void checkKey() {
        if (StdDraw.isKeyPressed(down) && (!dirUp)) {
            dirDown = true;
            dirRight = false;
            dirLeft = false;
        }

        if (StdDraw.isKeyPressed(up) && (!dirDown)) {
            dirUp = true;
            dirRight = false;
            dirLeft = false;
        }

        if (StdDraw.isKeyPressed(right) && (!dirLeft)) {
            dirRight = true;
            dirDown = false;
            dirUp = false;
        }

        if (StdDraw.isKeyPressed(left) && (!dirRight)) {
            dirLeft = true;
            dirDown = false;
            dirUp = false;
        }
    }
    
    // Move body in array depending on direction selected.
    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if (dirDown && !dirUp) {
            y[0] -= DOTSIZE;
        } else if (dirUp && !dirDown) {
            y[0] += DOTSIZE;
        } else if (dirRight && !dirLeft) {
            x[0] += DOTSIZE;
        } else if (dirLeft && !dirRight) {
            x[0] -= DOTSIZE;
        }
    }

    public void collisionDetect() {
        // Check to see if head overlaps body
        for (int i = dots; i > 0; i--) {
            if ((i > 1) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
                gameOver();
            }
        }
        // See if head hits borders of game board
        if (y[0] >= HEIGHT) {
            inGame = false;
            gameOver();
        }

        if (y[0] <= 0) {
            inGame = false;
            gameOver();
        }

        if (x[0] <= 0) {
            inGame = false;
            gameOver();
        }

        if (x[0] >= WIDTH) {
            inGame = false;
            gameOver();
        }
    }
    // Check to see if the head hits an apple
    public void checkApple() {
        for (int i = 0; i < appleNum; i++) {
            if ((x[0] == apples[i].getX()) && (y[0] == apples[i].getY())) {
                dots++;
                score++;
                apples[i] = new Apple(difficulty);
                paint();
            }
        }
    }
	
	// Check to see if the head hits a paddle
	public void checkPaddle() {
        for (int i = 0; i < paddleNum; i++) {
			for(int j = -24 ; j < 24 ; j++){
				for(int k= -9 ; k < 9 ; k++){
					if ( (x[0] == paddles[i].getX() + j) && (y[0] == paddles[i].getY() + k) ) {
						inGame = false;
						gameOver();
					}
				}
			}
        }
    }
	
	// Check to see if the head hits a paddle2
	public void checkPaddle2() {
		 for (int i = 0; i < paddle2Num; i++) {
			for(int j = -9 ; j < 9 ; j++){
				for(int k= -13 ; k < 13 ; k++){
					if ( (x[0] == paddles2[i].getX() + j) && (y[0] == paddles2[i].getY() + k) ) {
						inGame = false;
						gameOver();
					}
				}
			}
        }
    }
    
    // Display game over screen
    public void gameOver() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(endGame);
        if (score >= NUMBEROFSPACES - 400 ) {
            StdDraw.text(150, 150, "YOU WIN!");
            StdDraw.text(150, 140, "Score: " + score);
            // Taken out of loss because of key strokes malfunctioning.
            StdDraw.text(150, 130, "Play Again? Y/N ");
            StdDraw.show();
        } else {
            StdDraw.text(150, 150, "Game Over");
            StdDraw.text(150, 140, "Score: " + score);
			StdDraw.text(150, 120, "Play Again? Y/N ");
            StdDraw.show();
            while (inGame == false) {
                if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
                    score = 0;
                    inGame = true;
                    StdDraw.clear(StdDraw.BLACK);
                    startMenu();

                }
                if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                    StdDraw.clear(StdDraw.BLACK);
                    exitScreen();
                }
            }
        }
    }

    public static void main(final String[] args) {
        new Snake();
    }
}
