package comineashantrehan.linkedin.httpswww.ucourse;

public class Professor {
    public final String name;
    public final String email;
    public final int rating;
    public final String login;

    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_RATE = "rating";
    public static final String TABLE_NAME = "professors";
    public static final String COL_LOGIN ="login";

    public Professor(String name, String email, int rating, String login) {
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.login = login;

    }
}
