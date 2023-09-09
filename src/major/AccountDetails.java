package major;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
//this package is under major
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import homeui.HomeUI;

public class AccountDetails implements ActionListener
{
    JFrame frame;JLabel Account_number;
    JLabel Name_lab;JLabel Address_lab;
    JLabel DOB_lab;JLabel Account_Type;
    JLabel Ifsc_lab;JLabel Branch_Name;
    JButton button;    
    JLabel Account_number_val;JLabel Name_val;
    JLabel Address_val;JLabel Address_val2;JLabel DOB_val;
    JLabel Account_Type_val;JLabel Ifsc_val;
    JLabel Branch_Name_val;
    int lab_x_axis,lab_y_axis,lab_height,lab_width;
    int val_x_axis,val_height,val_width;
    int incremental_val;JButton home_butn;
    String user,Acc_no,username,address2,address,dob,acc_type,ifsc,branch;
       public AccountDetails(String name){
		user=name;
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
      	   Acc_no=rs.getString("Acc_no");
      	   username=rs.getString("Cust_name");
      	   address=rs.getString("Address");
      	   address2=rs.getString("Address2");
      	   dob=rs.getString("DOB");
      	   acc_type=rs.getString("Acc_type");
      	   ifsc=rs.getString("IFSC");
      	   branch=rs.getString("Branch");
      	   }  
         }catch(Exception e){
            System.out.println(e);}
		String cwd=System.getProperty("user.dir");
        String pic_url=cwd+"\\Imagesrc\\home.png";
        Icon icon=new ImageIcon(pic_url);
		home_butn=new JButton(icon);
		home_butn.setBounds(20,20,40,40);
		home_butn.addActionListener(this);
		home_butn.setActionCommand("home");        
		frame=new JFrame("Account details");
		frame.add(home_butn);
		Account_number=new JLabel("ACCOUNT NUMBER:");
		Account_number_val=new JLabel(Acc_no);
		Name_lab=new JLabel("NAME:");
		Name_val=new JLabel(username);
		Address_lab=new JLabel("ADDRESS:");
		Address_val=new JLabel(address);
		Address_val2=new JLabel(address2);
		DOB_lab=new JLabel("DATE OF BIRTH:");
		DOB_val=new JLabel(dob);
		Account_Type=new JLabel("ACCOUNT TYPE:");
		Account_Type_val=new JLabel(acc_type);
		Ifsc_lab=new JLabel("IFSC:");
		Ifsc_val=new JLabel(ifsc);
		Branch_Name=new JLabel("BRANCH NAME:");
		Branch_Name_val=new JLabel(branch);
		button=new JButton("OK");
		
		lab_x_axis=230;lab_y_axis=60;
		lab_height=30;lab_width=200;
		val_x_axis=420;
		val_height=30;val_width=200;
		incremental_val=35;
		     
		Account_number.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);
		Account_number_val.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val; 
		Name_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);
		Name_val.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val;
		DOB_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);
		DOB_val.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val;
		Branch_Name.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);
		Branch_Name_val.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val;
		Account_Type.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);
		Account_Type_val.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val;
		Ifsc_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);
		Ifsc_val.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val;
		Address_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);
		Address_val.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val;
		Address_val2.setBounds(val_x_axis,lab_y_axis,val_width,val_height);lab_y_axis+=incremental_val;
		button.setBounds(val_x_axis-100,lab_y_axis+40,60,40);;
		button.addActionListener(this);
		frame.setLayout(null);
		frame.setSize(800,500);
		frame.add(Account_number);
		frame.add( Account_number_val);
		frame.add(Name_lab);
		frame.add( Name_val);
		frame.add(Address_lab);
		frame.add( Address_val);
		frame.add(DOB_lab);
		frame.add( DOB_val);
		frame.add(Account_Type);
		frame.add(Account_Type_val);
		frame.add(Ifsc_lab);
		frame.add(Ifsc_val);
		frame.add(Branch_Name_val);
		frame.add(Branch_Name);
		frame.add(button);
		frame.add(Address_val2);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centreWindow(frame);
}
  public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="OK"){
           frame.dispose();
           new HomeUI(user);}
        else if(e.getActionCommand()=="home"){
            frame.dispose();
     	   new HomeUI(user);
        }
        
    }
     public static void main(String args[]){
        new AccountDetails("iam_spdarsan");
    }
     public static void centreWindow(Window frame) {
         Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
         int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
         int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
         frame.setLocation(x, y);
     }
}