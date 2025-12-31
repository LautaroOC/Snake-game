import java.awt.*;

public class Game {
    private int windowWidth;
    private int windowHeight;
    private int squareHeight;
    private int squareWidth;
    private int scoreCoordinateX;
    private int scoreCoordinateY;
    private int highestScoreCoordinateX;
    private int highestScoreCoordinateY;
    Snake snake;
    Food food;

    public Game(int windowWidth, int windowHeight, int squareWidth, int squareHeight) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.squareWidth = squareWidth;
        this.squareHeight = squareHeight;
        snake = new Snake( 20,20, 3, windowWidth, windowHeight);
        food = new Food(20,20,windowWidth,windowHeight, snake);
        scoreCoordinateX = windowWidth - 80;
        scoreCoordinateY = 20;
        highestScoreCoordinateX = scoreCoordinateX;
        highestScoreCoordinateY = scoreCoordinateY * 2;
    }

    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
        Font fontScore = new Font("Arial", Font.BOLD, 19);
        g.setFont(fontScore);
        g.drawString("Score: " + snake.getScore(), scoreCoordinateX, scoreCoordinateY);

        Font fontHighestScore = new Font("Arial", Font.BOLD, 10);
        g.setFont(fontHighestScore);
        g.drawString("Highest Score: " + snake.getHighestScore(), highestScoreCoordinateX, highestScoreCoordinateY);

        g.setColor(Color.BLACK);
        for (int i = 0; i < (windowHeight/squareHeight); i ++) {
            for (int x = 0; x < (windowWidth/squareWidth); x++) {
                g.drawRect(x * squareWidth, i * squareHeight , squareWidth,squareHeight);
            }
        }
    }

}
