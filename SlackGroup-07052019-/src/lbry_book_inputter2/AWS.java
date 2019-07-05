/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lbry_book_inputter2;

//import com.sun.javafx.PlatformUtil;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author John Blanton
 */
public class AWS {

//public static String Configuration="aws configure --profile MassUploadUser && AKIA4IL3ICM3V3OASRHV && jVwsarTbphGC3F8g9QDqrl/Uz8fpNeqb00a6LHtz && us-east-1 && json &&";    
    
//public static String ParentFilePath="";   
public AWS(){
 
}    
 
public void setParentFilePath(String filePath){
   // ParentFilePath=filePath;
}

public static void CheckForAWSCLI(){

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
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
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

        } catch (IOException ex) {

                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with checking for the aws cli.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
    }

public static void UploadThumbnailtoAWS(String fileName, String CompleteChannelName, String action){

        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c","aws s3 "+action+" \""+fileName+"\" s3://mass-uploader-thumbnails/"+CompleteChannelName+"/ --grants read=uri=http://acs.amazonaws.com/groups/global/AllUsers --profile MassUploadUser");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c","aws s3 "+action+" \""+fileName+"\" s3://mass-uploader-thumbnails/"+CompleteChannelName+"/ --grants read=uri=http://acs.amazonaws.com/groups/global/AllUsers --profile MassUploadUser"); 
               System.out.println("UploadThumbnail aws s3 "+action+" \""+fileName+"\" s3://mass-uploader-thumbnails/"+CompleteChannelName+"/ --grants read=uri=http://acs.amazonaws.com/groups/global/AllUsers --profile MassUploadUser");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                    "cmd.exe", "/c","aws s3 "+action+" \""+fileName+"\" s3://mass-uploader-thumbnails/"+CompleteChannelName+"/ --grants read=uri=http://acs.amazonaws.com/groups/global/AllUsers --profile MassUploadUser");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c","aws s3 "+action+" \""+fileName+"\" s3://mass-uploader-thumbnails/"+CompleteChannelName+"/ --grants read=uri=http://acs.amazonaws.com/groups/global/AllUsers --profile MassUploadUser");   
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
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

        } catch (IOException ex) {

                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with checking for the aws cli.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
    }

public static void ConfigureAWS() throws IOException{


try {
BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\.aws\\config"));
BufferedReader br2 = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\.aws\\credentials"));
    String line;
    String line2;
    String[] configLines=new String[1000];
    String[] credentialsLines=new String[1000];
    Boolean hasProfile=false;
    int counter=0;
    int counter2=0;
    while ((line=br.readLine()) != null) {
        line=line.trim();
        configLines[counter]=line;
        counter++;
if(line.endsWith("MassUploadUser]")){
   hasProfile=true;
   break;
}
    }
        while ((line2=br2.readLine()) != null) {
        line2=line2.trim();
        credentialsLines[counter2]=line2;
        counter2++;
if(line2.endsWith("MassUploadUser]")){
   hasProfile=true;
   break;
}
    }
 br.close();
 br2.close();
 
if(!hasProfile){
    PrintWriter writer = new PrintWriter(System.getProperty("user.home")+"\\.aws\\config", "UTF-8");
     PrintWriter writer2 = new PrintWriter(System.getProperty("user.home")+"\\.aws\\credentials", "UTF-8");
    for(int i=0;i<counter;i++){
      writer.println(configLines[i]);  
    }
        for(int x=0;x<counter2;x++){
      writer2.println(credentialsLines[x]);  
    }
writer2.println("[MassUploadUser]");
writer2.println("aws_access_key_id = AKIA4IL3ICM3V3OASRHV");
writer2.println("aws_secret_access_key = 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");
writer2.close();        
writer.println("[profile MassUploadUser]");
writer.println("region = us-east-1");
writer.println("output = json");
writer.close();
}    
    

}
catch(FileNotFoundException ie){
      PrintWriter writer = new PrintWriter(System.getProperty("user.home")+"\\.aws\\config", "UTF-8");
     PrintWriter writer2 = new PrintWriter(System.getProperty("user.home")+"\\.aws\\credentials", "UTF-8");

writer2.println("[MassUploadUser]");
writer2.println("aws_access_key_id = AKIA4IL3ICM3V3OASRHV");
writer2.println("aws_secret_access_key = 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");
writer2.close();        
writer.println("[profile MassUploadUser]");
writer.println("region = us-east-1");
writer.println("output = json");
writer.close();  
}    
    
//System.out.println("Configuring ");
//        try {
//            ProcessBuilder builder = new ProcessBuilder(
//                    "cmd.exe", "/c","aws configure --profile MassUploadUser && AKIA4IL3ICM3V3OASRHV && jVwsarTbphGC3F8g9QDqrl/Uz8fpNeqb00a6LHtz && us-east-1 && json");
//            if(PlatformUtil.isWindows()){
//               builder = new ProcessBuilder(
//                    "cmd.exe", "/c","aws configure --profile MassUploadUser && AKIA4IL3ICM3V3OASRHV && jVwsarTbphGC3F8g9QDqrl/Uz8fpNeqb00a6LHtz && us-east-1 && json"); 
//            }
//            else if(PlatformUtil.isMac()){
//                builder = new ProcessBuilder(
//                    "cmd.exe", "/c","aws configure --profile MassUploadUser && AKIA4IL3ICM3V3OASRHV && jVwsarTbphGC3F8g9QDqrl/Uz8fpNeqb00a6LHtz && us-east-1 && json");  
//            }
//             else if(PlatformUtil.isLinux() || PlatformUtil.isUnix()){
//               builder = new ProcessBuilder(
//                    "cmd.exe", "/c","aws configure --profile MassUploadUser && AKIA4IL3ICM3V3OASRHV && jVwsarTbphGC3F8g9QDqrl/Uz8fpNeqb00a6LHtz && us-east-1 && json");   
//            }
//            builder = new ProcessBuilder(
//                    "cmd.exe", "/c","aws configure --profile MassUploadUser");
//            builder.redirectErrorStream(true);
//            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
////            String id="";
////            String TotalLBCInWallet="";
//System.out.println("Configuring 2");
//            while ((line = r.readLine()) != null) {
////                //line = r.readLine();
//
//                line=line.trim();
////                if(line.startsWith("\"id\":")){
////                    id=line.substring(7, line.length()-2);
////                }
////                 if(line.startsWith("\"coins\":")){
////                    TotalLBCInWallet=line.substring(9, line.length()-1);
//System.out.println("Configuring "+line);
//               }
////                if (line == null) { break; }
////                System.out.println(line);
////            }
//System.out.println("DONE");
//        } catch (IOException ex) {
//
//                    JFrame Error = new JFrame("Error");
//                 JOptionPane.showMessageDialog(Error,
//         "There was an error. With connecting to LBRY. Make sure to download the app from lbry.com",
//         "Error",
//         JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
//        }
                   
    }       
}
