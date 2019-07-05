/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lbry_book_inputter2;

//import com.sun.javafx.PlatformUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author John Blanton
 */
public class GrabbingLbryInfoForFrame {

public String[] ChannelIds;    
    
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
    String[] ParamChannelIDs=new String[1];
    String[] mychannelIDs=new String[1];
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
        if(response.indexOf("\"claim_id\": \"")!=-1){
    ParamChannelIDs=response.split("\"claim_id\": \"");
    mychannelIDs=response.split("\"claim_id\": \"");
    for(int x=0;x<ParamChannelIDs.length-1;x++){
      mychannelIDs[x]=ParamChannelIDs[x+1].substring(0,ParamChannelIDs[x+1].indexOf("\""));
      System.out.println("Channel "+x+" ID "+mychannelIDs[x]);
    }
ChannelIds=mychannelIDs;
    //System.out.println(response);
    }
//    for(int i=0;i<channels.length;i++){
//     System.out.println(channels[i]);   
//    }
    os.flush();
    os.close();
    
    return channels;
}

public String[] getChannelIds(){
    return ChannelIds;
}

public String[] getChannelsCompleteName() throws MalformedURLException, IOException{
    
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
    if(response.indexOf("\"permanent_url\": \"lbry://@")!=-1){
    Paramchannels=response.split("\"permanent_url\": \"lbry://@");
    channels=response.split("\"permanent_url\": \"lbry://@");
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

public String getLBCValueInWallet(){

        String TotalLBCInWallet="";
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account balance");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\LBRY\\resources\\static\\daemon\" && lbrynet account balance"); 
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /Applications/LBRY.app/Contents/Resources/static/daemon\" && lbrynet account balance");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /opt/LBRY/resources/static/daemon\" && lbrynet account balance");   
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            
            TotalLBCInWallet="";
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
                line=line.trim();
                if(line.startsWith("\"")){
                    TotalLBCInWallet=line.substring(1, line.length()-1);
                }
                
                if (line == null) { break; }
                System.out.println(line);
                System.out.println(TotalLBCInWallet);
            }
   
        } catch (IOException ex) {
                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error. With finding the amount of LBC in your LBRY Wallet. Did you download the LBRY app at lbry.com yet?",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
          return TotalLBCInWallet;       
    

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
  
}
