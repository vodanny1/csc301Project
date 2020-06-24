package comineashantrehan.linkedin.httpswww.ucourse;

public class AnswerHelper implements API.TaskListener {
    @Override
    public void onFinished(HttpResponse result) {
        callback.onResult(!(result == null || result.responseCode != 200));
    }

    public interface AnswerCallback {
        void onResult(boolean result);
    }

    private final AnswerCallback callback;

    public AnswerHelper(AnswerCallback callback) {
        this.callback = callback;
    }

    public void answer(int qid, String uname, String answer) {
        API api = new API(this);
        api.execute(new String[] {"PUT", String.format("answer/%d/%s/%s", qid, answer, uname)});
    }
}
