/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pbo_praktikum3_passwordstore;

import java.util.Scanner;
import pbo_praktikum3_passwordstore.models.PasswordStore;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
//        System.out.println(new App().getGreeting());
        Scanner input = new Scanner(System.in);
        String name, username, password;
        int category;
        
        System.out.print("Input name: ");
        name = input.nextLine();
        
        System.out.print("Input username: ");
        username = input.nextLine();
        
        System.out.print("Input password: ");
        password = input.nextLine();
        
        PasswordStore account1 = new PasswordStore(name, username, password);
        account1.setCategory(PasswordStore.CAT_WEBAPP);
        
        System.out.println(account1);
        System.out.println("\nUsername: " + account1.username);
        System.out.println("Password: " + account1.getPassword());
    }
}
