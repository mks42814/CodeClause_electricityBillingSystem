
package electricitybillingsystem;

/**
 *
 * @author Mintu's PC
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class SignUp extends JFrame implements ActionListener {
    
    JButton create, back;
    Choice accounttype;
    JTextField meter, username, name, password;
    
    
    SignUp(){
        super("Signup || APDCL");    //frame title
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setBounds(650, 150, 450, 700);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(30,30,400,650);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);
        
        JLabel heading = new JLabel("I am referred to as ");
        heading.setBounds(10,300,150,20);
        heading.setForeground(Color.gray);
        heading.setFont(new Font("Tohoma", Font.BOLD, 14));
        panel.add(heading);
        
        accounttype = new Choice();
        accounttype.add("Admin");
        accounttype.add("Customer");
        accounttype.setBounds(200,300,150,20);
        panel.add(accounttype);      
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(10,350,140,20);
        lblmeter.setForeground(Color.gray);
        lblmeter.setFont(new Font("Tohoma", Font.BOLD, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        meter = new JTextField();
        meter.setBounds(200,350,150,20);
        meter.setVisible(false);
        panel.add(meter);       
       
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(10,400,140,20);
        lblusername.setForeground(Color.gray);
        lblusername.setFont(new Font("Tohoma", Font.BOLD, 14));
        panel.add(lblusername);
        username = new JTextField();
        username.setBounds(200,400,150,20);
        panel.add(username);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(10,450,450,20);
        lblname.setForeground(Color.gray);
        lblname.setFont(new Font("Tohoma", Font.BOLD, 14));
        panel.add(lblname);
        name = new JTextField();
        name.setBounds(200,450,150,20);
        panel.add(name);
        
        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe){
                
            }
            
            @Override
            public void focusLost(FocusEvent fe) {
                
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"' ");
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                        
                    }
                    
                } catch(Exception e){
                    e.printStackTrace();
                    
                }
            }
            
        });
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(10,500,140,20);
        lblpassword.setForeground(Color.gray);
        lblpassword.setFont(new Font("Tohoma", Font.BOLD, 14));
        panel.add(lblpassword);
        password = new JTextField();
        password.setBounds(200,500,150,20);
        panel.add(password);
        
        //to make meter no. field visible if selected customer
        accounttype.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                String user = accounttype.getSelectedItem();
                if (user.equals("Customer")) {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });
        
        create = new JButton("Create");
        create.setBackground(Color.DARK_GRAY);
        create.setForeground(Color.GREEN);
        create.setBounds(20, 570, 120, 25);
        create.addActionListener(this);
        panel.add(create);

        back = new JButton("Back");
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.YELLOW);
        back.setBounds(200, 570, 120, 25);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/signupimg.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(70, 10, 250, 250);
        panel.add(image);
        
        setVisible(true);
              
        
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == create){
            String atype = accounttype.getSelectedItem();
            String smeter = meter.getText();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            
            try{
                Conn c = new Conn();
                
                String query = null;
                if(atype.equals("Admin")){
                    query = "insert into login values('"+smeter+"', '"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
                
                }else{
                    query = "update login set username = '"+susername+"', password = '"+spassword+"', userType = '"+atype+"' where meter_no = '"+smeter+"' ";
                }
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();
                
            } catch(Exception e){
                e.printStackTrace();
            }
            
        } else if(ae.getSource() == back){
            setVisible(false);
            new Login();           
        }
    }
    
    
    
    
    public static void main(String[] args) {
        new SignUp();
    }
}
