import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DrawComponents extends JPanel implements Runnable {
    private int windowWidth;
    private int windowHeight;
    private Game game;
    private Thread gameThread;
    private int keyState;
    private long lastMoveTime ;

    public DrawComponents(int windowWidth, int windowHeight) {

        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        game = new Game(windowWidth, windowHeight, 20,20);

        gameThread = new Thread(this);
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        gameThread.start();
    }

    public void run() {

        Action pressedAAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 2;
                System.out.println("A");
            }
        };

        Action pressedDAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 1;
                System.out.println("D");
            }
        };
        Action pressedWAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 3;
                System.out.println("W");
            }
        };

        Action pressedSAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 4;
                System.out.println("S");
            }
        };
        Action releasedAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 0;
            }
        };

        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "dPressed");
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "dReleased");
        this.getActionMap().put("dPressed", pressedDAction);
        this.getActionMap().put("dReleased", releasedAction);
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0 , false), "APressed");
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0 , true), "AReleased");
        this.getActionMap().put("APressed", pressedAAction);
        this.getActionMap().put("AReleased", releasedAction);

        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "WPressed");
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "WReleased");
        this.getActionMap().put("WPressed", pressedWAction);
        this.getActionMap().put("WReleased", releasedAction);
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0 , false), "SPressed");
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0 , true), "SReleased");
        this.getActionMap().put("SPressed", pressedSAction);
        this.getActionMap().put("SReleased", releasedAction);

        int horizontalDirection =  0;
        int verticalDirection = 0;

        while(true) {

            switch (keyState) {
                case 0:
                    horizontalDirection = 0;
                    verticalDirection = 0;
                    break;
                case 1:
                    game.snake.move();
                    horizontalDirection = 1;
                    verticalDirection = 0;
                    break;
                case 2:
                    game.snake.move();
                    horizontalDirection = -1;
                    verticalDirection = 0;
                    break;
                case 3:
                    game.snake.move();
                    horizontalDirection = 0;
                    verticalDirection = -1;
                    break;
                case 4:
                    game.snake.move();
                    horizontalDirection = 0;
                    verticalDirection = 1;
                    break;
            }

            System.out.println("horizontal " + horizontalDirection);
            game.snake.checkDirection(horizontalDirection, verticalDirection);

            game.snake.move();

            if(horizontalDirection != 0 || verticalDirection != 0) {

              //  game.snake.move();
            }

            game.snake.checkBorderCollision();

            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.draw(g);
        game.snake.paint(g);
    }
}