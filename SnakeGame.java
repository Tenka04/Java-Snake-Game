import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList.*;
import javax.swing.*;



public class SnakeGame extends JPanel implements ActionListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    int boardWidth;
    int boardHeigth;
    int tileSize=25;

    Tile snakeHead;

    Tile food;
    Random random;

    Timer gameLoop;

    SnakeGame(int boardWidth, int boardHeigth){
        this.boardWidth=boardWidth;
        this.boardHeigth=boardHeigth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeigth));
        setBackground(Color.BLACK);

        snakeHead = new Tile(5,5);

        food= new Tile(10, 10);
        random= new Random();
        placeFood();

        gameLoop= new Timer(100,this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //for the grid
    /*  for (int i = 0; i < boardWidth/tileSize; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeigth);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
        } */

        g.setColor(Color.RED);
        g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);

        g.setColor(Color.green);
        g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize,tileSize);

    }

    public void placeFood(){
        food.x= random.nextInt(boardWidth/tileSize);
        food.y= random.nextInt(boardHeigth/tileSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
