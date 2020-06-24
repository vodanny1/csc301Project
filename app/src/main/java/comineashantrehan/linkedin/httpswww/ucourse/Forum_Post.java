package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Forum_Post extends AppCompatActivity implements PostHelper.PostCallback {

    Button b1; // Button for posting question
    ImageButton b2; // Button for going back to forum menu activity
    EditText t1; // Text field for accepting query

    PostHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post);

        this.helper = new PostHelper(this);

        Intent intent = getIntent();
        final String uname = intent.getStringExtra(ForumMenu.UNAME);

        b1 = (Button) findViewById(R.id.PostQuestion);
        b2 = (ImageButton) findViewById(R.id.ForumPostToForumMenu);
        t1 = (EditText) findViewById(R.id.EnterQuestion);

        final EditText t2 = (EditText) findViewById(R.id.QuestionTitle);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String title = t2.getText().toString();
                String desc = t1.getText().toString();
                if (title.isEmpty() || desc.isEmpty()) {
                    displayMessage("say something");
                    return;
                }
                helper.post(uname, title, desc);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() // Going back to forum menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
//                startActivity(new Intent(comineashantrehan.linkedin.httpswww.ucourse.Forum_Post.this, ForumMenu.class));
                finish();
            }
        });
    }
    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }

    @Override
    public void onResult(boolean result) {
        if (result) {
            displayMessage("Your question is posted, hopefully someone will help you out!");
            finish();
        }
        else {
            displayMessage("Unable to post for some reasons, please try again later!");
        }
    }
}
