package electricitybillingsystem;

/**
 *
 * @author Mintu's PC
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;


public class DepositDetails extends JFrame implements ActionListener{

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    DepositDetails() {
        super("Deposit Details || Admin Panel");
        setSize(700, 700);
        setLocation(550, 150);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblmeternumber = new JLabel("Search by meter number: ");
        lblmeternumber.setBounds(20, 20, 150, 20);
        add(lblmeternumber);
        meternumber = new Choice();
        meternumber.setBounds(180, 20, 150, 20);
        add(meternumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        JLabel lblmonth = new JLabel("Search by month: ");
        lblmonth.setBounds(400, 20, 110, 20);
        add(lblmonth);
        cmonth = new Choice();
            cmonth.setBounds(520, 20, 120, 20);
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
            add(cmonth);

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();

        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);
        
        
        
        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        //search.setBackground(Color.DARK_GRAY);
        search.setForeground(Color.BLUE);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        //print.setBackground(Color.DARK_GRAY);
        print.setForeground(Color.MAGENTA);
        print.addActionListener(this);
        add(print);
        

        setVisible(true);

    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            String query = "Select * from bill where meter_no = '"+meternumber+"' and month = '"+cmonth.getSelectedItem()+"' ";
            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
            }catch(Exception e){
                e.printStackTrace();
            }           
            
        }else if(ae.getSource() == print){
            try{
                table.print();
                
            } catch(Exception e){
                e.printStackTrace();
                
            }
        }
        
        
        
    }
    

    public static void main(String[] args) {
        new DepositDetails();
    }

}
