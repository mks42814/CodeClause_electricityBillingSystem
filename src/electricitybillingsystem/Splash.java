
package electricitybillingsystem;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mintu's PC
 */
public class Splash extends JFrame implements Runnable {
    Thread t;
    Splash() {
         super("APDCL");    //frame title
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/APDCL.png"));
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        setVisible(true);        
       
            setSize(650,580);
            setLocation(550,250);
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        
        t = new Thread(this);
        t.start();
        
        setVisible(true);
    }
    
    public void run() {
        try {
            Thread.sleep(3000);
            setVisible(false);
            
            // login frame
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Splash();
    }
}