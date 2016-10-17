
//TODO: Mangler dokumentation. 
public class Run {


    //TODO: Set port in config.
    public static void main(String args[]){

        WebServer webServer = new WebServer(5000);
        webServer.start();

    }

}
