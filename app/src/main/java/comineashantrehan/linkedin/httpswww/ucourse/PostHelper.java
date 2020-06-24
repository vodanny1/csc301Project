package comineashantrehan.linkedin.httpswww.ucourse;

public class PostHelper implements API.TaskListener {
    @Override
    public void onFinished(HttpResponse result) {
        this.callback.onResult(!(result == null || result.responseCode != 200));
    }

    public interface PostCallback {
        void onResult(boolean result);
    }

    public final PostCallback callback;

    public PostHelper(PostCallback callback) {
        this.callback = callback;
    }

    public void post(String uname, String title, String description) {
        API api = new API(this);
        api.execute(new String[] {"PUT", String.format("question/%s/%s/%s", title, description, uname)});
    }
}
