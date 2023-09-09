package major;
//this package under major
import javax.swing.*;

import homeui.HomeUI;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.*;

public class BalEnq implements ActionListener{
	public JFrame frame;
	int tot_bal,height,width,xval,xlab,ylab,incremental;
	String name,int_rate;
	public BalEnq(String user){
		name=user;
		try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String cwd=System.getProperty("user.dir");
            String con_url="jdbc:ucanaccess://"+cwd+"\\Database\\BankData.accdb";
            Connection c= DriverManager.getConnection(con_url);    
            PreparedStatement pst=c.prepareStatement("select * from bank where User_id=?");
            pst.setString(1,user);
     	    ResultSet rs=pst.executeQuery();  
    
     	    
     	 while(rs.next())
      	   {  
           tot_bal=rs.getInt("avail_money");
           int_rate=rs.getString("int_rate");
      	   }  
         }catch(Exception e){
            System.out.println(e);
        }
		height=20;width=170;
		xlab=90;ylab=60;
		incremental=30;
		xval=260;
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.white);
		frame.setBounds(100, 100, 450, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel bal_enq_lab = new JLabel("Balance Enquiry");
		bal_enq_lab.setVerticalAlignment(SwingConstants.TOP);
		bal_enq_lab.setForeground(Color.RED);
		bal_enq_lab.setFont(new Font("Times New Roman", Font.BOLD, 20));
		bal_enq_lab.setBounds(150,10,240,50);
		frame.getContentPane().add(bal_enq_lab);
		
		JLabel avail_withdrwl_lab = new JLabel("Avaliable Withdrawal");
		avail_withdrwl_lab.setFont(new Font("Times New Roman", Font.BOLD, 14));
		avail_withdrwl_lab.setForeground(Color.BLUE);
		avail_withdrwl_lab.setBounds(xlab,ylab,width,height);
		frame.getContentPane().add(avail_withdrwl_lab);
		
		int withdrawl_bal=tot_bal-1000;
		JLabel avail_withdrwl_val = new JLabel(":  \u20B9"+" "+withdrawl_bal);
		avail_withdrwl_val.setFont(new Font("Times New Roman", Font.BOLD, 14));
		avail_withdrwl_val.setBounds(xval,ylab,width,height);ylab+=incremental;
		frame.getContentPane().add(avail_withdrwl_val);
		
		JLabel ledgerbal_lab = new JLabel("Total Ledger Balance");
		ledgerbal_lab.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ledgerbal_lab.setForeground(Color.BLUE);
		ledgerbal_lab.setBounds(xlab,ylab,width,height);
		frame.getContentPane().add(ledgerbal_lab);
		
		JLabel ledgerbal_val = new JLabel(":  \u20B9"+" "+tot_bal);
		ledgerbal_val.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ledgerbal_val.setBounds(xval,ylab,width,height);ylab+=incremental;
		frame.getContentPane().add(ledgerbal_val);
		
		JLabel maintain_bal_lab = new JLabel("Minimum Maintain");
		maintain_bal_lab.setForeground(Color.BLUE);
		maintain_bal_lab.setFont(new Font("Times New Roman", Font.BOLD, 14));
		maintain_bal_lab.setBounds(xlab,ylab,width,height);
		frame.getContentPane().add(maintain_bal_lab);
		
		JLabel maintain_bal_val = new JLabel(":  \u20B9 1,000");
		maintain_bal_val.setFont(new Font("Times New Roman", Font.BOLD, 14));
		maintain_bal_val.setBounds(xval,ylab,width,height);ylab+=incremental;
		frame.getContentPane().add(maintain_bal_val);
		
		JLabel int_rate_lab = new JLabel("Interest");
		int_rate_lab.setForeground(Color.BLUE);
		int_rate_lab.setFont(new Font("Times New Roman", Font.BOLD, 14));
		int_rate_lab.setBounds(xlab,ylab,width,height);
		frame.getContentPane().add(int_rate_lab);
		
		JLabel int_rate_val = new JLabel(":  "+int_rate+" %");
		int_rate_val.setFont(new Font("Times New Roman", Font.BOLD, 14));
		int_rate_val.setBounds(xval,ylab,width,height);ylab+=incremental;
		frame.getContentPane().add(int_rate_val);
		
		JButton btnOk = new JButton("OK");
		btnOk.setForeground(Color.BLACK);
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.setVisible(true);
		btnOk.addActionListener(this);
		btnOk.setBounds(173, 200, 89, 23);
		frame.getContentPane().add(btnOk);
		centreWindow(frame);
	}
	@Override
	 public void actionPerformed(ActionEvent e){
	       if(e.getActionCommand()=="OK"){
	           frame.dispose();
	           new HomeUI(name);          
	       }
	    }
	public static void main(String args[]){
		new BalEnq("iam_spdarsan");
	}
	public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
