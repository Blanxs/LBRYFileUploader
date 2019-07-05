/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lbry_book_inputter2;

import com.jtattoo.plaf.smart.SmartLookAndFeel;
import java.awt.BorderLayout;
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
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import static lbry_book_inputter2.UTXOs.setTheLookAndFeel;

/**
 *
 * @author John Blanton
 */
public class Channel {
    
 public ChannelMenuItemListener JbtChannelMenuItem=null;
 public RunChannelListener JbtRunChannel=null;
 public EscChannelListener JbtEscChannel=null;
 public JFrame ChannelFrame=null;
 public JPanel ChannelPanel=null;
 public JLabel ChooseChannelLabel=null;
 public JLabel ChooseChannelAvatarImageLabel=null;
 public JLabel ChooseChannelBannerLabel=null;
 public JLabel ChooseChannelTitleLabel=null;
 public JLabel ChooseChannelEmailLabel=null;
 public JLabel ChooseChannelWebsiteURLLabel=null;
 public JButton jbtRunChannelProgram=null;
 public JButton jbtEscChannelProgram=null;
 
 public JTextField jtfBannerURL=null;
 public JTextField jtfEmail=null;
 public JTextField jtfProfilePicUrl=null;
 public JTextField jtfTitle=null;
 public JTextField jtfWebsiteURL=null;
 
 public JTextArea myDescription=null;
 public JScrollPane myScrollPane=null;
 
 public JMenuItem ChannelItem=null;
 public Dimension screenSize =null;   
 public int screenRes; 
 public static JComboBox ChannelsComboBox=null;  
 public GridLayout experimentLayout=null;
 public Image StartUpImage=null;
 
 public static String[] ChannelIds=null;
 
    public Channel(){
     setTheLookAndFeel();  
     JbtChannelMenuItem=new ChannelMenuItemListener();
     ChannelPanel=new JPanel();
     ChannelFrame=new JFrame();
     ChannelItem=new JMenuItem("Channel");
     ChannelItem.addActionListener(JbtChannelMenuItem);
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

   public class RunChannelListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
                   Cursor WaitCursor=new Cursor(Cursor.WAIT_CURSOR);
               Cursor DefaultCursor=new Cursor(Cursor.DEFAULT_CURSOR);
        ChannelFrame.setCursor(WaitCursor);
        try {
           System.out.println((String)ChannelsComboBox.getSelectedItem());
      String Description=myDescription.getText();
     // Description=Description.replaceAll("["+System.lineSeparator()+"]+", " \\\\\\n ");
      Description=Description.replaceAll("[\r\n]+", " \\\\\\n ");
      Description=Description.replaceAll("\"", "\\\\\"");
        
            String newParam="{"+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"channel_update\","+"\n"+"\"params\":"+"\n"+"{"+"\n"+"\"claim_id\": \""+ChannelIds[ChannelsComboBox.getSelectedIndex()]+"\","+"\n"+"\"thumbnail_url\": \""+jtfBannerURL.getText()+"\","+"\n"+"\"cover_url\": \""+jtfProfilePicUrl.getText()+"\","
                    +"\n"+"\"title\": \""+jtfTitle.getText()+"\","+"\n"+"\"website_url\": \""+jtfWebsiteURL.getText()+"\","+"\n"+"\"email\": \""+jtfEmail.getText()+"\","+"\n"+"\"description\": \""+Description+"\""+"\n"+"}"+"\n"+"}"+"\n";
            System.out.println(newParam);
            URL obj = new URL("http://localhost:5279/");
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            os.write(newParam.getBytes());
            os.flush();
            os.close();
  System.out.println("POST Response Message : " + postConnection.getResponseMessage());
  ChannelFrame.setCursor(DefaultCursor);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ProtocolException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChannelFrame.setVisible(false);
    }} 
   
      public class EscChannelListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
ChannelFrame.setVisible(false);
    }}
   
public class ChannelMenuItemListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    myDescription=new JTextArea("Channel Description Here");
    myScrollPane=new JScrollPane(myDescription, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    experimentLayout = new GridLayout(0,2);
    File destFolder=new File(System.getProperty("user.dir"));    
    File srcFolder=new File(destFolder.getPath()+File.separator+"src");
    StartUpImage=(new ImageIcon(srcFolder.getPath()+"\\splash.png")).getImage();
    JbtRunChannel=new RunChannelListener();
    JbtEscChannel=new EscChannelListener();
    jbtRunChannelProgram=new JButton("Edit Channel");
    jbtEscChannelProgram=new JButton("Esc");
    ChooseChannelLabel=new JLabel("Choose a Channel:");
    ChooseChannelEmailLabel=new JLabel("Your channel's email:");
    ChooseChannelAvatarImageLabel=new JLabel("Url for your channel's profile pic:");
    ChooseChannelBannerLabel=new JLabel("Url for your channel's banner:");
    ChooseChannelTitleLabel=new JLabel("Your channel's title:");
    ChooseChannelWebsiteURLLabel=new JLabel("Your channel's website url:");
    jtfTitle=new JTextField("",100);
    jtfEmail=new JTextField("",100);
    jtfWebsiteURL=new JTextField("",100);
    jtfBannerURL=new JTextField("",100);
    jtfProfilePicUrl=new JTextField("",100);
    ChannelFrame = new JFrame("Edit/Create Channel");
    ChannelFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    ChannelFrame.setSize((int)(screenSize.width*.35), (int)(screenSize.height*.25));
    ChannelFrame.setResizable(false);
    ChannelFrame.setLocationRelativeTo(null);
    ChannelFrame.setIconImage(StartUpImage);
    ChannelPanel=new JPanel();
    ChannelPanel.setLayout(experimentLayout);    
//    UTXOsTotalLBCLabel=new JLabel("Total LBC to use. ");
//    UTXOsTotalUTXOsLabel=new JLabel("Total number of UTXOs. ");
//    jbtUTXOsRunProgram= new JButton("Create UTXOs");
//    jbtUTXOsExitFrame= new JButton("ESC");
    ChannelPanel.add(ChooseChannelLabel);
    ChannelPanel.add(ChannelsComboBox);
    ChannelPanel.add(ChooseChannelAvatarImageLabel);
    ChannelPanel.add(jtfBannerURL);
    ChannelPanel.add(ChooseChannelBannerLabel);
    ChannelPanel.add(jtfProfilePicUrl);
    ChannelPanel.add(ChooseChannelWebsiteURLLabel);
    ChannelPanel.add(jtfWebsiteURL);
    ChannelPanel.add(ChooseChannelEmailLabel);
    ChannelPanel.add(jtfEmail);
    ChannelPanel.add(ChooseChannelTitleLabel);
    ChannelPanel.add(jtfTitle);
//    myUTXOsPanel.add(UTXOsTotalUTXOsLabel);
//    myUTXOsPanel.add(jtfUTXOsTotalUTXOs);
    ChannelPanel.add(jbtRunChannelProgram);
    ChannelPanel.add(jbtEscChannelProgram);
    ChannelFrame.add(ChannelPanel,BorderLayout.NORTH);
    ChannelFrame.add(myScrollPane,BorderLayout.CENTER);
    ChannelFrame.setVisible(true);
 jbtEscChannelProgram.addActionListener(JbtEscChannel);
jbtRunChannelProgram.addActionListener(JbtRunChannel);

    }}

public static void setChannelComboBox(JComboBox myComboBox){
    JComboBox tempJCB=new JComboBox();
    for(int i=1;i<myComboBox.getItemCount();i++){
     tempJCB.addItem(myComboBox.getItemAt(i));
    }
    ChannelsComboBox=tempJCB;
}

public static void setChannelIds(String[] Ids){
 ChannelIds=Ids;   
}

}
