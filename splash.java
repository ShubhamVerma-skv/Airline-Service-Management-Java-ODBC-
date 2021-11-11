import javax.swing.*;
import java.awt.*;
public class splash extends JWindow implements Runnable
{
        JLabel lb,lbi,lbname;
        static JLabel lbp;
        static JProgressBar pb;
        JPanel pn,pc,ps;
        Thread t=new Thread(this);
        int x;
        splash()
        {
                setLayout(null);
                pn=new JPanel();
                ps=new JPanel();
                pn.setBounds(0,0,700,100);
                pn.setBackground(new Color(123,75,180));
                lb=new JLabel("Welcome to Airline Management System");
                lb.setFont(new Font("algerian",Font.BOLD,21));
                lb.setForeground(Color.ORANGE);
                lbname=new JLabel("Made by : Shubham Verma");
                lbname.setFont(new Font("arial",Font.ITALIC,15));
                lbname.setForeground(Color.ORANGE);
                lb.setBounds(110,20,480,50);
                lbname.setBounds(240,60,220,40);
                pc=new JPanel(new GridLayout(1,1));
                pc.setBounds(0,100,600,310);




                pb=new JProgressBar(1,100);
                pb.setStringPainted(true);
                pb.setBorderPainted(true);
                lbi=new JLabel(new ImageIcon("Sunset.jpg"));
                t.start();
                add(lbi);
                getContentPane().add(lb);
                getContentPane().add(lbname);
                add(pn);
                pc.setBackground(Color.blue);
                add(pc);
                pb.setBounds(0,410,700,30);
                add(pb);
        }
        public void run()
        {
                Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
                setSize(700,450);
                setLocation(((s.width-700)/2),((s.height-450)/2));
                setVisible(true);
                try
                {
                        for(x=-350;x<=100;x=x+1)
                        {
                                lbi.setBounds(x,100,500,280);
                                t.sleep(10);
                        }
                }
                catch(InterruptedException e)
                {
                        JOptionPane.showMessageDialog(null,e.toString(),"ERROR",0);
                }
        }
        public static void loadSplashScreen()
        {
                splash sp=new splash();
                Thread th=new Thread(sp);
                th.start();
                if(!sp.isShowing())
                {
                         try
                         {
                                for(int a=1;a<=35;a++)
                                {
                                        pb.setValue(a);
                                        pb.setString(a+"%(PLEASE WAIT WHILE LOADING )");
                                        pb.setForeground(Color.red);
                                        th.sleep(50);
                                        pb.setVisible(true);
                                }
                                for(int a=36;a<=70;a++)
                                {
                                        pb.setValue(a);
                                        pb.setString(a+"%(PLEASE WAIT WHILE LOADING )");
                                        pb.setForeground(Color.yellow);
                                        th.sleep(50);
                                        pb.setVisible(true);
                                }
                                for(int a=71;a<=100;a++)
                                {
                                        pb.setValue(a);
                                        pb.setString(a+"%(PLEASE WAIT WHILE LOADING )");
                                        pb.setForeground(Color.green);
                                        th.sleep(50);
                                        pb.setVisible(true);
                                }
                                pb.setString("Thank you for waiting");
                                th.sleep(1000);
                                new login();
                                sp.dispose();
                          }
                          catch(Exception ex)
                          {
                                JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
                          }
                }
        }
        public static void main(String[] args)
        {
                splash.loadSplashScreen();
        }
}
