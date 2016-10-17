import dal.ServiceImpl;
import logic.MainController;
import service.Service;

//TODO: Mangler dokumentation.
public class Run {


    //TODO: Set port in config.
    public static void main(String args[]){

        WebServer webServer = new WebServer(5000);
        webServer.start();

        Service service = new ServiceImpl();
        new MainController(service);
    }

}
