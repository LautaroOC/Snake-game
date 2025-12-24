import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Snake {
    private int width;
    private int height;
    private int coordXSpawn;
    private int coordYSpawn;
    private int arcwidth = 20;
    private int archeight = 11;
    private int tailSpaces = 2;
    private int lastCoordinateX = 0;
    private int lastCoordinateY = 0;
    private ArrayList<Integer> coordinateX;
    private ArrayList<Integer> coordinateY;

    public Snake(int coordX, int coordY, int width, int height, int snakeSize) {
       this.coordXSpawn = coordX;
       this.coordYSpawn= coordY;
       this.width = width;
       this.height= height;
       coordinateX = new ArrayList<>();
       coordinateY = new ArrayList<>();
       grow(coordXSpawn, coordYSpawn,snakeSize);
    }

    public Snake getSnake() {
        return this;
    }


    public void grow(int coordXSpawn, int coordYSpawn, int grow) {
        for (int i = 0; i < grow; i++) {
            if (coordinateX.isEmpty() && coordinateY.isEmpty()) {
                coordinateX.add(i, coordXSpawn);
                coordinateY.add(i, coordYSpawn);
                lastCoordinateX = coordXSpawn - width;
                lastCoordinateY = coordYSpawn;
            } else {
                coordinateX.add(i, lastCoordinateX);
                coordinateY.add(i, lastCoordinateY);
                //coordinateY.add(lastCoordinateY + tailSpaces + width);

                lastCoordinateX = coordinateX.get(i) - width;
                lastCoordinateY = lastCoordinateY;
                //lastCoordinateY = coordinateY.get(coordinateY.size() -1);
            }
        }
    }

    int lastcordx = 0;
    int lastlastcordx = 0;
    int lastlastlastcordx = 0;
    int lastcordy = 0;
    public void move() {
        int beforecord = 0;
        //se mueve hacia la derecha
        //la cabeza se mueve primero hacia la derecha que seria posicion 0 + tailspaces + width
        //la cola sigue a la cabeza por lo tanto las demas posiciones = la ultima posicion 0
        for (int i = 0; i < coordinateX.size(); i++) {
            System.out.println(coordinateX.get(i));
            if (i == 0) {
                lastcordx = coordinateX.get(i);
                lastcordy = coordinateY.get(i);
                coordinateX.set(i, lastcordx + width);
            }
            else{
                //lastlastcordx = coordinateX.get(i - 1);
                //lastcordx = coordinateX.get(i-1);
                //coordinateX.set(i, lastcordx-width);
                beforecord = coordinateX.get(i);
                coordinateX.set(i, lastcordx);
                lastcordx = beforecord;
            }
        }
    }

    public void moveUp() {
        //la cabeza se mueve las demas partes lo siguen
        // por lo tanto la cabeza osea la posicion 0 debe de actualizar a una nueva posicion.
        //la nueva posicion va a ser y + tailspaces + width
        //coordinateY.set(0, coordinateY.get(0) + tailSpaces + width);

    }
    public void moveDown () {

    }
    public void moveLeft() {

    }
    public void moveRight() {

    }

    public void paint(Graphics g){

        g.setColor(Color.GREEN);
        for (int i = 0; i < coordinateX.size(); i++) {
            int x = coordinateX.get(i);
            int y = coordinateY.get(i);
            g.fillRoundRect(x,y,width,height, arcwidth,archeight);
        }
    }

}
