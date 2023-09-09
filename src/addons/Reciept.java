package addons;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import homeui.HomeUI;

public class Reciept implements ActionListener{
    JFrame frame;JLabel lab1;JButton button;
    JLabel lab2;
    int lab_x_axis,lab_y_axis,lab_height,lab_width;
    int incremental_val;
    ImageIcon success;
    JLabel SuccessLabel;
    String user;
    public Reciept(String beni_ref,String amount,String name){
        user=name;
    	frame=new JFrame("Transfered");
    	frame.getContentPane().setBackground(Color.white);
        lab1=new JLabel("Successfully transfered "+amount);
        lab2=new JLabel("to "+beni_ref);
        button=new JButton("OK");
        lab_x_axis=40;lab_y_axis=20;
        lab_height=50;lab_width=300;
        incremental_val=30;
        lab1.setBounds(lab_x_axis,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;   
        lab2.setBounds(lab_x_axis+10,lab_y_axis,lab_width,lab_height);lab_y_axis+=incremental_val;
        button.setBounds(lab_x_axis+80,lab_y_axis+20,60,30);
        button.addActionListener(this);
        frame.setSize(300,200);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreWindow(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="OK") {
        	frame.dispose();
        	new HomeUI(user);}
        }
    public static void main(String args[]){
        new Reciept("darsan","111110101","");
    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}