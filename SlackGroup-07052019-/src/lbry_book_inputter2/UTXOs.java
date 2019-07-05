/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lbry_book_inputter2;

import com.jtattoo.plaf.smart.SmartLookAndFeel;
//import com.sun.javafx.PlatformUtil;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author John Blanton
 */
public class UTXOs {
    
   public JFrame CreateUTXOsFrame=null; 
    
   public CreateUTXOsListener JbtmyCreateUTXOs=null;
   public EscUTXOsFrameListener JbtEscUTXOsFrame=null;
   public RunUTXOsProgramListener JbtRunUTXOsProgram=null;
   
   public JButton jbtCreateUTXOs=null;
   public JButton jbtUTXOsRunProgram=null;
   public JButton jbtUTXOsExitFrame=null;
   
   public JLabel UTXOsTotalLBCLabel=null;
   public JLabel UTXOsTotalUTXOsLabel=null;
   
   public JTextField jtfUTXOsTotalLBC=null;
   public JTextField jtfUTXOsTotalUTXOs=null;
   
   public JPanel myUTXOsPanel=null;
   
   public Dimension screenSize =null;   
   public int screenRes; 
   
   public GridLayout experimentLayout=null;
   public Image StartUpImage=null;

public UTXOs(){
setTheLookAndFeel();
       JbtmyCreateUTXOs=new CreateUTXOsListener();
   JbtEscUTXOsFrame =new EscUTXOsFrameListener();
   JbtRunUTXOsProgram=new RunUTXOsProgramListener();
         jbtCreateUTXOs= new JButton("Create UTXOs.");
     jbtUTXOsRunProgram= new JButton("Create UTXOs");
     jbtUTXOsExitFrame= new JButton("Exit");
            jbtCreateUTXOs.addActionListener(JbtmyCreateUTXOs);
       jbtUTXOsRunProgram.addActionListener(JbtRunUTXOsProgram);
       jbtUTXOsExitFrame.addActionListener(JbtEscUTXOsFrame);
          screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    
   screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
}

public static void setTheLookAndFeel(){
        try {
            // setup the look and feel properties
            Properties props = new Properties();
            
            props.put("logoString", "my company"); 
            props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");
            
            props.put("selectionBackgroundColor", "180 240 197"); 
            props.put("menuSelectionBackgroundColor", "180 240 197"); 
            
            props.put("controlColor", "218 254 230");
            props.put("controlColorLight", "218 254 230");
            props.put("controlColorDark", "180 240 197"); 

            props.put("buttonColor", "218 230 254");
            props.put("buttonColorLight", "255 255 255");
            props.put("buttonColorDark", "244 242 232");

            props.put("rolloverColor", "218 254 230"); 
            props.put("rolloverColorLight", "218 254 230"); 
            props.put("rolloverColorDark", "180 240 197"); 

            props.put("windowTitleForegroundColor", "0 0 0");
            props.put("windowTitleBackgroundColor", "180 240 197"); 
            props.put("windowTitleColorLight", "218 254 230"); 
            props.put("windowTitleColorDark", "180 240 197"); 
            props.put("windowBorderColor", "218 254 230");
            
            // set your theme
            SmartLookAndFeel.setCurrentTheme(props);
            //HiFiLookAndFeel.setCurrentTheme(props);
            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel"); 
            //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }   
}

public class EscUTXOsFrameListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
   CreateUTXOsFrame.setVisible(false);
    }}

public class RunUTXOsProgramListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
               Cursor WaitCursor=new Cursor(Cursor.WAIT_CURSOR);
               Cursor DefaultCursor=new Cursor(Cursor.DEFAULT_CURSOR);
        CreateUTXOsFrame.setCursor(WaitCursor);
        
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account list");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account list"); 
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /Applications/LBRY.app/Contents/Resources/static/daemon\" && lbrynet account list");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /opt/LBRY/resources/static/daemon\" && lbrynet account list");   
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            String id="";
            String TotalLBCInWallet="";
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
                line=line.trim();
                if(line.startsWith("\"id\":")){
                    id=line.substring(7, line.length()-2);
                }
                 if(line.startsWith("\"coins\":")){
                    TotalLBCInWallet=line.substring(9, line.length()-1);
                }
                if (line == null) { break; }
                System.out.println(line);
            }
             System.out.println();
              System.out.println("total utxos"+jtfUTXOsTotalUTXOs.getText());
               System.out.println("total lbc input"+jtfUTXOsTotalLBC.getText());
               System.out.println("TotalLBCINWallet "+TotalLBCInWallet);
            if(id.length()>2){
                         ProcessBuilder builder2 = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account fund --from_account="+id+" --to_account="+id+" --amount \""+Double.valueOf(jtfUTXOsTotalLBC.getText())+"\" --outputs="+jtfUTXOsTotalUTXOs.getText()+" --broadcast");
                         if(System.getProperty("os.name").contains("Windows")){
             builder2 = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account fund --from_account="+id+" --to_account="+id+" --amount \""+Double.valueOf(jtfUTXOsTotalLBC.getText())+"\" --outputs="+jtfUTXOsTotalUTXOs.getText()+" --broadcast");
            }
            else if(System.getProperty("os.name").contains("Mac")){
              builder2 = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /Applications/LBRY.app/Contents/Resources/static/daemon\" && ./lbrynet account fund --from_account="+id+" --to_account="+id+" --amount \""+Double.valueOf(jtfUTXOsTotalLBC.getText())+"\" --outputs="+jtfUTXOsTotalUTXOs.getText()+" --broadcast");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
              builder2 = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /opt/LBRY/resources/static/daemon\" && lbrynet account fund --from_account="+id+" --to_account="+id+" --amount \""+Double.valueOf(jtfUTXOsTotalLBC.getText())+"\" --outputs="+jtfUTXOsTotalUTXOs.getText()+" --broadcast");   
            }            
            builder2.redirectErrorStream(true);
            Process p2 = builder2.start();
            
            BufferedReader r2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line2;
            while ((line2 = r2.readLine()) != null) {
               // line2 = r2.readLine();
                line2=line2.trim();             
                if (line2 == null) { break; }
                System.out.println(line2);
            }   
            }
        
        JFrame Update = new JFrame("UTXOs Update");
                 JOptionPane.showMessageDialog(Update,
         "Total LBC used: "+jtfUTXOsTotalLBC.getText()+" \r\nTotal UTXOs Created: "+jtfUTXOsTotalUTXOs.getText()+" \r\nTotal LBC in Wallet: "+TotalLBCInWallet,
         "Successful",
         JOptionPane.PLAIN_MESSAGE);
         CreateUTXOsFrame.setCursor(DefaultCursor);
         CreateUTXOsFrame.setVisible(false);   
        } catch (IOException ex) {
            CreateUTXOsFrame.setCursor(DefaultCursor);
            CreateUTXOsFrame.setVisible(false);
                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error. And the UTXOs couldnt be created.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
    }}

public class CreateUTXOsListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    experimentLayout = new GridLayout(0,2);
    File destFolder=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5));    
    File srcFolder=new File(destFolder.getPath()+File.separator+"src");
    StartUpImage=(new ImageIcon(srcFolder.getPath()+"\\splash.png")).getImage();
    JbtEscUTXOsFrame=new EscUTXOsFrameListener();
    JbtRunUTXOsProgram=new RunUTXOsProgramListener();
    CreateUTXOsFrame = new JFrame("Create UTXOs");
    CreateUTXOsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    CreateUTXOsFrame.setSize((int)(screenSize.width*.35), (int)(screenSize.height*.25));
    CreateUTXOsFrame.setResizable(false);
    CreateUTXOsFrame.setLocationRelativeTo(null);
    CreateUTXOsFrame.setIconImage(StartUpImage);
    myUTXOsPanel=new JPanel();
    myUTXOsPanel.setLayout(experimentLayout);
    jtfUTXOsTotalLBC=new JTextField("200.0",50);
    jtfUTXOsTotalUTXOs=new JTextField("2000",50);       
    UTXOsTotalLBCLabel=new JLabel("Total LBC to use. ");
    UTXOsTotalUTXOsLabel=new JLabel("Total number of UTXOs. ");
    jbtUTXOsRunProgram= new JButton("Create UTXOs");
    jbtUTXOsExitFrame= new JButton("ESC");
    myUTXOsPanel.add(UTXOsTotalLBCLabel);
    myUTXOsPanel.add(jtfUTXOsTotalLBC);
    myUTXOsPanel.add(UTXOsTotalUTXOsLabel);
    myUTXOsPanel.add(jtfUTXOsTotalUTXOs);
    myUTXOsPanel.add(jbtUTXOsRunProgram);
    myUTXOsPanel.add(jbtUTXOsExitFrame);
    CreateUTXOsFrame.add(myUTXOsPanel);
    CreateUTXOsFrame.setVisible(true);
    jbtUTXOsExitFrame.addActionListener(JbtEscUTXOsFrame);
    jbtUTXOsRunProgram.addActionListener(JbtRunUTXOsProgram);
    }}
    
    
}
