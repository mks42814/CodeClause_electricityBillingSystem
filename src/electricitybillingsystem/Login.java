package electricitybillingsystem;

/**
 *
 * @author Mintu's PC
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JButton login, cancel, signup;
    JTextField username, password;
    Choice loggingin;
    
    Login(){
        super("Login || APDCL");    //frame title
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblcompany = new JLabel("Assam Power Distribution Corp. Ltd. (APDCL)");
        lblcompany.setForeground(Color.BLUE);
        lblcompany.setFont(new Font("Tohoma", Font.BOLD, 14));
        lblcompany.setBounds(40,10,320,20);
        add(lblcompany);
        
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(20,350,100,20);
        add(lblusername);
        username = new JTextField();
        username.setBounds(150,350,150,20);
        add(username);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(20,400,100,20);
        add(lblpassword);
        password = new JTextField();
        password.setBounds(150,400,150,20);
        add(password);
        
        JLabel loggininas = new JLabel("Login as");
        loggininas.setBounds(20,450,100,20);
        add(loggininas);
        loggingin = new Choice();
        loggingin.add("Admin");
        loggingin.add("Customer");
        loggingin.setBounds(150,450,150,20);
        add(loggingin);
        
        //buttons 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/login.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(i2));
        login.setBounds(50,550,100,20);
        login.addActionListener(this);
        add(login);
        
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("assets/cancel.png"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(200,550,100,20);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("assets/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup = new JButton("SignUp!",new ImageIcon(i6));
        signup.setBounds(50,600,100,20);
        signup.addActionListener(this);
        add(signup);
        
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("assets/userlogin.png"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(70,50,250,250);
        add(image);
               
                
        setSize(400, 700);
        setLocation(650, 200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            String susername = username.getText();
            String spassword = password.getText();
            String user = loggingin.getSelectedItem();
            
            try{
                Conn c = new Conn();
                String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and userType = '"+user+"' ";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user, meter);
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Credentials!\nPlease Check Your Input");
                    username.setText("");
                    password.setText("");
                }
                
            } catch(Exception e){
                e.printStackTrace();
                
            }
            
        }else if(ae.getSource() == cancel){
            setVisible(false);
            
        }else if(ae.getSource() == signup){
            setVisible(false);
            new SignUp();
            
        }
    }
    
    
    
    
 public static void main(String[] args) {
        new Login();
    }
    
}
