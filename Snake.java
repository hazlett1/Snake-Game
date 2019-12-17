import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Snake extends JPanel{
    
    private final int PPS = 20;
    private boolean up, down, right, left, Debounce;
    private int count = 0, speed = -1, xButt = 270;    
    private Font font;
    private Buttons butts;
    private SnakeData data = new SnakeData();
    
    
    public Snake(){
        
        Mover keys = new Mover();
        addKeyListener (keys);
        setFocusable(true);
        setBackground(Color.GREEN);
        butts = new Buttons();
        for(int i = 0; i < 7; i++){
            add(butts.getButtons().get(i));
            butts.getButtons().get(i).setVisible(true);
        }
        butts.getButtons().get(0).setVisible(false);
        butts.getButtons().get(6).setVisible(false);
        
        font = new Font("Roman", Font.ROMAN_BASELINE, 22);
        
        
    }
    public void setGame(Graphics2D g2){
        
        g2.setColor(Color.BLACK);
        g2.fillRect(20, 20, 1860, 840);
        
    }
    public void apple(Graphics2D g2){
        
        if(data.x == -1 && data.y == -1)
            data.rnd();
        g2.setColor(Color.RED);
        g2.fillRect(data.x, data.y, 15, 15);
        
    }
    public void ate(){
        
        if(data.getBody().get(data.getBody().size()-1).x == data.x && data.getBody().get(data.getBody().size()-1).y == data.y){
            data.grow(right, left, up, down);
            data.rnd();
        }
    }
    public void crash(){
        butts.getButtons().get(0).setVisible(true);
        butts.getButtons().get(0).setBounds(880, 390, 120, 25);
        butts.getButtons().get(6).setVisible(true);
        butts.getButtons().get(6).setBounds(880, 420, 120, 25);
        butts.getButtons().get(0).addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                data.resetBody();
                butts.getButtons().get(0).setVisible(false);
                butts.getButtons().get(6).setVisible(false);
            }
        });
        butts.getButtons().get(6).addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                butts.setVis();
                data.resetBody();
                butts.getButtons().get(0).setVisible(false);
                butts.getButtons().get(6).setVisible(false);
            }
        });
    }
    @Override
    public void paintComponent(Graphics page){
       
        super.paintComponent(page);
        Graphics2D g2 = (Graphics2D) page; 
        if(!data.checkBoundry() || !data.checkBodyHit()){
            crash();
            down = false; right = false; left = false; up = false;
        }
        repaint();
        setGame(g2);
        apple(g2);
        ate();
        speed = butts.getSpeed();
        if(count % speed == 0){
            move();
            count = 0;
        }
        for(Point p : data.getBody()){
            if(butts.getLudy())
                g2.setColor(new Color((int)(Math.random() * 0x1000000)));
            
            else
                g2.setColor(Color.YELLOW);
            g2.fillRect(p.x, p.y,15, 15);
        }
        g2.setColor(Color.BLACK);
        g2.setFont(font);
        g2.drawString("Number of boxes: " + data.getBody().size(),1680, 900);
        count++;
    }
    public void move(){
        if(data.checkBoundry() && data.checkBodyHit()){
            if(Debounce == true){
                Debounce = false;
            }
            if(up){
                data.getBody().add(new Point(data.getBody().get(data.getBody().size()-1).x,data.getBody().get(data.getBody().size()-1).y - PPS));
                data.getBody().remove(0);
            }
            else if(down){
                data.getBody().add(new Point(data.getBody().get(data.getBody().size()-1).x,data.getBody().get(data.getBody().size()-1).y + PPS));
                data.getBody().remove(0);
            }
            else if(right){
                data.getBody().add(new Point(data.getBody().get(data.getBody().size()-1).x + PPS,data.getBody().get(data.getBody().size()-1).y));
                data.getBody().remove(0);
            }
            else if(left){
                data.getBody().add(new Point(data.getBody().get(data.getBody().size()-1).x - PPS,data.getBody().get(data.getBody().size()-1).y));
                data.getBody().remove(0);
            }
        }
    }
    private class Mover extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {}
            if(((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && down == false)&& Debounce == false){
               up = true;
               down = false; right = false; left = false;
               Debounce = true;
            }
            else if(((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S )&& up == false)&& Debounce == false){
                down = true;
                up = false; right = false; left = false;
                Debounce = true;
            }
            else if(((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && left == false)&& Debounce == false){
                right = true;
                up = false; down = false; left = false;
                Debounce = true;
            }
            else if(((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)&& right == false) && Debounce == false){
                left = true;
                up = false; down = false; right = false;
                Debounce = true;
            }
        }
    }
}
