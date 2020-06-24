package comineashantrehan.linkedin.httpswww.ucourse;

import org.json.JSONObject;

public class HttpResponse {
    public final int responseCode;
    public final JSONObject json;

    public HttpResponse(int responseCode, JSONObject json) {
        this.responseCode = responseCode;
        this.json = json;
    }
}
