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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Forum_Show_Answers extends AppCompatActivity implements GetQuestionByIdHelper.QIDCallback {
    GetQuestionByIdHelper questionHelper;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_show_answers);

        questionHelper = new GetQuestionByIdHelper(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra(Forum_Answers.QID, -1);
        String qtitle = intent.getStringExtra(Forum_Answers.QTITLE);
        String qdesc = intent.getStringExtra(Forum_Answers.QDESC);

        questionHelper.get(id);

        ImageButton b1 = (ImageButton) findViewById(R.id.SeeAnswersToSearchQ);
        b1.setOnClickListener(new View.OnClickListener() // Going back to forum menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
//                startActivity(new Intent(comineashantrehan.linkedin.httpswww.ucourse.Forum_Show_Answers.this, Forum_Answers.class));
                finish();
            }
        });

//        String question;
        TextView t2 = (TextView) findViewById(R.id.QuestionSelected);
        t2.setText(id + "\n" + qtitle + "\n" + qdesc);
//        t2.setText(question);

//        String questions[];
//        String answers[];

//        ListAdapter listOfQuestions = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questions);
        list  = (ListView) findViewById(R.id.ListOfAnswers);
//        list.setAdapter(listOfQuestions);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                    }
                }
        );
    }

    @Override
    public void onResult(List<Answer> result) {
        if (result == null) {
            displayMessage("no result");
            return;
        }
        List<String> questions = new ArrayList<>();

        for (Answer q : result) {
            questions.add(q.uname + ":\n" + q.answer);
        }

        ListAdapter listOfQuestions = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
        list.setAdapter(listOfQuestions);
    }
    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }
}