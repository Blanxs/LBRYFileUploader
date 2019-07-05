/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lbry_book_inputter2;

//import com.sun.javafx.PlatformUtil;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

/**
 *
 * @author John Blanton
 */
public class GrabThumbnail {
    
public  Color lbryBlack= Color.decode("#212529");
private final int BUFFER_SIZE = 4096;
public  String VidDuration="00:00:00";
public  String VidNumOfHours="0";
public  String VidNumOfMinutes="0";
public  String VidNumOfSeconds="0";
public String VidNumOfFrames="0";
public String VidFps="0";
public String VidHeight="0";
public String VidWidth="0";
public String VidCreationDate="0";
public int NumberOfFramesEnd=1;
public String ParentFilePath="";
public GrabThumbnail(){
 
}

public void setParentFilePath(String filePath){
    ParentFilePath=filePath;
}

public void CreateGIF() throws IOException{
         BufferedImage first = ImageIO.read(new File("C:/Users/User 1/Desktop/0.png"));
        ImageOutputStream output = new FileImageOutputStream(new File("C:/Users/User 1/Desktop/first.gif"));

        GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 1, true);
        writer.writeToSequence(first);

//        File[] images = new File[]{
//                new File("/tmp/duke-image-watermarked.jpg"),
//                new File("/tmp/duke.jpg"),
//                new File("/tmp/duke-text-watermarked.jpg"),
//        };
File[] images = new File[20];
for(int x=0;x<20;x++){
    images[x]=new File("C:/Users/User 1/Desktop/"+x+".png");
}
        for (File image : images) {
            BufferedImage next = ImageIO.read(image);
            writer.writeToSequence(next);
        }

        writer.close();
        output.close();   
} 
 
public void downloadFile(String fileURL, String saveDir)throws IOException {
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
 
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
 
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

public BufferedImage ScaleImage(BufferedImage bim, int widthRatio, int heightRatio){
//            PrintWriter writer=new PrintWriter(ParentFilePath+"\\Scaleerror.log", "UTF-8");
//          File FirsterrorLog=new File(ParentFilePath+"\\Sccaleerror.log");    
//      System.out.println(ParentFilePath+"\\Scaleerror.log");
//      writer = new PrintWriter(ParentFilePath+"\\Scaleerror.log", "UTF-8");
//if(FirsterrorLog.exists()){
//  FirsterrorLog.delete();  
//}
    int CurrentWidth =bim.getWidth();
    int CurrentHeight =bim.getHeight();
    int NewWidth;
    int NewHeight;
    int pixel;
    int sidesColor=lbryBlack.getRGB();
    //int sidesColor=Color.TRANSLUCENT;
    double CurrentRatio=CurrentWidth/CurrentHeight;
    double DesiredRatio=widthRatio/heightRatio;
    Boolean HeightIsLarger=false;
    Boolean WidthIsLarger=false;
    if(DesiredRatio>CurrentRatio){
       NewWidth=(int)((widthRatio*CurrentHeight)/heightRatio); 
       HeightIsLarger=true;
             BufferedImage newWidthImage = new BufferedImage(NewWidth,CurrentHeight, BufferedImage.TYPE_INT_ARGB); 
                 for(int x=newWidthImage.getWidth()-1;x>=0;x--){ 
    for(int y=CurrentHeight-1;y>=0;y--){ 
      newWidthImage.setRGB(x, y, sidesColor);
    }
    }
                   for(int x=bim.getWidth()-1;x>=0;x--){ 
    for(int y=bim.getHeight()-1;y>=0;y--){ 
        pixel=bim.getRGB(x, y);
      newWidthImage.setRGB(x+((NewWidth-bim.getWidth())/2), y, pixel);
    }
    }
                 return newWidthImage;
    }
    else if(DesiredRatio<CurrentRatio){
       NewHeight=(int)((heightRatio*CurrentWidth)/widthRatio);
       WidthIsLarger=true;
        BufferedImage newHeightImage = new BufferedImage(CurrentWidth,NewHeight, BufferedImage.TYPE_INT_ARGB); 
            for(int x=CurrentWidth-1;x>=0;x--){ 
    for(int y=newHeightImage.getHeight()-1;y>=0;y--){ 
      newHeightImage.setRGB(x, y, sidesColor);
    }
    }
            
        for(int x=bim.getWidth()-1;x>=0;x--){ 
    for(int y=bim.getHeight()-1;y>=0;y--){ 
        pixel=bim.getRGB(x, y);
      newHeightImage.setRGB(x, y+((NewHeight-bim.getHeight())/2), pixel);
    }
    }        
            
            return newHeightImage;
    }
    else{
        return bim;
    }
    
//       BufferedImage bi = new BufferedImage(SCALE * bim.getWidth(null), SCALE
//        * bim.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//
//    Graphics2D grph = (Graphics2D) bi.getGraphics();
//    grph.scale(SCALE, SCALE);
//
//    grph.drawImage(img, 0, 0, null);
//    grph.dispose();
//    
//    return bim;
}

public BufferedImage ScaleImageSize(BufferedImage bim, int maxHeight){
    double scale=1.0;
    if(maxHeight<bim.getHeight()){
        scale=(double)(((double)(maxHeight))/((double)(bim.getHeight())));
//        System.out.println(maxHeight);
//        System.out.println(bim.getHeight());
//         System.out.println(scale);
    }
int w = (int)(bim.getWidth()*scale);
int h = (int)(bim.getHeight()*scale);
BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
AffineTransform at = new AffineTransform();
at.scale(scale, scale);
AffineTransformOp scaleOp = 
   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
after = scaleOp.filter(bim, after);   
return after;
}

public void pdfToImage(String PdfFilePath, String PdfFileName){
                File createLocation=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\");
            createLocation.mkdirs();
    //final String OUTPUT_DIR = "C:/Users/User 1/Desktop/";
    String OUTPUT_DIR =System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\";
            try (final PDDocument document123 = PDDocument.load(new File(PdfFilePath))){
            PDFRenderer pdfRenderer = new PDFRenderer(document123);
            //for (int page = 0; page < document.getNumberOfPages(); ++page)
          //  for (int page = 0; page < 3; ++page)
          //  {
          int page=0;
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = OUTPUT_DIR+PdfFileName+".png";
                System.out.println("PDF to Image "+fileName);
                ImageIOUtil.writeImage(bim, fileName, 300);
           // }
            document123.close();
        } catch (IOException e){
           // System.err.println("Exception while trying to create pdf document - " + e);
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

public void epubToImage(String filename, String FileImagePath) throws FileNotFoundException, IOException{
EpubReader erdr = new EpubReader();
InputStream epubInputStream = new FileInputStream(filename);
Book book = erdr.readEpub(epubInputStream);
Resource cvrImg = book.getCoverPage();
String cover=book.getCoverPage().getHref();
System.out.println("Cover "+book.getSpine().getResource(0).getHref());
System.out.println("Cover 2 "+book.getSpine().getResource(0));
//cvrImg.getHref()
//DataUtils.saveStreamAsCoverImage(book.getCoverImage().getInputStream(), FileImagePath);

	// if we did not find a cover page then we make the first page of the book the cover page
	if (book.getCoverPage() == null && book.getSpine().size() > 0) {
		book.setCoverPage(book.getSpine().getResource(0));
	}

//InputStream inputStream = cvrImg.getInputStream();
//
//            FileOutputStream outputStream = new FileOutputStream(FileImagePath);
// 
//            int bytesRead = -1;
//            byte[] buffer = new byte[BUFFER_SIZE];
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
// 
//            outputStream.close();
//            inputStream.close();
}

public void vidToImage(){
   // new org.bytedeco.javacv.Java2DFrameConverter;
   //https://en.wikipedia.org/wiki/FFmpeg
   Java2DFrameConverter converter=new Java2DFrameConverter();
  
     try {
         File vidFile=new File("C:/Users/User 1/Desktop/test/1.mp4");
         if(vidFile.exists()){
             System.out.println("Is a file");
         }
FFmpegFrameGrabber g = new FFmpegFrameGrabber(vidFile);
//ffmpeg gg = new ffmpeg();
//FFmpegFrameRecorder g1 = new FFmpegFrameRecorder(vidFile,5);
//FFmpegFrameFilter g2 = new FFmpegFrameFilter("hello",5);

g.start();
        int width = g.getImageWidth();
        int height = g.getImageHeight();
        double framerate = g.getVideoFrameRate();
        double framerate2 = g.getFrameRate();
        long length = g.getLengthInTime();
        long lengthframes=g.getLengthInFrames();
        System.out.println(width);
        System.out.println(height);
        System.out.println(length);
        System.out.println(lengthframes);
        System.out.println(framerate);
        System.out.println(framerate2);
//g.start();
g.setFrameNumber(1000);

for (int i = 0 ; i < 200 ; i++) {
    ImageIO.write(converter.convert(g.grabKeyFrame()), "png", new File("C:/Users/User 1/Desktop/vidtoimage/" + i +".png"));
}

      
           g.stop();
       } catch (FrameGrabber.Exception ex) {
           Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
       }
}

public void getVidFileInfo(String VidCompleteFilePath){
        int NumberOfFrames=1;
    int seconds=1;
    try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
               System.out.println("UploadThumbnail "+"cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                  "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            int TotalNumOfFrames;
            String[] duration;
            String[] FPS;
            String fps;
            Boolean foundDuration=false;
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
               line=line.trim();
               if(foundDuration){
                   System.out.println(line);
                   foundDuration=false;
                   FPS=line.split(",");
                   for(int x=0;x<=FPS.length-1;x++){
                       FPS[x]=FPS[x].trim();
                       if(FPS[x].endsWith("fps")){
                         fps=FPS[x].substring(0, FPS[x].length()-4);
                         
                         NumberOfFrames=(int)(seconds*Double.valueOf(fps));
                         VidNumOfFrames=String.valueOf(NumberOfFrames);
                         VidFps=String.valueOf(fps);
                         System.out.println("FPS "+fps);
                          System.out.println("Frames "+NumberOfFrames);
                       }
                       else if(FPS[x].endsWith("]")){
                           System.out.println(FPS[x]);
                           String[] a=FPS[x].split("\\[");
                           a=a[0].trim().split("x");
                           VidWidth=a[0];
                           System.out.println("Width in pixels "+VidWidth);
                           VidHeight=a[1];
                           System.out.println("Height in pixels "+VidHeight);
                       }
                       else{
                           Boolean isSize=true;
                           for(int i=0;i<FPS[x].length();i++){
                               if(FPS[x].charAt(i)=='1' || FPS[x].charAt(i)=='2' || FPS[x].charAt(i)=='3' || FPS[x].charAt(i)=='4' || FPS[x].charAt(i)=='5' || FPS[x].charAt(i)=='6' || FPS[x].charAt(i)=='7' || FPS[x].charAt(i)=='8' || FPS[x].charAt(i)=='9' || FPS[x].charAt(i)=='0' || FPS[x].charAt(i)=='x'){
                                   
                               }
                               else{
                                   isSize=false;
                                   break;
                               }
                           }
                           if(isSize){
                               System.out.println("Found Width and Height");
                           String[] a=FPS[x].split("x"); 
                           VidWidth=a[0];
                           System.out.println("Width in pixels"+VidWidth);
                           VidHeight=a[1];
                           System.out.println("Height in pixels"+VidHeight);
                           }
                           
                       }
                   }
               }
               if(line.startsWith("Duration: ")){
                   line=line.substring(10, line.length()-1);
                duration=line.split(",");
                VidDuration=duration[0];
                duration=duration[0].split(":");
                VidNumOfHours=duration[0];
                VidNumOfMinutes=duration[1];
                VidNumOfSeconds=duration[2];
                int sec1= ((int)((Double.valueOf(duration[0]))*3600));
                int sec2=((int)((Double.valueOf(duration[1]))*60));
               int sec3=((int)((Double.valueOf(duration[2]))*1));
                seconds=sec1+sec2+sec3;
                System.out.println("Sec1 "+duration[0]);
                 System.out.println("Sec2 "+duration[1]);
                  System.out.println("Sec3 "+duration[2]);
               System.out.println("Seconds "+seconds);
               foundDuration=true;
               }
               
               // System.out.println(line);
            }
        } catch (IOException ex) {

                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with creating a gif from the vid.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }  
}

public int getTotalNumOfFrames(String VidCompleteFilePath){
    int NumberOfFrames=1;
    int seconds=1;
    try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
               System.out.println("UploadThumbnail "+"cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                  "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\"");
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            int TotalNumOfFrames;
            String[] duration;
            String[] FPS;
            String fps;
            Boolean foundDuration=false;
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
               line=line.trim();
               if(foundDuration){
                   foundDuration=false;
                   FPS=line.split(",");
                   for(int x=0;x<=FPS.length-1;x++){
                       FPS[x]=FPS[x].trim();
                       if(FPS[x].endsWith("fps")){
                         fps=FPS[x].substring(0, FPS[x].length()-4);
                         
                         NumberOfFrames=(int)(seconds*Double.valueOf(fps));
                         System.out.println("FPS "+fps);
                          System.out.println("Frames "+NumberOfFrames);
                       }
                   }
               }
               if(line.startsWith("Duration: ")){
                   line=line.substring(10, line.length()-1);
                duration=line.split(",");
                duration=duration[0].split(":");
                int sec1= ((int)((Double.valueOf(duration[0]))*3600));
                int sec2=((int)((Double.valueOf(duration[1]))*60));
               int sec3=((int)((Double.valueOf(duration[2]))*1));
                seconds=sec1+sec2+sec3;
                System.out.println("Sec1 "+duration[0]);
                 System.out.println("Sec2 "+duration[1]);
                  System.out.println("Sec3 "+duration[2]);
               System.out.println("Seconds "+seconds);
               foundDuration=true;
               }
                System.out.println(line);
            }
        } catch (IOException ex) {

                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with creating a gif from the vid.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        } 
    return NumberOfFrames;
}

public String getImage(String FileLocation, File inputFile) throws MalformedURLException, IOException{
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

public BufferedImage getBufferedImageFromImageFilePath(String filePath) throws FileNotFoundException, UnsupportedEncodingException{
    
        BufferedImage bimg = null; 
        PrintWriter writer=new PrintWriter(ParentFilePath+"\\BFerror.log", "UTF-8");
        try { 
          File FirsterrorLog=new File(ParentFilePath+"\\BFerror.log");    
      System.out.println(ParentFilePath+"\\BFerror.log");
      writer = new PrintWriter(ParentFilePath+"\\BFerror.log", "UTF-8");
if(FirsterrorLog.exists()){
  FirsterrorLog.delete();  
}
     
    bimg = ImageIO.read(new File(filePath));  
    }
    catch (FileNotFoundException e) { 
       writer.println("FileNotFoundException"+filePath); 
       writer.close();
    e.printStackTrace();  
    }
    catch (UnsupportedEncodingException e) {  
        writer.println("UnsupportedEncodingException"+filePath);
        writer.close();
    e.printStackTrace();  
    }
      catch (IOException e) { 
          writer.println("IOException"+filePath);
          writer.close();
    e.printStackTrace();  
    }
     writer.close();   
    return bimg;
}

public void CreateGifWithFfmpegVidToGif(String startTime, String duration, String VidCompleteFilePath, String GifName){
 
  
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -ss "+startTime+" -t "+duration+" -i \""+VidCompleteFilePath+"\" -f gif \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -ss "+startTime+" -t "+duration+" -i \""+VidCompleteFilePath+"\" -f gif \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
               System.out.println("UploadThumbnail "+"cd \""+ParentFilePath+"\" && ffmpeg -ss "+startTime+" -t "+duration+" -i \""+VidCompleteFilePath+"\" -f gif \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -ss "+startTime+" -t "+duration+" -i \""+VidCompleteFilePath+"\" -f gif \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -ss "+startTime+" -t "+duration+" -i \""+VidCompleteFilePath+"\" -f gif \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
               // line=line.trim();

              //  System.out.println(line);
            }
        } catch (IOException ex) {

                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with creating a gif from the vid.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }  
}

public void CreateGifWithFfmpegImagesToGif( String ImageFolderCompleteFilePath, String GifName, long MaxFileSize) throws FileNotFoundException, UnsupportedEncodingException{
  //ffmpeg -i "C:\Users\User 1\Desktop\ffmpegTest\frames%d.png" output.gif
            File FirsterrorLog=new File(ParentFilePath+"\\ImagesToGiferror.log");    
if(FirsterrorLog.exists()){
  FirsterrorLog.delete();  
}
PrintWriter writer = new PrintWriter(ParentFilePath+"\\ImagesToGiferror.log", "UTF-8");
writer.println(ImageFolderCompleteFilePath);
writer.println(GifName);
writer.println(MaxFileSize);
writer.println(ParentFilePath);
writer.println(VidFps);
writer.println("cd \""+ParentFilePath+"\" && ffmpeg -framerate "+VidFps+" -i \""+ImageFolderCompleteFilePath+"\" \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");

  deleteAllImagesAfterFileMaxSizeIsReached(MaxFileSize);
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -framerate "+VidFps+" -i \""+ImageFolderCompleteFilePath+"\" \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -framerate "+VidFps+" -i \""+ImageFolderCompleteFilePath+"\" \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
               writer.println("Made it past proceess");
               System.out.println("UploadThumbnail "+"cd \""+ParentFilePath+"\" && ffmpeg -framerate "+VidFps+" -i \""+ImageFolderCompleteFilePath+"\" \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -framerate "+VidFps+" -i \""+ImageFolderCompleteFilePath+"\" \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -framerate "+VidFps+" -i \""+ImageFolderCompleteFilePath+"\" \""+System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+GifName+".gif\"");
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            writer.println("Made it before while");
            writer.close();
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
               // line=line.trim();
writer.println(line);
              //  System.out.println(line);
            }
        } catch (IOException ex) {
writer.close();
                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with creating a gif from the vid.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        } 
        writer.close();
}

public Boolean TestForCorruptedVid( String ImageFolderCompleteFilePath){
  //ffmpeg -i "C:\Users\User 1\Desktop\ffmpegTest\frames%d.png" output.gif
      File FirsterrorLog=new File(ParentFilePath+"\\error.log");    
      System.out.println(ParentFilePath+"\\error.log");
if(FirsterrorLog.exists()){
  FirsterrorLog.delete();  
}
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -v error -i \""+ImageFolderCompleteFilePath+"\" -f null - 2>error.log");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -v error -i \""+ImageFolderCompleteFilePath+"\" -f null - 2>error.log");
               System.out.println("UploadThumbnail "+"cd \""+ParentFilePath+"\" && ffmpeg -v error -i \""+ImageFolderCompleteFilePath+"\" -f null - 2>error.log");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -v error -i \""+ImageFolderCompleteFilePath+"\" -f null - 2>error.log");  
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -v error -i \""+ImageFolderCompleteFilePath+"\" -f null - 2>error.log");
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line=r.readLine())!=null) {
                //line = r.readLine();
               // line=line.trim();

             System.out.println(" !! in while loop");
            }
         BufferedReader Errorbr = new BufferedReader(new FileReader(ParentFilePath+"\\error.log"));

    StringBuilder Errorsb = new StringBuilder();
    String Errorline;
if((Errorline=Errorbr.readLine())!=null){
    System.out.println("error found!!!!!!!!!!!!!!!!!");
    File errorLog=new File(ParentFilePath+"\\error.log");
    errorLog.delete();
   return false; 
}
//    while (Errorline != null) {
//        Errorsb.append(Errorline);
//        Errorsb.append(System.lineSeparator());
//        Errorline = Errorbr.readLine();
//    }
//    String Erroreverything = Errorsb.toString();   
            
        } catch (IOException ex) {

                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with testing for a corrupted file.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
    File errorLog=new File(ParentFilePath+"\\error.log");
    errorLog.delete();
        return true;
}

public void CreateImagesWithFfmpegVidToImages(String VidCompleteFilePath,double percentageIntoVid,int NumOfFrames,String PathToPlaceImages) throws FileNotFoundException, UnsupportedEncodingException{
        File FirsterrorLog=new File(ParentFilePath+"\\VidToImageserror.log");    
if(FirsterrorLog.exists()){
  FirsterrorLog.delete();  
}
PrintWriter writer = new PrintWriter(ParentFilePath+"\\VidToImageserror.log", "UTF-8");

  //ffmpeg -i 1.mp4 -vf select='between(n\,2000\,2200)' -vsync 0 "C:\Users\User 1\Desktop\ffmpegTest\frames%d.png"
//int sdsdf=getTotalNumOfFrames(ParentFilePath,VidCompleteFilePath);
//System.exit(0);
getVidFileInfo(VidCompleteFilePath);
  int Start=(int)(Integer.valueOf(VidNumOfFrames)*percentageIntoVid);
 //int Start=5;
  int End=Start+NumOfFrames;
  if(End>=Integer.valueOf(VidNumOfFrames)){
    End= (int)(Integer.valueOf(VidNumOfFrames)*.95); 
  }
  NumberOfFramesEnd=End-Start;
  String StartFrame=String.valueOf(Start);
  String EndFrame=String.valueOf(End);
 // StartFrame="3000";
  //EndFrame="3250";
  System.out.println("StartFrame "+StartFrame);
  System.out.println("EndFrame "+EndFrame);
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" -vf select='between(n\\,"+StartFrame+"\\,"+EndFrame+")' -vsync 0 \""+PathToPlaceImages+"\"");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" -vf select='between(n\\,"+StartFrame+"\\,"+EndFrame+")' -vsync 0 \""+PathToPlaceImages+"\"");
               System.out.println("UploadThumbnail "+"cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" -vf select='between(n\\,"+StartFrame+"\\,"+EndFrame+")' -vsync 0 \""+PathToPlaceImages+"\"");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" -vf select='between(n\\,"+StartFrame+"\\,"+EndFrame+")' -vsync 0 \""+PathToPlaceImages+"\"");
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" -vf select='between(n\\,"+StartFrame+"\\,"+EndFrame+")' -vsync 0 \""+PathToPlaceImages+"\"");
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
               // line=line.trim();
             writer.println(line);

             System.out.println(line);
            }
        } catch (IOException ex) {

writer.close();
                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with creating a gif from the vid.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }

writer.close();
}

public void CreateImagesWithFfmpegGifToImages(String VidCompleteFilePath,String PathToPlaceImages) throws FileNotFoundException, UnsupportedEncodingException{
  //ffmpeg -i video.webm thumb%04d.jpg -hide_banner
            File FirsterrorLog=new File(ParentFilePath+"\\GifToImageserror.log");    
if(FirsterrorLog.exists()){
  FirsterrorLog.delete();  
}
PrintWriter writer = new PrintWriter(ParentFilePath+"\\GifToImagesserror.log", "UTF-8");

getVidFileInfo(VidCompleteFilePath);
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" \""+PathToPlaceImages+"\" -hide_banner");
            if(System.getProperty("os.name").contains("Windows")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" \""+PathToPlaceImages+"\" -hide_banner");
               System.out.println("UploadThumbnail "+"cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" \""+PathToPlaceImages+"\" -hide_banner");
            }
            else if(System.getProperty("os.name").contains("Mac")){
                builder = new ProcessBuilder(
                   "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" \""+PathToPlaceImages+"\" -hide_banner");
            }
             else if(System.getProperty("os.name").contains("Linux")){
               builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+ParentFilePath+"\" && ffmpeg -i \""+VidCompleteFilePath+"\" \""+PathToPlaceImages+"\" -hide_banner");
            }
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            
            while ((line = r.readLine()) != null) {
                //line = r.readLine();
               // line=line.trim();
writer.println(line);
             System.out.println(line);
            }
        } catch (IOException ex) {
writer.close();
                    JFrame Error = new JFrame("Error");
                 JOptionPane.showMessageDialog(Error,
         "There was an error with creating a gif from the vid.",
         "Error",
         JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LBRY_BOOK_INPUTTER2.class.getName()).log(Level.SEVERE, null, ex);
        }
   writer.close();    
}

public void deleteAllImagesAfterFileMaxSizeIsReached(long MaxFileSize){
    File srcFolder=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\");  
  String[] ImagesFiles=srcFolder.list();
  long CurrentFileSize=0;
  int filedeletedcounter=0;
  for(int x=0;x<ImagesFiles.length;x++){
  File Image=new File(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+ImagesFiles[x]);
  System.out.println(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()-5)+"\\src\\Images\\"+ImagesFiles[x]+"   "+Image.length());
  if((CurrentFileSize+Image.length())<=MaxFileSize){
    CurrentFileSize=CurrentFileSize+Image.length();  
  }
  else{
      filedeletedcounter++;
      System.out.println("Deleted Image "+ImagesFiles[x]+"  Size was "+Image.length()+" Total size is "+CurrentFileSize+" Files deleted "+filedeletedcounter);
  Image.delete();   
  }
  }  
}
}
