import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class passengerenq extends JPanel implements ActionListener
{
        JTextField txtpname;
        JLabel lbpname;
        JButton btnsubmit;
        JTable tb;
        JPanel pn,pc,ps;
        String colh[]={"PNR NO","Ticket No","Name","Age","Sex","Ph.No","Meal Pref"};
        String colv[][]=new String[20][7];
        Connection con;
        JScrollPane js;
        Statement st;
        int b=0;
        public void createcon()
        {
                try
                {
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        con=DriverManager.getConnection("jdbc:odbc:airline");
                        st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                }
                catch(Exception e)
                {
                        JOptionPane.showMessageDialog(null,e.toString(),"airline",2);
                }
        }
        public passengerenq()
        {
                pn=new JPanel();
                ps=new JPanel();
                pc=new JPanel();
                setLayout(new BorderLayout());
                pn.setLayout(new GridLayout(1,2));
                ps.setLayout(new GridLayout(1,1));
                pc.setLayout(new GridLayout(1,1));
                lbpname=new JLabel("Passenger Name");
                txtpname=new JTextField("");
                createcon();
                pn.add(lbpname);
                pn.add(txtpname);
                btnsubmit=new JButton("Show");
                tb=new JTable(colv,colh);
                js=new JScrollPane(tb);
                pc.add(js);
                ps.add(btnsubmit);
                add("North",pn);
                add("Center",pc);
                add("South",ps);
                btnsubmit.addActionListener(this);
        }
        public static void main(String arr[])
        {
                JFrame f=new JFrame("Passenger Enquiry");
                f.getContentPane().add(new passengerenq());
                f.setDefaultCloseOperation(3);
                f.setSize(500,500);
                f.show();
        }
        public void actionPerformed(ActionEvent e)
        {
                if(e.getSource()==btnsubmit)
                {
                        try
                        {
                                String n1=txtpname.getText();
                                String sql="select * from passengers where p_name like '"+n1+"%'";
                                ResultSet rs3=st.executeQuery(sql);
                                int i=0;
                                while(rs3.next())
                                {
                                        i++;
                                }
                                sql="select * from passengers where p_name like '"+n1+"%'";
                                rs3=st.executeQuery(sql);
                                int j=0;
                                colv=new String[i][7];
                                while(rs3.next())
                                {
                                        colv[j][0]=String.valueOf(rs3.getInt(2));
                                        colv[j][1]=String.valueOf(rs3.getInt(3));
                                        colv[j][2]=rs3.getString(4);
                                        colv[j][3]=String.valueOf(rs3.getInt(5));
                                        colv[j][4]=rs3.getString(6);
                                        colv[j][5]=rs3.getString(7);
                                        colv[j][6]=rs3.getString(8);
                                        j++;
                                }
                                pc.remove(js);
                                tb=new JTable(colv,colh);
                                js=new JScrollPane(tb);
                                pc.add(js);
                                this.validate();
                        }
                        catch(Exception q)
                        {
                                JOptionPane.showMessageDialog(null,q.toString(),"airline",2);
                        }
                }
        }
}
