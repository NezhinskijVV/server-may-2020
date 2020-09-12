import dao.UserDao;
import dao.UserDaoImpl;

public class MyApp {

    public static void main(String[] args) {
//        new Server().start();

        UserDao dao = new UserDaoImpl();

        System.out.println("dao.findByName(\"Nastya\") = " + dao.findByName("Nastya"));
    }
}