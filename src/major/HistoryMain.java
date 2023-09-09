package major;
//this package under mojor
import homeui.HomeUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//user defined - dependencies
import history.CreditHistory;
import history.DebitHistory;

public class HistoryMain implements ActionListener{
    JFrame frame;JButton cr_button;
    JButton deb_button;JLabel header;
    int button_x_axis,button_y_axis,button_height,button_width;
    JButton home_butn;String user;

    public HistoryMain(String name){
    	user=name;
    	String cwd=System.getProperty("user.dir");
        String pic_url=cwd+"\\Imagesrc\\home.png";
        Icon icon=new ImageIcon(pic_url);
        home_butn=new JButton(icon);
        home_butn.setBounds(20,20,40,40);
        home_butn.addActionListener(this);
        home_butn.setActionCommand("home");        
        frame=new JFrame("Transaction History");
        frame.add(home_butn);
        header=new JLabel("Kindly choose any one of this");
        cr_button=new JButton("Credit History");
        deb_button=new JButton("Debit History");
        button_x_axis=250;button_y_axis=130;
        button_height=60;button_width=300;
        header.setBounds(320,70,400,55);
        cr_button.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        button_y_axis+=100;
        deb_button.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        cr_button.addActionListener(this);
        deb_button.addActionListener(this);
        
        frame.setSize(800,500);
        frame.add(header,BorderLayout.NORTH);
        frame.add(cr_button);
        frame.add(deb_button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreWindow(frame);

    }
    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getActionCommand()=="Credit History"){
           frame.dispose();
           new CreditHistory(user);
    
        }
       else if(e.getActionCommand()=="home"){
           frame.dispose();
    	   new HomeUI(user);
       }
        else{
            frame.dispose();
            new DebitHistory(user);
        }
    }
    public static void main(String args[]){
        new HistoryMain("iam_spdarsan");
    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}