
package electricitybillingsystem;

/**
 *
 * @author Mintu's PC
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Meterinfo extends JFrame implements ActionListener{
    
    //JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton submit;
    //Label lblmeter;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;
    
    Meterinfo(String meternumber){
        
        this.meternumber = meternumber;
        setSize(700,500);
        setLocation(400,200);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
            JLabel heading = new JLabel("Meter Information");
            heading.setBounds(180,10,200,20);
            heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
            p.add(heading);
            
             
            JLabel lblname = new JLabel("Meter Number: ");
            lblname.setBounds(100,80,100,20);           
            p.add(lblname);
            JLabel lblmeternumber = new JLabel(meternumber);
            lblmeternumber.setBounds(240,80,100,20);           
            p.add(lblmeternumber);
           
            
            JLabel lblmeterno = new JLabel("Meter Location: ");
            lblmeterno.setBounds(100,120,100,20);           
            p.add(lblmeterno);
            meterlocation = new Choice();
            meterlocation.add("Outside");
            meterlocation.add("Inside"); 
            meterlocation.setBounds(240,120,200,20);
            p.add(meterlocation);
            
            
            
            JLabel lbladdress = new JLabel("Meter Type: ");
            lbladdress.setBounds(100,160,100,20);           
            p.add(lbladdress);
            metertype = new Choice();
            metertype.add("Electric Meter");
            metertype.add("Solar Meter"); 
            metertype.add("Smart Meter"); 
            metertype.setBounds(240,160,200,20);
            p.add(metertype);
            
            JLabel lblstate = new JLabel("Phase Code: ");
            lblstate.setBounds(100,200,100,20);           
            p.add(lblstate);
            phasecode = new Choice();
            phasecode.add("011");
            phasecode.add("012"); 
            phasecode.add("013");
            phasecode.add("014");
            phasecode.add("015"); 
            phasecode.add("016"); 
            phasecode.add("017");
            phasecode.add("018"); 
            phasecode.add("019"); 
            phasecode.setBounds(240,200,200,20);
            p.add(phasecode);
            
            JLabel lblcity = new JLabel("Bill type: ");
            lblcity.setBounds(100,240,100,20);           
            p.add(lblcity);
            billtype = new Choice();
            billtype.add("Domestic");
            billtype.add("Industrial");            
            billtype.setBounds(240,240,200,20);
            p.add(billtype);
            
            JLabel lblemail = new JLabel("Days: ");
            lblemail.setBounds(100,280,100,20);           
            p.add(lblemail);
            JLabel lblemails = new JLabel("30 Days ");
            lblemails.setBounds(240,280,100,20);           
            p.add(lblemails);
            
            JLabel lblphone = new JLabel("Note: ");
            lblphone.setBounds(100,320,100,20);           
            p.add(lblphone);
            JLabel lblphones = new JLabel("By default bill is calculated for 30 days only.");
            lblphones.setBounds(140,320,500,20);           
            p.add(lblphones);
            
            
            submit = new JButton("Submit");
            submit.setBounds(220, 390, 100, 25);
            submit.setBackground(Color.WHITE);
            submit.setForeground(Color.GREEN);
            submit.addActionListener(this);
            p.add(submit);
            
            
            
            
            setLayout(new BorderLayout());
            add(p,"Center");
            
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/meter.png"));
            Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            add(image,  "West");
            
            getContentPane().setBackground(Color.WHITE);
            
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){          
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";
           
            
            String query1 = "insert into meter_info values('"+meter+"', '"+location+"', '"+type+"', '"+code+"', '"+typebill+"', '"+days+"')";
            
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
               
                JOptionPane.showMessageDialog(null, "Meter information added successfully");
                setVisible(false);             
               
                            
            } catch(Exception e){
                e.printStackTrace();
                
            }
            
            
        } else {
            setVisible(false);
            
        }
    }
    
    public static void main(String args[]) {
        new Meterinfo("");
    }
}
