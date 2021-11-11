import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
public class passengers extends JPanel implements ActionListener
{
        JLabel lbid,lbpsng_no,lbticket_no,lbp_name,lbage,lbsex,lbprsnl_no,lbmeal_pref,lbidshow;
        JComboBox cbmeal_pref;
        JTextField txtid,txtpsng_no,txtticket_no,txtp_name,txtage,txtsex,txtprsnl_no;
        JButton btnadd,btnsave,btnnext,btnprevious,btnfirst,btnlast,btndelete,btnexit;
        JPanel pn,ps;
        String status="update";
        Connection cnn;
        Statement st;
        ResultSet rs;
        passengers()
        {
                setLayout(new BorderLayout());
                pn=new JPanel(new GridLayout(9,2));
                ps=new JPanel(new GridLayout(2,4));
                add("North",pn);
                add("South",ps);
                lbid=new JLabel("ID");
                lbpsng_no=new JLabel("Passanger_No");
                lbticket_no=new JLabel("Ticket_No");
                lbp_name=new JLabel("Passanger_Name");
                lbage=new JLabel("Age");
                lbsex=new JLabel("Gender");
                lbprsnl_no=new JLabel("Personal Number");
                lbmeal_pref=new JLabel("Meal Prefer");
                lbidshow=new JLabel("");
                txtpsng_no=new JTextField("");
                txtticket_no=new JTextField("");
                txtp_name=new JTextField("");
                txtage=new JTextField("");
                txtsex=new JTextField("");
                txtprsnl_no=new JTextField("");
                cbmeal_pref=new JComboBox();

                               
                btnadd=new JButton("Add");
                btnsave=new JButton("Save");
                btnnext=new JButton("Next");
                btnprevious=new JButton("Previous");
                btnfirst=new JButton("First");
                btnlast=new JButton("Last");
                btndelete=new JButton("Delete");
                btnexit=new JButton("Exit");
                btnadd.addActionListener(this);
                btnsave.addActionListener(this);
                btnnext.addActionListener(this);
                btnprevious.addActionListener(this);
                btnfirst.addActionListener(this);
                btnlast.addActionListener(this);
                btndelete.addActionListener(this);
                btnexit.addActionListener(this);

                pn.add(lbid);
                pn.add(lbidshow);
                pn.add(lbpsng_no);
                pn.add(txtpsng_no);
                pn.add(lbticket_no);
                pn.add(txtticket_no);
                pn.add(lbp_name);
                pn.add(txtp_name);
                pn.add(lbage);
                pn.add(txtage);
                pn.add(lbsex);
                pn.add(txtsex);
                pn.add(lbprsnl_no);
                pn.add(txtprsnl_no);
                pn.add(lbmeal_pref);
                pn.add(cbmeal_pref);

                ps.add(btnadd);
                ps.add(btnsave);
                ps.add(btnnext);
                ps.add(btnprevious);
                ps.add(btnfirst);
                ps.add(btnlast);
                ps.add(btndelete);
                ps.add(btnexit);
                pn.enable(true);

                createconnection();
                fillcombo();
                fillrecord();
                defaultrecord();
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
        void fillrecord()
        {
                try
                {
                        String s="select * from passengers";
                        rs=st.executeQuery(s);
                }
                catch(Exception e)
                {
                        JOptionPane.showMessageDialog(this,e.toString(),"airline",0);
                }
        }
      
        void defaultrecord()
        {
                try
                {
                        rs.first();
                        showrecord();
                }
                catch(Exception e1)
                {
                        JOptionPane.showMessageDialog(this,e1.toString(),"airline",0);
                }
        }     
        void showrecord()
        {
                try
                {
                        lbidshow.setText(String.valueOf(rs.getInt("Id")));
                        txtpsng_no.setText(rs.getString("psng_no"));
                        txtticket_no.setText(rs.getString("ticket_no"));
                        txtp_name.setText(rs.getString("p_name"));
                        txtage.setText(rs.getString("age"));
                        txtsex.setText(rs.getString("sex"));
                        txtprsnl_no.setText(rs.getString("prsnl_no"));
                        cbmeal_pref.setSelectedItem(rs.getString("meal_pref"));
                }
                catch(Exception e)
                {
                        JOptionPane.showMessageDialog(this,e.toString(),"airline",0);
                }
        }       
        void saverecord()
        {
                try
                {
                        String ac=txtpsng_no.getText();
                        String bc=txtticket_no.getText();
                        String cc=txtp_name.getText();
                        String dc=txtage.getText();
                        String ec=txtsex.getText();
                        String fc=txtprsnl_no.getText();
                        String gc=(String)cbmeal_pref.getSelectedItem();
                        
                        String s="insert into passengers(psng_no,ticket_no,p_name,age,sex,prsnl_no,meal_pref)values('"+ac+"','"+bc+"','"+cc+"','"+dc+"','"+ec+"','"+fc+"','"+gc+"')";
                        st.executeUpdate(s);
                        JOptionPane.showMessageDialog(this,"Record Sucessfully Saved","airline",0);
                        fillrecord();
                }
                catch(Exception e1)
                {
                        JOptionPane.showMessageDialog(this,e1.toString(),"airline",0);
                }
        }
        void updaterecord()
        {
                try
                {
                        int id=Integer.parseInt(lbidshow.getText());
                        String ac=txtpsng_no.getText();
                        String bc=txtticket_no.getText();
                        String cc=txtp_name.getText();
                        String dc=txtage.getText();
                        String ec=txtsex.getText();
                        String fc=txtprsnl_no.getText();
                        String gc=(String)cbmeal_pref.getSelectedItem();
                        
                        String s="update passengers set psng_no='"+ac+"',ticket_no='"+bc+"',p_name='"+cc+"',age='"+dc+"',sex='"+ec+"',prsnl_no='"+fc+"',meal_pref='"+gc+"' where id="+id;
                        st.executeUpdate(s);
                        JOptionPane.showMessageDialog(this,"Record Sucessfully updated","airline",2);
                        fillrecord();
                }
                catch(Exception e1)
                {
                        JOptionPane.showMessageDialog(this,e1.toString(),"airline",0);
                }
        }
        void deleterecord()
        {
                try
                {
                        if(rs.isLast())
                        {
                                btnnext.enable(false);
                                btnlast.enable(false);
                                JOptionPane.showMessageDialog(this,"Record not found","airline",0);
                        }
                        else
                        {
                                int id=Integer.parseInt(lbidshow.getText());
                                String s="delete from passengers where id="+id;
                                st.executeUpdate(s);
                                fillrecord();
                                rs.next();
                                showrecord();
                        }
                }
                catch(Exception e1)
                {
                        JOptionPane.showMessageDialog(this,e1.toString(),"airline",0);
                }
        }
        void fillcombo()
        {
                try
                {
                        String s="select mealname from mealmaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbmeal_pref.addItem(rs.getString("mealname"));
                        }
                                                           
                      
                }
                catch(Exception e)
                {
                        JOptionPane.showMessageDialog(this,e.toString(),"airline",0);
                }
        }
        public void actionPerformed(ActionEvent e)
        {
                try
                {
                        if(e.getSource()==btnfirst)
                        {
                                status="update";
                                rs.first();
                                showrecord();
                        }
                        else if(e.getSource()==btnlast)
                        {
                                status="update";
                                rs.last();
                                showrecord();
                        }
                        else if(e.getSource()==btnnext)
                        {
                                status="update";
                                if(rs.isLast())
                                {
                                        btnnext.enable(false);
                                        btnlast.enable(false);
                                        JOptionPane.showMessageDialog(this,"No More Records Found","airline",0);
                                }
                                else
                                {
                                        rs.next();
                                        showrecord();
                                }
                        }
                        else if(e.getSource()==btnprevious)
                        {
                                status="update";
                                if(rs.isFirst())
                                {
                                        btnprevious.enable(false);
                                        btnfirst.enable(false);
                                        JOptionPane.showMessageDialog(this,"No More Records Found","airline",0);
                                }
                                else
                                {
                                        rs.previous();
                                        showrecord();
                                }
                        }
                        else if(e.getSource()==btnadd)
                        {
                                status="save";
                                lbidshow.setText("");
                                txtpsng_no.setText("");
                                txtticket_no.setText("");
                                txtp_name.setText("");
                                txtage.setText("");
                                txtsex.setText("");
                                txtprsnl_no.setText("");
                                cbmeal_pref.setSelectedItem(rs.first());
                        }
                        else if(e.getSource()==btnsave)
                        {
                                if(status.equals("update"))
                                {
                                        updaterecord();
                                }
                                else
                                {
                                        saverecord();
                                }
                        }
                        else if(e.getSource()==btndelete)
                        {
                                deleterecord();
                        }
                        else if(e.getSource()==btnexit)
                        {
                                System.exit(1);
                        }
                }
                catch(Exception e1)
                {
                        JOptionPane.showMessageDialog(this,e1.toString(),"airline",0);
                }
        } 
        public static void main(String arr[])
        {
                JFrame f=new JFrame("Passengers");
                f.add(new passengers());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(400,250);
                f.show();
        }
}
