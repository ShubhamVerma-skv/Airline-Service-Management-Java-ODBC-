import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class login extends JFrame implements ActionListener
{
        JTextField txtusername;
        JLabel lbusername,lbpassword;
        JPasswordField pfpassword;
        JButton submit,cancel;
        ResultSet rs;
        Statement st;
        Connection cnn;
        public login()
        {
                setLayout(null);
                lbusername=new JLabel("UserName");
                lbpassword=new JLabel("Password");
                submit=new JButton("Submit");
                cancel=new JButton("Cancel");
                txtusername=new JTextField("");
                pfpassword=new JPasswordField("");
                submit.addActionListener(this);
                cancel.addActionListener(this);

                lbusername.setBounds(10,10,60,20);
                add(lbusername);
                txtusername.setBounds(80,10,100,20);
                add(txtusername);
                
                lbpassword.setBounds(10,30,60,20);
                pfpassword.setBounds(80,30,100,20);
                add(lbpassword);
                add(pfpassword);
                submit.setBounds(14,60,80,35);
                add(submit);
                cancel.setBounds(94,60,80,35);
                add(cancel);
                setSize(400,300);
                createconnection();
                show();
        }
        void createconnection()
        {
                try
                {
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        cnn=DriverManager.getConnection("jdbc:odbc:airline");
                        st=cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                }
                catch(Exception e1)
                {
                        JOptionPane.showMessageDialog(this,e1.toString(),"airline",0);
                }
        }
        public void actionPerformed(ActionEvent e)
        {
		try
                {       
                        String code1 = txtusername.getText();
                        String sql1= "select username from login";
                        rs = st.executeQuery(sql1);
                        rs.next();
                        String h1;
                        h1 = rs.getString(1);
                        





                        String code = pfpassword.getText();
                        String sql = "select password from login";
                        rs = st.executeQuery(sql);
                        rs.next();
                        String h;
                        h = rs.getString(1);
                        if((e.getSource()==submit)&(e.getSource()==submit))
                	{
                                if((h.equals(code))&(h1.equals(code1)))
				{
                                        JOptionPane.showMessageDialog(this,"password","Password Matched",0);
                                        new MDI();
                                        this.dispose();
				}
				else 
				{
                                        JOptionPane.showMessageDialog(this,"password","You Entered Wrong Password",0);
				}
			}
                        else if(e.getSource()==cancel)
			{	
				System.exit(0);
			}
                        
		}

            /*    try
                {
                        if(e.getSource()==submit)
                        {
                                String username = txtusername.getText();
                                String pass=pfpassword.getText();
                                
                        
                                if(username.equals("shubham"))
				{
                                        if(pass.equals("123"))
                                        {

                                         new MDI();
                                        }
                                        else
                                        {
                                                JOptionPane.showMessageDialog(this,"password","Password MisMatched",0);
                                        }

                                }
				else 
				{
                                        JOptionPane.showMessageDialog(this,"username","Entered Wrong Username",0);
				}
			}
                        
                } */
                
                catch(Exception e1)
                {
                         JOptionPane.showMessageDialog(this,e1.toString(),"actionPerformed",0);     
                }
        }
        public static void main(String arg[])
        {
                
                new login();
        }
}
