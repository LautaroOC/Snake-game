import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DrawWindow();
            }
        });
    }

    public static void DrawWindow(){
        int windowHeight = 600;
        int windowWidth = 600;

        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(windowWidth,windowHeight);
        frame.setLocationRelativeTo(null);
        frame.add(new DrawComponents(windowWidth, windowHeight));

        frame.setVisible(true);
    }
}