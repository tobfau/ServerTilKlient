import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import security.Digester;

//TODO: Mangler dokumentation.
public class ResponseHandler implements Runnable {

  private Socket remoteSocket;

  public ResponseHandler(Socket remoteSocket) {
    this.remoteSocket = remoteSocket;
  }

  public void run() {

    try {
      System.out.println("Connection!");
      System.out.println("Thread count: " + Thread.activeCount());

      //Read from input stream (from client)
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.remoteSocket.getInputStream()));

      //Print request headers from client
      String str = ".";
      while (!str.equals("")) {
        str = inFromClient.readLine();
      //  System.out.println(str);
      }

      //Create output stream (to client)
      PrintWriter outToClient = new PrintWriter(remoteSocket.getOutputStream());


      //Write response headers
      outToClient.println("HTTP/1.0 200 OK");
      outToClient.println("Content-Type: application/json");
      outToClient.println("Server: Hackerbot");
      outToClient.println("");

      String s = "hey";
      String sEncoded;
      String sDecoded;
      sEncoded = Digester.encrypt(s);
      sDecoded = Digester.decrypt(sEncoded);

      outToClient.println("Encoding word: " + s);
      outToClient.println("encoded: " + sEncoded);
      outToClient.println("decoded: " + sDecoded);


      //Flush'n'close
      outToClient.flush();

      outToClient.close();
      this.remoteSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
