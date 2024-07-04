package flappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private int boardWidth = 360;
    private int boardHeight = 640;

    private Image backgroundImg;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Random random = new Random();

    private Timer gameLoop;
    private Timer placePipeTimer;

    private boolean gameOver = false;
    private double score = 0;

    public FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        bird = new Bird(boardWidth / 8, boardHeight / 2, new ImageIcon(getClass().getResource("./flappybird.png")).getImage());

        pipes = new ArrayList<>();

        placePipeTimer = new Timer(1500, e -> placePipes());
        placePipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    private void placePipes() {
        int pipeHeight = 512;
        int randomPipeY = (int)(- pipeHeight/4- Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight / 4;

        pipes.add(new Pipe(boardWidth, randomPipeY, new ImageIcon(getClass().getResource("./toppipe.png")).getImage()));
        pipes.add(new Pipe(boardWidth, randomPipeY + pipeHeight + openingSpace, new ImageIcon(getClass().getResource("./bottompipe.png")).getImage()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        bird.draw(g);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + (int) score, 10, 35);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    private void move() {
        bird.move();

        for (Pipe pipe : pipes) {
            pipe.move();
            if (!pipe.isPassed() && bird.getX() > pipe.getX() + pipe.getWidth()) {
                score += 0.5;
                pipe.setPassed(true);
            }

            if (bird.collidesWith(pipe)) {
                gameOver = true;
            }
        }

        if (bird.getY() > boardHeight) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placePipeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.jump();
            if (gameOver) {
                resetGame();
            }
        }
    }

    private void resetGame() {
        bird.reset(boardWidth / 8, boardHeight / 2);
        pipes.clear();
        score = 0;
        gameOver = false;
        gameLoop.start();
        placePipeTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
