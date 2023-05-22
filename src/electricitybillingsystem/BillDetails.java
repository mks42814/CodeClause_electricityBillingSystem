
package electricitybillingsystem;
/**
 *
 * @author Mintu's PC
 */
import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;

public class BillDetails extends JFrame{
    
    
        
    BillDetails(String meter){
        super("Bill Status");    //frame title
        setSize(700,650);
        setLocation(600,250);
        
        getContentPane().setBackground(Color.WHITE);
        
        JTable table = new JTable();
        
        try{
            Conn c = new Conn();
            String query = "select * from bill where meter_no = '"+meter+"' ";
            ResultSet rs = c.s.executeQuery(query);
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);
        
        setVisible(true);
        
    }
    
    
    
    public static void main(String args[]) {

        new BillDetails("");
    }
    
}
