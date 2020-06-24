package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Forum_Search extends AppCompatActivity {

//    String[] questions; // List of questions
    ImageButton b2; // Button for going back to forum menu activity
//    Button b1;
    ListAdapter listOfQuestions;
    public static final String QUESTION = "comineashantrehan.linkedin.httpswww.ucourse.QUESTION";
    public static final String UNAME = "comineashantrehan.linkedin.httpswww.ucourse.UNAME";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_search);

        Intent intent = getIntent();
        final String uname = intent.getStringExtra(ForumMenu.UNAME);

//        b1 = (Button) findViewById(R.id.SubmitAnswer);
        b2 = (ImageButton) findViewById(R.id.FromSearchToMenu);

//        ListAdapter listOfQuestions = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questions);
//
//        ListView list  = (ListView) findViewById(R.id.ListOfQuestions);
//        list.setAdapter(listOfQuestions);

        final EditText t1 = (EditText) findViewById(R.id.SearchQuestion);
//        TextView t2 = (TextView) findViewById(R.id.QuestionDisplay);
        Button b3 = (Button) findViewById(R.id.SearchForQuestion);; // Button for submitting question for searching

        b3.setOnClickListener(new View.OnClickListener() // Submitting question
        {
            @Override
            public void onClick(View view)
            {
                String q = t1.getText().toString();
                if (q.isEmpty()) {
                    displayMessage("Type something");
                    return;
                }
                Intent intent = new Intent(Forum_Search.this, Forum_Search_Results.class);
                intent.putExtra(QUESTION, q);
                intent.putExtra(UNAME, uname);
                startActivity(intent);

            }
        });

//        b1.setOnClickListener(new View.OnClickListener() // Going back to forum menu activity
//        {
//            @Override
//            public void onClick(View view)
//            {
//                // Remove '//' later
//                startActivity(new Intent(comineashantrehan.linkedin.httpswww.ucourse.Forum_Search.this, ForumMenu.class));
//            }
//        });

        b2.setOnClickListener(new View.OnClickListener() // Going back to forum menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
//                startActivity(new Intent(comineashantrehan.linkedin.httpswww.ucourse.Forum_Search.this, ForumMenu.class));
                finish();
            }
        });


//        listOfQuestions.setOnItemClickListener(
//                new AdapterView.OnItemClickListener()
//                {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        String question = String.valueOf(parent.getItemAtPosition(position));
//                    }
//                }
//        );

    }
    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }


}
