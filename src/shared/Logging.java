package shared;

import logic.ConfigLoader;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO: Needs proper comments
public class Logging {

    protected static final Logger logger = Logger.getLogger("logging");

    public static void initiateLog(String debugLevel){
        if(debugLevel.equals("1")){
            logger.setLevel(Level.FINEST);
        }else if(debugLevel.equals("2")){
            logger.setLevel(Level.FINE);
        }else{
            logger.setLevel(Level.SEVERE);
        }
    }

    /**
     * Ved hjælp af switch er der
     * @param ex
     * @param level
     * @param msg
     */
    public static void log(Exception ex, int level, String msg) {
        FileHandler fh = null;

        try {
            fh = new FileHandler("application.log", true);
            logger.addHandler(fh);
            switch (level) {
                case 1:
                    logger.log(Level.FINEST, msg, ex);
                    if (!msg.equals(""))
                        System.out.println(msg + "finest error");
                    break;
                case 2:
                    logger.log(Level.FINE, msg, ex);
                    if (!msg.equals(""))
                        System.out.println(msg + "fine error");
                    break;
                case 3:
                    logger.log(Level.SEVERE, msg, ex);
                    if (!msg.equals(""))
                        System.out.println(msg + "severe error");
                    break;
                default:
                    logger.log(Level.CONFIG, msg, ex);
                    break;

            }
        } catch (IOException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } catch (SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        }finally {
            if (fh != null) fh.close();
        }
    }
}

