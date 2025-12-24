import java.awt.*;

public class Game {
    private int windowWidth;
    private int windowHeight;
    private int squareHeight;
    private int squareWidth;
    Snake snake = new Snake( 80,300,20,20, 3);

    public Game(int windowWidth, int windowHeight, int squareWidth, int squareHeight) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.squareWidth = squareWidth;
        this.squareHeight = squareHeight;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);

        for (int i = 0; i < (windowHeight/squareHeight); i ++) {
            for (int x = 0; x < (windowWidth/squareWidth); x++) {
                g.drawRect(x * squareWidth, i * squareHeight , squareWidth,squareHeight);
            }
        }
    }

}
