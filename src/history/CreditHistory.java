package history;
//package under history.
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import homeui.HomeUI;

public class CreditHistory implements ActionListener{
    JFrame frame;
    Object[][] data;
    String acc_no1="",recv_from1="",amount1="",time1="",remark1="";
    String acc_no2="",recv_from2="",amount2="",time2="",remark2="";
    String acc_no3="",recv_from3="",amount3="",time3="",remark3="";
    String acc_no4="",recv_from4="",amount4="",time4="",remark4="";
    String user;
    public CreditHistory(String name){
        user=name;
    	JButton button=new JButton("OK");
        String[] index = new String[] { "Account Number", "Recieved from","Amount", "Time",
        "Remark"  };
        frame = new JFrame("Transaction History");
        JLabel label = new JLabel("Credit History", JLabel.CENTER);
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String cwd=System.getProperty("user.dir");
            String con_url="jdbc:ucanaccess://"+cwd+"\\Database\\creditdb.accdb";
            Connection c= DriverManager.getConnection(con_url);     
            String qry="select * from transdata where beni_name=?";
            PreparedStatement pst=c.prepareStatement(qry);
            pst.setString(1,name);
            ResultSet rs=pst.executeQuery();    
     	 int a=0;
     	 while(rs.next())
      	   {a+=1;
      	   if(a==1) {
      		 data = new Object[][] {
             	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
             { rs.getString("acc_no"), rs.getString("recv_from"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
             acc_no1=rs.getString("acc_no");
             recv_from1=rs.getString("recv_from");
             amount1=rs.getString("amount");
             time1=rs.getString("time");
             remark1=rs.getString("remark");
      	   }
      	   else if(a==2) {
      		 data = new Object[][] {
              	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
              	{acc_no1,recv_from1,amount1,time1,remark1},
              { rs.getString("acc_no"), rs.getString("recv_from"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
              acc_no2=rs.getString("acc_no");
              recv_from2=rs.getString("recv_from");
              amount2=rs.getString("amount");
              time2=rs.getString("time");
              remark2=rs.getString("remark");
      	   }
      	   else if(a==3) {
      		 data = new Object[][] {
               	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
               	{acc_no1,recv_from1,amount1,time1,remark1},
               	{acc_no2,recv_from2,amount2,time2,remark2},
               { rs.getString("acc_no"), rs.getString("recv_from"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
               acc_no3=rs.getString("acc_no");
               recv_from3=rs.getString("recv_from");
               amount3=rs.getString("amount");
               time3=rs.getString("time");
               remark3=rs.getString("remark");
      	   }
      	 else if(a==4) {
      		 data = new Object[][] {
               	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
               	{acc_no1,recv_from1,amount1,time1,remark1},
               	{acc_no2,recv_from2,amount2,time2,remark2},
               	{acc_no3,recv_from3,amount3,time3,remark3},
               { rs.getString("acc_no"), rs.getString("recv_from"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
               acc_no4=rs.getString("acc_no");
               recv_from4=rs.getString("recv_from");
               amount4=rs.getString("amount");
               time4=rs.getString("time");
               remark4=rs.getString("remark");
      	   }
      	else if(a==5) {
     		 data = new Object[][] {
              	{ "Account Number", "Recieved from","Amount", "Time", "Remark"  },
              	{acc_no1,recv_from1,amount1,time1,remark1},
              	{acc_no2,recv_from2,amount2,time2,remark2},
              	{acc_no3,recv_from3,amount3,time3,remark3},
              	{acc_no4,recv_from4,amount4,time4,remark4},
              { rs.getString("acc_no"), rs.getString("recv_from"),rs.getString("amount"),rs.getString("time"),rs.getString("remark")},};
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
        new CreditHistory("jishnuar_ebi");
    }
     public static void centreWindow(Window frame) {
         Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
         int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
         int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
         frame.setLocation(x, y);
     }
}