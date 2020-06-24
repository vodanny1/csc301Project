package comineashantrehan.linkedin.httpswww.ucourse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Menu extends AppCompatActivity {
    public static final String UNAME = "comineashantrehan.linkedin.httpswww.ucourse.UNAME";
    public String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        uname = intent.getStringExtra(Login.LOGIN_ID);


        Button profd = (Button) findViewById(R.id.button6);
        profd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), ProfSearch.class);
                myIntent.putExtra("login", uname);
                startActivity(myIntent);
            }
        });

        Button Forum = (Button) findViewById(R.id.button9);
        Forum.setOnClickListener(new View.OnClickListener() // Going back to forum menu activity
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Menu.this, ForumMenu.class);
                intent.putExtra(UNAME, uname);
                startActivity(intent);
            }
        });

        Button doc = (Button) findViewById(R.id.button10);
        doc.setOnClickListener(new View.OnClickListener() // Switching to Viewing Documents activity
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Menu.this, Document_Menu.class));
            }
        });
        Button  addCourse= (Button) findViewById(R.id.button7);
        addCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), CourseInformation.class);
                startActivity(myIntent);
            }
        });
    }

    public void courses1(View view){
        setContentView(R.layout.courseinformation);
    }

    public void back(View view){
        setContentView(R.layout.activity_menu);
    }

    public void logout(View view) {
        Message.message(getApplicationContext(), "Logged out");
        finish();
    }
}
