//package comineashantrehan.linkedin.httpswww.ucourse_sprint_3;
package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
//import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.pm.PackageManager;
//import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import android.provider.MediaStore;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document_Upload extends AppCompatActivity {

    private Document_Database db;
    Button select; // Button for selecting file
    Button upload; // Button for uploading file
    ImageView image; // Image of the document uploaded
    String path; // Stroing file path
    EditText E1; // Text box for accepting file name
    File sourceFile; // The file that is to be uploaded
    int totalSize; // Total size of the file
    LinearLayout uploader_area;
    private static final int REQUEST_WRITE_STORAGE = 112;
    ImageButton ib; // Button for switching to Documents Menu Activity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_document_upload);

        select = (Button) findViewById(R.id.SelectDoc); // Button for selecting file
        upload = (Button) findViewById(R.id.UploadDoc); // Button for uploading file
        image = (ImageView) findViewById(R.id.DocView); // Image of the document uploaded
        ib = (ImageButton) findViewById(R.id.BackDocumentsMenu); // Button for switching to Documents Menu Activity
        db = new Document_Database(getApplicationContext());

        /*Boolean hasPermission = (ContextCompat.checkSelfPermission(Document_Upload.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED); // Checking if permission has been granted

        if (!hasPermission) // Requesting permission if it hasn't been granted
        {
            ActivityCompat.requestPermissions(Document_Upload.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }*/

        select.setOnClickListener(new View.OnClickListener() // Selecting file to be uploaded
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() // Uploading file
        {
            @Override
            public void onClick(View view)
            {
                E1 = (EditText)findViewById(R.id.InputFileName); // Text box for accepting file name
                String Filename = E1.getText().toString(); // Storing file name entered by user

                if (path != null && !Filename.isEmpty())
                {
                    /*

                    INSERT CODE FOR PUTTING IMAGE IN SQLITE DATABASE HERE

                     */
                    String[] p = path.split("/");
                    new API(null).execute(new String[] {String.format("documents/%s/%s", Filename, p[p.length-1]), path, ""});

                    Log.d("run", "uploading of document is now taking place");
//                    boolean result = db.Upload(Filename);
//
//                    if (result == true)
//                    {
//                        Toast.makeText(getApplicationContext(),
//                                "Document uploaded successfully.", Toast.LENGTH_LONG).show();
//                    }
//
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(),
//                                "Error uploading document.", Toast.LENGTH_LONG).show();
//                    }

                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Please select a file to upload.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ib.setOnClickListener(new View.OnClickListener() // Switching to Documents Menu activity
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Document_Upload.this, Document_Menu.class));
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload screen with permission granted or use the features that required permission
                }

                else
                {
                    Toast.makeText(Document_Upload.this, "You must give access to storage.", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == 1 && resultCode == RESULT_OK)
        {

            Uri selectedImageUri = data.getData();
            int perm = getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
            if (perm == PackageManager.PERMISSION_GRANTED) {
                System.err.println("perm");
            }
//            path = getPath(selectedImageUri);
            path = getPath(selectedImageUri);
            System.err.println("DEBUG0 " + path + " DATA: " + data + " path: " + data.getData().getPath());
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 10; // down sizing image as it throws OutOfMemory Exception for larger images
//            final Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                System.out.println("cannot get iamge");
                e.printStackTrace();
                return;
            }

            image.setImageBitmap(bitmap);

        }

    }

//    public String getPath(Uri uri) // Function for storing the path to the file selected
//    {
//        String[] projection = {MediaStore.Images.Media.DATA };
////        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
//        Cursor cursor = loader.loadInBackground();
////        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        if (cursor == null) {
//            System.out.println("DEBUG0 cursor");
//            return null;
//        }
//        cursor.moveToFirst();
//        int column_index = cursor.getColumnIndex(projection[0]);
////        cursor.getString(column_index);
//        String s = cursor.getString(column_index);
//        cursor.close();
//        System.err.println(s);
//        return s;
//    }

    public String getPath(Uri uri) {
        Pattern p = Pattern.compile("(\\d+)$");
        Matcher m = p.matcher(uri.toString());
        if (!m.find()) {
            System.err.println("no find");
            return null;
        }
        String id = m.group();
        System.err.println(id);
        String sel = MediaStore.Images.Media._ID + "=?";
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor c = getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, filePathColumn, sel, new String[] {id}, null);
        if (c == null) {
            System.err.println("DEBUG0 path null");
            return uri.getPath();
        }
        int i = c.getColumnIndex(filePathColumn[0]);
        if (!c.moveToFirst()) {
            System.err.println("c no row");
            return null;
        }

        System.err.println("i "+i);
        String s = c.getString(i);
        c.close();
        return s;
    }

}