package trans;
import java.awt.*;
import addons.Reciept;
import homeui.HomeUI;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class Transfer_Acc implements ActionListener{
    JFrame frame;JLabel acc_lab;JButton button;
    JLabel ifsc_lab;JLabel money_lab;JLabel remark_lab;
    JLabel header;JLabel beni_lab; 
    int lab_x_axis,lab_y_axis,lab_height,lab_width;
    int field_x_axis,field_y_axis,field_height,field_width;
    int incremental_val;
    JTextField ifsc_field;JTextField acc_field;
    JTextField money_field;JTextField remark_field;
    JTextField beni_field;JButton home_butn;
    String user;
    String beni_id,sender_accno,time;
    int avail_money;
    public Transfer_Acc(String name){
    	user=name;
    	String cwd=System.getProperty("user.dir");
        String pic_url=cwd+"\\Imagesrc\\home.png";
        Icon icon=new ImageIcon(pic_url);
        home_butn=new JButton(icon);
        home_butn.setBounds(20,20,40,40);
        home_butn.addActionListener(this);
        home_butn.setActionCommand("home");        
        frame=new JFrame("Transfer Amount");
        frame.add(home_butn);
        header=new JLabel("MONEY TRANSFER");
        acc_lab=new JLabel("Account No");
        ifsc_lab=new JLabel("IFSC Code");
        money_lab=new JLabel("Money");
        remark_lab=new JLabel("Remark");
        beni_lab=new JLabel("Benificiary");
        button=new JButton("Submit");
        ifsc_field=new JTextField();
        acc_field=new JTextField();
        money_field=new JTextField();
        remark_field=new JTextField();
        beni_field=new JTextField();
        lab_x_axis=200;lab_y_axis=70;
        lab_height=30;lab_width=200;
        field_x_axis=350;field_y_axis=70;
        field_height=30;field_width=200;
        incremental_val=60;
        header.setBounds(300,15,400,55);        
        acc_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        acc_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;        
        ifsc_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        ifsc_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;
        money_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        money_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;
        beni_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        beni_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;        
        remark_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        remark_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;
        button.setBounds(field_x_axis,field_y_axis,lab_width,lab_height);
        button.addActionListener(this);
        frame.setSize(800,500);
        frame.add(header);
        frame.add(beni_lab);
        frame.add(beni_field);
        frame.add(acc_lab);
        frame.add(acc_field);
        frame.add(ifsc_lab);
        frame.add(ifsc_field);
        frame.add(remark_lab);
        frame.add(remark_field);
        frame.add(money_lab);
        frame.add(money_field);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreWindow(frame);

    }

    @SuppressWarnings("unused")
	@Override
    public void actionPerformed(ActionEvent e){
       if(e.getActionCommand()=="Submit"){
			int money=Integer.parseInt(money_field.getText());
    	    String acc_no=acc_field.getText();
			String remark=remark_field.getText();
			String ifsc=ifsc_field.getText();
			String beni=beni_lab.getText();
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
		     		 sender_accno=rs.getString("Acc_no");
		      	   avail_money=Integer.parseInt(rs.getString("avail_money"))-money;
		      	   }
		     	 pst.close();
		     	 
		     	 //deduct money from account
		     	 PreparedStatement st=c.prepareStatement("update bank set avail_money=? where User_id=?");
		     	 st.setString(1,""+avail_money);
		     	 st.setString(2,user);
		     	 st.executeUpdate();
		     	 st.close();
		     	 
		     	 //credit to beni_account
		     	PreparedStatement cpst=c.prepareStatement("select * from bank where Acc_no=?");
	            cpst.setString(1,acc_no);
	     	    ResultSet crs=cpst.executeQuery();
	     	    
	     	    while(crs.next())
		      	   {
	     	    	beni_id=crs.getString("User_id");
		      	   avail_money=Integer.parseInt(crs.getString("avail_money"))+money;
		      	   }
		     	 cpst.close();
		     	 
		     	 PreparedStatement cst=c.prepareStatement("update bank set avail_money=? where Acc_no=?");
		     	 cst.setString(1,""+avail_money);
		     	 cst.setString(2,acc_no);
		     	 cst.executeUpdate();
		     	 cst.close();
		     	 c.close();
		     	 
		     	 //making bill history
		     	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");  
		     	   LocalDateTime now = LocalDateTime.now();  
		     	   time=dtf.format(now);
		     	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		     	 String cwd1=System.getProperty("user.dir");
		            String con_url1="jdbc:ucanaccess://"+cwd1+"\\Database\\creditdb.accdb";
		            Connection nc= DriverManager.getConnection(con_url1); 
		     	 PreparedStatement nst=nc.prepareStatement("insert into transdata values(?,?,?,?,?,?,?)");
		     	 nst.setString(1,sender_accno);
		     	 nst.setString(2,user);
		     	nst.setString(3,""+money);
		     	nst.setString(4,time);
		     	nst.setString(5,remark);
		     	nst.setString(6,beni_id);
		     	nst.setString(7,acc_no);
		     	 nst.executeUpdate();
		     	 nst.close();
		     	 nc.close();
	     	    
		         }
	            catch(Exception ex){
		            System.out.println(ex);}
    	    frame.dispose();
            new Reciept("this account "+acc_no,money_field.getText()+" Rupees",user);
    }
      else if(e.getActionCommand()=="home"){
           frame.dispose();
    	   new HomeUI(user);
       }
       
    }
    public static void main(String args[]){
        new Transfer_Acc("jishnuar_ebi");
    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}