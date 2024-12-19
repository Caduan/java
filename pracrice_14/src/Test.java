public class Test {
    public static void main(String[] args) {

        Subject subject = new Subject();

        User user1 = new User("Пользователь 1 –– ");
        User user2 = new User("Пользователь 2 –– ");
        User user3 = new User("Пользователь 3 –– ");

        subject.addObserver(user1);
        subject.addObserver(user2);
        subject.addObserver(user3);

        System.out.println("Изменение состояния в 'Subject'");
        subject.changeState("The status has been changed!");

        System.out.println("\nУдаление пользователя 2 из подписчиков");
        subject.removeObserver(user2);

        System.out.println("\nИзменение состояния после удаления подписчика");
        subject.changeState("New condition!");
    }
}