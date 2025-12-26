import java.awt.*;
import java.util.ArrayList;

public class Food {
    private int coordinateX;
    private int coordinateY;
    private int width;
    private int height;
    private int arcwidth = 20;
    private int archeight = 11;
    private int windowWidth;
    private int windowHeight;
    private Snake snake;
    private boolean created;

    public Food(int width, int height, int windowWidth, int windowHeight, Snake snake) {
       this.width = width;
       this.height = height;
       this.windowWidth = windowWidth;
       this.windowHeight = windowHeight;
       this.snake = snake;
    }


    public void createFood() {
        if (!created) {
            generateRandomCoordinates();
            created = true;
        }

    }

    public void generateRandomCoordinates() {
        int maxColumn = windowWidth/width;
        int maxRow = windowHeight/height;
        int randColumn;
        int randRow;
        ArrayList<Integer> snakeColumns = snake.getColumns();
        ArrayList<Integer> snakeRows = snake.getRows();
        boolean match = false;

        do {
            match = false;
            randColumn = (int)(Math.random() * maxColumn) + 0;
            randRow = (int)(Math.random() * maxRow) + 0;
            for (int i = 0; i < snakeColumns.size(); i++) {

                if ((snakeColumns.get(i) == randColumn) && (snakeRows.get(i) == randRow)) {
                    match = true;
                }
            }
        }
        while (match);

        coordinateX = randColumn * width;
        coordinateY = randRow * height;
        System.out.println(coordinateX);
        System.out.println(coordinateY);

    }

    public void resetFood() {
        if (snake.eatFood(coordinateX, coordinateY)) {
            created = false;
            createFood();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRoundRect(coordinateX, coordinateY, width, height, arcwidth,archeight);
    }
}
