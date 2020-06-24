//package comineashantrehan.linkedin.httpswww.ucourse_sprint_3;
package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Document_Search extends AppCompatActivity implements DocSuggestHelper.SuggestCallback {

    String filename; // Name of file being searched by the user
    EditText sv; // Search View component on the UI
    Button b; // Button for confirming file name and initiating search
    ImageView image; // Image for displaying the document searched
    private Document_Database db; // Database being used for storing the file
    ImageButton ib; // Button for going back to Documents Menu activity
    ListView list;
    DocSuggestHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_search);

        helper = new DocSuggestHelper(this);

        b = (Button) findViewById(R.id.searchbutton); // Button for confirming file name and initiating search
        sv = (EditText) findViewById(R.id.SearchBar); // Search View component on the UI
        image = (ImageView) findViewById(R.id.documentView); // Image of the document requested
        ib = (ImageButton) findViewById(R.id.ToDocMenu); // Image of the document requested

        b.setOnClickListener(new View.OnClickListener() // Displaying file
        {
            @Override
            public void onClick(View view)
            {
                filename = sv.getText().toString(); // Storing file name entered by user

                if (filename != null)
                {
                    /*

                    INSERT CODE FOR PULLING IMAGE FROM SQLITE DATABASE HERE

                     */

//                    Log.d("run", "searching of document is now taking place");
//                    boolean result = db.Download(filename);

//                    if (result == true)
//                    {
//                        Toast.makeText(getApplicationContext(), "Document found successfully.", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(), "Error, please enter a valid document name.", Toast.LENGTH_SHORT).show();
//                    }

//                    Bitmap b = API.getImage(sv.getText().toString());
//                    image.setImageBitmap(b);

//                    new DocSearchHelper(image).execute(new String[] {filename});
                    helper.suggest(filename);
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Please select a file name for viewing.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ib.setOnClickListener(new View.OnClickListener() // Switching to Documents Menu activity
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Document_Search.this, Document_Menu.class));
            }
        });

        Button download = (Button) findViewById(R.id.downloadbutton);
        download.setOnClickListener(new View.OnClickListener() // Displaying file
        {
            @Override
            public void onClick(View view)
            {
                filename = sv.getText().toString(); // Storing file name entered by user

                if (filename != null)
                {
                    /*

                    INSERT CODE FOR PULLING IMAGE FROM SQLITE DATABASE HERE

                     */

//                    Bitmap b = API.getImage(sv.getText().toString());
//                    image.setImageBitmap(b);
                    new DocSearchHelper(image).execute(new String[] {filename});

                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Please select a file name for viewing.", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        String suggestions[];
//        ListAdapter listOfQuestions = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestions);
        list  = (ListView) findViewById(R.id.Suggestions);
//        list.setAdapter(listOfQuestions);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        String suggestion = String.valueOf(parent.getItemAtPosition(position));
                        sv.setText(suggestion);
                    }
                }
        );

    }



    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }

    @Override
    public void onResult(List<String> result) {
        if (result == null) {
            displayMessage("no result");
            return;
        }
        ListAdapter listOfQuestions = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, result);
        list.setAdapter(listOfQuestions);
    }
}

