package lbry_book_inputter2;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import com.jtattoo.plaf.smart.*;
import com.jtattoo.plaf.hifi.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Properties;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import com.sun.javafx.PlatformUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDPage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Blanxs
 */
public class LBRY_BOOK_INPUTTER2 {
   
   public static LBRY_BOOK_INPUTTER2 app = null;
    
   public JFrame frame=null;
   public JFrame CreateUTXOsFrame=null; 
   
   
   public Dimension screenSize =null;
    
   public int screenRes;
       
   public EscRobotListener JbtEscRobot=null;
   public RunProgramListener JbtRunProgram=null;
   public FileChooserListener JbtmyFileChooser=null;
   public DisplayMetadataListener JbtmyDisplayMetadata=null;
   public CreateUTXOsListener JbtmyCreateUTXOs=null;
   public EscUTXOsFrameListener JbtEscUTXOsFrame=null;
   public RunUTXOsProgramListener JbtRunUTXOsProgram=null;
   
   public File[] myPublishFiles=null; 
   
   public int myPublishFilesCounter;
   
   public GridLayout experimentLayout=null;
   public GridLayout experimentLayout2=null;
   
   public JButton button1=null;
   public JButton button3=null;
   public JButton button2=null;
   public JButton jbtChooseFiles=null;
   public JButton jbtDisplayMetadata=null;
   public JButton jbtCreateUTXOs=null;
   public JButton jbtUTXOsRunProgram=null;
   public JButton jbtUTXOsExitFrame=null;
   
   
   public JLabel FilePath=null; 
   public JLabel ChannelName=null;  
   public JLabel Price=null; 
   public JLabel Bid=null; 
   public JLabel WalletAddres=null; 
   public JLabel Currency2=null;
   public JLabel NSFW2=null;
   public JLabel ExtractedMetadata=null;
   public JLabel HasCover2=null; 
   public JLabel CoverURL=null; 
   public JLabel LanguageLabel=null;
   public JLabel TagsLabel=null;
   public JLabel UTXOsTotalLBCLabel=null;
   public JLabel UTXOsTotalUTXOsLabel=null;
   
   //new stuff from 052619
   public JLabel URLNameLabel=null;
   public JLabel TitleLabel=null;
   public JLabel LicenseLabel=null;
   public JComboBox LicenseComboBox=null;
   public JTextField jtfURLName=null;
   public JTextField jtfTitle=null;
   public JTextArea myFilesInfoTextArea=null;
   public JScrollPane myFilesInfoScrollPane=null;
   
   public JTextField jtfChannelName=null;     
   public JTextField jtfPrice=null;
   public JTextField jtfBid=null;
   public JTextField jtfWalletAddres=null;  
   public JTextField jtfCoverURL=null;
   public JTextField jtfTags=null;
   public JTextField jtfUTXOsTotalLBC=null;
   public JTextField jtfUTXOsTotalUTXOs=null;
   
   public JTextArea myProgressTextArea=null;
   public JTextArea myDescription=null;
   public JScrollPane myScrollPane=null;
   public JScrollPane myProgressScrollPane=null;    
   
   public JRadioButton jrbCurrencyLBC=null;
   public JRadioButton jrbCurrencyUSD=null;
   public JRadioButton jrbNSFWTrue=null;
   public JRadioButton jrbNSFWFalse=null;
   public JRadioButton jrbHasCoverTrue=null;
   public JRadioButton jrbHasCoverFalse=null;
   public JRadioButton jrbUseExtractedMetadataTrue=null;
   public JRadioButton jrbUseExtractedMetadataFalse=null;
   
   public ButtonGroup CurrencyButtonGroup=null;
   public ButtonGroup NSFWButtonGroup=null;
   public ButtonGroup HasCoverButtonGroup=null;
   public ButtonGroup ExtractedMetadataButtonGroup=null;
   
   public JPanel myPanel=null;
   public JPanel myPanel2=null;
   public JPanel myBottomParentPanel=null; 
   public JPanel myTopParentPanel=null;
   public JPanel myEastParentPanel=null; 
   public JPanel myWestParentPanel=null;
   public JPanel myUTXOsPanel=null;
   
   public JFrame ProgressFrame=null;
   public JLabel myCurrentFileLabel=null;
   public JPanel ProgressPanel=null;
   public JProgressBar progressBar=null;
   
   public Color lbryGray3=null;
   public Color lbryGray4=null;
   public Color lbryGray5=null;
   
   public Color lbryTeal3=null;
   public Color lbryTeal4=null;
   public Color lbryTeal5=null;
   
   public Image StartUpImage=null;
    
   public Border border=null;
   
   public JComboBox ChannelsComboBox=null;
   public JComboBox LanguageComboBox=null;
   
   private static final int BUFFER_SIZE = 4096;
   
public  LBRY_BOOK_INPUTTER2() throws IOException{ 
      // getWalletAddress();
               File createLocation=new File(System.getProperty("user.dir")+"\\src\\");
            createLocation.mkdirs();         
        border = new EmptyBorder(5, 5, 5, 5);
           

   screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    
   screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
       
    JbtEscRobot=new EscRobotListener();
    JbtRunProgram=new RunProgramListener();
    JbtmyFileChooser=new FileChooserListener();
   JbtmyDisplayMetadata=new DisplayMetadataListener();
   JbtmyCreateUTXOs=new CreateUTXOsListener();
   JbtEscUTXOsFrame =new EscUTXOsFrameListener();
   JbtRunUTXOsProgram=new RunUTXOsProgramListener();
   
    frame = new JFrame("LBRY Uploader");
   
    myPublishFiles=new File[1000000]; 
   
    myPublishFilesCounter=0;
   
    experimentLayout = new GridLayout(0,2);
    experimentLayout2 = new GridLayout(0,3);
   
   ChannelsComboBox= new JComboBox(getChannels()); 
   // String[] fakeChannels=new String[2];
   // fakeChannels[0]="Option1";
   // fakeChannels[1]="Option2";
   // ChannelsComboBox=new JComboBox(fakeChannels);
    ChannelsComboBox.setSelectedIndex(0);
    
    LanguageComboBox= new JComboBox(getLanguages());
    LanguageComboBox.setSelectedIndex(0);
    
     button1 = new JButton("ESC");
     button3 = new JButton("ESC");
     button2 = new JButton("Run Program");
     jbtChooseFiles = new JButton("Choose Files.");
     jbtCreateUTXOs= new JButton("Create UTXOs.");
     jbtDisplayMetadata = new JButton("Display Metadata");
     jbtUTXOsRunProgram= new JButton("Create UTXOs");
     jbtUTXOsExitFrame= new JButton("Exit");
     
     FilePath=new JLabel("Choose Files."); 
     ChannelName=new JLabel("Select a Channel to publish to.");  
     Price=new JLabel("Price."); 
     Bid=new JLabel("Bid for each publish."); 
     WalletAddres=new JLabel("This is your Wallet Address."); 
     Currency2=new JLabel("Currency?");
     NSFW2=new JLabel("NSFW?"); 
     ExtractedMetadata=new JLabel("Use extracted metadata?");
     HasCover2=new JLabel("Has Cover?"); 
     CoverURL=new JLabel("Cover URL."); 
     LanguageLabel=new JLabel("Choose your language.");
     TagsLabel=new JLabel("Write in your tags seperated by commas.");
     
    //new stuff from 052619
    URLNameLabel=new JLabel("URL Name.");
    TitleLabel=new JLabel("Published Title.");
    LicenseLabel=new JLabel("Choose License.");
    LicenseComboBox= new JComboBox(getLicenses());
    LicenseComboBox.setSelectedIndex(0);
    jtfURLName=new JTextField("Title_with_Extension",50);
    jtfTitle=new JTextField("Title_without_Extension",50);
    myFilesInfoTextArea=new JTextArea("Choose your Files...");
    myFilesInfoScrollPane=new JScrollPane(myFilesInfoTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    myFilesInfoTextArea.setEditable(false);
    myFilesInfoTextArea.setLineWrap(true);
    myFilesInfoScrollPane.setSize((int)(screenSize.width*.85),(int)(screenSize.height*.30));  
     
     
    // jtfChannelName=new JTextField("TestChannel",200);     
     jtfPrice=new JTextField("0.00",50);
     jtfBid=new JTextField("0.00001",50);
     jtfWalletAddres=new JTextField(getWalletAddress(),50);  
     //jtfWalletAddres=new JTextField("XXXXXXXXXXXXXXXXXXXX",50);  
     jtfCoverURL=new JTextField("https://i.imgur.com/TI60tyj.jpg",200);
     jtfTags=new JTextField("",5000);
     
    myProgressTextArea=new JTextArea("Starting...");
    myDescription=new JTextArea("DESCRIPTION FOR YOUR FILES GO IN THIS BLOCK! \r\nDelete all of this and edit it to what you want to see as your description. \r\nEverything after the collons below are variables you can use for your description! \r\n \r\nTitle: Title_without_Extension \r\nFile Name: Title_with_Extension \r\nFile Path: "
            + "Absolute_file_Path \r\nThe Number of the File in the List being publish:"
            + " My_file_Counter \r\nYour Cover Url: Cover_URL \r\nYour Wallet Address: Wallet_Address \r\nYour Price Set on the file: "
            + "Price_of_Publish \r\nYour Currency on the price: Currency_of_Publish \r\nYour chosen language: "
            + "Chosen_Language \r\nThe Size of your file: File_Size \r\nYour Channel Name: Channel_Name");
    
    myScrollPane=new JScrollPane(myDescription, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    myProgressScrollPane=new JScrollPane(myProgressTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);    
    myScrollPane.setBorder(border);
    
     jrbCurrencyLBC=new JRadioButton("LBC");
     jrbCurrencyUSD=new JRadioButton("USD");
     jrbNSFWTrue=new JRadioButton("True");
     jrbNSFWFalse=new JRadioButton("False");
     jrbHasCoverTrue=new JRadioButton("True");
     jrbHasCoverFalse=new JRadioButton("False");
     jrbUseExtractedMetadataTrue=new JRadioButton("True");
     jrbUseExtractedMetadataFalse=new JRadioButton("False");
     
     CurrencyButtonGroup= new ButtonGroup();
     NSFWButtonGroup= new ButtonGroup();
     HasCoverButtonGroup= new ButtonGroup();
     ExtractedMetadataButtonGroup= new ButtonGroup();
     
     myPanel=new JPanel();
     myPanel2=new JPanel();
     myBottomParentPanel=new JPanel();
     myTopParentPanel=new JPanel();
     myEastParentPanel=new JPanel();
     myWestParentPanel=new JPanel();
     
     ProgressFrame=new JFrame();
     myCurrentFileLabel=new JLabel("AAAAAAAAAAAAA");
     ProgressPanel=new JPanel();
     progressBar=new JProgressBar();
   
     lbryGray3= Color.decode("#ced4da");
     lbryGray4= Color.decode("#abb1b7");
     lbryGray5= Color.decode("#898e93");
   
     lbryTeal3= Color.decode("#38d9a9");
     lbryTeal4= Color.decode("#33b58f");
     lbryTeal5= Color.decode("#2f9176");
   
    // StartUpImage=(new ImageIcon("src/splash.png")).getImage();
   // StartUpImage=(new ImageIcon(System.getProperty("user.dir")+"\\src\\splash.png")).getImage();
   String icon2 ="https://i.imgur.com/vq5zEhY.png";
        String iconDir = System.getProperty("user.dir")+"\\src\\icon.png";
       // String saveDir = System.getProperty("user.dir")+"\\src\\Images";
        try {
            downloadFile(icon2, iconDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        StartUpImage=(new ImageIcon(System.getProperty("user.dir")+"\\src\\icon.png")).getImage(); 
                 
     myPanel.setBorder(border);
    myPanel2.setBorder(border);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize((int)(screenSize.width*.85), (int)(screenSize.height*.85));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setIconImage(StartUpImage);
    
//    myPanel.setBackground(Color.white);
//    myPanel2.setBackground(Color.white);
        
//    jrbNSFWTrue.setBackground(Color.white);
//    jrbNSFWFalse.setBackground(Color.white);
//    jrbHasCoverTrue.setBackground(Color.white);
//    jrbHasCoverFalse.setBackground(Color.white);
//    jrbCurrencyUSD.setBackground(Color.white);
//    jrbCurrencyLBC.setBackground(Color.white);
  
    myDescription.setEditable(true);
    myDescription.setLineWrap(true);
    myScrollPane.setSize((int)(screenSize.width*.85),(int)(screenSize.height*.30)); 
    myProgressTextArea.setEditable(false);
    myProgressTextArea.setLineWrap(true);
    myProgressScrollPane.setSize((int)(screenSize.width*.85),(int)(screenSize.height*.30));
    
    NSFWButtonGroup.add(jrbNSFWTrue);
    NSFWButtonGroup.add(jrbNSFWFalse);      
    jrbNSFWFalse.setSelected(true);

    HasCoverButtonGroup.add(jrbHasCoverTrue);
    HasCoverButtonGroup.add(jrbHasCoverFalse);
    jrbHasCoverFalse.setSelected(true);

    //ExtractedMetadataButtonGroup.add(jrbUseExtractedMetadataTrue);
    //ExtractedMetadataButtonGroup.add(jrbUseExtractedMetadataFalse);
   // jrbUseExtractedMetadataFalse.setSelected(true);
    
    CurrencyButtonGroup.add(jrbCurrencyUSD);
    CurrencyButtonGroup.add(jrbCurrencyLBC);
    jrbCurrencyLBC.setSelected(true);

    myPanel.setLayout(experimentLayout);
    myPanel2.setLayout(experimentLayout2);
       
       myPanel.add(FilePath);
       myPanel.add(jbtChooseFiles);
       myPanel.add(URLNameLabel);
       myPanel.add(jtfURLName);
       myPanel.add(TitleLabel);    
       myPanel.add(jtfTitle);     
       myPanel.add(LicenseLabel);
       myPanel.add(LicenseComboBox);
       myPanel.add(ChannelName);
       //myPanel.add(jtfChannelName);
       myPanel.add(ChannelsComboBox);
       myPanel.add(LanguageLabel);
       myPanel.add(LanguageComboBox);
       myPanel.add(TagsLabel);
       myPanel.add(jtfTags);
       myPanel.add(Price);
       myPanel.add(jtfPrice);
       myPanel.add(Bid);
       myPanel.add(jtfBid);
       myPanel.add(WalletAddres);
       myPanel.add(jtfWalletAddres);
       myPanel.add(CoverURL);
       myPanel.add(jtfCoverURL);
       
       myPanel2.add(Currency2);
       myPanel2.add(jrbCurrencyUSD);
       myPanel2.add(jrbCurrencyLBC);
       myPanel2.add(NSFW2);
       myPanel2.add(jrbNSFWTrue);
       myPanel2.add(jrbNSFWFalse);     
       myPanel2.add(HasCover2);
       myPanel2.add(jrbHasCoverTrue);
       myPanel2.add(jrbHasCoverFalse);
     //  myPanel2.add(ExtractedMetadata);
     //  myPanel2.add(jrbUseExtractedMetadataTrue);
     //  myPanel2.add(jrbUseExtractedMetadataFalse);
       myPanel2.add(button1);
       myPanel2.add(button2);
       myPanel2.add(jbtCreateUTXOs);
     //  myPanel2.add(jbtDisplayMetadata);
       
       myBottomParentPanel.setLayout(experimentLayout);
       myBottomParentPanel.add(myPanel);
       myBottomParentPanel.add(myPanel2);
       
//       myEastParentPanel.add(myScrollPane);
//       myWestParentPanel.add(myPanel,BorderLayout.NORTH);
//       myWestParentPanel.add(myPanel2,BorderLayout.SOUTH);
       
       myTopParentPanel.setLayout(experimentLayout);
       myTopParentPanel.add(myScrollPane);
       myTopParentPanel.add(myFilesInfoScrollPane);
       
       //frame.add(myPanel,BorderLayout.NORTH);
      // frame.add(myTopParentPanel,BorderLayout.NORTH);
       frame.add(myTopParentPanel,BorderLayout.CENTER);
      // frame.add(myPanel2,BorderLayout.SOUTH);
       frame.add(myBottomParentPanel,BorderLayout.SOUTH);
//       frame.add(myEastParentPanel,BorderLayout.EAST);
//       frame.add(myWestParentPanel,BorderLayout.WEST);
       
       frame.setVisible(true);
       //frame.pack();
       button1.addActionListener(JbtEscRobot);
       button2.addActionListener(JbtRunProgram);
       button3.addActionListener(JbtEscRobot);
       jbtChooseFiles.addActionListener(JbtmyFileChooser);
       jbtCreateUTXOs.addActionListener(JbtmyCreateUTXOs);
       jbtUTXOsRunProgram.addActionListener(JbtRunUTXOsProgram);
       jbtUTXOsExitFrame.addActionListener(JbtEscUTXOsFrame);

   
   
}

public static void main(String[] args)throws AWTException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    JFrame Warning = new JFrame("Message LBRY Status");
    Boolean isRunning=false;
 try{
     if(isLBRYRunning()==false){
       
        JOptionPane.showMessageDialog(Warning,
        "LBRY needs to be open and running.",
        "Message",
        JOptionPane.ERROR_MESSAGE);
        System.exit(0);
       }
     else{
         isRunning=true;
     }
}
catch(MalformedURLException mE){
        JOptionPane.showMessageDialog(Warning,
        "LBRY needs to be open and running.MalformedURLException",
        "Message",
        JOptionPane.ERROR_MESSAGE);
      System.exit(0);
}
catch(ProtocolException pe){
          JOptionPane.showMessageDialog(Warning,
        "LBRY needs to be open and running.ProtocolException",
        "Message",
        JOptionPane.ERROR_MESSAGE);
      System.exit(0);
}
catch(IOException ie){
      JOptionPane.showMessageDialog(Warning,
        "LBRY needs to be open and running.IOException",
        "Message",
        JOptionPane.ERROR_MESSAGE);
      System.exit(0);
}
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
 
    if(isRunning==true){
       // pdfToImage();
        //System.out.println("Done");
    app=new LBRY_BOOK_INPUTTER2();
    
    
//String TestCurl="curl -F 'name=dfasdfyghh' -F 'file=C:/Users/User 1/Desktop/cover.jpg' https://spee.ch/api/claim/publish";
//Runtime rt = Runtime.getRuntime();
//Process pr = rt.exec(TestCurl);
//
//BufferedReader response = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//StringBuilder result = new StringBuilder();
//String s;
//while((s = response.readLine()) != null) {
//    result.append(s);  
//}
//System.out.println(result.toString());
//response.close();


  //  File mytestfile=new File("C:\\Users\\User 1\\Desktop\\pg18.epub");
  //   getImage("C:\\\\Users\\\\User 1\\\\Desktop\\\\Crypto.jpg",mytestfile);
    }
    }
    
public class EscRobotListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    System.exit(0);
    }}

public class EscUTXOsFrameListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
   CreateUTXOsFrame.setVisible(false);
    }}

public class RunUTXOsProgramListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account list");
            if(PlatformUtil.isWindows()){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account list"); 
            }
            else if(PlatformUtil.isMac()){
                builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /Applications/LBRY.app/Contents/Resources/static/daemon\" && lbrynet account list");  
            }
             else if(PlatformUtil.isLinux() || PlatformUtil.isUnix()){
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
                         if(PlatformUtil.isWindows()){
             builder2 = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account fund --from_account="+id+" --to_account="+id+" --amount \""+Double.valueOf(jtfUTXOsTotalLBC.getText())+"\" --outputs="+jtfUTXOsTotalUTXOs.getText()+" --broadcast");
            }
            else if(PlatformUtil.isMac()){
              builder2 = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /Applications/LBRY.app/Contents/Resources/static/daemon\" && ./lbrynet account fund --from_account="+id+" --to_account="+id+" --amount \""+Double.valueOf(jtfUTXOsTotalLBC.getText())+"\" --outputs="+jtfUTXOsTotalUTXOs.getText()+" --broadcast");  
            }
             else if(PlatformUtil.isLinux() || PlatformUtil.isUnix()){
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
         "Total LBC used: "+jtfUTXOsTotalLBC.getText()+" Total UTXOs Created: "+jtfUTXOsTotalUTXOs.getText()+" Total LBC in Wallet: "+TotalLBCInWallet,
         "Successful",
         JOptionPane.PLAIN_MESSAGE); 
         CreateUTXOsFrame.setVisible(false);   
        } catch (IOException ex) {
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

public class DisplayMetadataListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    JFrame MetadataWarningFrame = new JFrame("Message General Error");
    
      if(myPublishFilesCounter==0){
         JOptionPane.showMessageDialog(MetadataWarningFrame,
         "You need to choose at least 1 file before clicking this button.",
         "Message",
         JOptionPane.ERROR_MESSAGE);        
      }
    
    }}

public class FileChooserListener  implements ActionListener{
    @Override
  public void actionPerformed(ActionEvent e){
     myPublishFilesCounter=0;
     myPublishFiles=new File[1000000]; 
     JFileChooser chooser=new JFileChooser();
     String userDir = System.getProperty("user.home");
     chooser.setCurrentDirectory(new File(userDir +"/Desktop"));
     chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
     chooser.setMultiSelectionEnabled(true);
     chooser.showDialog(frame,"Select Files");

     File[] files=chooser.getSelectedFiles();

     
   for(int x=0;x<files.length;x++){
     if(files[x].isDirectory()){
      CheckSubFolder(files[x]); 
      }
       else{
       //  System.out.println(files[x].getAbsolutePath());
         myPublishFiles[myPublishFilesCounter]=new File(files[x].getAbsolutePath());
         myPublishFilesCounter++;
       }
    }
//   FilePath=new JLabel("You have selected "+(myPublishFilesCounter+1)+" files to publish."); 
//   myPanel.add(FilePath);
//   myPanel.repaint();
   jbtChooseFiles.setText("You have chosen "+myPublishFilesCounter+" files.");
   if(myPublishFilesCounter>=1){
   String FileInfo="CHOSEN FILES BELOW"+" "+System.lineSeparator()+System.lineSeparator()+System.lineSeparator();
   for(int i=0;i<myPublishFilesCounter;i++){
       FileInfo=FileInfo.concat(myPublishFiles[i].getName()+" "+System.lineSeparator());
   }
       
  
   myFilesInfoTextArea.setText(FileInfo);   
   myFilesInfoTextArea.repaint();
   }
   }}
    
public void CheckSubFolder(File myFile){
         String[] fileList=myFile.list();
         
         if(fileList.length>0){
             for(int x=0;x<fileList.length;x++){
                 File currentFile=new File(myFile.getAbsolutePath()+"\\"+fileList[x]);
                 if(currentFile.isDirectory()){
                     CheckSubFolder(currentFile);
                 }
                 else{
                  // System.out.println(currentFile.getAbsolutePath());
                   myPublishFiles[myPublishFilesCounter]=new File(currentFile.getAbsolutePath());
                   myPublishFilesCounter++;
                 }
             }
         }
  }
       
public class RunProgramListener  implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e){
     

   progressBar.setMinimum(0);
   progressBar.setMaximum(myPublishFilesCounter);
   progressBar.setStringPainted(true);
   ProgressPanel.add(myProgressScrollPane);
   ProgressPanel.add(progressBar);
  // ProgressPanel.add(button3);
   ProgressPanel.setLayout(experimentLayout);
   ProgressFrame.setLocationRelativeTo(null);
   ProgressFrame.setSize(800,400);
   ProgressFrame.setTitle("Publishing...");
   ProgressFrame.add(ProgressPanel);
   ProgressFrame.setIconImage(StartUpImage);
   ProgressFrame.setVisible(true);
   ProgressFrame.setLocationRelativeTo(null);
   
   
   
   
  
     
     
        JFrame Warningframe1 = new JFrame("Message General Error");
        JFrame Warningframe9 = new JFrame("Message File Path");
        JFrame Warningframe2 = new JFrame("Message LBRY Status");
        JFrame Warningframe3 = new JFrame("Message Price");
        JFrame Warningframe4 = new JFrame("Message Wallet Address");
        JFrame Warningframe5 = new JFrame("Message Currency");
        JFrame Warningframe6 = new JFrame("Message NSFW");
        JFrame Warningframe7 = new JFrame("Message Select Files");
        JFrame Warningframe8 = new JFrame("Message Cover URL");
            
        File myFile=new File("F:\\Comics\\Test\\");
       // String channel="TestChannel";
        double myPrice=0.00;
        double myBid=0.001;
        String myWallet="asdihasdkjhasdkjh";
        String myCoverURL="ksjdhfksjhdf";
        String Description;
        String myTags;
        String[] Tags=new String[1];
        if(jtfTags.getText()!=null){
        myTags=jtfTags.getText();
        Tags=myTags.split(",");
        }
        if((jtfCoverURL.getText()!="") || jtfCoverURL.getText()!=null){
            myCoverURL=jtfCoverURL.getText();
        }
        else{
            myCoverURL="AAAAAA";
        }
        
        
      // Description=myDescription.getText().replaceAll("[\r\n]+", " \\\\n ");
     // Description=myDescription.getText().replaceAll("\\n", " \\\\\\n ");
     
     Description=myDescription.getText();
     // Description=Description.replaceAll("["+System.lineSeparator()+"]+", " \\\\\\n ");
      Description=Description.replaceAll("[\r\n]+", " \\\\\\n ");
        Description=Description.replaceAll("\"", "\\\\\"");
try{
     if(isLBRYRunning()==false){
        JOptionPane.showMessageDialog(Warningframe2,
        "LBRY needs to be open and running.",
        "Message",
        JOptionPane.ERROR_MESSAGE);
        return;
       }
}
catch(MalformedURLException mE){
    
}
catch(ProtocolException pe){
    
}
catch(IOException ie){
    
}
     if(jtfPrice.getText().length()>0){
         myPrice=Double.valueOf(jtfPrice.getText());
     }
      else{
         JOptionPane.showMessageDialog(Warningframe3,
         "You need to input a price.Even if its 0.00",
         "Message",
         JOptionPane.ERROR_MESSAGE);
         return;
      }
      if(jtfBid.getText().length()>0){
         myBid=Double.valueOf(jtfBid.getText());
        
     }
      else{
         JOptionPane.showMessageDialog(Warningframe3,
         "You need to input a bid.Even if its 0.0001",
         "Message",
         JOptionPane.ERROR_MESSAGE);
         return;
      }
     if(jtfWalletAddres.getText().length()>1){
         myWallet=jtfWalletAddres.getText();
     }
      else{
         JOptionPane.showMessageDialog(Warningframe4,
         "You need to input a wallet address. /n  If price is 0.00 you can put anything there. Like xxxxxxxxxxxxxxx.",
         "Message",
         JOptionPane.ERROR_MESSAGE);
         return;
      }
     if(myPublishFilesCounter==0){
         JOptionPane.showMessageDialog(Warningframe7,
         "You need to Select at least 1 file.",
         "Message",
         JOptionPane.ERROR_MESSAGE);
         return;
     }

//     if(jtfCoverURL.getText().length()>1){
//          myCoverURL=jtfCoverURL.getText();
//     }
//      else{
//          JOptionPane.showMessageDialog(Warningframe8,
//          "You need to input a Cover URL if Has aCover is false, it wont be placed.But something needs to be in the input.",
//          "Message",
//          JOptionPane.ERROR_MESSAGE);
//          return;
//      }


     if(jtfPrice.getText().length()>0){
     if(jtfWalletAddres.getText().length()>1){         
//     if(jtfCoverURL.getText().length()>1){    
//System.out.println("Channel Name : "+jtfChannelName.getText()+"  "+channel);
//System.out.println("Price : "+jtfPrice.getText());
//System.out.println("Wallet Address : "+jtfWalletAddres.getText());
//System.out.println("Cover URL : "+jtfCoverURL.getText()+"   "+myCoverURL);
     

      for(int i=0;i<myPublishFilesCounter;i++){
      // ProgressFrame.notify();
       ProgressFrame.repaint();
       ProgressFrame.update(ProgressFrame.getGraphics());
       
        myProgressTextArea.setText(String.valueOf(i+1)+" of "+String.valueOf(myPublishFilesCounter)+"  "+myPublishFiles[i].getName());
        try {   

           
           Process(myPublishFiles[i], (String)ChannelsComboBox.getSelectedItem(), myPrice,myWallet,myCoverURL,Description,i,Tags);
           progressBar.setValue(i+1);

         } 
          catch (IOException ex) {
           JOptionPane.showMessageDialog(Warningframe1,
          "Somewhere along the way you messed up. Double check everything is correct then try again.",
          "Message",
          JOptionPane.ERROR_MESSAGE);
          return;
          }
          
      }

 
    //  }
  
    }}
     
     // System.exit(0);
     
     RestartProgram();
    }}
   
public void Process(File FilePath,String channelName, double price, String walletAddress, String CoverURL, String Description, int Counter,String[] Tags) throws IOException{
    
   long myFileSize=FilePath.length();
   String myFileSizeNotation="Bytes";
   
   if(myFileSize<1000){
     myFileSizeNotation="Bytes";  
   }
   else if(myFileSize<1000000){
       myFileSize=myFileSize/1000;
     myFileSizeNotation="KB";  
   }
   else if(myFileSize<1000000000){
       myFileSize=myFileSize/1000000;
     myFileSizeNotation="MB";  
   }
   else if((myFileSize/1000)<1000000000){
       myFileSize=myFileSize/1000000000;
    myFileSizeNotation="GB";   
   }
   else if((myFileSize/1000000)<1000000000){
       myFileSize=myFileSize/1000000000;
       myFileSize=myFileSize/1000;
    myFileSizeNotation="TB";   
   }
   else{
       myFileSize=myFileSize/1000000000;
       myFileSize=myFileSize/1000;
       myFileSize=myFileSize/1000;
      myFileSizeNotation="PB"; 
   }
   String TagsCombo="";
   if(jtfTags.getText().length()>1){
       for(int i=0;i<Tags.length-1;i++){
          // System.out.println(Tags[i]+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
           TagsCombo=TagsCombo.concat("\"");
           TagsCombo=TagsCombo.concat(Tags[i]);
           TagsCombo=TagsCombo.concat("\"");
           TagsCombo=TagsCombo.concat(",");
           TagsCombo=TagsCombo.concat("\n");
       }
           TagsCombo=TagsCombo.concat("\"");
           TagsCombo=TagsCombo.concat(Tags[Tags.length-1]);
           TagsCombo=TagsCombo.concat("\"");
          
          if(jrbNSFWTrue.isSelected()){
              TagsCombo=TagsCombo.concat(",");
               TagsCombo=TagsCombo.concat("\n");
                      TagsCombo=TagsCombo.concat("\"");
           TagsCombo=TagsCombo.concat("mature");
           TagsCombo=TagsCombo.concat("\"");
           TagsCombo=TagsCombo.concat("\n");   
          }
          else{
            TagsCombo=TagsCombo.concat("\n");  
          }
   }
   else{
       if(jrbNSFWTrue.isSelected()){
        TagsCombo=TagsCombo.concat("\"");
           TagsCombo=TagsCombo.concat("mature");
           TagsCombo=TagsCombo.concat("\"");
           TagsCombo=TagsCombo.concat("\n");  
       }
   }
   String NSFW="false";
   String currency="LBC";
   String hasCoverURL="true";
   String myCounter=String.valueOf(Counter+1);
  if(jrbNSFWTrue.isSelected()){
   NSFW="true";  
  }
   else{
    NSFW="false";  
   }

  if(jrbCurrencyLBC.isSelected()){
   currency="LBC";  
  }
   else{
    currency="USD";  
   }

   if(jrbHasCoverTrue.isSelected()){
    hasCoverURL="true";  
   }
    else{
     hasCoverURL="false";  
    }


    String[] ParentFolderNames=new String[50];
    int m=0;
  for(int n=0;n<50;n++){
    ParentFolderNames[n]="";  
  }
    String myFilePath=FilePath.getAbsolutePath();
  for(int i=0;i<myFilePath.length();i++){
   if(myFilePath.charAt(i)=='\\'){
     m++;
    }
     else{
      ParentFolderNames[m]=ParentFolderNames[m].concat(myFilePath.substring(i, i+1)); 
     }
    }
   
      String openbracket="{";
      String closedbracket="}"; 
      String BookTitle=jtfURLName.getText();  
      String TitleWithoutSeries=""; 
      String temp=FilePath.getName();
      String PostParams="";
      String PublishedTitle=jtfTitle.getText();
   if(channelName.startsWith("anonymous")){
       PostParams=PostParams.concat(openbracket+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"publish\","+"\n"+"\"params\":"+"\n"+openbracket+"\n"+"\"name\": \"");
       
   }
   else{
      PostParams=PostParams.concat(openbracket+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"publish\","+"\n"+"\"params\":"+"\n"+openbracket+"\n"+"\"channel_name\": \"@"+channelName+"\","+"\n"+"\"name\": \"");
   }
      Boolean hasntFoundDot=true;
   
    for(int i=0;i<temp.length();i++){
      if(temp.charAt(i)!='.' && hasntFoundDot){
       TitleWithoutSeries=TitleWithoutSeries.concat(temp.substring(i, i+1));
      }
       else{
         hasntFoundDot=false;
       }
        
    }

String myAbsoluteFilePath2="";

   for(int g=0;g<50;g++){
    if(ParentFolderNames[g]!=""){
      myAbsoluteFilePath2=myAbsoluteFilePath2.concat(ParentFolderNames[g]+"\\\\\\\\");
    }
   }
   myAbsoluteFilePath2.concat(FilePath.getName());
   myAbsoluteFilePath2=myAbsoluteFilePath2.substring(0, myAbsoluteFilePath2.length()-4);
   BookTitle=getVariable(BookTitle, TitleWithoutSeries, myAbsoluteFilePath2, myCounter, CoverURL, walletAddress, price, currency,myFileSize,myFileSizeNotation,FilePath.getName(),channelName);
   PublishedTitle=getVariable(PublishedTitle, TitleWithoutSeries, myAbsoluteFilePath2, myCounter, CoverURL, walletAddress, price, currency,myFileSize,myFileSizeNotation,FilePath.getName(),channelName);    

   
    BookTitle=getOnlyAZ09(BookTitle);
    
    PostParams=PostParams.concat(BookTitle); 
    PostParams=PostParams.concat("\",");
    PostParams=PostParams.concat("\n ");
    PostParams=PostParams.concat("\"file_path\":"+"\n");
    PostParams=PostParams.concat("\"");

String myAbsoluteFilePath="";

   for(int g=0;g<50;g++){
    if(ParentFolderNames[g]!=""){
      PostParams=PostParams.concat(ParentFolderNames[g]+"\\\\");
      myAbsoluteFilePath=myAbsoluteFilePath.concat(ParentFolderNames[g]+"\\\\\\\\");
    }
   }
   myAbsoluteFilePath.concat(FilePath.getName());
   myAbsoluteFilePath=myAbsoluteFilePath.substring(0, myAbsoluteFilePath.length()-4);
//   for(int x=0;x<Description.length();x++){
//    System.out.print(Description.charAt(x));
//   }
   
     //Description=Description.replaceAll("Title_with_Extension", FilePath.getName());
//     Description=Description.replaceAll("Title_without_Extension", TitleWithoutSeries);
//     Description=Description.replaceAll("Absolute_file_Path", myAbsoluteFilePath);
//     Description=Description.replaceAll("My_file_Counter", myCounter);
//     Description=Description.replaceAll("Cover_URL", CoverURL);
//     Description=Description.replaceAll("Wallet_Address", walletAddress);
//     Description=Description.replaceAll("Price_of_Publish", String.valueOf(price));
//     Description=Description.replaceAll("Currency_of_Publish", currency);
//     Description=Description.replaceAll("Chosen_Language", (String)LanguageComboBox.getSelectedItem().toString().toLowerCase());
//     Description=Description.replaceAll("File_Size", String.valueOf(myFileSize)+" "+myFileSizeNotation);

     Description=getVariable(Description, TitleWithoutSeries, myAbsoluteFilePath2, myCounter, CoverURL, walletAddress, price, currency,myFileSize,myFileSizeNotation,FilePath.getName(),channelName);
     //System.out.println(Description);
   
     PostParams=PostParams.substring(0, PostParams.length()-2);
     PostParams=PostParams.concat("\","+"\n"+"\"bid\": \""+jtfBid.getText()+"\","+"\n"+"\"description\":\""+Description);  
     PostParams= PostParams.concat("\","+"\n");

 
   if(price !=0.00){
      //PostParams= PostParams.concat("\"fee\":"+"\n"+openbracket+"\n"+"\"address\": \""+walletAddress+"\","+"\n"+"\"amount\": "+price+",\n"+"\"currency\": \""+currency+"\""+"\n"+closedbracket+","+"\n");
     PostParams= PostParams.concat("\"fee_address\": \""+walletAddress+"\","+"\n"+"\"fee_amount\": "+price+",\n"+"\"fee_currency\": \""+currency+"\","+"\n");
   }
   PostParams=PostParams.concat("\"title\": \""+PublishedTitle+"\","+"\n");
   if(jrbHasCoverTrue.isSelected()){
      PostParams=PostParams.concat("\"thumbnail_url\": \""+CoverURL+"\",\n");
   }
if((jrbNSFWTrue.isSelected())||jtfTags.getText().length()>1){
      PostParams=PostParams.concat("\"tags\": ["+TagsCombo+"],"+"\n"+"\"languages\": [\""+(String)LanguageComboBox.getSelectedItem().toString().toLowerCase()+"\"],"+"\n"+"\"license\": \"Public\""+"\n"+closedbracket+"\n"+closedbracket); 
}
else{
    PostParams=PostParams.concat("\"languages\": [\""+(String)LanguageComboBox.getSelectedItem().toString().toLowerCase()+"\"],"+"\n"+"\"license\": \"Public\""+"\n"+closedbracket+"\n"+closedbracket); 
}
        
POSTRequest(PostParams);

   }
   
public void POSTRequest(String Params) throws IOException {
   //Check out
//https://stackoverflow.com/questions/25011927/how-to-get-response-body-using-httpurlconnection-when-code-other-than-2xx-is-re
   System.out.println(Params);
   
    URL obj = new URL("http://localhost:5279/");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();   
    postConnection.setRequestMethod("POST");
    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(Params.getBytes());	    
    os.flush();
    os.close();
   
    //int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
}
 
public static Boolean isLBRYRunning() throws MalformedURLException, ProtocolException, IOException{
    Boolean isRunning=false;
    
    String newParam="{"+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"status\","+"\n"+"\"params\":"+"{}}";
    URL obj = new URL("http://localhost:5279/");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();   
    postConnection.setRequestMethod("POST");
    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(newParam.getBytes());
    
  //  Working response code below 
    Reader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(), "UTF-8"));
    StringBuilder sb = new StringBuilder();
    
    for (int c; (c = in.read()) >= 0;){
    sb.append((char)c);
    }
    
    String response = sb.toString();
  //  System.out.println(response);
    String[] statusResponse=response.split("\"is_running\": ");
    if(statusResponse[1].charAt(0)=='t'){
        isRunning=true;
    }
    
    return isRunning;
}

public String[] getChannels() throws MalformedURLException, IOException{
    
    String[] Paramchannels=new String[1];
    String[] channels=new String[1];
    String newParam="{"+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"channel_list\","+"\n"+"\"params\":"+"{}}";
    URL obj = new URL("http://localhost:5279/");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();   
    postConnection.setRequestMethod("POST");
    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(newParam.getBytes());
    
  //  Working response code below 
    Reader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(), "UTF-8"));
    StringBuilder sb = new StringBuilder();
    
    for (int c; (c = in.read()) >= 0;){
    sb.append((char)c);
    }
    
    String response = sb.toString();
    if(response.indexOf("\"name\": \"@")!=-1){
    Paramchannels=response.split("\"name\": \"@");
    channels=response.split("\"name\": \"@");
    for(int x=1;x<Paramchannels.length;x++){
      channels[x]=Paramchannels[x].substring(0,Paramchannels[x].indexOf("\""));
    }
    
    //System.out.println(response);
    }
    channels[0]="anonymous";
//    for(int i=0;i<channels.length;i++){
//     System.out.println(channels[i]);   
//    }
    os.flush();
    os.close();
    
    return channels;
}

public String getWalletAddress() throws MalformedURLException, IOException{
    
    String[] Paramchannels=new String[1];
    String walletaddress="";
    String newParam="{"+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"address_list\","+"\n"+"\"params\":"+"{}}";
    URL obj = new URL("http://localhost:5279/");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();   
    postConnection.setRequestMethod("POST");
    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(newParam.getBytes());
    
  //  Working response code below 
    Reader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(), "UTF-8"));
    StringBuilder sb = new StringBuilder();
    
    for (int c; (c = in.read()) >= 0;){
    sb.append((char)c);
    }
    
    String response = sb.toString();
    //System.out.println(response);
    
    if(response.indexOf("\"result\": [\n")!=-1){
    Paramchannels=response.split("\"result\":");

      walletaddress=Paramchannels[1].substring(0,Paramchannels[1].indexOf("\","));
    walletaddress=walletaddress.trim();
    walletaddress=walletaddress.substring(7, walletaddress.length());
    
    //System.out.println(response);
    }
//System.out.println(walletaddress);
    os.flush();
    os.close();
    return walletaddress;
  //  return channels;
}

public String[] getLanguages(){
  String[] languages=new String[185];
  languages[0]="EN";
  languages[1]="UNKNOWN_LANGUAGE";  
  languages[2]="AA"; 
  languages[3]="AB"; 
  languages[4]="AE"; 
  languages[5]="AF"; 
  languages[6]="AK"; 
  languages[7]="AM"; 
  languages[8]="AN"; 
  languages[9]="AR"; 
  languages[10]="AS";
  languages[11]="AV";
  languages[12]="AY";
  languages[13]="AZ";
  languages[14]="BA";
  languages[15]="BE";
  languages[16]="BG";
  languages[17]="BH";
  languages[18]="BI";
  languages[19]="BM";
  languages[20]="BN";
  languages[21]="BO";
  languages[22]="BR";
  languages[23]="BS";
  languages[24]="CA";
  languages[25]="CE";
  languages[26]="CH";
  languages[27]="CO";
  languages[28]="CR";
  languages[29]="CS";
  languages[30]="CU";
  languages[31]="CV";
  languages[32]="CY";
  languages[33]="DA";
  languages[34]="DE";
  languages[35]="DV";
  languages[36]="DZ";
  languages[37]="EE";
  languages[38]="EL";
  languages[39]="EO";
  languages[40]="ES";
  languages[41]="ET";
  languages[42]="EU";
  languages[43]="FA";
  languages[44]="FF";
  languages[45]="FI";
  languages[46]="FJ";
  languages[47]="FO";
  languages[48]="FR";
  languages[49]="FY";
  languages[50]="GA";
  languages[51]="GD";
  languages[52]="GL";
  languages[53]="GN";
  languages[54]="GU";
  languages[55]="GV";
  languages[56]="HA";
  languages[57]="HE";
  languages[58]="HI";
  languages[59]="HO";
  languages[60]="HR";
  languages[61]="HT";
  languages[62]="HU";
  languages[63]="HY";
  languages[64]="HZ";
  languages[65]="IA";
  languages[66]="ID";
  languages[67]="IE";
  languages[68]="IG";
  languages[69]="II";
  languages[70]="IK";
  languages[71]="IO";
  languages[72]="IS";
  languages[73]="IT";
  languages[74]="IU";
  languages[75]="JA";
  languages[76]="JV";
  languages[77]="KA";
  languages[78]="KG";
  languages[79]="KI";
  languages[80]="KJ";
  languages[81]="KK";
  languages[82]="KL";
  languages[83]="KM";
  languages[84]="KN";
  languages[85]="KO";
  languages[86]="KR";
  languages[87]="KS";
  languages[88]="KU";
  languages[89]="KV";
  languages[90]="KW";
  languages[91]="KY";
  languages[92]="LA";
  languages[93]="LB";
  languages[94]="LG";
  languages[95]="LI";
  languages[96]="LN";
  languages[97]="LO";
  languages[98]="LT";
  languages[99]="LU";
  languages[100]="LV";
  languages[101]="MG";
  languages[102]="MH";
  languages[103]="MI";
  languages[104]="MK";
  languages[105]="ML";
  languages[106]="MN";
  languages[107]="MR";
  languages[108]="MS";
  languages[109]="MT";
  languages[110]="MY";
  languages[111]="NA";
  languages[112]="NB";
  languages[113]="ND";
  languages[114]="NE";
  languages[115]="NG";
  languages[116]="NL";
  languages[117]="NN";
  languages[118]="NO";
  languages[119]="NR";
  languages[120]="NV";
  languages[121]="NY";
  languages[122]="OC";
  languages[123]="OJ";
  languages[124]="OM";
  languages[125]="OR";
  languages[126]="OS";
  languages[127]="PA";
  languages[128]="PI";
  languages[129]="PL";
  languages[130]="PS";
  languages[131]="PT";
  languages[132]="QU";
  languages[133]="RM";
  languages[134]="RN";
  languages[135]="RO";
  languages[136]="RU";
  languages[137]="RW";
  languages[138]="SA";
  languages[139]="SC";
  languages[140]="SD";
  languages[141]="SE";
  languages[142]="SG";
  languages[143]="SI";
  languages[144]="SK";
  languages[145]="SL";
  languages[146]="SM";
  languages[147]="SN";
  languages[148]="SO";
  languages[149]="SQ";
  languages[150]="SR";
  languages[151]="SS";
  languages[152]="ST";
  languages[153]="SU";
  languages[154]="SV";
  languages[155]="SW";
  languages[156]="TA";
  languages[157]="TE";
  languages[158]="TG";
  languages[159]="TH";
  languages[160]="TI";
  languages[161]="TK";
  languages[162]="TL";
  languages[163]="TN";
  languages[164]="TO";
  languages[165]="TR";
  languages[166]="TS";
  languages[167]="TT";
  languages[168]="TW";
  languages[169]="TY";
  languages[170]="UG";
  languages[171]="UK";
  languages[172]="UR";
  languages[173]="UZ";
  languages[174]="VE";
  languages[175]="VI";
  languages[176]="VO";
  languages[177]="WA";
  languages[178]="WO";
  languages[179]="XH";
  languages[180]="YI";
  languages[181]="YO";
  languages[182]="ZA";
  languages[183]="ZH";
  languages[184]="ZU";
  
  return languages;
}

public String[] getLicenses(){
 String[] Licenses=new String[8];
 Licenses[0]="None";
 Licenses[1]="Public Domain";
 Licenses[2]="Creative Commons Attribution 4.0 International";
 Licenses[3]="Creative Commons Attribution-ShareAlike 4.0 International";
 Licenses[4]="Creative Commons Attribution-NoDerivatives 4.0 International";
 Licenses[5]="Creative Commons Attribution-NonCommercial 4.0 International";
 Licenses[6]="Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International";
 Licenses[7]="Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International";
  return Licenses;
}

public String getVariable(String input, String TitleWithoutSeries, String myAbsoluteFilePath, String myCounter, String CoverURL, String walletAddress, double price, String currency, long myFileSize, String myFileSizeNotation, String FilePathString, String channelName){
     input=input.replaceAll("Title_without_Extension", TitleWithoutSeries);
     input=input.replaceAll("Title_with_Extension", FilePathString);
     
     input=input.replaceAll("Absolute_file_Path", myAbsoluteFilePath);
     input=input.replaceAll("My_file_Counter", myCounter);
     input=input.replaceAll("Cover_URL", CoverURL);
     input=input.replaceAll("Wallet_Address", walletAddress);
     input=input.replaceAll("Price_of_Publish", String.valueOf(price));
     input=input.replaceAll("Currency_of_Publish", currency);
     input=input.replaceAll("Chosen_Language", (String)LanguageComboBox.getSelectedItem().toString().toLowerCase());
     input=input.replaceAll("File_Size", String.valueOf(myFileSize)+" "+myFileSizeNotation);
     input=input.replaceAll("Channel_Name", "@"+channelName);
    
    return input;
}

public void RestartProgram(){
    ProgressFrame.setVisible(false);
 
    myPublishFiles=new File[1000000];   
    myPublishFilesCounter=0;
    
    ProgressFrame=new JFrame();
    myCurrentFileLabel=new JLabel("AAAAAAAAAAAAA");
    ProgressPanel=new JPanel();
    progressBar=new JProgressBar();
    myProgressTextArea=new JTextArea("Starting...");   
    myProgressScrollPane=new JScrollPane(myProgressTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);    
    myProgressTextArea.setEditable(false);
    myProgressTextArea.setLineWrap(true);
    myProgressScrollPane.setSize((int)(screenSize.width*.85),(int)(screenSize.height*.30));
    jbtChooseFiles.setText("Choose Files.");
    myFilesInfoTextArea.setText("Choose your Files...");
    myFilesInfoTextArea.repaint();
}

public String getOnlyAZ09(String input){
    String newInput="";
    for(int i=0;i<input.length();i++){
        if(input.charAt(i)=='A'||input.charAt(i)=='a'||input.charAt(i)=='B'||input.charAt(i)=='b'||input.charAt(i)=='C'||input.charAt(i)=='c'||input.charAt(i)=='D'||input.charAt(i)=='d'||input.charAt(i)=='E'||input.charAt(i)=='e'||input.charAt(i)=='F'||input.charAt(i)=='f'||input.charAt(i)=='G'||input.charAt(i)=='g'||input.charAt(i)=='H'||input.charAt(i)=='h'||input.charAt(i)=='I'||input.charAt(i)=='i'||input.charAt(i)=='J'||input.charAt(i)=='j'||input.charAt(i)=='K'||input.charAt(i)=='k'||input.charAt(i)=='L'||input.charAt(i)=='l'||input.charAt(i)=='M'||input.charAt(i)=='m'||input.charAt(i)=='N'||input.charAt(i)=='n'||input.charAt(i)=='O'||input.charAt(i)=='o'||input.charAt(i)=='P'||input.charAt(i)=='p'||input.charAt(i)=='Q'||input.charAt(i)=='q'||input.charAt(i)=='R'||input.charAt(i)=='r'||input.charAt(i)=='S'||input.charAt(i)=='s'||input.charAt(i)=='T'||input.charAt(i)=='t'||input.charAt(i)=='U'||input.charAt(i)=='u'||input.charAt(i)=='V'||input.charAt(i)=='v'||input.charAt(i)=='W'||input.charAt(i)=='w'||input.charAt(i)=='X'||input.charAt(i)=='x'||input.charAt(i)=='Y'||input.charAt(i)=='y'||input.charAt(i)=='Z'||input.charAt(i)=='z'){
          newInput=newInput.concat(String.valueOf(input.charAt(i)));  
        }
        else if(input.charAt(i)=='-'||input.charAt(i)=='1'||input.charAt(i)=='2'||input.charAt(i)=='3'||input.charAt(i)=='4'||input.charAt(i)=='5'||input.charAt(i)=='6'||input.charAt(i)=='7'||input.charAt(i)=='8'||input.charAt(i)=='9'||input.charAt(i)=='0'){
         newInput=newInput.concat(String.valueOf(input.charAt(i)));    
        }

    }
    return newInput;
}

public static void pdfToImage(){
    final String OUTPUT_DIR = "C:/Users/User 1/Desktop/";
            try (final PDDocument document123 = PDDocument.load(new File("C:\\Users\\User 1\\Desktop\\Colors.pdf"))){
            PDFRenderer pdfRenderer = new PDFRenderer(document123);
            //for (int page = 0; page < document.getNumberOfPages(); ++page)
          //  for (int page = 0; page < 3; ++page)
          //  {
          int page=0;
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = OUTPUT_DIR + "image-" + page + ".png";
                ImageIOUtil.writeImage(bim, fileName, 300);
           // }
            document123.close();
        } catch (IOException e){
            System.err.println("Exception while trying to create pdf document - " + e);
        }
//      try {
//        String sourceDir = "C:/Users/User 1/Desktop/Colors.pdf"; // Pdf files are read from this folder
//        String destinationDir = "C:/Users/User 1/Desktop/Converted_PdfFiles_to_Image/"; // converted images from pdf document are saved here
//
//        File sourceFile = new File(sourceDir);
//        File destinationFile = new File(destinationDir);
//        if (!destinationFile.exists()) {
//            destinationFile.mkdir();
//            System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
//        }
//        if (sourceFile.exists()) {
//            System.out.println("Images copied to Folder: "+ destinationFile.getName());             
//            PDDocument document = PDDocument.load(sourceDir);
//            List<PDPage> list = document.getDocumentCatalog().getAllPages();
//            System.out.println("Total files to be converted -> "+ list.size());
//
//            String fileName = sourceFile.getName().replace(".pdf", "");             
//            int pageNumber = 1;
//            for (PDPage page : list) {
//                BufferedImage image = page.convertToImage();
//                File outputfile = new File(destinationDir + fileName +"_"+ pageNumber +".png");
//                System.out.println("Image Created -> "+ outputfile.getName());
//                ImageIO.write(image, "png", outputfile);
//                pageNumber++;
//            }
//            document.close();
//            System.out.println("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
//        } else {
//            System.err.println(sourceFile.getName() +" File not exists");
//        }
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
}

public static String getImage(String FileLocation, File inputFile) throws MalformedURLException, IOException{
     System.setProperty("http.agent", "Chrome");
    String[] myImage=null;
    String Image="";
    System.out.println(FileLocation);
    System.out.println(inputFile.getName().substring(0, inputFile.getName().length()-5));
    String newParam="{"+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"publish\","+"\n"+"\"params\":"+"\n"+"{";
    newParam=newParam.concat("\n"+"\"name\": \"AASASJHVBBXASIKBXKQBSDQKJNWQSKJNQSKNQKJSNLKQMS\",\n"+"\"file\": \""+FileLocation+"\""+"\n"+"}"+"\n"+"}");
    System.out.println(newParam);
    URL obj = new URL("https://spee.ch/api/claim/publish");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();   
    postConnection.setRequestMethod("POST");
    postConnection.setRequestProperty("Content-Type", "application/json; utf-8");
    postConnection.setRequestProperty("Accept", "application/json");
    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(newParam.getBytes());
    
  //  Working response code below 
    Reader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(), "UTF-8"));
    StringBuilder sb = new StringBuilder();
    
    for (int c; (c = in.read()) >= 0;){
    sb.append((char)c);
    }
    
    String response = sb.toString();
    System.out.println(response);
    if(response.indexOf("\"url\":")!=-1){    
    myImage=response.split("\"url\":");

    
    //System.out.println(response);
    }
    myImage=myImage[1].split(",");
    Image=myImage[0];
    Image=Image.trim();
//    for(int i=0;i<myImage.length;i++){
//     System.out.println(myImage[i]);   
//    }
    os.flush();
    os.close();
    System.out.println("!!!!!!!!!!!!!!!"+Image);
    return Image;
}

       public static void downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
 
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();
 
            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }
 
            //System.out.println("Content-Type = " + contentType);
            //System.out.println("Content-Disposition = " + disposition);
            //System.out.println("Content-Length = " + contentLength);
            //System.out.println("fileName = " + fileName);
 
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
           //String saveFilePath = saveDir + File.separator + fileName;
            String saveFilePath = saveDir;
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();
 
            //System.out.println("File downloaded");
        } else {
            //System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}