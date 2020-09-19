
import lombok.SneakyThrows;
import services.Server;

public class MyApp {

    @SneakyThrows
    public static void main(String[] args) {
        new Server().start();

    }
}

//HW:
//1. Регистрация + клиента (авторизация или регистрация?)
//2. Доделать оставшиеся
//3. Интернационализация

//1. Logger (@Log4j) (вначале на своем маленьком проекте)
//2. Сохранение переписки в файл
//3. Подгрузка переписки

//БД
//1. Табличка сообщения

//5. Личные сообщения
//6. Json (библиотека Gson)
//7. Security (класс Encryptor)
//8. Swing (Java FX) прикрутить интерфейс