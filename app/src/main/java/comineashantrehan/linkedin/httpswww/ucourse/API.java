package comineashantrehan.linkedin.httpswww.ucourse;


import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class API extends AsyncTask<String, Void, HttpResponse> {
    public static final String SERVER_ADDR = "https://dajiba-test.herokuapp.com";
    public interface TaskListener {
        void onFinished(HttpResponse result);
    }



    private final TaskListener listener;

    public API(TaskListener listener) {
        this.listener = listener;
    }

    public HttpResponse upload(String url, String path) {
        System.err.println("UPLOAD: " + String.format("%s/%s", "http://10.0.2.2:5000", url));
        try {

            String charset = "UTF-8";
            File uploadFile1 = new File(path);
            String requestURL = String.format("%s/%s", "http://10.0.2.2:5000", url);

            MultipartUtility multipart = new MultipartUtility(requestURL, charset);

//            multipart.addHeaderField("User-Agent", "CodeJava");
//            multipart.addHeaderField("Test-Header", "Header-Value");

//            multipart.addFormField("friend_id", "Cool Pictures");
            multipart.addFormField("userid", "Java,upload,Spring");

            multipart.addFilePart("uploaded_file", uploadFile1);

            List<String> response = multipart.finish();



//            Log.v("rht", "SERVER REPLIED:");
//
//            for (String line : response) {
//                Log.v("rht", "Line : "+line);
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static HttpResponse _upload(final String url, final String path) {
//        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
//        File myFile = new File(path);
//        System.err.println(myFile.getAbsolutePath());
//        Bitmap bm = BitmapFactory.decodeFile(myFile.getAbsolutePath());
//        if (bm == null) {
//            System.err.println("DEBUG0 bm null");
//            return null;
//        }
//        ByteArrayOutputStream bao = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 90, bao);
//        byte[] ba = bao.toByteArray();
////        Base64.en
//
//        try {
//            nameValuePairs.add(new BasicNameValuePair("uploaded_file", Base64.encodeToString(ba, 0)));
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost(String.format("%s/%s", "http://10.0.2.2:5000", url));
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
////            System.err.println(String.format("%s/%s", "http://localhost:5000", url));
//            org.apache.http.HttpResponse response = httpclient.execute(httppost);
//
////            HttpEntity e =  response.getEntity();
////            System.err.println(e.getContent().);
//            String st = EntityUtils.toString(response.getEntity());
//
//            System.err.println("response: " + response.getStatusLine().getStatusCode() + "\n" + st);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public HttpResponse call(final String method, final String url) {
        URL server;
        HttpsURLConnection con;
//        HttpURLConnection con;
        System.err.println("API call: " +method+ String.format(" %s/%s", SERVER_ADDR, url));
        try {
            server = new URL(String.format("%s/%s", SERVER_ADDR, url));

//            server = new URL(String.format("%s/%s", "http://10.0.2.2:5000", url));
            con = (HttpsURLConnection) server.openConnection();

//            con = (HttpURLConnection) server.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 1.6; en-us; GenericAndroidDevice) AppleWebKit/528.5+ (KHTML, like Gecko) Version/3.1.2 Mobile Safari/525.20.1");
            con.setRequestMethod(method);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int code = con.getResponseCode();
            if (code == 200) {
                // decode json
                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String response = "";
                String line;
                while ((line = bf.readLine()) != null) {
                    response += line;
                }
                bf.close();
                con.disconnect();
                if (response.isEmpty()) {
//                    System.out.println("no response");
                    return new HttpResponse(code, null);
                }
                JSONObject json = new JSONObject(response);
//                System.out.println("response");
                return new HttpResponse(code, json);
            }
            System.err.println("code: " + code);

            return new HttpResponse(code, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected HttpResponse doInBackground(String... strings) {
        if (strings.length == 3) return upload(strings[0], strings[1]);
        if (strings.length != 2) return null;
        return call(strings[0], strings[1]);
    }

    @Override
    protected void onPostExecute(HttpResponse result) {
        super.onPostExecute(result);
        if (this.listener != null) {
            this.listener.onFinished(result);
        }
    }
}
