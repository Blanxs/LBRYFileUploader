package lbry_book_inputter2;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Cursor;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import org.apache.pdfbox.*;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.fontbox.*;
//import com.sun.javafx.PlatformUtil;
import java.awt.Robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDPage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import nl.siegmann.epublib.Constants;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Guide;
import nl.siegmann.epublib.domain.GuideReference;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.StringUtil;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.bytedeco.ffmpeg.ffmpeg;
import org.bytedeco.javacv.FFmpegFrameFilter;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlSerializer;

import org.opencv.core.Core;
//import org.opencv.core.Mat;
import org.opencv.highgui.*;
//import org.opencv.highgui.VideoCapture;
//import javafx.scene.Cursor;


/**
 *
 * @author Blanxs
 */
public class LBRY_BOOK_INPUTTER2 {
   
   public static LBRY_BOOK_INPUTTER2 app = null;
    
   public JFrame frame=null;
   
   
   
   public Dimension screenSize =null;
    
   public int screenRes;
       
   public EscRobotListener JbtEscRobot=null;
   public RunProgramListener JbtRunProgram=null;
   public FileChooserListener JbtmyFileChooser=null;
   public DisplayMetadataListener JbtmyDisplayMetadata=null;
   public ReadMeMenuItemListener JbtReadMeMenuItem=null;
   
   
   public File[] myPublishFiles=null; 
   
   public int myPublishFilesCounter;
   
   public String[] CompleteChannelsName=null;
   
   public GridLayout experimentLayout=null;
   public GridLayout experimentLayout2=null;
   
   public JButton button1=null;
   public JButton button3=null;
   public JButton button2=null;
   public JButton jbtChooseFiles=null;
   public JButton jbtDisplayMetadata=null;

   
   
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

   
   //new stuff from 052619
   public JLabel URLNameLabel=null;
   public JLabel TitleLabel=null;
   public JLabel LicenseLabel=null;
   public JComboBox LicenseComboBox=null;
   public JTextField jtfURLName=null;
   public JTextField jtfTitle=null;
   public static JTextArea myFilesInfoTextArea=null;
   public JScrollPane myFilesInfoScrollPane=null;
   
   public JTextField jtfChannelName=null;     
   public JTextField jtfPrice=null;
   public JTextField jtfBid=null;
   public JTextField jtfWalletAddres=null;  
   public JTextField jtfCoverURL=null;
   public JTextField jtfTags=null;
  
   public String myProgressTextAreaCurrentString=null;
   
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

   
   public JFrame ProgressFrame=null;
   public JLabel myCurrentFileLabel=null;
   public JPanel ProgressPanel=null;
   public JProgressBar progressBar=null;
    
    public static Color lbryGray1= Color.decode("#e2e5e9");
    public static Color lbryGray2= Color.decode("#d8dde1");
    public static Color lbryGray3= Color.decode("#ced4da");
    public static Color lbryGray4= Color.decode("#abb1b7");
    public static Color lbryGray5= Color.decode("#898e93");   
    public static Color lbryBlack= Color.decode("#212529");     
    public static Color lbryTeal3= Color.decode("#38d9a9");
    public static Color lbryTeal4= Color.decode("#33b58f");
    public static Color lbryTeal5= Color.decode("#2f9176");   
    public static Color lbryWhite= Color.decode("#fff");
    public static Color lbrySilver= Color.decode("#f1f3f5"); 
    
   public Image StartUpImage=null;
    
   public Border border=null;
   
   public JComboBox ChannelsComboBox=null;
   public JComboBox LanguageComboBox=null;
   
   public JMenuBar menuBar=null;
   public JMenu AdvancedMenu=null;
   public JMenu HelpMenu=null;
   public JMenu LBCWalletMenu=null;   
   public JMenuItem ReadMeItem=null;
  
   
   public NewRunProgramThread myProgressThread=null;
   
   private static final int BUFFER_SIZE = 4096;
   
   public static Boolean runningConnectedToLBRYApp=true;
   
   public static UTXOs UTXOsController=new UTXOs();
   public static GrabbingLbryInfoForFrame LbryUserInfo=new GrabbingLbryInfoForFrame();
   public static GrabThumbnail myThumbnail=new GrabThumbnail();
   public static AWS AwsController=new AWS();
   public static Channel ChannelController=new Channel();
   
   public static String FFmpegParentFilePath="";
   //public static String AwsParentFilePath="";
   public static Boolean is64bitOs=false;
   public Robot robot;
   
   public File myFileChoosersCurrentDirectory=new File(System.getProperty("user.home") +"/Desktop");
   
public  LBRY_BOOK_INPUTTER2() throws IOException, AWTException{ 
      // getWalletAddress();
      myProgressThread=new NewRunProgramThread();
      robot=new Robot();
     CompleteChannelsName=LbryUserInfo.getChannelsCompleteName();

               File createLocation=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\");
            createLocation.mkdirs();         
        border = new EmptyBorder(5, 5, 5, 5);
           

   screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    
   screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
       
    JbtEscRobot=new EscRobotListener();
    JbtRunProgram=new RunProgramListener();
    JbtmyFileChooser=new FileChooserListener();
   JbtmyDisplayMetadata=new DisplayMetadataListener();
   //JbtmyCreateUTXOs=new CreateUTXOsListener();
   //JbtEscUTXOsFrame =new EscUTXOsFrameListener();
   //JbtRunUTXOsProgram=new RunUTXOsProgramListener();
   JbtReadMeMenuItem=new ReadMeMenuItemListener();
   
   
    frame = new JFrame("LBRY Uploader");
   
    myPublishFiles=new File[1000000]; 
   
    myPublishFilesCounter=0;
   
    experimentLayout = new GridLayout(0,2);
    experimentLayout2 = new GridLayout(0,3);
    
    if(runningConnectedToLBRYApp){
    ChannelsComboBox= new JComboBox(LbryUserInfo.getChannels());        
    }
    else{
    String[] fakeChannels=new String[2];
    fakeChannels[0]="Option1";
    fakeChannels[1]="Option2";
    ChannelsComboBox=new JComboBox(fakeChannels);    
    }
    ChannelsComboBox.setSelectedIndex(0);
    ChannelController.setChannelComboBox(ChannelsComboBox);
    ChannelController.setChannelIds(LbryUserInfo.getChannelIds());
    LanguageComboBox= new JComboBox(LbryUserInfo.getLanguages());
    LanguageComboBox.setSelectedIndex(0);
    


   menuBar=new JMenuBar();
   AdvancedMenu=new JMenu("Advanced");
   HelpMenu=new JMenu("Help");
   ReadMeItem=new JMenuItem("Read Me");
   LBCWalletMenu = new JMenu("Wallet: "+LbryUserInfo.getLBCValueInWallet()+" LBC");
  JSeparator vertSep=new JSeparator(JSeparator.VERTICAL);
   AdvancedMenu.add(ChannelController.ChannelItem);
   HelpMenu.add(ReadMeItem);
   menuBar.add(AdvancedMenu);
  // menuBar.add(vertSep);
   menuBar.add(HelpMenu);
   menuBar.add(Box.createHorizontalGlue());
   menuBar.add(LBCWalletMenu);
   
   
       
     button1 = new JButton("ESC");
     button3 = new JButton("ESC");
     button2 = new JButton("Run Program");
     jbtChooseFiles = new JButton("Choose Files.");
     //jbtCreateUTXOs= new JButton("Create UTXOs.");
     jbtDisplayMetadata = new JButton("Display Metadata");
    // jbtUTXOsRunProgram= new JButton("Create UTXOs");
    // jbtUTXOsExitFrame= new JButton("Exit");
     
     FilePath=new JLabel("Choose Files."); 
     ChannelName=new JLabel("Select a Channel to publish to.");  
     Price=new JLabel("Price."); 
     Bid=new JLabel("Bid for each publish."); 
     WalletAddres=new JLabel("This is your Wallet Address."); 
     Currency2=new JLabel("Currency?");
     NSFW2=new JLabel("NSFW?"); 
     ExtractedMetadata=new JLabel("Use extracted metadata?");
     HasCover2=new JLabel("Set default Cover URL?"); 
     HasCover2.setToolTipText("Choose whether or not to set the default thumbnail for when an image cant be grabbed from the file.");
     CoverURL=new JLabel("Default Cover URL."); 
     CoverURL.setToolTipText("Image used for the thumbnail when an image cant be grabbed from the file.");
     LanguageLabel=new JLabel("Choose your language.");
     TagsLabel=new JLabel("Write in your tags seperated by commas.");
     
    URLNameLabel=new JLabel("URL Name.");
    TitleLabel=new JLabel("Published Title.");
    LicenseLabel=new JLabel("Choose License.");
    LicenseComboBox= new JComboBox(LbryUserInfo.getLicenses());
    LicenseComboBox.setSelectedIndex(0);
    jtfURLName=new JTextField("Title_with_Extension",50);
    jtfTitle=new JTextField("Title_without_Extension",50);
    myFilesInfoTextArea=new JTextArea("Choose your Files...");
    myFilesInfoScrollPane=new JScrollPane(myFilesInfoTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    myFilesInfoTextArea.setEditable(false);
    myFilesInfoTextArea.setLineWrap(true);
    myFilesInfoScrollPane.setSize((int)(screenSize.width*.85),(int)(screenSize.height*.30));  
         
     jtfPrice=new JTextField("0.05",50);
     jtfBid=new JTextField("0.00001",50);
     
     if(runningConnectedToLBRYApp){
     jtfWalletAddres=new JTextField(LbryUserInfo.getWalletAddress(),50);  
     }
     else{
     jtfWalletAddres=new JTextField("XXXXXXXXXXXXXXXXXXXX",50); 
     }
     
     jtfCoverURL=new JTextField("https://i.imgur.com/TI60tyj.jpg",200);
     jtfTags=new JTextField("",5000);
     
    myProgressTextArea=new JTextArea("Starting...");
    myDescription=new JTextArea("DESCRIPTION FOR YOUR FILES GO IN THIS BLOCK! \r\nDelete all of this and edit it to what you want to see as your description. \r\nEverything after the collons below are variables you can use for your description! \r\n \r\nTitle: Title_without_Extension \r\nFile Name: Title_with_Extension \r\nFile Path: "
            + "Absolute_file_Path \r\nThe Number of the File in the List being publish:"
            + " My_file_Counter \r\nYour Cover Url: Cover_URL \r\nYour Wallet Address: Wallet_Address \r\nYour Price Set on the file: "
            + "Price_of_Publish \r\nYour Currency on the price: Currency_of_Publish \r\nYour chosen language: "
            + "Chosen_Language \r\nThe Size of your file: File_Size \r\nYour Channel Name: Channel_Name \r\nYour Vid/Gif Duration: VidGif_Duration \r\nYour Vid/Gif Framerate: VidGif_Framerate \r\nYour Vid/Gif/Img height in pixels: File_Height \r\nYour Vid/Gif/Img width in pixels: File_Width \r\nYour File Extension: File_Extension");
    
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
   
    File destFolder=new File(System.getProperty("user.dir"));    
    File srcFolder=new File(destFolder.getPath().substring(0, destFolder.getPath().length()-5)+File.separator+"src");
    StartUpImage=(new ImageIcon(srcFolder.getPath()+"\\splash.png")).getImage();
    System.out.println(srcFolder.getPath()+"\\splash.png");
    myPanel.setBorder(border);
    myPanel2.setBorder(border);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize((int)(screenSize.width*.85), (int)(screenSize.height*.85));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setIconImage(StartUpImage);
    
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
    jrbHasCoverTrue.setSelected(true);
   
    CurrencyButtonGroup.add(jrbCurrencyUSD);
    CurrencyButtonGroup.add(jrbCurrencyLBC);
    jrbCurrencyUSD.setSelected(true);

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
       myPanel2.add(button1);
       myPanel2.add(button2);
       myPanel2.add(UTXOsController.jbtCreateUTXOs);

       myBottomParentPanel.setLayout(experimentLayout);
       myBottomParentPanel.add(myPanel);
       myBottomParentPanel.add(myPanel2);
          // File destFolder2=new File(System.getProperty("user.dir"));    

         //myFilesInfoTextArea.setText(destFolder2.getPath().substring(0, destFolder2.getPath().length()-5)+File.separator+"src");      
       myTopParentPanel.setLayout(experimentLayout);
       myTopParentPanel.add(myScrollPane);
       myTopParentPanel.add(myFilesInfoScrollPane);
       
       frame.add(myTopParentPanel,BorderLayout.CENTER);
       frame.add(myBottomParentPanel,BorderLayout.SOUTH);

       frame.setJMenuBar(menuBar);
       frame.setVisible(true);

       button1.addActionListener(JbtEscRobot);
       button2.addActionListener(JbtRunProgram);
       button3.addActionListener(JbtEscRobot);
       jbtChooseFiles.addActionListener(JbtmyFileChooser);
//       jbtCreateUTXOs.addActionListener(JbtmyCreateUTXOs);
//       jbtUTXOsRunProgram.addActionListener(JbtRunUTXOsProgram);
//       jbtUTXOsExitFrame.addActionListener(JbtEscUTXOsFrame);
       ReadMeItem.addActionListener(JbtReadMeMenuItem);
       
}

public static void main(String[] args)throws AWTException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

LbryRunningInitialCheck();
setTheLookAndFeel();
deleteImagesInSrcFolder();
setFFmpegFilePath();
setAwsFilePath();
isAwsInstalled();
AwsController.ConfigureAWS();  
myThumbnail.setParentFilePath(FFmpegParentFilePath);
//AwsController.setParentFilePath(AwsParentFilePath);
  // pdfToImage();
  //CreateGIF();
  // vidToImage();

  
  app=new LBRY_BOOK_INPUTTER2();
  //for(int x=1;x<=601;x++){
  // myThumbnail.CreateGifWithFfmpegImagesToGif(System.getProperty("user.dir")+"\\lib\\ffmpeg-win64\\bin", "C:\\Users\\User 1\\Desktop\\ffmpegTestResult\\frames%d.png", "dbz2");
  //AwsController.UploadThumbnailtoAWS("new.gif", System.getProperty("user.dir")+"\\src\\Images\\", "TEST","cp");
  // myThumbnail.CreateGifWithFfmpegVidToGif(System.getProperty("user.dir")+"\\lib\\ffmpeg-win64\\bin" ,"15.0", "25.0", "C:\\Users\\User 1\\Desktop\\test\\new.mp4", "mynewgif");
  // String filepath=System.getProperty("user.home") +"/Desktop/ffmpegTest/frames"+x+".png";
  // ImageIOUtil.writeImage(myThumbnail.ScaleImageSize(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(filepath),16,9),150),System.getProperty("user.home") +"/Desktop/ffmpegTestResult/frames"+x+".png",300);
  //}
 
    }

public static void setFFmpegFilePath(){
  
    
          boolean is64bit = false;
          is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
          FFmpegParentFilePath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\ffmpeg-win64\\bin";
if (System.getProperty("os.name").contains("Windows")) {
    is64bit = (System.getenv("ProgramFiles(x86)") != null);
    if(is64bit){
    FFmpegParentFilePath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\ffmpeg-win64\\bin"; 
    }
    else{
     FFmpegParentFilePath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\ffmpeg-win32\\bin";    
    }
} else if(System.getProperty("os.name").contains("Mac")){
    if(is64bit){
     FFmpegParentFilePath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\ffmpeg-macos64\\bin";    
    }
}
    else if(System.getProperty("os.name").contains("Linux")){
          if(is64bit){
     FFmpegParentFilePath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\ffmpeg-Linux64\\bin";    
    }
          else{
            FFmpegParentFilePath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\ffmpeg-Linux32\\bin";    
          }
    }
  is64bitOs=is64bit;  

}

public static void isAwsInstalled(){
  
        Boolean AwsNotInstalled=true;
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "aws --version");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "aws --version"); 
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                    "cmd.exe", "/c", "aws --version");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "aws --version");   
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            
           
            while ((line = r.readLine()) != null) {
                line=line.trim();
                if(line.startsWith("aws-cli")){
                    AwsNotInstalled=false;
                }
                System.out.println(line);
            }
            if(AwsNotInstalled){
                                 JFrame Error = new JFrame("Error");
                 if(JOptionPane.showConfirmDialog(Error,
         "It appears you dont have AWS installed, which is needed to upload thumbnails. \r\nIf you install it, restart the LBRY Mass Uploader to be able to upload thumbnails. \r\nDo you wish to install it now?",
         "Error",
         JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){   
             ProcessBuilder builder2 = new ProcessBuilder(
                    "cmd.exe", "/c",  "cd \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\Amazon\""+"&& start Win64.msi"  );
            if(System.getProperty("os.name").contains("Windows")){
                if(is64bitOs){
               builder2 = new ProcessBuilder(
                    "cmd.exe", "/c",  "cd \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\Amazon\""+"&& start Win64.msi"  ); 
                }
                else{
                 builder2 = new ProcessBuilder(
                    "cmd.exe", "/c",  "cd \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\Amazon\""+"&& start Win32.msi"  );    
                }
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder2 = new ProcessBuilder(
                    "cmd.exe", "/c",  "cd \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\Amazon\""+"&& start Win64.msi"  );  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder2 = new ProcessBuilder(
                    "cmd.exe", "/c",  "cd \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\lib\\Amazon\""+"&& start Win64.msi"  );   
            }
            builder2.redirectErrorStream(true);
            Process p2 = builder2.start();   
            System.exit(0);
            }
                 else{
                     System.exit(0);
                 }       
            
            }
            
   
        } catch (IOException ex) {
                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error. With finding the amount of LBC in your LBRY Wallet. Did you download the LBRY app at lbry.com yet?",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      
}

public static void setAwsFilePath(){
//          boolean is64bit = false;
//          is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
//          AwsParentFilePath=System.getProperty("user.dir")+"\\lib\\Amazon\\AWSCLI\\bin";
//if (System.getProperty("os.name").contains("Windows")) {
//    is64bit = (System.getenv("ProgramFiles(x86)") != null);
//    if(is64bit){
//    AwsParentFilePath=System.getProperty("user.dir")+"\\lib\\Amazon\\AWSCLI\\bin"; 
//    }
//    else{
//     AwsParentFilePath=System.getProperty("user.dir")+"\\lib\\Amazon\\AWSCLI\\bin";   
//    }
//} else if(PlatformUtil.isMac()){
//    if(is64bit){
//     AwsParentFilePath=System.getProperty("user.dir")+"\\lib\\Amazon\\AWSCLI\\bin";   
//    }
//}
//    else if(PlatformUtil.isLinux() || PlatformUtil.isUnix()){
//          if(is64bit){
//     AwsParentFilePath=System.getProperty("user.dir")+"\\lib\\Amazon\\AWSCLI\\bin";   
//    }
//          else{
//            AwsParentFilePath=System.getProperty("user.dir")+"\\lib\\Amazon\\AWSCLI\\bin";   
//          }
//    }
    

}

public static void LbryRunningInitialCheck(){
 
    if(runningConnectedToLBRYApp){
    JFrame Warning = new JFrame("Message LBRY Status");
    
 try{
     if(LbryUserInfo.isLBRYRunning()==false){
       
        JOptionPane.showMessageDialog(Warning,
        "LBRY needs to be open and running.",
        "Message",
        JOptionPane.ERROR_MESSAGE);
        System.exit(0);
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
 
 }   
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

public class EscRobotListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    System.exit(0);
    }}

public class ReadMeMenuItemListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    
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
     chooser.setCurrentDirectory(myFileChoosersCurrentDirectory);
     ///////For Testing and should be romoved after testing
     //chooser.setCurrentDirectory(new File("I:\\UploaderTestFolder"));
     /////////
     chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
     chooser.setMultiSelectionEnabled(true);
     chooser.showDialog(frame,"Select Files");
     
     File[] files=chooser.getSelectedFiles();
     myFileChoosersCurrentDirectory=chooser.getCurrentDirectory();
     
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
      else{
        jbtChooseFiles.setText("Choose Files.");
    myFilesInfoTextArea.setText("Choose your Files...");
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
   myProgressThread=new NewRunProgramThread();
  
   progressBar.setMinimum(0);
   progressBar.setMaximum(myPublishFilesCounter);
   progressBar.setStringPainted(true);
   ProgressPanel.add(myProgressScrollPane);
   ProgressPanel.add(progressBar);
  // ProgressPanel.add(button3);
   Cursor WaitCursor=new Cursor(Cursor.WAIT_CURSOR);
   final Cursor DefaultCursor=new Cursor(Cursor.DEFAULT_CURSOR);
   ProgressPanel.setCursor(WaitCursor);
   
   ProgressPanel.setLayout(experimentLayout);
   ProgressFrame.setLocationRelativeTo(null);
   ProgressFrame.setSize(700,150);
   ProgressFrame.setTitle("Publishing...");
   ProgressFrame.add(ProgressPanel);
   ProgressFrame.setIconImage(StartUpImage);
   //ProgressFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   ProgressFrame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
myProgressThread.stopRunning();
ProgressFrame.setVisible(false);
ProgressPanel.setCursor(DefaultCursor);
RestartProgram(); 
    }
});
   ProgressFrame.setVisible(true);
   ProgressFrame.setLocationRelativeTo(null);
     
      
      
     myProgressThread.start();

    }}

public class NewRunProgramThread extends Thread{
    private volatile boolean flag = true;
    
    public void stopRunning()
    {
        flag = false;
    }
    
    @Override
    public void run(){
   Cursor DefaultCursor=new Cursor(Cursor.DEFAULT_CURSOR);
  
   
  
     
     
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
     if(LbryUserInfo.isLBRYRunning()==false){
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
          if(flag){
      // ProgressFrame.notify();
       //ProgressFrame.repaint();
       //ProgressFrame.update(ProgressFrame.getGraphics());
       
        myProgressTextArea.setText(String.valueOf(i+1)+" of "+String.valueOf(myPublishFilesCounter)+"  "+myPublishFiles[i].getName());
        myProgressTextAreaCurrentString=String.valueOf(i+1)+" of "+String.valueOf(myPublishFilesCounter)+"  "+myPublishFiles[i].getName();
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
      }

 
    //  }
  
    }}
     
     // System.exit(0);
     ProgressPanel.setCursor(DefaultCursor);
     RestartProgram(); 
    }
}
 
public class FileSizeandNotation{
 long FileSize;
 String myFileSizeNotation;
 public FileSizeandNotation(long myFileSize){
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
      FileSize=myFileSize;
 }
 public long getFileSize() {
            return FileSize;
        }
 public String getMyFileSizeNotation() {
            return myFileSizeNotation;
        }   
}

public void Process(File FilePath,String channelName, double price, String walletAddress, String CoverURL, String Description, int Counter,String[] Tags) throws IOException{
    
   long myFileSize=FilePath.length();
   FileSizeandNotation myFileSizeandNotation=new FileSizeandNotation(myFileSize); 
   String myFileSizeNotation=myFileSizeandNotation.getMyFileSizeNotation();
   myFileSize=myFileSizeandNotation.FileSize;

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
      String TitleExtension="";
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
      int lastDotLocation=0;
   for(int x=0;x<temp.length();x++){
          if(temp.charAt(x)=='.'){
       lastDotLocation=x;  
      }   
   }
   TitleWithoutSeries=temp.substring(0,lastDotLocation);
   TitleExtension=temp.substring(lastDotLocation+1,temp.length());
//    for(int i=0;i<temp.length();i++){
//        if(hasntFoundDot==false){
//         TitleExtension=TitleExtension.concat(temp.substring(i, i+1));   
//        }
//       
//      if(temp.charAt(i)!='.' && hasntFoundDot){
//       TitleWithoutSeries=TitleWithoutSeries.concat(temp.substring(i, i+1));
//      }
//       else{
//         hasntFoundDot=false;
//       }
//      if(temp.charAt(i)=='.'){
//       TitleExtension="";   
//      }
//        
//    }

String myAbsoluteFilePath2="";

   for(int g=0;g<50;g++){
    if(ParentFolderNames[g]!=""){
      myAbsoluteFilePath2=myAbsoluteFilePath2.concat(ParentFolderNames[g]+"\\\\\\\\");
    }
   }
   myAbsoluteFilePath2.concat(FilePath.getName());
   myAbsoluteFilePath2=myAbsoluteFilePath2.substring(0, myAbsoluteFilePath2.length()-4);
   BookTitle=getVariable(BookTitle, TitleWithoutSeries, myAbsoluteFilePath2, myCounter, CoverURL, walletAddress, price, currency,myFileSize,myFileSizeNotation,FilePath.getName(),channelName,TitleExtension);
   PublishedTitle=getVariable(PublishedTitle, TitleWithoutSeries, myAbsoluteFilePath2, myCounter, CoverURL, walletAddress, price, currency,myFileSize,myFileSizeNotation,FilePath.getName(),channelName,TitleExtension);    

   
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

   

System.out.println("TitleExtension "+TitleExtension);
System.out.println("Parent Filepath "+FilePath.getParent());
System.out.println("Complete channel name "+CompleteChannelsName[ChannelsComboBox.getSelectedIndex()]);
System.out.println("File name "+FilePath.getName());
System.out.println("absolute file path "+myAbsoluteFilePath);
System.out.println("TitleWithoutExtension "+TitleWithoutSeries);

TitleExtension=TitleExtension.toLowerCase();

     if(TitleExtension.startsWith("pdf") && jrbHasCoverTrue.isSelected()){
         deleteImagesInSrcFolder();
      try{   
      myThumbnail.pdfToImage(myAbsoluteFilePath, TitleWithoutSeries); 
      ImageIOUtil.writeImage(myThumbnail.ScaleImageSize(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+TitleWithoutSeries+".png"),16,9),300),System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+TitleWithoutSeries+".png",300);
      AwsController.UploadThumbnailtoAWS(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+TitleWithoutSeries+".png", CompleteChannelsName[ChannelsComboBox.getSelectedIndex()],"mv");
      CoverURL="https://mass-uploader-thumbnails.s3.amazonaws.com/"+CompleteChannelsName[ChannelsComboBox.getSelectedIndex()]+"/"+TitleWithoutSeries+".png";
      }
        catch (NoSuchMethodError e){
          CoverURL=jtfCoverURL.getText();
        }
              catch (NullPointerException e){
          CoverURL=jtfCoverURL.getText();
        }
        
      System.out.println("CoverURL "+CoverURL);
     }
     else if((TitleExtension.startsWith("jpg") || TitleExtension.startsWith("png")) && jrbHasCoverTrue.isSelected()){
         deleteImagesInSrcFolder();
      ImageIOUtil.writeImage(myThumbnail.ScaleImageSize(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(FilePath.getPath()),16,9),300),System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+TitleWithoutSeries+".png",300);
      AwsController.UploadThumbnailtoAWS(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+TitleWithoutSeries+".png",CompleteChannelsName[ChannelsComboBox.getSelectedIndex()],"cp"); 
      CoverURL="https://mass-uploader-thumbnails.s3.amazonaws.com/"+CompleteChannelsName[ChannelsComboBox.getSelectedIndex()]+"/"+FilePath.getName();
       System.out.println("CoverURL "+CoverURL);
     }  
     else if(TitleExtension.startsWith("gif")&& jrbHasCoverTrue.isSelected()){
         p("Made it to gif...");
         long MaxFileSize=15000000;
         //ffmpeg -i video.webm thumb%04d.jpg -hide_banner
         deleteImagesInSrcFolder();
          String newtxtArea=myProgressTextAreaCurrentString+" \r\n "+"Testing for corrupted file...";
          myProgressTextArea.setText(newtxtArea);
          if(myThumbnail.TestForCorruptedVid(myAbsoluteFilePath)){
               String txtArea=myProgressTextAreaCurrentString+" \r\n "+"Getting Images from gif...";
          myProgressTextArea.setText(txtArea);
          myProgressTextArea.setText(FFmpegParentFilePath);
          myThumbnail.CreateImagesWithFfmpegGifToImages(myAbsoluteFilePath,System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\frames%04d.png"); 
          txtArea=myProgressTextAreaCurrentString+" \r\n "+"Scaling Images for gif...";
          myProgressTextArea.setText(txtArea); 
          File GifImageLocation=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\");
          String[] gifImages=GifImageLocation.list();
          for(int i=0;i<gifImages.length;i++){
        String filepath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+gifImages[i];
       ImageIOUtil.writeImage(myThumbnail.ScaleImageSize(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(filepath),16,9),300),filepath,300);   
          }
          myThumbnail.CreateGifWithFfmpegImagesToGif(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\frames%04d.png", TitleWithoutSeries,MaxFileSize);
          //  ImageIOUtil.writeImage(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(FilePath.getPath()),16,9),System.getProperty("user.dir")+"\\src\\Images\\"+TitleWithoutSeries+".png",300);
 AwsController.UploadThumbnailtoAWS(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+TitleWithoutSeries+".gif", CompleteChannelsName[ChannelsComboBox.getSelectedIndex()],"cp");
 CoverURL="https://mass-uploader-thumbnails.s3.amazonaws.com/"+CompleteChannelsName[ChannelsComboBox.getSelectedIndex()]+"/"+TitleWithoutSeries+".gif";
          }
     }
    else if((TitleExtension.startsWith("mpeg-1") || TitleExtension.startsWith("mpeg-2") || TitleExtension.startsWith("mpeg-4")|| TitleExtension.startsWith("m-jpeg") || TitleExtension.startsWith("mjpeg")|| TitleExtension.startsWith("av1") || TitleExtension.startsWith("dv") || TitleExtension.startsWith("avs")|| TitleExtension.startsWith("wmv") || TitleExtension.startsWith("apng")|| TitleExtension.startsWith("avi")|| TitleExtension.startsWith("hdv")|| TitleExtension.startsWith("wav")|| TitleExtension.startsWith("wma")|| TitleExtension.startsWith("mp4")|| TitleExtension.startsWith("mov")|| TitleExtension.startsWith("webm")|| TitleExtension.startsWith("flv")|| TitleExtension.startsWith("mpeg")|| TitleExtension.startsWith("mkv")|| TitleExtension.startsWith("mov")|| TitleExtension.startsWith("asf")|| TitleExtension.startsWith("divx")|| TitleExtension.startsWith("mpg")) && jrbHasCoverTrue.isSelected()){
                String newtxtArea=myProgressTextAreaCurrentString+" \r\n "+"Testing for corrupted file...";
          myProgressTextArea.setText(newtxtArea);
        if(myThumbnail.TestForCorruptedVid(myAbsoluteFilePath)){
int NumOfFrames=250;
double PercentageIntoVid=.50;
long MaxFileSize=15000000;
deleteImagesInSrcFolder();

    // String userDir=System.getProperty("user.dir");
    //String userDir2=userDir.replaceAll("\\", "/");
         String txtArea=myProgressTextAreaCurrentString+" \r\n "+"Getting Images from vid for gif...";
          myProgressTextArea.setText(txtArea);
        myThumbnail.CreateImagesWithFfmpegVidToImages(myAbsoluteFilePath, PercentageIntoVid,NumOfFrames,System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\frames%04d.png"); 
        //robot.delay(50000);
          txtArea=myProgressTextAreaCurrentString+" \r\n "+"Scaling Images for gif...";
          myProgressTextArea.setText(txtArea);
          NumOfFrames=myThumbnail.NumberOfFramesEnd;
      for(int x=1;x<=NumOfFrames+1;x++){
       String filepath="";
       //System.out.println(filepath);
       myProgressTextArea.setText(String.valueOf(x));
       if(x<10){
           filepath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\frames000"+x+".png";
       ImageIOUtil.writeImage(myThumbnail.ScaleImageSize(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(filepath),16,9),300),filepath,300);
       }
       else if(x<100){
           filepath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\frames00"+x+".png";
        ImageIOUtil.writeImage(myThumbnail.ScaleImageSize(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(filepath),16,9),300),filepath,300);   
       }
       else if(x<1000){
           filepath=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\frames0"+x+".png";
        ImageIOUtil.writeImage(myThumbnail.ScaleImageSize(myThumbnail.ScaleImage(myThumbnail.getBufferedImageFromImageFilePath(filepath),16,9),300),filepath,300);   
       }
      }
    
          txtArea=myProgressTextAreaCurrentString+" \r\n "+"Creating gif from images...";
          myProgressTextArea.setText(txtArea);
           myProgressTextArea.setText(FFmpegParentFilePath);
             robot.delay(5000);
       myThumbnail.CreateGifWithFfmpegImagesToGif(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\frames%04d.png", TitleWithoutSeries,MaxFileSize);
       txtArea=myProgressTextAreaCurrentString+" \r\n "+"Sending gif to server...";
       myProgressTextArea.setText(txtArea);
       AwsController.UploadThumbnailtoAWS(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+TitleWithoutSeries+".gif", CompleteChannelsName[ChannelsComboBox.getSelectedIndex()],"cp");
      CoverURL="https://mass-uploader-thumbnails.s3.amazonaws.com/"+CompleteChannelsName[ChannelsComboBox.getSelectedIndex()]+"/"+TitleWithoutSeries+".gif";
      }}
    else{
          CoverURL=jtfCoverURL.getText();       
    }
//     else if(TitleExtension.startsWith("epub") && jrbHasCoverTrue.isSelected()){
//      epubToImage(myAbsoluteFilePath, System.getProperty("user.dir")+"\\src\\Images\\"+TitleWithoutSeries+".png"); 
//      ImageIOUtil.writeImage(ScaleImage(getBufferedImageFromImageFilePath(System.getProperty("user.dir")+"\\src\\Images\\"+TitleWithoutSeries+".png"),16,9),System.getProperty("user.dir")+"\\src\\Images\\"+TitleWithoutSeries+".png",300);
//      UploadThumbnailtoAWS(TitleWithoutSeries+".png", System.getProperty("user.dir")+"\\src\\Images\\", CompleteChannelsName[ChannelsComboBox.getSelectedIndex()],"cp");
//      CoverURL="https://mass-uploader-thumbnails.s3.amazonaws.com/"+CompleteChannelsName[ChannelsComboBox.getSelectedIndex()]+"/"+TitleWithoutSeries+".png";
//     }
     CoverURL=CoverURL.replaceAll("#", "%23");
     CoverURL=CoverURL.replaceAll(" ", "+");

     Description=getVariable(Description, TitleWithoutSeries, myAbsoluteFilePath2, myCounter, CoverURL, walletAddress, price, currency,myFileSize,myFileSizeNotation,FilePath.getName(),channelName,TitleExtension);
  
     PostParams=PostParams.substring(0, PostParams.length()-2);
     PostParams=PostParams.concat("\","+"\n"+"\"bid\": \""+jtfBid.getText()+"\","+"\n"+"\"description\":\""+Description);  
     PostParams= PostParams.concat("\","+"\n");

 
   if(price !=0.00){
     PostParams= PostParams.concat("\"fee_address\": \""+walletAddress+"\","+"\n"+"\"fee_amount\": "+price+",\n"+"\"fee_currency\": \""+currency+"\","+"\n");
   }
   PostParams=PostParams.concat("\"title\": \""+PublishedTitle+"\","+"\n");
   if(jrbHasCoverTrue.isSelected()){
      PostParams=PostParams.concat("\"thumbnail_url\": \""+CoverURL+"\",\n");
   }
if((jrbNSFWTrue.isSelected())||jtfTags.getText().length()>1){
    TagsCombo=getVariable(TagsCombo, TitleWithoutSeries, myAbsoluteFilePath2, myCounter, CoverURL, walletAddress, price, currency,myFileSize,myFileSizeNotation,FilePath.getName(),channelName,TitleExtension);   
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
   String txtArea=myProgressTextAreaCurrentString+" \r\n "+"Publishing to LBRY...";
   myProgressTextArea.setText(txtArea);
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

public String getVariable(String input, String TitleWithoutSeries, String myAbsoluteFilePath, String myCounter, String CoverURL, String walletAddress, double price, String currency, long myFileSize, String myFileSizeNotation, String FilePathString, String channelName,String extension){
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
     input=input.replaceAll("VidGif_Duration",myThumbnail.VidDuration);
     input=input.replaceAll("VidGif_Framerate",myThumbnail.VidFps);
     input=input.replaceAll("File_Height",myThumbnail.VidHeight);
     input=input.replaceAll("File_Width",myThumbnail.VidWidth);
     input=input.replaceAll("File_Extension",extension);

    return input;
}

public void RestartProgram(){
    ProgressFrame.setVisible(false);
 
    myPublishFiles=new File[1000000];   
    myPublishFilesCounter=0;
    LBCWalletMenu.setText("Wallet: "+LbryUserInfo.getLBCValueInWallet()+" LBC");
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
    myProgressThread=new NewRunProgramThread();
    deleteImagesInSrcFolder();
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

public static void RunLBRYNET(){

        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet start");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet start"); 
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /Applications/LBRY.app/Contents/Resources/static/daemon\" && lbrynet start");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /opt/LBRY/resources/static/daemon\" && lbrynet start");   
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            String id="";
//            String TotalLBCInWallet="";
//            while ((line = r.readLine()) != null) {
//                //line = r.readLine();
//                line=line.trim();
//                if(line.startsWith("\"id\":")){
//                    id=line.substring(7, line.length()-2);
//                }
//                 if(line.startsWith("\"coins\":")){
//                    TotalLBCInWallet=line.substring(9, line.length()-1);
//                }
//                if (line == null) { break; }
//                System.out.println(line);
//            }
       ImageIcon loadingicon = new ImageIcon(new URL("https://media.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif")); 
        JFrame Update = new JFrame("Connecting to LBRY...");
                 JOptionPane.showMessageDialog(Update,
         "Connecting to LBRY...",
         "Loading...",
         JOptionPane.PLAIN_MESSAGE,loadingicon);

        } catch (IOException ex) {

                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error. With connecting to LBRY. Make sure to download the app from lbry.com",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
    }

public static void deleteImagesInSrcFolder(){
  File srcFolder=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\");
  if(srcFolder.exists()){
  String[] ImagesFiles=srcFolder.list();
  for(int x=0;x<ImagesFiles.length;x++){
  File Image=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+ImagesFiles[x]);
  if(Image.delete()){
  //System.out.println("Succuess");    
  }
  }}
}

public static void p(String thingToPrint){
    System.out.println(thingToPrint);
}


}