import java.io.*;
import java.net.Socket;

/**
 * @author: Imalka
 * @created_date: 1/31/2021
 */
public class ThreadSupporter extends Thread {
    private Socket socket;

    public ThreadSupporter(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            final InputStream inputStream = socket.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            final OutputStream outputStream = socket.getOutputStream();
            final PrintWriter writer = new PrintWriter(outputStream, true);
            String receiver;
            String parserResponse;
            do {
                receiver = reader.readLine();
                char getFirstLetter = receiver.toUpperCase().charAt(0);
                if ('A' == getFirstLetter) {
                    parserResponse = "You added first letter of the alphabet!";
                    writer.println("Server Response: " + parserResponse);
                }
                getFirstLetter--;
                final char[] getValidateReceiver = receiver.toCharArray();
                if (1 < getValidateReceiver.length) {
                    parserResponse = "Please enter a valid letter of alphabet!";
                } else {
                    parserResponse = String.valueOf(getFirstLetter);
                }
                writer.println("Server Response: " + parserResponse);

            } while (!receiver.equals("exit"));
            socket.close();
        } catch (IOException exception) {
            System.out.println("Return Server Exception! " + exception.getMessage());
        }
    }
}
