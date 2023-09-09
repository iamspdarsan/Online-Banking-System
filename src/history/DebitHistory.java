package history;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import homeui.HomeUI;

import java.awt.*;

public class DebitHistory implements ActionListener{
    JFrame frame;
    Object[][] data;
    String acc_no1="",beni_name1="",amount1="",time1="",remark1="";
    String acc_no2="",beni_name2="",amount2="",time2="",remark2="";
    String acc_no3="",beni_name3="",amount3="",time3="",remark3="";
    String acc_no4="",beni_name4="",amount4="",time4="",remark4="";
    String user;
    public DebitHistory(String name){
        user=name;
    	JButton button=new JButton("OK");
        String[] index = new String[] { "Account Number", "Recieved from","Amount", "Time",
        "Remark"  };
        frame = new JFrame("Transaction History");
        JLabel label = new JLabel("Debit History", JLabel.CENTER);
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String cwd=System.getProperty("user.dir");
            String con_url="jdbc:ucanaccess://"+cwd+"\\Database\\creditdb.accdb";
            Connection c= DriverManager.getConnection(con_url);    
            String qry="select * from transdata where recv_from=?";
            PreparedStatement pst=c.prepareStatement(qry);
            pst.setString(1,name);
            ResultSet rs=pst.executeQuery();    
     	 int a=0;
     	 while(rs.next())
      	   {a+=1;
      	   if(a==1) {
      		 data = new Object[][] {
             	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
             { rs.getString("beni_accno"), rs.getString("beni_name"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
             acc_no1=rs.getString("beni_accno");
             beni_name1=rs.getString("beni_name");
             amount1=rs.getString("amount");
             time1=rs.getString("time");
             remark1=rs.getString("remark");
      	   }
      	   else if(a==2) {
      		 data = new Object[][] {
              	{ "Account Number", "Sent to","Amount", "Time", "Remark"  },
              	{acc_no1,beni_name1,amount1,time1,remark1},
              { rs.getString("beni_accno"), rs.getString("beni_name"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
              acc_no2=rs.getString("beni_accno");
              beni_name2=rs.getString("beni_name");
              amount2=rs.getString("amount");
              time2=rs.getString("time");
              remark2=rs.getString("remark");
      	   }
      	   else if(a==3) {
      		 data = new Object[][] {
               	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
               	{acc_no1,beni_name1,amount1,time1,remark1},
               	{acc_no2,beni_name2,amount2,time2,remark2},
               { rs.getString("beni_accno"), rs.getString("beni_name"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
               acc_no3=rs.getString("beni_accno");
               beni_name3=rs.getString("beni_name");
               amount3=rs.getString("amount");
               time3=rs.getString("time");
               remark3=rs.getString("remark");
      	   }
      	 else if(a==4) {
      		 data = new Object[][] {
               	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
               	{acc_no1,beni_name1,amount1,time1,remark1},
               	{acc_no2,beni_name2,amount2,time2,remark2},
               	{acc_no3,beni_name3,amount3,time3,remark3},
               { rs.getString("beni_accno"), rs.getString("beni_name"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
               acc_no4=rs.getString("beni_accno");
               beni_name4=rs.getString("beni_name");
               amount4=rs.getString("amount");
               time4=rs.getString("time");
               remark4=rs.getString("remark");
      	   }
      	else if(a==5) {
     		 data = new Object[][] {
              	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
              	{acc_no1,beni_name1,amount1,time1,remark1},
              	{acc_no2,beni_name2,amount2,time2,remark2},
              	{acc_no3,beni_name3,amount3,time3,remark3},
              	{acc_no4,beni_name4,amount4,time4,remark4},
              { rs.getString("beni_accno"), rs.getString("beni_name"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
     	   }
      	else{
      		break;
      	}
           
      	   }  
         }catch(Exception e){
            System.out.println(e);
        }
        DefaultTableModel TableModel = new DefaultTableModel(data, index);
        JTable JTable = new JTable(TableModel);
        DefaultTableCellRenderer JTableCellRenderer = new DefaultTableCellRenderer();
        JTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        JTable.setDefaultRenderer(Object.class, JTableCellRenderer);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(JTable, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(label, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(button,BorderLayout.SOUTH);
        frame.setSize(800, 500);
        frame.setVisible(true);
        button.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreWindow(frame);
        }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="OK"){
           frame.dispose();
           new HomeUI(user);
       }
       
    }
     public static void main(String args[]){
        new DebitHistory("gowtham_ebi");
    }
     public static void centreWindow(Window frame) {
         Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
         int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
         int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
         frame.setLocation(x, y);
     }
}