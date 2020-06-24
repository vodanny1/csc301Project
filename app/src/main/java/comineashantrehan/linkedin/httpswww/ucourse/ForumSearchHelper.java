package comineashantrehan.linkedin.httpswww.ucourse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForumSearchHelper implements API.TaskListener {

    public enum MODES {
        QUESTION("question"),
        USER("question/user")
        ;

        private final String text;

        /**
         * @param text
         */
        MODES(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
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
                List<Question> re = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject o = arr.getJSONObject(i);
                    Question q = new Question(o.getInt("id"), o.getString("title"), o.getString("description"));
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


    public interface SearchCallback {
        void onResult(List<Question> result);
    }

    private final SearchCallback callback;
    private final String mode;

    public ForumSearchHelper(SearchCallback callback, MODES mode) {
        this.callback = callback;
        this.mode = mode.toString();
    }

    public void search(String word) {
        API api = new API(this);
        api.execute(new String[] {"GET", String.format("%s/%s", this.mode, word)});
    }
}
