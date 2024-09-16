import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList.*;
import java.util.random.*;
import javax.swing.*;


public class SnakeGame extends JPanel {
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

    SnakeGame(int boardWidth, int boardHeigth){
        this.boardWidth=boardWidth;
        this.boardHeigth=boardHeigth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeigth));
        setBackground(Color.BLACK);

        snakeHead = new Tile(5,5);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(snakeHead.x,snakeHead.y,tileSize,tileSize);

    }
}
