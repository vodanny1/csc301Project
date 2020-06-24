//package comineashantrehan.linkedin.httpswww.ucourse_sprint_3;
package comineashantrehan.linkedin.httpswww.ucourse;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Document_Menu extends AppCompatActivity {

    Button b1; // Button for switching to Upload Documents activity
    Button b2; // Button for switching to Viewing Documents activity
    ImageButton b3; // Button for going back to menu activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_document_menu);

        b1 = (Button) findViewById(R.id.UploadDocOption);
        b2 = (Button) findViewById(R.id.ViewDocOption);
        b3 = (ImageButton) findViewById(R.id.BackToMenu);

        ImageButton back = (ImageButton) findViewById(R.id.BackToMenu);

        b1.setOnClickListener(new View.OnClickListener() // Switching to Upload Documents activity
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Document_Menu.this, Document_Upload.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() // Switching to Viewing Documents activity
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Document_Menu.this, Document_Search.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() // Going back to menu activity
        {
            @Override
            public void onClick(View view)
            {
                // Remove '//' later
                // startActivity(new Intent(Document_Menu.this, Menu.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Menu.class);
                startActivity(myIntent);
            }
        });


    }
}
