package services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@RequiredArgsConstructor
public class ClientRunnable implements Runnable, Observer {
    private final Socket socket;
    private final Server server;
    private User user;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Client connected");

        BufferedReader readerFromClient =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String clientInput;
        while ((clientInput = readerFromClient.readLine()) != null) {
            if (clientInput.startsWith("!@#$")) {
                final String[] authorizationStrings = clientInput.substring(4).split(":");
                user = new User(authorizationStrings[0], authorizationStrings[1]);
                server.addObserver(this);
            } else {
                System.out.println(user.getName() + ":" + clientInput);
                server.notifyObserversExceptObserver(user.getName() + ":" + clientInput, this);
            }
        }
    }

    @SneakyThrows
    @Override
    public void notifyMe(String message) {
        PrintWriter printWriterToClient = new PrintWriter(socket.getOutputStream());
        printWriterToClient.println(message);
        printWriterToClient.flush();
    }
}