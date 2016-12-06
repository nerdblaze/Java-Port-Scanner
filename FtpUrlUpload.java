import java.net.*;
import java.io.*;

class FtpUrlUpload implements Runnable{
    int BUFFER_SIZE = 4096;
    String ftpUrl = "ftp://%s:%s@%s/%s;type=a";
    String host;
    String user = "anonymous";
    String pass = "GreatIndians";
    String filePath = "index.html";
    String uploadPath = "GreatIndians.html";
    FtpUrlUpload(String s){
        host = s;
        Thread t = new Thread(this, "threads");
        t.start();
    }
    public void run(){ 
        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            //System.out.println("Connected to the URL: "+ host );
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
 
            System.out.println("File uploaded Successfully.");
        }
        catch (Exception e){
            switch(e.toString().split(":")[0]){
                case "sun.net.ftp.FtpLoginException":
                    //System.out.println("Login Failed");
                    break;
                case "java.io.FileNotFoundException":
                    System.out.println("Logged In"+ host + " \nPermission denied");
                    break;
                case "java.net.ConnectException":
                    //System.out.println("Connection Failed");
                    break;
                default:
                    //System.out.println(e);
                    break;
            }
        }
    }
}
