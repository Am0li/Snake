import java.util.LinkedList;
import java.util.List;

public class Board {
    int[][] board;
    Snake snake;

    public Board(int x, int y) {
        board = new int[x][y];
    }

    public int[] growApple(){
        int i = 10;
        while(i>0){
            int x = (int)(Math.random() * board.length);
            int y = (int)(Math.random() * board[0].length);
            if(board[x][y]==0){
                board[x][y] =2;
                return new int[]{x,y};
            }
            i--;
        }
        for(int j = 0;j<=board.length;j++)
        {
            for(int k = 0;k<=board[0].length;k++)
            {
                if(board[j][k]==0){
                    board[j][k]=2;
                    return new int[]{j,k};
                }
            }
        }
        return new int[]{-1};
    }

    public void placeHead(int x, int y){
        board[x][y] =1;
        snake = new Snake(x,y);
    }

    public LinkedList<int[]> moveSnake(int x, int y){
        LinkedList<int[]> changes = new LinkedList<int[]>();
        int[] head = snake.addBody(x,y);

        if(isColiding(head[0],head[1])){
            changes.add(new int[]{-1});
            return changes;
        }

        changes.add(new int[]{head[0],head[1],1});

        if(board[head[0]][head[1]]!=2) {
            int[] tail = snake.removeTail();
            board[tail[0]][tail[1]]=0;
            changes.add(new int[]{tail[0],tail[1],0});
        }
        else{
            int[] apple = growApple();
            if(apple[0]<0){
                changes.add(new int[]{-2});
                return changes;
            }
            changes.add(new int[]{apple[0],apple[1],2});
        }
        board[head[0]][head[1]]=1;
        return changes;
    }

    public boolean isColiding(int x, int y){
        if(x<0||y<0) return true;
        if(x>=board.length||y>=board[0].length) return true;
        return board[x][y] == 1;
    }
}
