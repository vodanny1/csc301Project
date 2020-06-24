package comineashantrehan.linkedin.httpswww.ucourse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DocSearchHelper extends AsyncTask<String, Void, Bitmap> {
    private ImageView view;
    public DocSearchHelper(ImageView v) {
        view = v;
    }

    public static Bitmap getImage(String name) {
        try {
            URL url = new URL("http://10.0.2.2:5000/documents/" + name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return getImage(strings[0]);
    }

    protected void onPostExecute(Bitmap result) {
        view.setImageBitmap(result);
    }
}
