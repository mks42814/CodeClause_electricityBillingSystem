package electricitybillingsystem;

/**
 *
 * @author Mintu's PC
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ViewInformation extends JFrame implements ActionListener {

    JButton cancel;
    String meter;
    ViewInformation(String meter) {
        super("View User Information");    //frame title
        this.meter = meter;

        setBounds(550, 250, 800, 480);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tohoma", Font.PLAIN, 20));
        add(heading);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);
        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);

        JLabel lblmeter = new JLabel("Meter Number:");
        lblmeter.setBounds(70, 140, 100, 20);
        add(lblmeter);
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 140, 100, 20);
        add(meternumber);

        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);
        JLabel address = new JLabel("");
        address.setBounds(250, 200, 200, 20);
        add(address);

        JLabel lblcity = new JLabel("City:");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);
        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);

        JLabel lblstate = new JLabel("State:");
        lblstate.setBounds(450, 80, 100, 20);
        add(lblstate);
        JLabel state = new JLabel("");
        state.setBounds(590, 80, 100, 20);
        add(state);

        JLabel lblemail = new JLabel("Email ID:");
        lblemail.setBounds(450, 140, 100, 20);
        add(lblemail);
        JLabel email = new JLabel("");
        email.setBounds(590, 140, 150, 20);
        add(email);

        JLabel lblphone = new JLabel("Phone Number:");
        lblphone.setBounds(450, 200, 100, 20);
        add(lblphone);
        JLabel phone = new JLabel("");
        phone.setBounds(590, 200, 100, 20);
        add(phone);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "' ");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                meternumber.setText(rs.getString("meter_no"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Close");
        cancel.setBackground(Color.DARK_GRAY);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(600, 340, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/viewcust.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(70, 130, 600, 300);
        add(image);

        setVisible(true);
    }
    
    
     public void actionPerformed(ActionEvent ae){
       if(ae.getSource() == cancel){
            setVisible(false);
            
        }
    }
    
    

    public static void main(String args[]) {

        new ViewInformation("");
    }
}
