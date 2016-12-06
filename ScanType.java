import java.io.*;
class ScanType{
    String targetIP, targetPort, fileName;
    ScanType(String str[]){
        String opt, var;
        for(int i=0;i<str.length;i++){
            opt=str[i].split("=")[0];
            var=str[i].split("=")[1];
            switch(opt){
                case "--tHOST":
                    targetIP=var;
                    System.out.println("The target host is set to: " + var);
                    break;
                case "--tPORT":
                    targetPort=var;
                    System.out.println("The target port is set to: " + var);
                    break;
                case "--file":
                    fileName=var;
                    break;
                case "--scan":
                    switch(var){
                        case "FPS":
                            FullPortScan();
                            break;
                        case "FFS":
                            FullFTPScan();
                            break;
                        case "FTP":
                            FTPScan();
                            break;
                        default:
                            System.out.println(var + " is not a valid ScanType.");
                    }
                    break;
                default:
                    System.out.println(opt + " is not a valid option.");
                    break;
            }           
        }
    }
    void FullPortScan(){
        for(int i=0; i<65536; i++){
            new PortScan(targetIP,i);
        }
    }
    void FullFTPScan(){
        /*try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        }*/
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            for(int i=0; (line = br.readLine()) != null; i++) {
                if(i%4000==0){
                    Thread.sleep(10000);
                    System.out.println(i);
                }
                try{
                    new FtpUrlUpload(line);
                }
                catch(Exception e){
                    System.out.println();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    void FTPScan(){
        new FtpUrlUpload(targetIP);
    }
    void displaySettings(){
        System.out.println("Targeted IP is ==> " + targetIP);
        System.out.println("Targeted PORT is ==> " + targetPort);
    }
    
}