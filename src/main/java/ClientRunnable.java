import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

@RequiredArgsConstructor
public class ClientRunnable implements Runnable {
    private final Socket socket;
    private User user;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Client connected");
//        PrintWriter printWriterToClient = new PrintWriter(socket.getOutputStream());
//        printWriterToClient.println("Message from server: connected");
//        printWriterToClient.flush();

        BufferedReader readerFromClient =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String clientInput;
        while ((clientInput = readerFromClient.readLine()) != null) {
            if (clientInput.startsWith("!@#$")) {
                final String[] authorizationStrings = clientInput.substring(4).split(":");
                user = new User(authorizationStrings[0], authorizationStrings[1]);
            } else {
                System.out.println(user.getName() + ":" + clientInput);
            }
        }
    }
}