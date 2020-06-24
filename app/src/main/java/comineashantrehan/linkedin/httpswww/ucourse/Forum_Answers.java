package comineashantrehan.linkedin.httpswww.ucourse;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class Forum_Answers extends AppCompatActivity implements ForumSearchHelper.SearchCallback {
    ForumSearchHelper searchHelper;
    ListView list;
    List<Question> questions;

    public static final String QID = "comineashantrehan.linkedin.httpswww.ucourse.QID";
    public static final String QTITLE = "comineashantrehan.linkedin.httpswww.ucourse.QTITLE";
    public static final String QDESC = "comineashantrehan.linkedin.httpswww.ucourse.QDESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_answers);

        searchHelper = new ForumSearchHelper(this, ForumSearchHelper.MODES.USER);

        Intent intent = getIntent();
        String q = intent.getStringExtra(ForumMenu.UNAME);

        searchHelper.search(q);

        ImageButton b1 = (ImageButton) findViewById(R.id.SearchQToForumMenu);
        b1.setOnClickListener(new View.OnClickListener() // Going back to forum menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
//                startActivity(new Intent(Forum_Answers.this, ForumMenu.class));
                finish();
            }
        });

        list  = (ListView) findViewById(R.id.ListOfQ);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Question q = Forum_Answers.this.questions.get(position);
                        Intent intent = new Intent(Forum_Answers.this, Forum_Show_Answers.class);
                        intent.putExtra(QID, q.id);
                        intent.putExtra(QTITLE, q.title);
                        intent.putExtra(QDESC, q.description);
                        startActivity(intent);
                    }
                }
        );

//        ListAdapter listOfQuestions = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questions);
//        ListView list  = (ListView) findViewById(R.id.ListOfQ);
//        list.setAdapter(listOfQuestions);
//
//        list.setOnItemClickListener(
//                new AdapterView.OnItemClickListener()
//                {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        String question = String.valueOf(parent.getItemAtPosition(position));
//                        startActivity(new Intent(Forum_Answers.this, Forum_Show_Answers.class));
//                    }
//                }
//        );
    }

    @Override
    public void onResult(List<Question> result) {
        if (result == null) {
            displayMessage("no result");
            return;
        }
        this.questions = result;
        List<String> questions = new ArrayList<>();

        for (Question q : result) {
            questions.add(q.title + "\n" + q.description);
        }

        ListAdapter listOfQuestions = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
        list.setAdapter(listOfQuestions);
    }

    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }
}