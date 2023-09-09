package trans;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import javax.swing.*;
import addons.Reciept;
import homeui.HomeUI;

public class Transfer_UPI implements ActionListener{
	int avail_money;
    JFrame frame;JLabel upi_lab;JButton button;
    JLabel beni_lab;JLabel money_lab;JLabel remark_lab;
    JLabel header;JButton home_butn;
    int lab_x_axis,lab_y_axis,lab_height,lab_width;
    int field_x_axis,field_y_axis,field_height,field_width;
    int incremental_val;
    JTextField upi_field;JTextField beni_field;
    JTextField money_field;JTextField remark_field;
    String user;
    String beni_accno,sender_accno,time;
    public Transfer_UPI(String name){
        user=name;
    	frame=new JFrame("Transfer Amount");
        header=new JLabel("UPI MONEY TRANSFER");
        String cwd=System.getProperty("user.dir");
        String pic_url=cwd+"\\Imagesrc\\home.png";
        Icon icon=new ImageIcon(pic_url);
        home_butn=new JButton(icon);
        home_butn.setBounds(20,20,40,40);
        home_butn.addActionListener(this);
        home_butn.setActionCommand("home");        
        frame.add(home_butn);
        upi_lab=new JLabel("UPI ID");
        beni_lab=new JLabel("Benificiary");
        money_lab=new JLabel("Money");
        remark_lab=new JLabel("Remark");
        button=new JButton("Submit");
        upi_field=new JTextField();
        beni_field=new JTextField();
        money_field=new JTextField();
        remark_field=new JTextField();
        lab_x_axis=200;lab_y_axis=80;
        lab_height=30;lab_width=200;
        field_x_axis=350;field_y_axis=80;
        field_height=30;field_width=200;
        incremental_val=60;
        header.setBounds(300,15,400,55);        
        upi_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        upi_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;        
        beni_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        beni_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;
        money_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        money_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;
        remark_lab.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        remark_field.setBounds(field_x_axis,field_y_axis,field_width,field_height);field_y_axis=lab_y_axis;
        button.setBounds(field_x_axis,field_y_axis,lab_width,lab_height);
        button.addActionListener(this);
        frame.setSize(800,500);
        frame.add(header);
        frame.add(upi_lab);
        frame.add(upi_field);
        frame.add(remark_lab);
        frame.add(remark_field);
        frame.add(money_lab);
        frame.add(money_field);
        frame.add(beni_lab);
        frame.add(beni_field);
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
            String beni_name=beni_field.getText();
            String remark=remark_field.getText();
            String upi_id=upi_field.getText();
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
	     	PreparedStatement cpst=c.prepareStatement("select * from bank where User_id=?");
            cpst.setString(1,upi_id);
     	    ResultSet crs=cpst.executeQuery();
     	    
     	    while(crs.next())
	      	   {
     	    	beni_accno=crs.getString("Acc_no");
	      	   avail_money=Integer.parseInt(crs.getString("avail_money"))+money;
	      	   }
	     	 cpst.close();
	     	 
	     	 PreparedStatement cst=c.prepareStatement("update bank set avail_money=? where User_id=?");
	     	 cst.setString(1,""+avail_money);
	     	 cst.setString(2,upi_id);
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
	     	nst.setString(6,upi_id);
	     	nst.setString(7,beni_accno);
	     	 nst.executeUpdate();
	     	 nst.close();
	     	 nc.close();
     	    
	         }
            catch(Exception ex){
	            System.out.println(ex);}
            frame.dispose();
            new Reciept("this account "+upi_id,money_field.getText()+" Rupees",user);
       }
       else if(e.getActionCommand()=="home"){
           frame.dispose();
    	   new HomeUI(user);
       }
       
    }
    public static void main(String args[]){
        new Transfer_UPI("iam_spdarsan");
    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}