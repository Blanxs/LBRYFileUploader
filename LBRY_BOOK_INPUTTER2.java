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
   
   public File[] myPublishFiles=null; 
   
   public int myPublishFilesCounter;
   
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
   public JLabel WalletAddres=null; 
   public JLabel Currency2=null;
   public JLabel NSFW2=null;
   public JLabel ExtractedMetadata=null;
   public JLabel HasCover2=null; 
   public JLabel CoverURL=null; 
   public JLabel LanguageLabel=null;
   public JLabel TagsLabel=null;
   
   public JTextField jtfChannelName=null;     
   public JTextField jtfPrice=null;
   public JTextField jtfWalletAddres=null;  
   public JTextField jtfCoverURL=null;
   public JTextField jtfTags=null;
   
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
   
public  LBRY_BOOK_INPUTTER2() throws IOException{ 
      // getWalletAddress();
            
        border = new EmptyBorder(5, 5, 5, 5);
           
         
         
   screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    
   screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
       
    JbtEscRobot=new EscRobotListener();
    JbtRunProgram=new RunProgramListener();
    JbtmyFileChooser=new FileChooserListener();
   JbtmyDisplayMetadata=new DisplayMetadataListener();
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
     jbtDisplayMetadata = new JButton("Display Metadata");
     
     FilePath=new JLabel("Choose Files."); 
     ChannelName=new JLabel("Select a Channel to publish to.");  
     Price=new JLabel("Price."); 
     WalletAddres=new JLabel("This is your Wallet Address."); 
     Currency2=new JLabel("Currency?");
     NSFW2=new JLabel("NSFW?"); 
     ExtractedMetadata=new JLabel("Use extracted metadata?");
     HasCover2=new JLabel("Has Cover?"); 
     CoverURL=new JLabel("Cover URL."); 
     LanguageLabel=new JLabel("Choose your language.");
     TagsLabel=new JLabel("Write in your tags seperated by commas.");
     
     jtfChannelName=new JTextField("TestChannel",200);     
     jtfPrice=new JTextField("0.00",50);
     jtfWalletAddres=new JTextField(getWalletAddress(),50);  
     //jtfWalletAddres=new JTextField("XXXXXXXXXXXXXXXXXXXX",50);  
     jtfCoverURL=new JTextField("https://i.imgur.com/TI60tyj.jpg",200);
     jtfTags=new JTextField("",5000);
     
    myProgressTextArea=new JTextArea("Starting...");
    myDescription=new JTextArea("DESCRIPTION FOR YOUR FILES GO IN THIS BLOCK! \r\nDelete all of this and edit it to what you want to see as your description. \r\nEverything after the collons below are variables you can use for your description! \r\n \r\nTitle: Title_without_Extension \r\nFile Name: Title_with_Extension \r\nFile Path: "
            + "Absolute_file_Path \r\nThe Number of the File in the List being publish:"
            + " My_file_Counter \r\nYour Cover Url: Cover_URL \r\nYour Wallet Address: Wallet_Address \r\nYour Price Set on the file: "
            + "Price_of_Publish \r\nYour Currency on the price: Currency_of_Publish \r\nYour chosen language: "
            + "Chosen_Language \r\nThe Size of your file: File_Size");
    
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
   
     StartUpImage=(new ImageIcon(System.getProperties().getProperty("user.dir")+"/src/lbry_book_inputter2/splash.png")).getImage();
    
    
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
       myPanel.add(ChannelName);
       //myPanel.add(jtfChannelName);
       myPanel.add(ChannelsComboBox);
       myPanel.add(LanguageLabel);
       myPanel.add(LanguageComboBox);
       myPanel.add(TagsLabel);
       myPanel.add(jtfTags);
       myPanel.add(Price);
       myPanel.add(jtfPrice);
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
     //  myPanel2.add(jbtDisplayMetadata);
       
       myBottomParentPanel.setLayout(experimentLayout);
       myBottomParentPanel.add(myPanel);
       myBottomParentPanel.add(myPanel2);
       
//       myEastParentPanel.add(myScrollPane);
//       myWestParentPanel.add(myPanel,BorderLayout.NORTH);
//       myWestParentPanel.add(myPanel2,BorderLayout.SOUTH);
       
//       myTopParentPanel.setLayout(experimentLayout);
//       myTopParentPanel.add(myPanel);
       
       //frame.add(myPanel,BorderLayout.NORTH);
      // frame.add(myTopParentPanel,BorderLayout.NORTH);
       frame.add(myScrollPane,BorderLayout.CENTER);
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
    
    
}

public static void main(String[] args)throws AWTException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
 
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
            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");                     
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } 
 
    
       app=new LBRY_BOOK_INPUTTER2();
    }
    
public class EscRobotListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    System.exit(0);
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
   ProgressFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
   
   
  
     
     
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


     if(jtfChannelName.getText().length()>1){
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
  
    }}}
      System.exit(0);
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
      String BookTitle=FilePath.getName();  
      String TitleWithoutSeries="";   
      String PostParams="";
   if(channelName.startsWith("anonymous")){
       PostParams=PostParams.concat(openbracket+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"publish\","+"\n"+"\"params\":"+"\n"+openbracket+"\n"+"\"name\": \"");
       
   }
   else if(channelName.startsWith("Anonymous")){
       PostParams=PostParams.concat(openbracket+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"publish\","+"\n"+"\"params\":"+"\n"+openbracket+"\n"+"\"name\": \"");
       
   }
   else{
      PostParams=PostParams.concat(openbracket+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"publish\","+"\n"+"\"params\":"+"\n"+openbracket+"\n"+"\"channel_name\": \"@"+channelName+"\","+"\n"+"\"name\": \"");
   }
      Boolean hasntFoundDot=true;
   
    for(int i=0;i<BookTitle.length();i++){
      if(BookTitle.charAt(i)!='.' && hasntFoundDot){
       TitleWithoutSeries=TitleWithoutSeries.concat(BookTitle.substring(i, i+1));
      }
       else{
         hasntFoundDot=false;
       }
        
    }

       
      //System.out.println(BookTitle);
      //Change it so that it only accepts acceptable characters instead of trying to eliminate all the unacceptable ones. This method below should work for 99% of publishes with english characters
      BookTitle=BookTitle.replace(" ", "");
      BookTitle=BookTitle.replace("'", "");
      BookTitle=BookTitle.replace(",", "");
      BookTitle=BookTitle.replace(".", "");
      BookTitle=BookTitle.replace("!", "");
      BookTitle=BookTitle.replace("*", "");
      BookTitle=BookTitle.replace("{", "");
      BookTitle=BookTitle.replace("}", "");
      BookTitle=BookTitle.replace("[", ""); 
      BookTitle=BookTitle.replace("]", ""); 
      BookTitle=BookTitle.replace("@", "");
      BookTitle=BookTitle.replace("#", "");
      BookTitle=BookTitle.replace("$", "");
      BookTitle=BookTitle.replace("%", "");
      BookTitle=BookTitle.replace("^", "");
      BookTitle=BookTitle.replace("&", "");
      BookTitle=BookTitle.replace("-", "");
      BookTitle=BookTitle.replace("_", "");
      BookTitle=BookTitle.replace("=", "");
      BookTitle=BookTitle.replace("~", "");
      BookTitle=BookTitle.replace("(", "");
      BookTitle=BookTitle.replace(")", "");
      BookTitle=BookTitle.replace("`", "");
      BookTitle=BookTitle.replace("<", "");
      BookTitle=BookTitle.replace(">", "");
      BookTitle=BookTitle.replace("|", "");
      BookTitle=BookTitle.replace(";", "");
      BookTitle=BookTitle.replace(":", "");
      BookTitle=BookTitle.replace("?", "");
      BookTitle=BookTitle.replace("\\", "");
      BookTitle=BookTitle.replace("/", "");
      BookTitle=BookTitle.replace("+", "");

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
   
     Description=Description.replaceAll("Title_with_Extension", FilePath.getName());
     Description=Description.replaceAll("Title_without_Extension", TitleWithoutSeries);
     Description=Description.replaceAll("Absolute_file_Path", myAbsoluteFilePath);
     Description=Description.replaceAll("My_file_Counter", myCounter);
     Description=Description.replaceAll("Cover_URL", CoverURL);
     Description=Description.replaceAll("Wallet_Address", walletAddress);
     Description=Description.replaceAll("Price_of_Publish", String.valueOf(price));
     Description=Description.replaceAll("Currency_of_Publish", currency);
     Description=Description.replaceAll("Chosen_Language", (String)LanguageComboBox.getSelectedItem().toString().toLowerCase());
     Description=Description.replaceAll("File_Size", String.valueOf(myFileSize)+" "+myFileSizeNotation);
     
     //System.out.println(Description);
   
     PostParams=PostParams.substring(0, PostParams.length()-2);
     PostParams=PostParams.concat("\","+"\n"+"\"bid\": \"0.0001\","+"\n"+"\"description\":\""+Description);  
     PostParams= PostParams.concat("\","+"\n");

 
   if(price !=0.00){
      //PostParams= PostParams.concat("\"fee\":"+"\n"+openbracket+"\n"+"\"address\": \""+walletAddress+"\","+"\n"+"\"amount\": "+price+",\n"+"\"currency\": \""+currency+"\""+"\n"+closedbracket+","+"\n");
     PostParams= PostParams.concat("\"fee_address\": \""+walletAddress+"\","+"\n"+"\"fee_amount\": "+price+",\n"+"\"fee_currency\": \""+currency+"\","+"\n");
   }
   PostParams=PostParams.concat("\"title\": \""+TitleWithoutSeries+"\","+"\n");
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
 
public Boolean isLBRYRunning() throws MalformedURLException, ProtocolException, IOException{
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
    System.out.println(response);
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
}