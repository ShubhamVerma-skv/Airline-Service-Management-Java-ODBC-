import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
public class flightdetails extends JPanel implements ActionListener
{
        JLabel lbid,lbaircraftcode,lbclasscode,lbfare,lbseats,lbidshow;
        JComboBox cbclasscode;
        JTextField txtaircraftcode,txtfare,txtseats;
        JButton btnadd,btnsave,btnnext,btnprevious,btnfirst,btnlast,btndelete,btnexit;
        JPanel pn,ps;
        String status="update";
        Connection cnn;
        Statement st;
        ResultSet rs;
        flightdetails()
        {
                setLayout(new BorderLayout());
                pn=new JPanel(new GridLayout(5,2));
                ps=new JPanel(new GridLayout(2,4));
                add("North",pn);
                add("South",ps);
                lbid=new JLabel("ID");
                lbaircraftcode=new JLabel("Aircraft Code");
                lbclasscode=new JLabel("Class Code");
                 lbfare=new JLabel("Fare");
                lbseats=new JLabel("Seats");
                txtaircraftcode=new JTextField("");
                txtfare=new JTextField("");
                txtseats=new JTextField("");

                lbidshow=new JLabel("");

                cbclasscode=new JComboBox();
               
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
                pn.add(txtaircraftcode);
                pn.add(lbclasscode);
                pn.add(cbclasscode);
                pn.add(lbfare);
                pn.add(txtfare);
                pn.add(lbseats);
                pn.add(txtseats);
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
                        String s="select * from flightdetails";
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
                        txtaircraftcode.setText(rs.getString("aircraftcode"));
                        cbclasscode.setSelectedItem(rs.getString("classcode"));
                        txtfare.setText(rs.getString("fare"));
                        txtseats.setText(rs.getString("seats"));
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
                        String ac=txtaircraftcode.getText();
                        String cc=(String)cbclasscode.getSelectedItem();
                        String cn=txtfare.getText();
                        String cs=txtseats.getText();
                        String s="insert into flightdetails(aircraftcode,classcode,fare,seats)values('"+ac+"','"+cc+"','"+cn+"','"+cs+"')";
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
                        String ac=txtaircraftcode.getText();
                        String cc=(String)cbclasscode.getSelectedItem();
                        String cn=txtfare.getText();
                        String cs=txtseats.getText();
                        String s="update flightdetails set aircraftcode='"+ac+"',classcode='"+cc+"',fare='"+cn+"',seats='"+cs+"'where id="+id;
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
                                String s="delete from flightdetails where id="+id;
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
                        String s="select classcode from classmaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbclasscode.addItem(rs.getString("classcode"));
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
                                txtaircraftcode.setText("");
                                cbclasscode.setSelectedItem(rs.first());
                                txtfare.setText("");
                                txtseats.setText("");
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
                JFrame f=new JFrame("FlightDetails");
                f.add(new flightdetails());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(400,250);
                f.show();
        }
}
