import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MDI extends JFrame implements ActionListener
{
        JMenu mnum,mnua,mnuf,mnup,mnue,mnut;
        JMenuItem miam,micam,micim,miclm,midm,mimm,misem,mistm,miairlinemeal,miairlineservice,miflight,miflightdays,miflightdetails,mipassenger,mireservation,mipassenq,miflightenq,mitree;
        JMenuBar mb;
        JDesktopPane desk;

        MDI()
        {
        super("Multiple Document Interface");
        setLayout(new BorderLayout());
        mnum=new JMenu("Masters");
        mnua=new JMenu("Airline");
        mnuf=new JMenu("Flight");
        mnup=new JMenu("Passenger");
        mnue=new JMenu("Enquiry");
        mnut=new JMenu("Create Users");

        miam=new JMenuItem("Airline Master");
        micam=new JMenuItem("Category Master");
        micim=new JMenuItem("City Master");
        miclm=new JMenuItem("Class Master");
        midm=new JMenuItem("Day Master");
        mimm=new JMenuItem("Meal Master");
        misem=new JMenuItem("Service Master");
        mistm=new JMenuItem("Status Master");

        //miairlinemeal=new JMenuItem("Airline Meal");
        miairlineservice=new JMenuItem("Airline Service");

        miflight=new JMenuItem("Flight");
        miflightdays=new JMenuItem("Flight Days");
        miflightdetails=new JMenuItem("Flight Details");

        mipassenger=new JMenuItem("Passengers");
        
        mireservation=new JMenuItem("Reservation");

        mipassenq=new JMenuItem("Passenger Enquiry");
        miflightenq=new JMenuItem("Flight");

        mitree=new JMenuItem("Create User");

        mb=new JMenuBar();
        desk=new JDesktopPane();

        miam.addActionListener(this);
        micam.addActionListener(this);
        micim.addActionListener(this);
        miclm.addActionListener(this);
        midm.addActionListener(this);
        mimm.addActionListener(this);
        misem.addActionListener(this);
        mistm.addActionListener(this);

//        miairlinemeal.addActionListener(this);
        miairlineservice.addActionListener(this);
        miflight.addActionListener(this);
        miflightdays.addActionListener(this);
        miflightdetails.addActionListener(this);
        mipassenger.addActionListener(this);
        mipassenq.addActionListener(this);
        mireservation.addActionListener(this);
        mipassenq.addActionListener(this);
        miflightenq.addActionListener(this);
        mitree.addActionListener(this);

        mnum.add(miam);
        mnum.add(micam);
        mnum.add(micim);
        mnum.add(miclm);
        mnum.add(midm);
        mnum.add(mimm);
        mnum.add(misem);
        mnum.add(mistm);

//        mnua.add(miairlinemeal);
        mnum.add(miairlineservice);
        mnuf.add(miflight);
        mnuf.add(miflightdays);
        mnuf.add(miflightdetails);
        mnup.add(mipassenger);
        mnue.add(mipassenq);
        mnup.add(mireservation);
        mnut.add(mitree);

        mb.add(mnum);
//        mb.add(mnua);
        mb.add(mnuf);
        mb.add(mnup);
        mb.add(mnue);
        mb.add(mnut);
        setJMenuBar(mb);
        add(desk);
        Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(s.width,s.height-30);
        setResizable(false);
        show();
        }
        public void actionPerformed(ActionEvent e)
        {
                if(e.getSource()==miam)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Airline Master",true,true,true,true);
                        f.add(new airlinemaster());
                        f.setSize(400,400);
                        desk.add(f);
                        f.show();
                }
                else if(e.getSource()==micam)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Category Master",true,true,true,true);
                        f.add(new categorymaster());
                        f.setSize(400,400);
                        desk.add(f);
                        f.show();
                }
                else if(e.getSource()==micim)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("City Master",true,true,true,true);
                        f.add(new citymaster());
                        f.setSize(400,400);
                        desk.add(f);
                        f.show();
                }
                else if(e.getSource()==miclm)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Class Master",true,true,true,true);
                        f.add(new classmaster());
                        f.setSize(400,400);
                        desk.add(f);
                        f.show();
                }
                else if(e.getSource()==midm)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Day Master",true,true,true,true);
                        f.add(new daymaster());
                        f.setSize(400,400);
                        desk.add(f);
                        f.show();
                }
                else if(e.getSource()==mimm)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Meal Master",true,true,true,true);
                        f.add(new mealmaster());
                        f.setSize(400,400);
                        desk.add(f);
                        f.show();
                }
                else if(e.getSource()==misem)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Service Master",true,true,true,true);
                        f.add(new servicemaster());
                        f.setSize(400,400);
                        desk.add(f);
                        f.show();
                }
                else if(e.getSource()==mistm)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Status Master",true,true,true,true);
                        f.add(new statusmaster());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }
            /*    else if(e.getSource()==miairlinemeal)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Airline Meal",true,true,true,true);
                        f.add(new airlinemeal());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }   */
                else if(e.getSource()==miairlineservice)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Airline Service",true,true,true,true);
                        f.add(new airlineservice());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }
                else if(e.getSource()==miflight)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Flight",true,true,true,true);
                        f.add(new flight());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }
                else if(e.getSource()==miflightdays)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Flight Days",true,true,true,true);
                        f.add(new flightdays());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }
                else if(e.getSource()==miflightdetails)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Flight Days",true,true,true,true);
                        f.add(new flightdetails());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }

                else if(e.getSource()==mipassenger)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Passenger",true,true,true,true);
                        f.add(new passengers());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }
                else if(e.getSource()==mipassenq)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Passenger Enquiry",true,true,true,true);
                        f.add(new passengerenq());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }

                else if(e.getSource()==mireservation)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Reservation",true,true,true,true);
                        f.add(new reservation());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }
                else if(e.getSource()==mipassenq)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Status Master",true,true,true,true);
                        f.add(new passengerenq());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }
               /* else if(e.getSource()==miflightenq)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Status Master",true,true,true,true);
                        f.add(new flightenq());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                }    */
                else if(e.getSource()==mitree)
                {
                        JInternalFrame f;
                        f=new JInternalFrame("Create User",true,true,true,true);
                        f.add(new createuser());
                        f.setSize(400,400);
                        desk.add(f);

                        f.show();
                } 
                



        }
        public static void main(String args[])
        {
                MDI m=new MDI();
        }
}
