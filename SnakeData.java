import java.awt.Point;
import java.util.ArrayList;

public class SnakeData {
    
    private ArrayList<Point> body;
    public int x = -1, y = -1;
    private final int PPS = 20;
    
    public SnakeData(){
        
        body = new ArrayList<>();
        body.add(new Point(40,60));
        body.add(new Point(60,60));
        body.add(new Point(80,60));
        
    }
    public void rnd(){
        while(true){
            int xtemp = (int)(Math.random() * 780 + 10);
            if(xtemp % 20 == 0){
                x = xtemp;
                break;
            }
        }
        while(true){
            int ytemp = (int)(Math.random() * 780 + 10);
            if(ytemp % 20 == 0){
                y = ytemp;
                break;
            }
        }
    }
    public void grow(boolean right, boolean left, boolean up, boolean down){
        for(int i = 0; i < 5; i++){
            if(right)
               body.add(2, new Point(body.get(body.size()-1).x - PPS, body.get(body.size()-1).y));
            else if(left)
               body.add(2, new Point(body.get(body.size()-1).x + PPS, body.get(body.size()-1).y));
            else if(up)
               body.add(2, new Point(body.get(body.size()-1).x, body.get(body.size()-1).y + PPS));
            else if(down)
               body.add(2, new Point(body.get(body.size()-1).x, body.get(body.size()-1).y - PPS));
        }
    }
    public boolean checkBoundry(){
        Point p = body.get(body.size()-1);
        if((p.x < 1880 && p.x >= 10) && (p.y < 860 && p.y >= 10))
            return true;
        return false;
    }
    public boolean checkBodyHit(){
        for(int i = 0; i < body.size()-1; i++)
            if(body.get(body.size()-1).x == body.get(i).x && body.get(body.size()-1).y == body.get(i).y){
                return false;
            }
        return true;
    }
    public ArrayList<Point> getBody(){
        return body;
    }
    public void resetBody(){
        
        body.clear();
        body.add(new Point(40,60));
        body.add(new Point(60,60));
        body.add(new Point(80,60));
        
    }
}
