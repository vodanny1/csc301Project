package comineashantrehan.linkedin.httpswww.ucourse;

import java.security.NoSuchAlgorithmException;

public class RegisterHelper implements API.TaskListener {
    @Override
    public void onFinished(HttpResponse result) {
        if (result == null) {
            this.callback.onResult(REGISTER.FAIL);
        }
        else if (result.responseCode == 200) {
            this.callback.onResult(REGISTER.SUCCESS);
        }
        else if (result.responseCode == 404) {
            this.callback.onResult(REGISTER.EXIST);
        }
        else {
            this.callback.onResult(REGISTER.FAIL);
        }
    }
    public interface RegisterCallback {
        void onResult(REGISTER result);
    }
    public enum REGISTER {
        SUCCESS,
        FAIL,
        EXIST
    }

    private final RegisterCallback callback;

    public RegisterHelper(RegisterCallback callback) {
        this.callback = callback;
    }

    public void register(String name, String pass) {
        String md5pass;
        try {
            md5pass = MD5Helper.getMD5(pass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            this.onFinished(null);
            return;
        }
        API api = new API(this);
        api.execute(new String[] {"PUT", String.format("login/%s/%s", name, md5pass)});
    }
}
