package services;

import dao.UserDao;
import dao.UserDaoImpl;
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
    private final UserDao userDao = new UserDaoImpl();
    private User user;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Client connected");

        BufferedReader readerFromClient =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String clientInput;
        while ((clientInput = readerFromClient.readLine()) != null) {
            if (clientInput.startsWith("!@#$autho")) {
                final String[] authorizationStrings = clientInput.substring(9).split(":");
                user = authorization(authorizationStrings[0], authorizationStrings[1]);
                if (user != null) {
                    server.addObserver(this);
                } else {
                    notifyMe("Не правильное имя пользователя или пароль");
                    notifyMe("401");
                    socket.close();
                    break;
                }
            } else if (clientInput.startsWith(" ")) {
//регистрация

            } else {
                System.out.println(user.getName() + ":" + clientInput);
                server.notifyObserversExceptObserver(user.getName() + ":" + clientInput, this);
            }
        }
    }

    public User authorization(String name, String password) {
        User user = userDao.findByName(name);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @SneakyThrows
    @Override
    public void notifyMe(String message) {
        PrintWriter printWriterToClient = new PrintWriter(socket.getOutputStream());
        printWriterToClient.println(message);
        printWriterToClient.flush();
    }
}