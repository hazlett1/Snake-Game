import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class Buttons {
    
    private ArrayList <JButton> buttons;
    private int speed = -1;
    private SnakeData data = new SnakeData();
    private boolean Ludy = false;
    
    public Buttons(){
        
        buttons = new ArrayList<>();
        buttons.add(new JButton("New Game"));
        buttons.add(new JButton("Beginner"));
        buttons.add(new JButton("Normal"));
        buttons.add(new JButton("Master"));
        buttons.add(new JButton("Expert"));
        buttons.add(new JButton("Ludicrous"));
        buttons.add(new JButton("Choose Speed"));
        
    }
    public ArrayList<JButton> getButtons(){
        return buttons;
    }
    public int getSpeed(){
        int xButt = 290;
        buttons.get(1).addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                speed = 25;
                setNotVis();
                Ludy = false;
            }
        });
        buttons.get(2).addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                speed = 15;
                setNotVis();
                Ludy = false;
            }
        });
        buttons.get(3).addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                speed = 12;
                setNotVis();
                Ludy = false;
            }
        });
        buttons.get(4).addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                speed = 6;
                setNotVis();
                Ludy = false;
            }
        });
        buttons.get(5).addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                speed = 3;
                setNotVis();
                Ludy = true;
            }
        });
        for(int i = 1; i < 6; i++){
            buttons.get(i).setBounds(890, xButt, 100, 25);
            xButt+=30;
        }
        return speed;
    }
    public boolean getLudy(){
        return Ludy;
    }
    public void setNotVis(){
        for(int i = 1; i < 6; i++){
            buttons.get(i).setVisible(false);
        }
    }
    public void setVis(){
        for(int i = 1; i < 6; i++){
            buttons.get(i).setVisible(true);
        }
    }
}
