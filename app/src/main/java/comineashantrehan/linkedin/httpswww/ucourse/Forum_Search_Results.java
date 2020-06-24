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

import java.util.ArrayList;
import java.util.List;

public class Forum_Search_Results extends AppCompatActivity implements ForumSearchHelper.SearchCallback, AnswerHelper.AnswerCallback  {

    ForumSearchHelper searchHelper;
    ListView list;
    AnswerHelper answerHelper;
    List<Question> questions = null;
    int selected = -1;
    TextView about;
    String uname;

    public static final String QID = "comineashantrehan.linkedin.httpswww.ucourse.QID";
    public static final String QTITLE = "comineashantrehan.linkedin.httpswww.ucourse.QTITLE";
    public static final String QDESC = "comineashantrehan.linkedin.httpswww.ucourse.QDESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_search_results);

        searchHelper = new ForumSearchHelper(this, ForumSearchHelper.MODES.QUESTION);
        answerHelper = new AnswerHelper(this);

        Intent intent = getIntent();
        String q = intent.getStringExtra(Forum_Search.QUESTION);
        uname = intent.getStringExtra(Forum_Search.UNAME);

        searchHelper.search(q);


//        String question;
        about = (TextView) findViewById(R.id.QuestionAsked);
        about.setText(String.format("%s About \"%s\"", about.getText().toString(), q));
//        t1.setText(question);
        final EditText t2 = (EditText) findViewById(R.id.AnswerGiven);
        Button b2 = (Button) findViewById(R.id.AnswerSubmit);
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (selected == -1) {
                    displayMessage("Choose a question first");
                    return;
                }
                String ans = t2.getText().toString();
                if (ans.isEmpty()) {
                    displayMessage("Say something");
                }
                else {
                    answerHelper.answer(questions.get(Forum_Search_Results.this.selected).id, uname, ans);
                }
            }
        });

        Button b3 = (Button) findViewById(R.id.AnswerView);
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Question q = Forum_Search_Results.this.questions.get(Forum_Search_Results.this.selected);
                Intent intent = new Intent(Forum_Search_Results.this, Forum_Show_Answers.class);
                intent.putExtra(QID, q.id);
                intent.putExtra(QTITLE, q.title);
                intent.putExtra(QDESC, q.description);
                startActivity(intent);
            }
        });

        ImageButton b1 = (ImageButton) findViewById(R.id.BackToSearchForum);
        b1.setOnClickListener(new View.OnClickListener() // Going back to forum menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
//                startActivity(new Intent(comineashantrehan.linkedin.httpswww.ucourse.Forum_Search_Results.this, Forum_Search.class));
                finish();
            }
        });

//        String questions[] ={"Hi", "Bye"};
//        String answers[] = {"a", "b"};



        list  = (ListView) findViewById(R.id.ListOfResults);


        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Forum_Search_Results.this.selected = position;
                        Question q = questions.get(position);
                        Forum_Search_Results.this.about.setText(q.title + "\n" + q.description);
                    }
                }
        );
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

    @Override
    public void onResult(boolean result) {
        if (result) {
            displayMessage("Your answer has been posted, thank you for helping others!");
        }
        else {
            displayMessage("Cannot post answer at this moment, please try again later!");
        }
    }

    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }
}