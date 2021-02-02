import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author: Imalka
 * @created_date: 1/31/2021
 */
public class Client {

    public static void main(String[] args) {
        if (args.length < 2) return;
        final String hostName = args[0];
        final int portNumber = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(hostName, portNumber)) {
            final OutputStream outputStream = socket.getOutputStream();
            final PrintWriter writer = new PrintWriter(outputStream, true);
            final Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Letter: ");
            String inputText;
            do {
                inputText = scanner.nextLine();
                writer.println(inputText);

                final InputStream inputStream = socket.getInputStream();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                final String responseFromServer = reader.readLine();
                System.out.println(responseFromServer);
            } while (!inputText.equals("exit"));

            socket.close();
        } catch (UnknownHostException hostException) {
            System.out.println("Invalid Server: " + hostException.getMessage());
        } catch (IOException ioException) {
            System.out.println("I/O Error: " + ioException.getMessage());
        }
    }
}