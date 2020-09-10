public interface Observable {
    void addObserver(Observer obs);

    void deleteObserver(Observer obs);

    void notifyObservers(String message);

    void notifyObserversExceptObserver(String message, Observer exceptObserver);
}
