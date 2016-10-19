package src.shared;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by esbenbak on 19/10/2016.
 */
public class Logging {

    protected static final Logger logger = Logger.getLogger("logging");

    /**
     * Ved hjælp af switch er der
     * @param ex
     * @param level
     * @param msg
     */
    public static void log(Exception ex, String level, String msg) {
        FileHandler fh = null;

        try {
            fh = new FileHandler("log.txt", true);
            logger.addHandler(fh);
            switch (level) {

                case "FINEST":
                    logger.log(Level.FINEST, msg, ex);
                    if (!msg.equals(""))
                        System.out.println(msg + "finest error");
                    break;
                case "FINE":
                    logger.log(Level.FINE, msg, ex);
                    if (!msg.equals(""))
                        System.out.println(msg + "fine error");
                    break;
                case "SEVERE":
                    logger.log(Level.SEVERE, msg, ex);
                    if (!msg.equals(""))
                        System.out.println(msg + "severe error");
                    break;
                default:
                    logger.log(Level.CONFIG, msg, ex);
                    break;

            }


        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally {
            if (fh != null) fh.close();
        }
    }

    public static void main(String[] args) {
        IOException e = new IOException();
        try {
            throw e;
        } catch (IOException e1) {
            e1.printStackTrace();

        }
        log(e, "SEVERE", "hey");
    }

}

