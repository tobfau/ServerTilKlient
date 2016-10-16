import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

  private int port;
  private ServerSocket serverSocket;

  public WebServer(){
    this.port = 1337; //default port
  }

  public WebServer(int port){
    this.port = port;
  }

  public void start(){
    try {

      this.serverSocket = new ServerSocket(this.port);

      while (true){

        System.out.println("Waiting for clients on port " + this.port);

        //Hang while-loop and wait for socket request
        Socket remoteSocket = this.serverSocket.accept();

        Runnable responseHandler = new ResponseHandler(remoteSocket);
        new Thread(responseHandler).start();

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
