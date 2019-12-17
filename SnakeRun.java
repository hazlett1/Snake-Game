import javax.swing.JApplet;

public class SnakeRun extends JApplet{
    
     private final int WIDTH = 1920, HEIGHT = 920;
    
    public void init(){
        
        setSize (WIDTH, HEIGHT);
        getContentPane().add(new Snake());
        
    }    
}

