import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
public class flightdays extends JPanel implements ActionListener
{
        JLabel lbid,lbaircraftcode,lbdaycode,lbidshow;
        JComboBox cbaircraftcode,cbdaycode;
        JButton btnadd,btnsave,btnnext,btnprevious,btnfirst,btnlast,btndelete,btnexit;
        JPanel pn,ps;
        String status="update";
        Connection cnn;
        Statement st;
        ResultSet rs;
        flightdays()
        {
                setLayout(new BorderLayout());
                pn=new JPanel(new GridLayout(3,2));
                ps=new JPanel(new GridLayout(2,4));
                add("North",pn);
                add("South",ps);
                lbid=new JLabel("ID");
                lbaircraftcode=new JLabel("Aircraft Code");
                lbdaycode=new JLabel("Day Code");
                lbidshow=new JLabel("");
                cbaircraftcode=new JComboBox();
                cbdaycode=new JComboBox();
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
                pn.add(lbaircraftcode);
                pn.add(cbaircraftcode);
                pn.add(lbdaycode);
                pn.add(cbdaycode);
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
                        String s="select * from flightdays";
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
                        cbaircraftcode.setSelectedItem(rs.getString("aircraftcode"));
                        cbdaycode.setSelectedItem(rs.getString("daycode"));
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
                        String cc=(String)cbaircraftcode.getSelectedItem();
                        String cn=(String)cbdaycode.getSelectedItem();
                        String s="insert into flightdays(aircraftcode,daycode)values('"+cc+"','"+cn+"')";
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
                        String cc=(String)cbaircraftcode.getSelectedItem();
                        String cn=(String)cbdaycode.getSelectedItem();
                        String s="update flightdays set aircraftcode='"+cc+"',daycode='"+cn+"'where id="+id;
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
                                String s="delete from flightdays where id="+id;
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
                        String s="select aircraftcode from flightdetails";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbaircraftcode.addItem(rs.getString("aircraftcode"));
                        }
                        s="select daycode from daymaster";

                  rs=st.executeQuery(s);
                  while(rs.next())
                  {
                        cbdaycode.addItem(rs.getString("daycode"));
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
                                cbaircraftcode.setSelectedItem(rs.first());
                                cbdaycode.setSelectedItem(rs.first());
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
                JFrame f=new JFrame("FlightDays");
                f.add(new flightdays());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(400,250);
                f.show();
        }
}
