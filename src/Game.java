import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Game extends JFrame implements KeyListener {
    int x;
    int y;
    int x_size ;
    int y_size;
    Board board;
    JLabel[][] cells;
    public Game() {
        x_size = 21;
        y_size = 21;
        cells = new JLabel[x_size][y_size];

        this.setTitle("SnakeGame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setLayout(new GridLayout(x_size,y_size));
        for(int i = 0; i<x_size;i++)
        {
            for(int j = 0; j<y_size;j++)
            {
                cells[i][j]=new JLabel("0");
                cells[i][j].setOpaque(true);
                cells[i][j].setForeground(Color.BLACK);
                cells[i][j].setBackground(Color.BLACK);
                this.add(cells[i][j]);
            }
        }
        this.setVisible(true);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);


        x=-1;//x==1 -> down
        y=0;//y==1 -> right
        board = new Board(x_size,y_size);
        runGame();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case 87:
            case 38:
                x=-1;
                y=0;
                break;
            case 65:
            case 37:
                x=0;
                y=-1;
                break;
            case 83:
            case 40:
                x=1;
                y=0;
                break;
            case 68:
            case 39:
                x=0;
                y=1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void runGame(){
        board.placeHead(x_size/2,y_size/2);
        cells[x_size/2][y_size/2].setBackground(Color.GREEN);
        cells[x_size/2][y_size/2].setForeground(Color.GREEN);
        int[] apple = board.growApple();
        cells[apple[0]][apple[1]].setBackground(Color.RED);
        cells[apple[0]][apple[1]].setForeground(Color.RED);
        int state = 0;
        LinkedList<int[]> changes = new LinkedList<int[]>();
        while(true)
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            changes = board.moveSnake(x, y);
            state = changes.peekFirst()[0];
            if (state < 0) break;
            for (int[] change : changes) {
                switch (change[2]) {
                    case 0:
                        cells[change[0]][change[1]].setBackground(Color.BLACK);
                        cells[change[0]][change[1]].setForeground(Color.BLACK);
                        break;
                    case 1:
                        cells[change[0]][change[1]].setBackground(Color.GREEN);
                        cells[change[0]][change[1]].setForeground(Color.GREEN);
                        break;
                    case 2:
                        cells[change[0]][change[1]].setBackground(Color.RED);
                        cells[change[0]][change[1]].setForeground(Color.RED);
                        break;
                }
            }
        }
        if(state == -1) gameLost();
        else gameWon();
    }
    public void gameWon(){
        JOptionPane.showMessageDialog(null,"YOU WON!!!","Win",JOptionPane.PLAIN_MESSAGE);
    }
    public void gameLost(){
        JOptionPane.showMessageDialog(null,"YOU LOST!!!","Loss",JOptionPane.PLAIN_MESSAGE);
    }
}
