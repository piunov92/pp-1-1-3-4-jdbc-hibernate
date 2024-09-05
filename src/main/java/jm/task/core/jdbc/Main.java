package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        List<User> users = new ArrayList<>();

        users.add(new User("John", "X", (byte) 22));
        users.add(new User("Mike", "Y", (byte) 23));
        users.add(new User("Max", "Z", (byte) 24));
        users.add(new User("Bob", "W", (byte) 25));

        service.createUsersTable();
        for (User user : users) {
            service.saveUser(user.getName(), user.getLastName(), user.getAge());
        }

        List<User> allUsers = service.getAllUsers();
        allUsers.forEach(System.out::println);
        service.removeUserById(3);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
