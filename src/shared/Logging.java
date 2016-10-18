package shared;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.io.IOException;
import java.util.logging.Handler;




/**
 * Created by benjikyster on 17/10/2016.
 */

public static void main(String[] args){
public class Logging {
    public Logger log;


    //public Logging() {

        //Logger objekt, hvor den kalder getlogger metoden
        /*log = new Logger.getLogger("uva");

        log.log(Level.FINEST, "Mindre betydelig fejl"); // 1. niveau printes ikke ud i konsol, men ses i compiler
        log.log(Level.FINE, "Fejl"); //2. niveau
        log.log(Level.WARNING, "Stor fejl"); //3. niveau af fejl
        log.log(Level.SEVERE, "Største fejl"); //4. niveau

        //log.log(Level.FINE,""); Oprettet af Henrik
        fh = new FileHandler("./syslog");*/


//Constructor

        Handler consoleHandler = null;
        Handler fileHandler = null;

        try {

            //Konstruerer FileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler = new FileHandler("./syslog.formatter.log");


            log.addHandler(fileHandler);
            log.addHandler(consoleHandler);

            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            log.setLevel(Level.ALL);

            log.config("konfiguration lavet");

            log.removeHandler(consoleHandler);

            log.log(Level.FINE, "Fejl logget");

        } catch(IOException exception) {

            log.log(Level.SEVERE, "Fejl i FileHandler!", exception);
        }

    }

}
