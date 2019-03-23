
package lbry_book_inputter2;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Blanxs
 */
public class LBRY_BOOK_INPUTTER2 {

   public  static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    
   public int screenRes = Toolkit.getDefaultToolkit().getScreenResolution
       
   public static  EscRobotListener JbtEscRobot=new EscRobotListener();
   public static  RunProgramListener JbtRunProgram=new RunProgramListener();
   public static  FileChooserListener JbtmyFileChooser=new FileChooserListener();
    
   public static JFrame frame = new JFrame("LBRY Uploader");
    
   public static File[] myPublishFiles=new File[1000000]; 
    
   public static int myPublishFilesCounter=0;
    
   public static GridLayout experimentLayout = new GridLayout(0,2);
   public static GridLayout experimentLayout2 = new GridLayout(0,3);
    
   public static JButton button1 = new JButton("ESC");
   public static JButton button2 = new JButton("Run Program");
   public static JButton jbtChooseFiles = new JButton("Choose Files.");
    
   public static JLabel FilePath=new JLabel("Choose Files."); 
   public static JLabel ChannelName=new JLabel("Channel Name. Does not accept anonymous input yet.");  
   public static JLabel Price=new JLabel("Price. As a double. Like 5.0, 10.5, 2,57."); 
   public static JLabel WalletAddres=new JLabel("Wallet Address.If price is free keep it as xxxxxxxx."); 
   public static JLabel Currency2=new JLabel("Currency.");
   public static JLabel NSFW2=new JLabel("NSFW?"); 
   public static JLabel HasCover2=new JLabel("Has Cover?"); 
   public static JLabel CoverURL=new JLabel("Cover URL. If Has Cover is false, put whatever here as long as its not empty."); 
    
   public static JTextField jtfChannelName=new JTextField("TestChannel",50);     
   public static JTextField jtfPrice=new JTextField("0.00",50);
   public static JTextField jtfWalletAddres=new JTextField("xxxxxxxxxxxxxxxxxxxxxxx",50);  
   public static JTextField jtfCoverURL=new JTextField("https://i.imgur.com/TI60tyj.jpg",50);
    
   public static JTextArea myDescription=new JTextArea("DESCRIPTION FOR YOUR FILES GO IN THIS BLOCK! \r\nEverything after the collons below are variables you can use for your description! \r\n \r\nTitle: Title_without_Extension \r\nFile Name: Title_with_Extension \r\nFile Path: Absolute_file_Path \r\nThe Number of the File in the List being publish: My_file_Counter");
   public static JScrollPane myScrollPane=new JScrollPane(myDescription, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
   public static JRadioButton jrbCurrencyLBC=new JRadioButton("LBC");
   public static JRadioButton jrbCurrencyUSD=new JRadioButton("USD");
   public static JRadioButton jrbNSFWTrue=new JRadioButton("True");
   public static JRadioButton jrbNSFWFalse=new JRadioButton("False");
   public static JRadioButton jrbHasCoverTrue=new JRadioButton("True");
   public static JRadioButton jrbHasCoverFalse=new JRadioButton("False");
    
   public static ButtonGroup CurrencyButtonGroup= new ButtonGroup();
   public static ButtonGroup NSFWButtonGroup= new ButtonGroup();
   public static ButtonGroup HasCoverButtonGroup= new ButtonGroup();
    
   public static JPanel myPanel=new JPanel();
   public static JPanel myPanel2=new JPanel();
   
public static void main(String[] args)throws AWTException, IOException {
        
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize((int)(screenSize.width*.85), (int)(screenSize.height*.85));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
        
    myPanel.setBackground(Color.white);
    myPanel2.setBackground(Color.white);
        
    jrbNSFWTrue.setBackground(Color.white);
    jrbNSFWFalse.setBackground(Color.white);
    jrbHasCoverTrue.setBackground(Color.white);
    jrbHasCoverFalse.setBackground(Color.white);
    jrbCurrencyUSD.setBackground(Color.white);
    jrbCurrencyLBC.setBackground(Color.white);
  
    myDescription.setEditable(true);
    myDescription.setLineWrap(true);
    myScrollPane.setSize((int)(screenSize.width*.85),(int)(screenSize.height*.30)); 
  
    NSFWButtonGroup.add(jrbNSFWTrue);
    NSFWButtonGroup.add(jrbNSFWFalse);      
    jrbNSFWFalse.setSelected(true);

    HasCoverButtonGroup.add(jrbHasCoverTrue);
    HasCoverButtonGroup.add(jrbHasCoverFalse);
    jrbHasCoverFalse.setSelected(true);

    CurrencyButtonGroup.add(jrbCurrencyUSD);
    CurrencyButtonGroup.add(jrbCurrencyLBC);
    jrbCurrencyLBC.setSelected(true);

    myPanel.setLayout(experimentLayout);
    myPanel2.setLayout(experimentLayout2);
       
       myPanel.add(FilePath);
       myPanel.add(jbtChooseFiles);
       myPanel.add(ChannelName);
       myPanel.add(jtfChannelName);
       myPanel.add(Price);
       myPanel.add(jtfPrice);
       myPanel.add(WalletAddres);
       myPanel.add(jtfWalletAddres);
       myPanel.add(CoverURL);
       myPanel.add(jtfCoverURL);
          
       myPanel2.add(NSFW2);
       myPanel2.add(jrbNSFWTrue);
       myPanel2.add(jrbNSFWFalse);
       myPanel2.add(Currency2);
       myPanel2.add(jrbCurrencyUSD);
       myPanel2.add(jrbCurrencyLBC);
       myPanel2.add(HasCover2);
       myPanel2.add(jrbHasCoverTrue);
       myPanel2.add(jrbHasCoverFalse);
       myPanel2.add(button1);
       myPanel2.add(button2);
       
       
       frame.add(myPanel,BorderLayout.NORTH);
       frame.add(myScrollPane,BorderLayout.CENTER);
       frame.add(myPanel2,BorderLayout.SOUTH);
       
       frame.setVisible(true);
       //frame.pack();
       button1.addActionListener(JbtEscRobot);
       button2.addActionListener(JbtRunProgram);
       jbtChooseFiles.addActionListener(JbtmyFileChooser);
    
       new LBRY_BOOK_INPUTTER2();
    }
    
public LBRY_BOOK_INPUTTER2()throws AWTException, IOException{
  }

public static class EscRobotListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    System.exit(0);
    }}
    
 public static class FileChooserListener  implements ActionListener{
    @Override
  public void actionPerformed(ActionEvent e){
     JFileChooser chooser=new JFileChooser();
     chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
     chooser.setMultiSelectionEnabled(true);
     chooser.showDialog(frame,"Select Files");

     File[] files=chooser.getSelectedFiles();
   for(int x=0;x<files.length;x++){
     if(files[x].isDirectory()){
      CheckSubFolder(files[x]); 
      }
       else{
         System.out.println(files[x].getAbsolutePath()); 
         myPublishFiles[myPublishFilesCounter]=new File(files[x].getAbsolutePath());
         myPublishFilesCounter++;
       }
    }
   }}
    
 public static void CheckSubFolder(File myFile){
         String[] fileList=myFile.list();
         
         if(fileList.length>0){
             for(int x=0;x<fileList.length;x++){
                 File currentFile=new File(myFile.getAbsolutePath()+"\\"+fileList[x]);
                 if(currentFile.isDirectory()){
                     CheckSubFolder(currentFile);
                 }
                 else{
                   System.out.println(currentFile.getAbsolutePath());
                   myPublishFiles[myPublishFilesCounter]=new File(currentFile.getAbsolutePath());
                   myPublishFilesCounter++;
                 }
             }
         }
  }
    
public static class RunProgramListener  implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e){
        JFrame Warningframe1 = new JFrame("Message General Error");
        JFrame Warningframe9 = new JFrame("Message File Path");
        JFrame Warningframe2 = new JFrame("Message Channel Name");
        JFrame Warningframe3 = new JFrame("Message Price");
        JFrame Warningframe4 = new JFrame("Message Wallet Address");
        JFrame Warningframe5 = new JFrame("Message Currency");
        JFrame Warningframe6 = new JFrame("Message NSFW");
        JFrame Warningframe7 = new JFrame("Message Select Files");
        JFrame Warningframe8 = new JFrame("Message Cover URL");
            
        File myFile=new File("F:\\Comics\\Test\\");
        String channel="TestChannel";
        double myPrice=0.00;
        String myWallet="asdihasdkjhasdkjh";
        String myCoverURL="ksjdhfksjhdf";
        String Description;
        Description=myDescription.getText().replaceAll("[\r\n]+", "\\\\n");
        Description=Description.replaceAll("\"", "\\\\\"");

     if(jtfChannelName.getText().length()>1){
        channel=jtfChannelName.getText();
     }
      else{
        JOptionPane.showMessageDialog(Warningframe2,
        "You need to input a channel name.",
        "Message",
        JOptionPane.ERROR_MESSAGE);
        return;
       }
     if(jtfPrice.getText().length()>1){
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

     if(jtfCoverURL.getText().length()>1){
          myCoverURL=jtfCoverURL.getText();
     }
      else{
          JOptionPane.showMessageDialog(Warningframe8,
          "You need to input a Cover URL if Has aCover is false, it wont be placed.But something needs to be in the input.",
          "Message",
          JOptionPane.ERROR_MESSAGE);
          return;
      }


     if(jtfChannelName.getText().length()>1){
     if(jtfPrice.getText().length()>1){
     if(jtfWalletAddres.getText().length()>1){         
     if(jtfCoverURL.getText().length()>1){    
//System.out.println("Channel Name : "+jtfChannelName.getText()+"  "+channel);
//System.out.println("Price : "+jtfPrice.getText());
//System.out.println("Wallet Address : "+jtfWalletAddres.getText());
//System.out.println("Cover URL : "+jtfCoverURL.getText()+"   "+myCoverURL);
     
     
      for(int i=0;i<myPublishFilesCounter;i++){
        try {   
           Process(myPublishFiles[i], channel, myPrice,myWallet,myCoverURL,Description,i);
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
  
    }}}
      System.exit(0);
    }}
   
public static void Process(File FilePath,String channelName, double price, String walletAddress, String CoverURL, String Description, int Counter) throws IOException{

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
      String PostParams=openbracket+"\n"+"\"jsonrpc\": \"2.0\","+"\n"+"\"method\": \"publish\","+"\n"+"\"params\":"+"\n"+openbracket+"\n"+"\"channel_name\": \"@"+channelName+"\","+"\n"+"\"name\": \"";
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


   for(int g=0;g<50;g++){
    if(ParentFolderNames[g]!=""){
      PostParams=PostParams.concat(ParentFolderNames[g]+"\\\\");
    }
   }
   
   for(int x=0;x<Description.length();x++){
    System.out.print(Description.charAt(x));
   }
   
     Description=Description.replaceAll("Title_with_Extension", FilePath.getName());
     Description=Description.replaceAll("Title_without_Extension", TitleWithoutSeries);
     Description=Description.replaceAll("Absolute_file_Path", FilePath.getAbsolutePath());
     Description=Description.replaceAll("My_file_Counter", myCounter);

     //System.out.println(Description);
   
     PostParams=PostParams.substring(0, PostParams.length()-2);
     PostParams=PostParams.concat("\","+"\n"+"\"bid\": \"0.0001\","+"\n"+"\"metadata\":"+"\n"+openbracket+"\n"+"\"description\":\""+Description);  
     PostParams= PostParams.concat("\","+"\n");

 
   if(price !=0.00){
      PostParams= PostParams.concat("\"fee\":"+"\n"+openbracket+"\n"+"\"address\": \""+walletAddress+"\","+"\n"+"\"amount\": "+price+",\n"+"\"currency\": \""+currency+"\""+"\n"+closedbracket+","+"\n");
   }
   
   if(jrbHasCoverTrue.isSelected()){
      PostParams=PostParams.concat("\"title\": \""+TitleWithoutSeries+"\","+"\n"+"\"thumbnail\": \""+CoverURL+"\","+"\n"+"\"language\": \"en\","+"\n"+"\"license\": \"Public\","+"\n"+"\"nsfw\": "+NSFW+"\n"+closedbracket+"\n"+closedbracket+"\n"+closedbracket);
   }
    else{
      PostParams=PostParams.concat("\"title\": \""+TitleWithoutSeries+"\","+"\n"+"\"language\": \"en\","+"\n"+"\"license\": \"Public\","+"\n"+"\"nsfw\": "+NSFW+"\n"+closedbracket+"\n"+closedbracket+"\n"+closedbracket); 
    }
        
POSTRequest(PostParams);

   }
   
public static void POSTRequest(String Params) throws IOException {

    //System.out.println(Params);
    URL obj = new URL("http://localhost:5279/");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("POST");
    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(Params.getBytes());
    os.flush();
    os.close();
   
    //int responseCode = postConnection.getResponseCode();
    //System.out.println("POST Response Message : " + postConnection.getResponseMessage());
}
   
}


