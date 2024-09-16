import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList.*;
import java.util.random.*;
import javax.swing.*;


public class SnakeGame extends JPanel {
    int boardWidth;
    int boardHeigth;

    SnakeGame(int boardWidth, int boardHeigth){
        this.boardWidth=boardWidth;
        this.boardHeigth=boardHeigth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeigth));
        setBackground(Color.BLACK);
    }

}
