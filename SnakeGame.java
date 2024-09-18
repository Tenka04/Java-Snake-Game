import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x, y;
        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int boardHeight, boardWidth, tileSize = 25;
    private Tile snakeHead;
    private ArrayList<Tile> snakeBody;
    private Tile food;
    private Random random;
    private Timer game;
    private int veloX, veloY;
    private boolean gameOver = false;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(10, 10);
        snakeBody = new ArrayList<>();
        food = new Tile(15, 15);
        random = new Random();
        placeFood();

        veloX = 0;
        veloY = 0;

        game = new Timer(100, this);
        game.start();
    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        drawGrid(g);

        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        for (Tile part : snakeBody) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(part.x * tileSize, part.y * tileSize, tileSize, tileSize);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        if (gameOver) {
            g.drawString("Game Over! Press R to Restart", boardWidth / 4, boardHeight / 2);
        } else {
            g.drawString("Score: " + snakeBody.size(), 10, 20);
        }
    }

    private void drawGrid(Graphics g) {
       /*  g.setColor(Color.GRAY);
        for (int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
        }
        for (int i = 0; i < boardHeight / tileSize; i++) {
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }*/
    }

    private void placeFood() {
        boolean onSnake;
        do {
            food.x = random.nextInt(boardWidth / tileSize);
            food.y = random.nextInt(boardHeight / tileSize);
            onSnake = snakeBody.stream().anyMatch(tile -> collsion(tile, food));
        } while (onSnake);
    }

    private boolean collsion(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    private void move() {
        if (collsion(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        snakeHead.x += veloX;
        snakeHead.y += veloY;

        if (snakeBody.stream().anyMatch(part -> collsion(snakeHead, part)) ||
            snakeHead.x < 0 || snakeHead.x >= 575 / tileSize ||
            snakeHead.y < 0 || snakeHead.y >= 575 / tileSize) {
            gameOver = true;
        }
    }

    
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
        }
        repaint();
    }

    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && veloY != 1) {
            veloX = 0;
            veloY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && veloY != -1) {
            veloX = 0;
            veloY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && veloX != 1) {
            veloX = -1;
            veloY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && veloX != -1) {
            veloX = 1;
            veloY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            resetGame();
        }
    }

    private void resetGame() {
        snakeHead = new Tile(10, 10);
        snakeBody.clear();
        placeFood();
        veloX = 0;
        veloY = 0;
        gameOver = false;
    }

    
    public void keyTyped(KeyEvent e) { }

    
    public void keyReleased(KeyEvent e) { }
}
