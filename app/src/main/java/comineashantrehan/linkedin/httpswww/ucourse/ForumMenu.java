package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ForumMenu extends AppCompatActivity {

    public static final String UNAME = "comineashantrehan.linkedin.httpswww.ucourse.UNAME";
    String uname;

    Button b1; // Button for switching to Post Question activity
    Button b2; // Button for switching to Search Question activity
    ImageButton b3; // Button for going back to menu activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_menu);

        Intent intent = getIntent();
        uname = intent.getStringExtra(Menu.UNAME);

        b1 = (Button) findViewById(R.id.ForumMenuToPost);
        b2 = (Button) findViewById(R.id.ForumMenuToSearch);
        b3 = (ImageButton) findViewById(R.id.ForumMenuToMenu);

        Button userPost = (Button) findViewById(R.id.SearchAQuestion);
        userPost.setOnClickListener(new View.OnClickListener() // Going back to menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
                Intent intent = new Intent(ForumMenu.this, Forum_Answers.class);
                intent.putExtra(UNAME, uname);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() // Switching to Upload Documents activity
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ForumMenu.this, Forum_Post.class);
                intent.putExtra(UNAME, uname);
                startActivity(intent);
//                startActivity(new Intent(comineashantrehan.linkedin.httpswww.ucourse.ForumMenu.this, Forum_Post.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ForumMenu.this, Forum_Search.class);
                intent.putExtra(UNAME, uname);
                startActivity(intent);
//                startActivity(new Intent(comineashantrehan.linkedin.httpswww.ucourse.ForumMenu.this, Forum_Search.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() // Going back to menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
//                startActivity(new Intent(ForumMenu.this, Menu.class));
                finish();
            }
        });
    }
}
