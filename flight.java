import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
public class flight extends JPanel implements ActionListener
{
        JLabel lbid,lbaircraftcode,lbairlinecode,lbtype,lbsource,lbdestination,lbcategory,lbdeptime,lbjourneyhours,lbidshow;
        JComboBox cbaircraftcode,cbairlinecode,cbtype,cbsource,cbdestination,cbcategory;
        JTextField txtdeptime,txtjourneyhours;
        JButton btnadd,btnsave,btnnext,btnprevious,btnfirst,btnlast,btndelete,btnexit;
        JPanel pn,ps;
        String status="update";
        Connection cnn;
        Statement st;
        ResultSet rs;
        flight()
        {
                setLayout(new BorderLayout());
                pn=new JPanel(new GridLayout(9,2));
                ps=new JPanel(new GridLayout(2,4));
                add("North",pn);
                add("South",ps);
                lbid=new JLabel("ID");
                lbaircraftcode=new JLabel("Aircraft Code");
                lbairlinecode=new JLabel("Airline Code");
                lbtype=new JLabel("Type");
                lbsource=new JLabel("Source");
                lbdestination=new JLabel("Destination");
                lbcategory=new JLabel("Category");
                cbaircraftcode=new JComboBox();
                cbairlinecode=new JComboBox();
                cbtype=new JComboBox();
                cbsource=new JComboBox();
                cbdestination=new JComboBox();
                cbcategory=new JComboBox();
                lbdeptime=new JLabel("Deptime");
                lbjourneyhours=new JLabel("Journey Hours");
                txtdeptime=new JTextField("");
                txtjourneyhours=new JTextField("");
                

                lbidshow=new JLabel("");

                               
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
                pn.add(lbairlinecode);
                pn.add(cbairlinecode);
                pn.add(lbtype);
                pn.add(cbtype);
                pn.add(lbsource);
                pn.add(cbsource);
                pn.add(lbdestination);
                pn.add(cbdestination);
                pn.add(lbcategory);
                pn.add(cbcategory);
                pn.add(lbdeptime);
                pn.add(txtdeptime);
                pn.add(lbjourneyhours);
                pn.add(txtjourneyhours);
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
                        String s="select * from flight";
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
                        cbairlinecode.setSelectedItem(rs.getString("airlinecode"));
                        cbtype.setSelectedItem(rs.getString("type"));
                        cbsource.setSelectedItem(rs.getString("source"));
                        cbdestination.setSelectedItem(rs.getString("destination"));
                        cbcategory.setSelectedItem(rs.getString("category"));
                        txtdeptime.setText(rs.getString("deptime"));
                        txtjourneyhours.setText(rs.getString("journeyhours"));
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
                        String ac=(String)cbaircraftcode.getSelectedItem();
                        String bc=(String)cbairlinecode.getSelectedItem();
                        String cc=(String)cbtype.getSelectedItem();
                        String dc=(String)cbsource.getSelectedItem();
                        String ec=(String)cbdestination.getSelectedItem();
                        String fc=(String)cbcategory.getSelectedItem();
                        String gc=txtdeptime.getText();
                        String hc=txtjourneyhours.getText();
                        String s="insert into flight(aircraftcode,airlinecode,type,source,destination,category,deptime,journeyhours)values('"+ac+"','"+bc+"','"+cc+"','"+dc+"','"+ec+"','"+fc+"','"+gc+"','"+hc+"')";
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
                        String ac=(String)cbaircraftcode.getSelectedItem();
                        String bc=(String)cbairlinecode.getSelectedItem();
                        String cc=(String)cbtype.getSelectedItem();
                        String dc=(String)cbsource.getSelectedItem();
                        String ec=(String)cbdestination.getSelectedItem();
                        String fc=(String)cbcategory.getSelectedItem();
                        String gc=txtdeptime.getText();
                        String hc=txtjourneyhours.getText();
                        String s="update flight set aircraftcode='"+ac+"',airlinecode='"+bc+"',type='"+cc+"',source='"+dc+"',destination='"+ec+"',category='"+fc+"',deptime='"+gc+"',journeyhours='"+hc+"'where id="+id;
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
                                String s="delete from flight where id="+id;
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

                        s="select airlinecode from airlinemaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbairlinecode.addItem(rs.getString("airlinecode"));
                        }

                        s="select classname from classmaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbtype.addItem(rs.getString("classname"));
                        }

                        s="select cityname from citymaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbsource.addItem(rs.getString("cityname"));
                        }

                        s="select cityname from citymaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbdestination.addItem(rs.getString("cityname"));
                        }
                        s="select categoryname from categorymaster";
                        rs=st.executeQuery(s);
                        while(rs.next())
                        {
                                cbcategory.addItem(rs.getString("categoryname"));
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
                                cbairlinecode.setSelectedItem(rs.first());
                                cbtype.setSelectedItem(rs.first());
                                cbsource.setSelectedItem(rs.first());
                                cbdestination.setSelectedItem(rs.first());
                                txtdeptime.setText("");
                                txtjourneyhours.setText("");
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
                JFrame f=new JFrame("Flight");
                f.add(new flight());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(400,250);
                f.show();
        }
}
