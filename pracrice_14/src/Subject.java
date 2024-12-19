import java.util.ArrayList;
import java.util.List;

class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Метод для изменения состояния и уведомления подписчиков
    public void changeState(String newState) {
        System.out.println("Состояние изменено на: " + newState);
        notifyObservers("Состояние изменено на: " + newState);
    }
}
