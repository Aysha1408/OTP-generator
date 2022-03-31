package miniproject;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/*<APPLET code = "SendOtpSMS.class" WIDTH=500 HEIGHT =500></APPLET>*/
import javax.swing.*;
public class SendOtpSMS extends JFrame implements ActionListener{
	String otpStr=generateOtp();
	String phno;
    String to;
	Date d1,d2;
	JFrame f;
	JButton btn,b,b1,b2,b3,b4;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JPanel p;
	JTextField tf1,tf2,tf3,tf4;
	Font font = new Font("SansSerif", Font.BOLD, 14); 
	public SendOtpSMS()
	{
		setTitle("Amazoff SignIn Page");
		f=new JFrame();
		btn=new JButton("Next");
		btn.addActionListener(this);
		tf1=new JTextField(30);
		tf2=new JTextField(30);
		tf3=new JTextField(30);
		tf4=new JTextField(30);
		btn.setBackground(Color.white);
		btn.setForeground(Color.black);
		l1=new JLabel("ENTER NAME");
		l1.setForeground(Color.white);
		l1.setFont(font);
		l2=new JLabel("ENTER PHONENUMBER");
		l2.setForeground(Color.white);
		l2.setFont(font);
		l7=new JLabel("ENTER EMAIL ADDRESS");
		l7.setForeground(Color.white);
		l7.setFont(font);
		l3=new JLabel("ENTER OTP");
		l3.setForeground(Color.white);
		l3.setFont(font);
		l4=new JLabel(" ");
		l5=new JLabel(" ");
		b=new JButton("SUBMIT OTP");
		b.setBackground(Color.white);
		b.setForeground(Color.black);
		b.addActionListener(this);
		p=new JPanel();
		p.setBackground(Color.DARK_GRAY);
		p.setForeground(Color.white);
		p.setLayout(new GridLayout(4,4));
		p.add(l1);
		p.add(tf1);
		p.add(l2);
		p.add(tf2);
		p.add(l7);
		p.add(tf4);
		p.add(l4);
		p.add(btn);
		add(new JLabel("OTP"),"North");
		add(p,"Center");
		//add(btn,"South");	
		setSize(200,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		public void actionPerformed(ActionEvent e)
		{
			
			
			String name=tf1.getText();
			if(e.getSource()==btn)
			{	
				
				try
				{
					if(tf1.getText().isEmpty())
					{

				JOptionPane.showMessageDialog(this,"Please Enter valid details");
					}
					else if(!(isNumeric(tf2.getText())))
					{
					JOptionPane.showMessageDialog(this,"Please Enter valid Phone Number");
					}
					else {
						p.removeAll();
						p.setLayout(new FlowLayout(FlowLayout.CENTER));
						p.setBackground(Color.DARK_GRAY);
						l6=new JLabel("Choose your preference:");
						l6.setForeground(Color.white);
						l6.setFont(font);
						p.add(l6);
						b2=new JButton("OTP through SMS");
						b2.setBackground(Color.white);
						b2.setForeground(Color.black);
						b2.addActionListener(this);
						p.add(b2);
						b3=new JButton("OTP through mail");
						b3.setBackground(Color.white);
						b3.setForeground(Color.black);
						b3.addActionListener(this);
						p.add(b3);
						
					}
				}
				catch(Exception ex)
				{
					
					JOptionPane.showMessageDialog(this,"Error!!");
				}
					
			}
			if(e.getSource()==b2) {

				otpStr=generateOtp();
			System.out.println("Generated OTP="+otpStr);
				phno=tf2.getText();
				SendOtpSMS.sendSMS(otpStr,phno);
				JOptionPane.showMessageDialog(this,"The OTP has been sent to "+phno+".The OTP is valid for 1 min. Please Enter the OTP to confirm your identity.");
				p.removeAll();
				p.setLayout(new FlowLayout(FlowLayout.CENTER));
				p.setBackground(Color.DARK_GRAY);
				//p.setSize(300,300);
				//p.setVisible(true);
				b1=new JButton("Regenerate OTP");
				b1.setBackground(Color.white);
				b1.setForeground(Color.black);
				p.add(l3);
				p.add(tf3);
				p.add(b1);
				b1.addActionListener(this);
				p.add(b);
				d1 = new Date();
                System.out.println(d1);				
				l5=new JLabel(d1.toString()); 
				l5.setForeground(Color.white);
				l5.setFont(font);
				p.add(l5);
				}
			if(e.getSource()==b3) {
				    otpStr=generateOtp();
				    System.out.println("Generated OTP="+otpStr);
					to=tf4.getText();
					SendOtpSMS.sendMail(to,otpStr);
					JOptionPane.showMessageDialog(this,"The OTP has been sent to "+to+".The OTP is valid for 1 min. Please Enter the OTP to confirm your identity.");
					p.removeAll();
					p.setLayout(new FlowLayout(FlowLayout.CENTER));
					p.setBackground(Color.DARK_GRAY);
					//p.setSize(300,300);
					//p.setVisible(true);
					b4=new JButton("Regenerate OTP");
					b4.setBackground(Color.white);
					b4.setForeground(Color.black);
					p.add(l3);
					p.add(tf3);
					p.add(b4);
					b4.addActionListener(this);
					p.add(b);
					d1 = new Date();
	                System.out.println(d1);				
					l5=new JLabel(d1.toString()); 
					l5.setForeground(Color.white);
					l5.setFont(font);
					p.add(l5);
				
			}
		     if(e.getSource()==b)
		    {
				 
                d2 = new Date();
				System.out.println((d2.getTime()-d1.getTime())/1000);
				if(((d2.getTime()-d1.getTime())/1000)>60)
				{
				JOptionPane.showMessageDialog(this,"TIME'S UP!!THE OTP IS INVALID ! PLEASE RE-GENERATE OTP");
				}
				else
				{
				String verify=tf3.getText();
				System.out.println(verify);
				if(verify.equals(otpStr))
				{
					//JOptionPane.showMessageDialog(this,"Welcome "+name+" !!");
					p.removeAll();
					p.setBackground(Color.white);
					p.add(new JLabel("Welcome "+name+" !! You Have Successfully Signed In!"));
				}
				else{
				JOptionPane.showMessageDialog(this,"WRONG OTP!!PLEASE RE-GENERATE OTP");
		    			}
				}
			
		}	
		     if(e.getSource()==b1)
		     {   
		    	 d1 = new Date();
			    p.remove(l5);
			    l5=new JLabel(d1.toString());  
				p.add(l5);
				tf3.setText("");
		    	otpStr=generateOtp();
				System.out.println("Generated OTP="+otpStr);
		    	SendOtpSMS.sendSMS(otpStr,phno);
				JOptionPane.showMessageDialog(this,"The OTP has been sent to "+phno+".The OTP is valid for 1 min. Please Enter the OTP to confirm your identity.");
		     }
		     if(e.getSource()==b4)
		     {  d1 = new Date();
			    p.remove(l5);
			    l5=new JLabel(d1.toString());  
				p.add(l5);
				tf3.setText("");
		    	otpStr=generateOtp();
				System.out.println("Generated OTP="+otpStr);
				SendOtpSMS.sendMail(to,otpStr);
				JOptionPane.showMessageDialog(this,"The OTP has been sent to "+to+".The OTP is valid for 1 min. Please Enter the OTP to confirm your identity.");
		     }
		}
		public static boolean isNumeric(String str) {
 
		if (str == null || str.length()!=10) {
			return false;
		}
 
		try {
			Double.parseDouble(str);
			return true;
 
		} catch (NumberFormatException e) {
			return false;
		}
 
	}

	public static void main(String[] args) {
		new SendOtpSMS();
		// TODO Auto-generated method stub
       // String otpStr=SendOtpSMS.generateOtp();
        //System.out.println("Generated OTP="+otpStr);
       // SendOtpSMS.sendSMS(otpStr);
	}
public static void sendSMS (String otpStr,String phno) {
	  try {
	   
	   String apiKey = "apiKey=" + "7fGpN/m1j3g-E1MB4PXB4Bbi2cLjbDVS3KQgUESCpV";
	   
	   String message = "&message=" + URLEncoder.encode("Your Amazoff OTP Code is " + otpStr+ ".Code is valid for 1 minute only,one time use.Please DO NOT share this OTP with anyone.",
	     "UTF-8");
	   
	   String numbers = "&numbers=" + phno;
	   
	   String apiURL = "https://api.textlocal.in/send/?" + apiKey + message + numbers;
	   
	   URL url = new URL(apiURL);
	   URLConnection connection = url.openConnection();
	   connection.setDoOutput(true);
	   
	   BufferedReader reader = new BufferedReader(new 
	     InputStreamReader(connection.getInputStream()));
	   
	   String line = "";
	   StringBuilder sb = new StringBuilder();
	   
	   while ( (line = reader.readLine()) != null) {
	    sb.append(line).append("\n");
	   }
	   
	   System.out.println(sb.toString());
	   
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
public static String generateOtp()
{
	//generates pin between the range 10000 to 90000
	int Pin=(int)(10000+Math.random()*90000);
	String otp  =String.valueOf(Pin);
	return otp; 
}
public static void sendMail(String to,String otpStr)
{
	  String from = "info.amazoff@gmail.com"; // from address. As this is using Gmail SMTP your from address should be gmail
	  String password = "Amazoff123"; // password for from gmail address that you have used in above line. 
	  Properties prop = new Properties();
	  prop.put("mail.smtp.host", "smtp.gmail.com");
	  prop.put("mail.smtp.port", "465");
	  prop.put("mail.smtp.auth", "true");
	  prop.put("mail.smtp.socketFactory.port", "465");
	  prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
	   protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(from, password);
	   }
	  });

	  try {

	   Message message = new MimeMessage(session);
	   message.setFrom(new InternetAddress(from));
	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	   message.setSubject("Amazoff Account - Verification Code");
	   message.setText("Hi,\n\n Greetings!\n\n You are a step away from accessing your Amazoff account.\nWe are sharing a verification code to access your account. The code is valid for 1 minute and for one time use only.\n Once you have verified the code, you'll be forwarded to main page.\n Your OTP: "+otpStr+"\n\n\nBest Regards,\nTeam Amazoff");
	   Transport.send(message);
	   System.out.println("Mail Sent...");

	  } catch (MessagingException e) {
	   e.printStackTrace();
	  }
}
}


