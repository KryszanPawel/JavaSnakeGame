package kry.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;
    public static final int FIELD_SIZE = 25;
    public static final int GAME_UNITS = (WINDOW_HEIGHT * WINDOW_WIDTH) / FIELD_SIZE;
    public static final int DELAY = 500;
    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];
    private int size = 4;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;



    public GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }

    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public  void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){
        drawMesh(g);
        drawApple(g);
        drawSnake(g);

    }

    public void drawMesh(Graphics g){
        g.setColor(Color.GRAY);
        for(int position = FIELD_SIZE; position < WINDOW_WIDTH; position += FIELD_SIZE){
            g.drawLine(position, 0, position, WINDOW_HEIGHT);
        }
        for(int position = FIELD_SIZE; position < WINDOW_HEIGHT; position += FIELD_SIZE){
            g.drawLine(0, position, WINDOW_WIDTH, position);
        }
    }

    public void newApple(){
        appleX = random.nextInt(WINDOW_WIDTH / FIELD_SIZE) * FIELD_SIZE;
        appleY = random.nextInt(WINDOW_HEIGHT / FIELD_SIZE) * FIELD_SIZE;
    }

    public void drawApple(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, FIELD_SIZE, FIELD_SIZE);
    }

    public void move(){
        for (int i = size; i > 0; i--){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch(direction){
            case 'U' -> y[0] = y[0] - FIELD_SIZE;
            case 'D' -> y[0] = y[0] + FIELD_SIZE;
            case 'L' -> x[0] = x[0] - FIELD_SIZE;
            case 'R' -> x[0] = x[0] + FIELD_SIZE;
        };
    }

    public void drawSnake(Graphics g){
        Color color;
        for(int i = 0; i < size; i++){
            if(i == 0){
                color = new Color(79, 161, 76, 255);
            }
            else{
                color = Color.GREEN;
            }
            g.setColor(color);
            g.fillRect(x[i],y[i],FIELD_SIZE, FIELD_SIZE);
        }
    }

    public void checkApple(){

    }

    public void checkCollision(){

    }

    public void gameOver(){

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){

        }
    }
}
