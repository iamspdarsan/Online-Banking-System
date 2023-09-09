package major;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import homeui.HomeUI;
import trans.Transfer_UPI;
import trans.Transfer_Acc; 

public class Transfer_Main implements ActionListener{
    JFrame frame;JButton upi_trans;
    JButton bank_trans;JLabel header;
    int button_x_axis,button_y_axis,button_height,button_width;
    JButton home_butn;
    String user;
    public Transfer_Main(String name){
    	user=name;
        frame=new JFrame("Transfer Amount");
        String cwd=System.getProperty("user.dir");
        String pic_url=cwd+"\\Imagesrc\\home.png";
        Icon icon=new ImageIcon(pic_url);
        home_butn=new JButton(icon);
        home_butn.setBounds(20,20,40,40);
        home_butn.addActionListener(this);
        home_butn.setActionCommand("home");        
        frame.add(home_butn);
        header=new JLabel("Available Money Transfer methods");
        upi_trans=new JButton("UPI Transfer");
        bank_trans=new JButton("Bank Transfer");
        button_x_axis=210;button_y_axis=130;
        button_height=60;button_width=300;
        header.setBounds(260,70,400,55);
        upi_trans.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        button_y_axis+=100;
        bank_trans.setBounds(button_x_axis,button_y_axis,button_width,button_height);
        upi_trans.addActionListener(this);
        bank_trans.addActionListener(this);
        
        frame.setSize(800,500);
        frame.add(header);
        frame.add(upi_trans);
        frame.add(bank_trans);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreWindow(frame);

    }
    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getActionCommand()=="UPI Transfer"){
           frame.dispose();
          new Transfer_UPI(user);
        }
       else if(e.getActionCommand()=="home"){
           frame.dispose();
    	   new HomeUI(user);
       }
       
        else{
           frame.dispose();
           new Transfer_Acc(user); 
        }
    }
    public static void main(String args[]){
        new Transfer_Main("");
    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}