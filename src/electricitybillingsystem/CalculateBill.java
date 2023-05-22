package electricitybillingsystem;

/**
 *
 * @author Mintu's PC
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
    
    JTextField tfname, tfaddress, tfunits, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblname, labeladdress;
    Choice meternumber, cmonth;
    
    CalculateBill(){
        super("Calculate Bill || Admin Panel");    //frame title
        setSize(700,500);
        setLocation(600,150);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
            JLabel heading = new JLabel("- Calculate Bill -");
            heading.setBounds(180,10,200,20);
            heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
            p.add(heading);
            
             
            JLabel lblmeternumber = new JLabel("Meter Number: ");
            lblmeternumber.setBounds(100,80,100,20);           
            p.add(lblmeternumber);
            meternumber = new Choice();            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from customer");
                while(rs.next()){
                    meternumber.add(rs.getString("meter_no"));
                }
                
            } catch(Exception e){                
                e.printStackTrace();
                
            }
            
            meternumber.setBounds(240,80,200,20);
            p.add(meternumber);
            
            JLabel lblmeterno = new JLabel("Name: ");
            lblmeterno.setBounds(100,120,100,20);           
            p.add(lblmeterno);
            lblname = new JLabel("");
            lblname.setBounds(240,120,100,20);           
            p.add(lblname);          
                        
            JLabel lbladdress = new JLabel("Address: ");
            lbladdress.setBounds(100,160,100,20);           
            p.add(lbladdress);
            labeladdress = new JLabel();
            labeladdress.setBounds(240,160,200,20);
            p.add(labeladdress);
            
             try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meternumber.getSelectedItem()+"' ");
                while(rs.next()){
                    lblname.setText(rs.getString("name"));
                    labeladdress.setText(rs.getString("address"));
                }
                
            } catch(Exception e){                
                e.printStackTrace();
                
            }
            
             meternumber.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie){
                   try{
                        Conn c = new Conn();
                        ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meternumber.getSelectedItem()+"' ");
                        while(rs.next()){
                            lblname.setText(rs.getString("name"));
                            labeladdress.setText(rs.getString("address"));
                        }   
                        
                    } catch(Exception e){                
                        e.printStackTrace();

                    } 
                } 
            });
             
            
            JLabel lblstate = new JLabel("Units Consumed: ");
            lblstate.setBounds(100,200,100,20);           
            p.add(lblstate);
            tfunits = new JTextField();
            tfunits.setBounds(240,200,200,20);
            p.add(tfunits);
            
            JLabel lblcity = new JLabel("Month: ");
            lblcity.setBounds(100,240,100,20);           
            p.add(lblcity);
            cmonth = new Choice();
            cmonth.setBounds(240,240,200,20);
            cmonth.add("January");
            cmonth.add("February");
            cmonth.add("March");
            cmonth.add("April");
            cmonth.add("May");
            cmonth.add("June");
            cmonth.add("July");
            cmonth.add("August");
            cmonth.add("September");
            cmonth.add("October");
            cmonth.add("Novenber");
            cmonth.add("Decenber");
            
            p.add(cmonth);          
                       
            
            next = new JButton("Submit");
            next.setBounds(120, 350, 100, 25);
            next.setBackground(Color.DARK_GRAY);
            next.setForeground(Color.GREEN);
            next.addActionListener(this);
            p.add(next);
            
            cancel = new JButton("Cancel");
            cancel.setBounds(250, 350, 100, 25);
            cancel.setBackground(Color.DARK_GRAY);
            cancel.setForeground(Color.YELLOW);
            cancel.addActionListener(this);
            p.add(cancel);
            
            
            setLayout(new BorderLayout());
            add(p,"Center");
            
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/calcbill.png"));
            Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            add(image,  "West");
            
            getContentPane().setBackground(Color.WHITE);
            
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){           
            String meter = meternumber.getSelectedItem();
            String units = tfunits.getText();
            String month = cmonth.getSelectedItem();          
            
            int totalbill = 0;
            int unit_consumed = Integer.parseInt(units);
            
            
            String query = "select * from tax";  
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);

                    while(rs.next()){
                        totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                        totalbill += Integer.parseInt(rs.getString("meter_rent"));
                        totalbill += Integer.parseInt(rs.getString("service_charge"));
                        totalbill += Integer.parseInt(rs.getString("service_tax"));
                        totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                        totalbill += Integer.parseInt(rs.getString("fixed_tax"));

                    }                

                } catch(Exception e){
                    e.printStackTrace();

                }
                
            String query2 = "insert into bill values('"+meter+"' ,  '"+month+"' , '"+units+"' , '"+totalbill+"', 'Not Paid' )";
                try {
                    Conn c = new Conn();
                    c.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Customer bill updated successfully");
                    setVisible(false);                  

                } catch(Exception e){
                    e.printStackTrace();

                }            
            
        } else {
            setVisible(false);
            
        }
    }
    
    public static void main(String args[]) {
        new CalculateBill();
    }
}
