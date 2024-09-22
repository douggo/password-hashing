import java.util.Objects;

public class User {

    private final String username;
    private final String password;

    private User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User create(String username, String password) {
        Creator.userName(username);
        Creator.password(password);
        return Creator.create();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    private static class Creator {

        private static String username;
        private static String password;

        private static void userName(String username) {
            Creator.username = username;
        }

        private static void password(String password) {
            Creator.password = password;
        }

        public static User create() {
            return new User(Creator.username, Creator.password);
        }
    }
}
