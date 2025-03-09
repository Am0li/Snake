import java.awt.*;
import java.util.LinkedList;

public class Snake {
    LinkedList<Integer> bodyX;
    LinkedList<Integer> bodyY;
    public Snake(int x, int y){
        bodyX = new LinkedList<>();
        bodyY = new LinkedList<>();
        bodyX.add(x);
        bodyY.add(y);
    }
    public int[] removeTail()
    {
        return new int[]{bodyX.removeFirst(),bodyY.removeFirst()};
    }
    public int[] addBody(int x, int y)
    {
        bodyX.add(bodyX.peekLast()+x);
        bodyY.add(bodyY.peekLast()+y);
        return new int[]{bodyX.peekLast(),bodyY.peekLast()};
    }
    public int[] getHead()
    {
        return new int[] {bodyX.peekLast(),bodyY.peekLast()};
    }
}
