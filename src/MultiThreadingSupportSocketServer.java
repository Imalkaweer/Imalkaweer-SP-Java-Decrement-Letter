import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Imalka
 * @created_date: 1/31/2021
 */
public class MultiThreadingSupportSocketServer {

    public static void main(String[] args) {
        if (args.length <1) return;
        final int portNumber = Integer.parseInt(args[0]);

        try (final ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Socket server is listening => PORT NO " + portNumber);
            while (true) {
                final Socket socketAccept = serverSocket.accept();
                System.out.println("New Client connected");
                new ThreadSupporter(socketAccept).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
