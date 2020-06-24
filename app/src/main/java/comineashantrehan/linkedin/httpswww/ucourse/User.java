package comineashantrehan.linkedin.httpswww.ucourse;

public class User {
    public final String id;
    public final String pass;

    public static final String COL_ID = "id";
    public static final String COL_PASS = "pass";
    public static final String TABLE_NAME = "users";

    public User(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }
}
