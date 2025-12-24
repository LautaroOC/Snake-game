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
            }
        };

        Action pressedDAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 1;
            }
        };
        Action pressedWAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 3;
            }
        };

        Action pressedSAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyState = 4;
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

        while(true) {

            game.snake.move();

            switch (keyState) {
                case 1:
                    game.snake.moveRight();
                    break;
                case 2:
                    game.snake.moveLeft();
                    break;
                case 3:
                    game.snake.moveUp();
                    break;
                case 4:
                    game.snake.moveDown();
                    break;
            }

            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Se interrumpio el hilo");
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.draw(g);
        game.snake.paint(g);

    }
}