package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.controller.util.Commands;

import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;
        while (true) {
            String command = prompt("Insert a command: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("User ID: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user ID: ");
                    userController.updateUser(userId, createUser());
                case LIST:
                    System.out.println(userController.getAllUsers());
                case DELETE:
                    String deleteId = prompt("Enter user ID: ");
                    boolean deleted = userController.deleteUser(Long.parseLong(deleteId));
                    if (deleted) {
                        System.out.println("User is deleted successfully.");
                    } else {
                        System.out.println("User is not found or deletion is failed.");
                    }
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Firstname: ");
        if (firstName.isEmpty()){
            throw new RuntimeException("Firstname must be inserted");
        }
        String lastName = prompt("Lastname: ");
        if (lastName.isEmpty()){
            throw new RuntimeException("Lastname must be inserted");
        }
        String phone = prompt("Phone : ");
        if (phone.isEmpty()){
            throw new RuntimeException("Phone  must be inserted");
        }
        return new User(firstName.replaceFirst(" ",""), lastName.replaceFirst(" ",""), phone);
    }

}
