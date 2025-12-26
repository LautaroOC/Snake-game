import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Snake {
    private int spawnCoordX = 80;
    private int spawnCoordY = 300;
    private int width;
    private int height;
    private int arcwidth = 20;
    private int archeight = 11;
    private int tailSpaces = 2;
    private ArrayList<Integer> coordinateX;
    private ArrayList<Integer> coordinateY;
    private long lastMoveTime;
    private int windowWidth;
    private int windowHeight;
    private boolean moveState = false;
    private int currentHorizontalDirection;
    private int currentVerticalDirection;

    public Snake(int width, int height, int snakeSize, int windowWidth, int windowHeight) {
       this.width = width;
       this.height= height;
       this.windowWidth = windowWidth;
       this.windowHeight = windowHeight;
       coordinateX = new ArrayList<>();
       coordinateY = new ArrayList<>();
       grow(spawnCoordX, spawnCoordY,snakeSize);
    }

    public Snake getSnake() {
        return this;
    }


    public void grow(int coordXSpawn, int coordYSpawn, int grow) {
        int lastCoordinateX = 0;
        int lastCoordinateY = 0;
        for (int i = 0; i < grow; i++) {
            if (coordinateX.isEmpty() && coordinateY.isEmpty()) {
                coordinateX.add(i, coordXSpawn);
                coordinateY.add(i, coordYSpawn);
                lastCoordinateX = coordXSpawn - width;
                lastCoordinateY = coordYSpawn;
            } else {
                coordinateX.add(i, lastCoordinateX);
                coordinateY.add(i, lastCoordinateY);

                lastCoordinateX = coordinateX.get(i) - width;
                lastCoordinateY = lastCoordinateY;
            }
        }
    }

    public void move() {
        long snakeTimer = 100_000_000L;
        int lastCoordinateX = 0;
        int lastCoordinateY = 0;
        int beforeCoordinateX = 0;
        int beforeCoordinateY = 0;

        if (coordinateX.isEmpty())  {
            return;
        }
        if (lastMoveTime > 0) {
            long timeNow = System.nanoTime() - lastMoveTime;
            if (timeNow < snakeTimer) {
                return;
            }
            lastMoveTime += snakeTimer;
            int newCoordinateX = 0;
            int newCoordinateY = 0;
            for (int i = 0; i < coordinateX.size(); i++) {
                if (i == 0) {
                    lastCoordinateX= coordinateX.get(i);
                    lastCoordinateY = coordinateY.get(i);
                    coordinateX.set(i, lastCoordinateX + (currentHorizontalDirection * width));
                    coordinateY.set(i, lastCoordinateY + (currentVerticalDirection * width));
                    newCoordinateX = coordinateX.get(i);
                    newCoordinateY = coordinateY.get(i);
                }
                else {
                    if ((newCoordinateX != lastCoordinateX) || (newCoordinateY != lastCoordinateY)){
                        beforeCoordinateX = coordinateX.get(i);
                        beforeCoordinateY = coordinateY.get(i);
                        coordinateX.set(i, lastCoordinateX);
                        coordinateY.set(i, lastCoordinateY);
                        lastCoordinateX= beforeCoordinateX;
                        lastCoordinateY = beforeCoordinateY;
                        moveState = true;
                    }
                }
            }
        }
       else {
           lastMoveTime = System.nanoTime();
        }
    }

    public void checkBorderCollision() {
        if (coordinateX.isEmpty()) {
            return;
        }
        if ((coordinateX.get(0) < 0) || (coordinateX.get(0) >= windowWidth)) {
           coordinateX.clear();
           coordinateY.clear();
           System.out.println("Perdiste pana");
            resetSnake();
        }
        else if ((coordinateY.get(0) < 0) || (coordinateY.get(0) >= windowHeight)) {
           coordinateX.clear();
           coordinateY.clear();
           System.out.println("Perdiste pana");
           resetSnake();
        }
    }

    public void resetSnake() {
        grow(spawnCoordX,spawnCoordY,3);
        currentHorizontalDirection = 0;
        currentVerticalDirection = 0;
        moveState = false;
    }

    public void checkDirection(int horizontalDirection, int verticalDirection) {

        System.out.println("horizontal " + horizontalDirection + "    vertical : " + verticalDirection);
        System.out.println("currenthorizontal " + currentHorizontalDirection + "    currentvertical : " + currentVerticalDirection);

        if (!moveState && horizontalDirection == -1){
            horizontalDirection = 0;
        }

        if ((currentHorizontalDirection == 1 && horizontalDirection == -1) || (currentHorizontalDirection == -1 && horizontalDirection == 1)) {
            System.out.println("la horizontal y la currenthorizontal son opuestas");
        }
        else if ((currentVerticalDirection == 1 && verticalDirection == -1) || (currentVerticalDirection == -1 && verticalDirection == 1)) {
            System.out.println("la vertical la currentvertical son opuestas");
        }
        else {
            if (horizontalDirection != 0 || verticalDirection != 0 ) {
                currentHorizontalDirection = horizontalDirection;
                currentVerticalDirection = verticalDirection;
            }
        }

    }

    public void paint(Graphics g){

        g.setColor(Color.GREEN);
        if (coordinateX.isEmpty()) {
            return;
        }
        for (int i = 0; i < coordinateX.size(); i++) {
            int x = coordinateX.get(i);
            int y = coordinateY.get(i);
            g.fillRoundRect(x,y,width,height, arcwidth,archeight);
        }
    }

}
