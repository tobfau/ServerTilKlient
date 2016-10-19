//TODO: Mangler dokumentation.
import com.google.gson.Gson;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import logic.ConfigLoader;
import logic.UserController;
import shared.Logging;
import javax.ws.rs.*;
import java.io.PrintStream;

//TODO: Missing documentation and use of config variables.
public class Run {

    public static void main(String[] args) throws IOException {

        HttpServer server = null;

        //Loader configfilen
        ConfigLoader.parseConfig();

        try {
            PrintStream stdout = System.out;
            System.setOut(null);
            server = HttpServerFactory.create("http://" + ConfigLoader.SERVER_ADDRESS + ":" + ConfigLoader.SERVER_PORT + "/");
            System.setOut(stdout);
        }catch(ArrayIndexOutOfBoundsException a){
            Logging.log(a, 3, "Fejl. Sysem startede ikke!");
            System.exit(20);
        }

        server.start();

        //Setup logLevel and prepare to log
        Logging.initiateLog(ConfigLoader.DEBUG);

        //Loader courses og lectures ind til databasen
        System.out.println("Server running");
        System.out.println("Visit: http://" + ConfigLoader.SERVER_ADDRESS + ":" + ConfigLoader.SERVER_PORT + "/");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        System.out.println("Server stopped");
        System.out.println();
    }
}
