import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Main {

    private static final String pepper = "system_test";
    private static User user;

    public static void main(String[] args) {
        System.out.println();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String username = "douglas";
        String password = "Argon2id@Douglas22092024!";
        userCreation(argon2, username, password);

        jumpLines();

        userAuthentication(argon2, password);

        jumpLines();

        System.out.println("~Passing a wrong password~");
        userAuthentication(argon2, "123");
    }

    private static void userCreation(Argon2 argon2, String username, String password) {
        String hashedPassword = hashPassword(argon2, (password + pepper).toCharArray());
        user = User.create(username, hashedPassword);
        System.out.println("Password: " + password);
        System.out.println("Hashed Password: " + user.getPassword());
        System.out.println("Success: " + user);
    }

    private static void userAuthentication(Argon2 argon2, String password) {
        System.out.println("Authenticating user...");
        if (argon2.verify(user.getPassword(), (password + pepper).toCharArray())) {
            System.out.println("Authentication completed.");
            System.out.println("Welcome back, " + user.getUsername() + "!");
            return;
        }
        System.out.println("Authentication failed.");
    }

    private static String hashPassword(Argon2 argon2, char[] password) {
        int iterations = 2;
        int memory = 65536;
        int parallelism = 1;
        return argon2.hash(iterations, memory, parallelism, password);
    }

    private static void jumpLines() {
        System.out.println();
        System.out.println("---");
        System.out.println();
    }

}