import flappyBird.FlappyBird;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(360,640);
        frame.setResizable(false);


        FlappyBird flapyBird = new FlappyBird();
        frame.add(flapyBird);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
