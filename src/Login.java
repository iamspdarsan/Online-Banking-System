import homeui.HomeUI;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
public class Login {

	private JFrame frame;
	private JTextField username;
	private JPasswordField passwordField;
	String pass;
	int height,width,xval,xlab,ylab,increment;
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		initialize();
	}
	private void initialize() {
		height=30;width=80;
		xlab=230;ylab=150;
		xval=350;increment=50;
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(xlab,ylab,width,height);
		frame.getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(xval,ylab,width+100,height);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		ylab+=increment;
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(xlab,ylab,width,height);
		frame.getContentPane().add(lblPassword);
	
		passwordField = new JPasswordField();
		passwordField.setBounds(xval,ylab,width+100,height);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String uname=username.getText();
				@SuppressWarnings("deprecation")
				String psd=passwordField.getText();
				try{
		            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		            String cwd=System.getProperty("user.dir");
		            String con_url="jdbc:ucanaccess://"+cwd+"\\Database\\BankData.accdb";
		            Connection c= DriverManager.getConnection(con_url);    
		            PreparedStatement pst=c.prepareStatement("select * from bank where User_id=?");
		            pst.setString(1,uname);
		     	    ResultSet rs=pst.executeQuery();  
		    
		     	    
		     	 while(rs.next())
		      	   {  
		      	   pass=rs.getString("pass");
		      	   }  
		     	if(pass.equals(psd))
				{	frame.dispose();
					new HomeUI(uname);
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"Wrong password");
				}
		         }catch(Exception e){
		            System.out.println(e);
		            JOptionPane.showMessageDialog(frame,"Invalid Username");
		        }
				
			}
		});
		btnNewButton.setBounds(340, 292, 117, 56);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().setBackground(Color.white);
		JLabel lblWelcomeToLog = new JLabel("Welcome To Excelsior Bank of India:)");
		lblWelcomeToLog.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcomeToLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToLog.setBounds(190, 50, 400, 35);
		lblWelcomeToLog.setForeground(Color.blue);
		frame.getContentPane().add(lblWelcomeToLog);
		centreWindow(frame);
	}
	public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
