import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
public class reservation extends JPanel implements ActionListener
{
        JLabel lbid,lbpsng_no,lbaircraftcode,lbjourneydate,lbclasscode,lbno_of_seats,lbaddress,lbcontact_no,lbstatuscode,lbidshow;
        JComboBox cbpsng_no,cbaircraftcode,cbclasscode,cbstatuscode;
        JTextField txtjourneydate,txtno_of_seats,txtaddress,txtcontact_no,txtstatuscode;
        JButton btnadd,btnsave,btnnext,btnprevious,btnfirst,btnlast,btndelete,btnexit;
        JPanel pn,ps;
        String statuscode="update";
        Connection cnn;
        Statement st;
        ResultSet rs;
        reservation()
        {
                setLayout(new BorderLayout());
                pn=new JPanel(new GridLayout(9,2));
                ps=new JPanel(new GridLayout(2,4));
                add("North",pn);
                add("South",ps);
                lbid=new JLabel("ID");
                lbpsng_no=new JLabel("Passanger_No");
                lbaircraftcode=new JLabel("Aircraft Code");
                lbjourneydate=new JLabel("Journey Date");
                lbclasscode=new JLabel("Class Code");
                lbno_of_seats=new JLabel("No_of_Seats");
                lbaddress=new JLabel("address");
                lbcontact_no=new JLabel("Contact Number");
                lbstatuscode=new JLabel("Status");
                lbidshow=new JLabel("");


                txtjourneydate=new JTextField("");
                txtno_of_seats=new JTextField("");
                txtaddress=new JTextField("");
                txtcontact_no=new JTextField("");
                
                
                cbpsng_no=new JComboBox();
                cbaircraftcode=new JComboBox();
                cbclasscode=new JComboBox();               
                cbstatuscode=new JComboBox();

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
                pn.add(cbpsng_no);
                pn.add(lbaircraftcode);
                pn.add(cbaircraftcode);
                pn.add(lbjourneydate);
                pn.add(txtjourneydate);
                pn.add(lbclasscode);
                pn.add(cbclasscode);
                pn.add(lbno_of_seats);
                pn.add(txtno_of_seats);
                pn.add(lbaddress);
                pn.add(txtaddress);
                pn.add(lbcontact_no);
                pn.add(txtcontact_no);
                pn.add(lbstatuscode);
                pn.add(cbstatuscode);

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
                        String s="select * from reservation";
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
                        cbpsng_no.setSelectedItem(rs.getString("psng_no"));
                        cbaircraftcode.setSelectedItem(rs.getString("aircraftcode"));
                        txtjourneydate.setText(rs.getString("journeydate"));
                        cbclasscode.setSelectedItem(rs.getString("classcode"));
                        txtno_of_seats.setText(rs.getString("no_of_seats"));
                        txtaddress.setText(rs.getString("address"));
                        txtcontact_no.setText(rs.getString("contact_no"));
                        cbstatuscode.setSelectedItem(rs.getString("status"));
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
                        String ac=(String)cbpsng_no.getSelectedItem();
                        String bc=(String)cbaircraftcode.getSelectedItem();
                        String cc=txtjourneydate.getText();
                        String dc=(String)cbclasscode.getSelectedItem();
                        String ec=txtno_of_seats.getText();
                        String fc=txtaddress.getText();
                        String gc=txtcontact_no.getText();
                        String hc=(String)cbstatuscode.getSelectedItem();
                        
                        String s="insert into reservation(psng_no,aircraftcode,journeydate,classcode,no_of_seats,address,contact_no,status)values('"+ac+"','"+bc+"','"+cc+"','"+dc+"','"+ec+"','"+fc+"','"+gc+"','"+hc+"')";
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
                        String ac=(String)cbpsng_no.getSelectedItem();
                        String bc=(String)cbaircraftcode.getSelectedItem();
                        String cc=txtjourneydate.getText();
                        String dc=(String)cbclasscode.getSelectedItem();
                        String ec=txtno_of_seats.getText();
                        String fc=txtaddress.getText();
                        String gc=txtcontact_no.getText();
                        String hc=(String)cbstatuscode.getSelectedItem();
                        
                        String s="update reservation set psng_no='"+ac+"',aircraftcode='"+bc+"',journeydate='"+cc+"',classcode='"+dc+"',no_of_seats='"+ec+"',address='"+fc+"',contact_no='"+gc+"',status='"+gc+"' where id="+id;
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
                                String s="delete from reservation where id="+id;
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
                        String s="select psng_no from passengers";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbpsng_no.addItem(rs.getString("psng_no"));
                        }
                        s="select aircraftcode from flightdetails";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbaircraftcode.addItem(rs.getString("aircraftcode"));
                        }
                        s="select classcode from classmaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbclasscode.addItem(rs.getString("classcode"));
                        }
                        s="select statuscode from statusmaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbstatuscode.addItem(rs.getString("statuscode"));
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
                                statuscode="update";
                                rs.first();
                                showrecord();
                        }
                        else if(e.getSource()==btnlast)
                        {
                                statuscode="update";
                                rs.last();
                                showrecord();
                        }
                        else if(e.getSource()==btnnext)
                        {
                                statuscode="update";
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
                                statuscode="update";
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
                                statuscode="save";
                                lbidshow.setText("");
                                cbpsng_no.setSelectedItem(rs.first());
                                cbaircraftcode.setSelectedItem(rs.first());
                                txtjourneydate.setText("");
                                cbclasscode.setSelectedItem(rs.first());
                                txtno_of_seats.setText("");
                                txtaddress.setText("");
                                txtcontact_no.setText("");
                                cbstatuscode.setSelectedItem(rs.first());
                        }
                        else if(e.getSource()==btnsave)
                        {
                                if(statuscode.equals("update"))
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
                JFrame f=new JFrame("Reservation");
                f.add(new reservation());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(400,250);
                f.show();
        }
}
