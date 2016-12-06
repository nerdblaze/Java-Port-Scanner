import java.net.*;
class PortScan implements Runnable {
    int port;
    String site;
    PortScan(String s, int p){
        Thread t = new Thread(this, "thread");
        port = p;
        site = s;
        t.start();
    }
    public void run(){
        try{            
                Socket s = new Socket(site,port);
                System.out.println(s.getPort());
                s.close();
            }
            catch (Exception e){
                //System.out.println("Thread Interrupted.");
            }
    }
}