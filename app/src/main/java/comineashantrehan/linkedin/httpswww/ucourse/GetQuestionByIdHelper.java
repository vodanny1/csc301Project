package comineashantrehan.linkedin.httpswww.ucourse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetQuestionByIdHelper implements API.TaskListener {
    @Override
    public void onFinished(HttpResponse result) {
        if (result == null || result.responseCode != 200) {
            this.callback.onResult(null);
        }
        else {
            try {
                JSONArray arr = result.json.getJSONArray("results");
                List<Answer> re = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject o = arr.getJSONObject(i);
                    Answer q = new Answer(o.getInt("id"), o.getString("uname"), o.getString("answer"));
                    re.add(q);
                }
                this.callback.onResult(re);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                this.callback.onResult(null);
                return;
            }
        }
    }

    public interface QIDCallback {
        void onResult(List<Answer> result);
    }

    private final QIDCallback callback;

    public GetQuestionByIdHelper(QIDCallback callback) {
        this.callback = callback;
    }

    public void get(int id) {
        API api = new API(this);
        api.execute(new String[] {"GET", String.format("answer/%d", id)});
    }
}
