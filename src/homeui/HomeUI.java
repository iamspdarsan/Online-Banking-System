//this package name is homeui
package homeui;
import java.awt.Window;

import java.awt.Dimension;
import java.awt.Toolkit;
//user defined packages
import major.*;
import relog.Relog;

//inbuild packages
import java.awt.event.*;

import javax.swing.*;

public class HomeUI implements ActionListener{
    JFrame frame;JButton butn1;
    JButton butn3;JButton butn4;
    JButton butn2;JLabel header;
    JButton logout;
    int button_x_axis,button_y_axis,button_height,button_width;
    String user;
    public HomeUI(String name){
    	user=name;
        frame=new JFrame("Home");
        String cwd=System.getProperty("user.dir");
        String pic_url=cwd+"\\Imagesrc\\logout.png";
        Icon icon=new ImageIcon(pic_url);
        logout=new JButton(icon);
        logout.setActionCommand("logout");
        header=new JLabel("Choose any option");
        butn1=new JButton("Balance Enquiry");
        butn2=new JButton("Account details");
        butn3=new JButton("Transaction History");
        butn4=new JButton("Transfer amount");
        button_x_axis=200;button_y_axis=70;
        button_height=60;button_width=300;
        header.setBounds(300,15,400,55);
        logout.setBounds(700,20,40,40);
        butn1.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        button_y_axis+=100;
        butn2.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        button_y_axis+=100;
        butn3.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        button_y_axis+=100;
        butn4.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        butn1.addActionListener(this);
        butn2.addActionListener(this);
        butn3.addActionListener(this);
        butn4.addActionListener(this);
        logout.addActionListener(this);
        
        frame.setSize(800,500);
        frame.add(logout);
        frame.add(header);
        frame.add(butn1);
        frame.add(butn2);
        frame.add(butn3);
        frame.add(butn4);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreWindow(frame);

    }
    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getActionCommand()=="Balance Enquiry"){
           frame.dispose();
           new BalEnq(user);          
        }
        else if(e.getActionCommand()=="Account details"){
           frame.dispose();
           new AccountDetails(user);           
        }
        else if(e.getActionCommand()=="Transaction History"){
            frame.dispose();
            new HistoryMain(user);          
         }
         else if(e.getActionCommand()=="Transfer amount"){
            frame.dispose();
           new Transfer_Main(user);           
         }
         else if(e.getActionCommand()=="logout") {
        	 frame.dispose();
        	 new Relog();        
         }
    }
    public static void main(String args[]){
        new HomeUI("iam_spdarsan");
    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}