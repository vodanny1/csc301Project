package comineashantrehan.linkedin.httpswww.ucourse;

import java.security.NoSuchAlgorithmException;

public class LoginHelper implements API.TaskListener {
    @Override
    public void onFinished(HttpResponse result) {
        if (result == null) {
            this.callback.onResult(false);
        }
        else {
            this.callback.onResult(result.responseCode == 200);
        }
    }

    public interface LoginCallback {
        void onResult(boolean result);
    }

    private final LoginCallback callback;

    public LoginHelper(LoginCallback callback) {
        this.callback = callback;
    }

    public void login(String name, String pass) {
        String md5pass;
        try {
            md5pass = MD5Helper.getMD5(pass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            this.onFinished(null);
            return;
        }
        System.err.println("login");
        API api = new API(this);
        api.execute(new String[] {"POST", String.format("login/%s/%s", name, md5pass)});
    }
}
