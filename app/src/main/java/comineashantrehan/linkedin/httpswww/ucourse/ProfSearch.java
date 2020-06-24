package comineashantrehan.linkedin.httpswww.ucourse;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.database.Cursor;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

public class ProfSearch extends AppCompatActivity implements View.OnClickListener{

    private SQLManager db;
    private EditText txt_name;
    ArrayList<String> profNames;
    ArrayAdapter adapter;
    ListView profLists;
    public String f_email ="";
    public String f_name ="";
    public String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_search);
        db = new SQLManager(getApplicationContext());

        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        profNames = new ArrayList<>();

        ImageButton back = (ImageButton) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), Menu.class);
                myIntent.putExtra("login", login);
                startActivity(myIntent);
            }
        });

        profLists = (ListView)findViewById(R.id.profLists);

        profLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String text = profLists.getItemAtPosition(i).toString();
                //Toast.makeText(ProfSearch.this, ""+text, Toast.LENGTH_SHORT).show();


                Intent myIntent = new Intent(view.getContext(), ProfDetail.class);
                myIntent.putExtra("name", f_name);
                myIntent.putExtra("email", f_email);
                myIntent.putExtra("login", login);
                startActivity(myIntent);

            }
        });


        Button search = (Button) findViewById(R.id.button7);
        search.setOnClickListener(this);

        Button add = (Button)findViewById(R.id.button6);
        //add.setOnClickListener(this);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), ProfDetail.class);
                myIntent.putExtra("login", login);
                startActivity(myIntent);
            }
        });



    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button7:
                // searching professor

                txt_name = (EditText)findViewById(R.id.profName);
                String name = txt_name.getText().toString();

                if (name.isEmpty()){
                    displayMessage("Missing name");
                    return;
                }
                profNames.clear();
                EditText pName = (EditText)findViewById(R.id.profName);
                pName.getText().clear();

                // disable keyboard after button click
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                viewData(name);


        }
    }

    private void viewData(String name){


        Cursor cursor = db.viewData(name);

        if (cursor.getCount() == 0){
            displayMessage("No professor found");
        } else {


            while (cursor.moveToNext()){
                String output = "Name: " + cursor.getString(1) + "\n" +  "E-mail: " + cursor.getString(2) +
                        "\n" + "Rating: " + cursor.getString(3) + "/5" + "\n" + "By User: " + cursor.getString(4);
                //profNames.add(cursor.getString(2));
                profNames.add(output);
                f_name = cursor.getString(1);
                f_email = cursor.getString(2);
            }


            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, profNames);
            profLists.setAdapter(adapter);


        }

    }

    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }
}
