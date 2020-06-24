package comineashantrehan.linkedin.httpswww.ucourse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DocSuggestHelper implements API.TaskListener {
    interface SuggestCallback {
        void onResult(List<String> result);
    }
    private SuggestCallback callback;

    public DocSuggestHelper(SuggestCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onFinished(HttpResponse result) {
        if (result == null || result.responseCode != 200) {
            this.callback.onResult(null);
        }
        else {
            // parse json
            try {
                JSONArray arr = result.json.getJSONArray("results");
                List<String> re = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject o = arr.getJSONObject(i);
//                    Question q = new Question(o.getInt("id"), o.getString("title"), o.getString("description"));
                    re.add(o.getString("name"));
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

    public void suggest(String name) {
        API api = new API(this);
        api.execute(new String[] {"GET", String.format("documents/suggestions/%s", name)});
        System.err.println(String.format("documents/suggestions/%s", name));
    }
}
